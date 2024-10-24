#!/bin/bash

ARCH=$(uname -m)

export PATH="/sbin:/bin:/usr/sbin:/usr/bin"

umask 0022

early_setup() {
	mkdir -p /proc
	mkdir -p /sys
	mkdir -p /run/lock
	mount -t proc proc /proc
	mount -t sysfs sysfs /sys
	mount -t efivarfs efivarfs /sys/firmware/efi/efivars
	mount -t devtmpfs none /dev

	# Set hostname
	echo "recovery" | tee /etc/hostname > /proc/sys/kernel/hostname
}

# HACK: BIOS enables cstates when they should be disabled and this makes the
# processor frequency go bonkers on crio's 903x dual-core (not 9034) affecting
# performance of the restore-mode's rootfs unpacking. We disable all cstates
# except C0 in all cpu cores until we get a BIOS update with cstates disabled
disable_x64_cstates() {
	shopt -s nullglob
	for CSTATE_DISABLE in /sys/devices/system/cpu/cpu*/cpuidle/state[^0]/disable; do
		echo 1 > $CSTATE_DISABLE
	done
}

early_setup

# Arch-specific set-up
if [[ $ARCH == "x86_64" ]]; then
	disable_x64_cstates
fi

# Setup overlay
mkdir -p "/lower"
mkdir -p "/upp_work"
mkdir -p "/merged"

NFS_SRV=192.168.1.1
NFS_LOWER=/srv/nfs/RunmodeMinimal
NFS_UPPER=/srv/nfs/empty

echo "Mounting lower filesystem (nfs)"
mount -t nfs "$NFS_SRV:$NFS_LOWER" "/lower" -o ro,nolock

echo "Mounting upper filesystem (tmpfs)"
mount -t tmpfs tmpfs "/upp_work" -o size=1G,rw
# upper and work need to be on same filesystem
mkdir -p "/upp_work/upper"
mkdir -p "/upp_work/work"

echo "Creating nfs+tmpfs overlay"
mount -t overlay overlay -olowerdir=/lower,upperdir=/upp_work/upper,workdir=/upp_work/work /merged

echo "Running switch_root to /merged/"
exec switch_root "/merged/" /sbin/init $init_options

# Uh oh. Something went wrong. We should never reach this point.
# Sync file systems and exit init (this process).

exit 1

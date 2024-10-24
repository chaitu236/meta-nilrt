DESCRIPTION ?= "Small initramfs for booting NILRT NFS targets"
LICENSE = "MIT"

PACKAGE_INSTALL = "${ROOTFS_BOOTSTRAP_INSTALL} ${VIRTUAL-RUNTIME_base-utils} init-nfs"

BAD_RECOMMENDATIONS += "shared-mime-info ca-certificates"

IMAGE_FEATURES ??= ""
IMAGE_LINGUAS ??= ""

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

inherit core-image

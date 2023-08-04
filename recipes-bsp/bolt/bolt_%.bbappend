FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DEPENDS += "update-rc.d-native"

SRC_URI += "\
   file://bolt \
   file://boltctl \
   file://0001-Update-udev-rule-for-hosts-that-don-t-have-systemd.patch \
"

FILES:${PN} += "${sysconfdir}/init.d/bolt"

do_install:append() {
   install -d ${D}${sysconfdir}/init.d
   install -m 0755 ${WORKDIR}/bolt ${D}${sysconfdir}/init.d/bolt

   # Move boltctl to a directory that's not in PATH and install a wrapper
   # in its place
   mv ${D}${bindir}/boltctl ${D}${libexecdir}/
   install -m 0755 ${WORKDIR}/boltctl ${D}${bindir}/

   update-rc.d -r ${D} bolt           start 3 2 3 5 .
}

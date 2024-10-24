SUMMARY = "Extremely basic live image init script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


PV = "1.0"


SRC_URI = "\
	file://init-nfs.sh \
"

RDEPENDS:${PN} += "bash nfs-utils-mount"

do_install() {
	install -m 0755 ${WORKDIR}/init-nfs.sh ${D}/init
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += " /init"

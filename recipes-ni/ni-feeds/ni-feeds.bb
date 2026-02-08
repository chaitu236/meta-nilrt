SUMMARY = "NI specific feeds"
DESCRIPTION = "NI specific feeds containing packages built outside OE."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SECTION = "base"

RDEPENDS:${PN} += "bash"

FILES:${PN} += "\
	${sysconfdir}/opkg/ni-software.conf \
	${sysconfdir}/opkg/ni-third-party.conf \
"

S = "${WORKDIR}"

inherit allarch

do_install () {
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/opkg

	# ni-software.conf
	for LV_YEAR in ${SUPPORTED_LV_YEARS}; do
		echo "#src/gz   ni-labview-$LV_YEAR  ${NILRT_FEEDS_URI}/${NILRT_FEED_NAME}/ni-lv$LV_YEAR" >> ${D}${sysconfdir}/opkg/ni-software.conf
	done
	# Enable most recent LV year
	sed -i '$s/^#//' ${D}${sysconfdir}/opkg/ni-software.conf

	echo "src/gz   ni-software      ${NILRT_FEEDS_URI}/${NILRT_FEED_NAME}/ni-main" >> ${D}${sysconfdir}/opkg/ni-software.conf

	# ni-third-party.conf
	echo "#src/gz  ni-third-party ${EXTERNAL_FEEDS_URI}/${NILRT_FEED_NAME}/ni-third-party" >> ${D}${sysconfdir}/opkg/ni-third-party.conf
}

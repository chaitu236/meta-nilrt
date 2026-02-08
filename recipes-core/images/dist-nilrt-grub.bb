DESCRIPTION = "Linux RT System Link Package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += " \
	file://nilrt-base-system-image.postinst \
	file://nisystemimage_pre \
	file://nisystemimage_post \
	file://${BPN}-config.cdf \
	file://setimage.ini \
	file://SetSystemImageBlacklist \
"

PV = "${DISTRO_VERSION}"

# Use xz as it's supported on old BSIs and needed for SL install workflow.
OPKGBUILDCMD = "opkg-build -Z xz"

RDEPENDS:${PN} += "bash"

SYSTEMLINK_GUID = "3CDECBF0-04E7-462E-BDD2-2AD4D9B02235"

FILES:${PN} += "\
	.syscfg-action/${SYSTEMLINK_GUID}/* \
"

CDFGUID:x64 = "4C0005F7-54D1-492B-A7E7-C1E58BD9B972"
CDFGUID:xilinx-zynq = "8E3EACD0-B36E-462B-A500-88AE644AB3B0"

OSVALUE:x64 = "NI-Linux x64"
OSVALUE:xilinx-zynq = "Linux-ARMv7-A"

OSVERSION:x64 = "7.0"
# For BSI to fit on smaller ARM targets, safemode needs zlib compression and other space saving measures.
# So set minimum compatible safemode version to 26.3 which has them.
OSVERSION:xilinx-zynq = "26.3"

PACKAGE_ADD_METADATA_IPK:${PN} = "MinimumSafeMode: ${OSVERSION}"

ROOTFS_IMAGE = "nilrt-runmode-rootfs"
do_install[depends] += "${ROOTFS_IMAGE}:do_image_complete"

do_install () {
	install -d ${D}/.syscfg-action
	install -d ${D}/.syscfg-action/${SYSTEMLINK_GUID}

	install -m 0644 ${DEPLOY_DIR_IMAGE}/${ROOTFS_IMAGE}-${MACHINE}.rootfs.${NILRT_BSI_FSTYPE} ${D}/.syscfg-action/${SYSTEMLINK_GUID}/systemimage.${NILRT_BSI_FSTYPE}

	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/natinst
	install -d ${D}${sysconfdir}/natinst/share

	install -m 0755 ${WORKDIR}/nilrt-base-system-image.postinst ${D}${sysconfdir}/natinst/share/baseimage_postinst
	install -m 0755 ${WORKDIR}/nisystemimage_pre ${D}${sysconfdir}/natinst/share/
	install -m 0755 ${WORKDIR}/nisystemimage_post ${D}${sysconfdir}/natinst/share/

	install -d ${D}${sysconfdir}/natinst/share/systemimage_files
}

do_install:append () {
	CDFOUT="${D}${sysconfdir}/natinst/share/systemimage_files/config.cdf"
	install -m 0644 ${WORKDIR}/${BPN}-config.cdf $CDFOUT

	SHORTVER=$(echo ${BUILDNAME} | sed 's/^\([0-9.]*\).*/\1/;')
	TARFILE="nilrt-base-system-image-${MACHINE}.tar"

	LV_VERSIONS_TEXT=""
	for LV_YEAR in ${SUPPORTED_LV_YEARS}; do
		LV_VERSIONS_TEXT="${LV_VERSIONS_TEXT}LabVIEW $LV_YEAR Q1, "
	done

	sed -i "s/%systemlink-guid%/${SYSTEMLINK_GUID}/g; s!%release%!${NILRT_FEED_NAME}!g; s/%lvversions%/$LV_VERSIONS_TEXT/g; s/%guid%/${CDFGUID}/g; s/%version%/$SHORTVER/g; s/%osvalue%/${OSVALUE}/g; s/%osversion%/${OSVERSION}/g; s/%filename%/$TARFILE/g;" $CDFOUT
}

do_install:append () {
	install -m 0644 ${WORKDIR}/setimage.ini ${D}/.syscfg-action/${SYSTEMLINK_GUID}/setimage.ini
	sed -i "s/%systemlink-guid%/${SYSTEMLINK_GUID}/g; s/%bsi_fstype%/${NILRT_BSI_FSTYPE}/g" ${D}/.syscfg-action/${SYSTEMLINK_GUID}/setimage.ini

	install -m 0644 ${WORKDIR}/SetSystemImageBlacklist ${D}/.syscfg-action/${SYSTEMLINK_GUID}/
}

pkg_preinst:${PN} () {
	INSTALLED_VER_SHORT=$(nisafemodeversion | awk -F. '{print $1"."$2}')
	if (( $(echo "$INSTALLED_VER_SHORT ${OSVERSION}" | awk '{print ($1 < $2)}') )); then
		echo "Minimum safemode required ${OSVERSION}. Installed version $INSTALLED_VER_SHORT. Please upgrade firmware."
		exit 1
	fi
}

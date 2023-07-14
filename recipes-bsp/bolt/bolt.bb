SUMMARY = "Thunderbolt user-space management tool"
DESCRIPTION = "Userspace system daemon to enable security levels for Thunderbolt on GNU/Linux"
HOMEPAGE = "https://gitlab.freedesktop.org/bolt/bolt"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "cmake-native udev polkit"

SRC_URI = "git://gitlab.freedesktop.org/bolt/bolt.git;protocol=https;branch=master"
SRCREV = "d3e0fd43fd99fc51488154f72cb15ff0481d3a86"

S = "${WORKDIR}/git"

inherit cmake pkgconfig meson

FILES:${PN} += "${datadir}/dbus-1/* ${datadir}/polkit-1/*"

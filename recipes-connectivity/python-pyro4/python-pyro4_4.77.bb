SUMMARY = "Python Remote Objects"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cd13dafd4eeb0802bb6efea6b4a4bdbc"

SRC_URI[md5sum] = "21f015ae93cf9ea2bbbc418a2267e9fb"
SRC_URI[sha256sum] = "2bfe12a22f396474b0e57c898c7e2c561a8f850bf2055d8cf0f7119f0c7a523f"

PYPI_PACKAGE = "Pyro4"

inherit pypi setuptools

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-selectors34 \
    ${PYTHON_PN}-serpent \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-zlib \
    "

PNBLACKLIST[python-pyro4] ?= "${@bb.utils.contains('I_SWEAR_TO_MIGRATE_TO_PYTHON3', 'yes', '', 'python2 is out of support for long time, read https://www.python.org/doc/sunset-python-2/ https://python3statement.org/ and if you really have to temporarily use this, then set I_SWEAR_TO_MIGRATE_TO_PYTHON3 to "yes"', d)}"

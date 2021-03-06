SUMMARY = "Symbolic constants in Python"
DESCRIPTION = "A library that provides symbolic constant support. It includes \
collections and constants with text, numeric, and bit flag values. Originally \
twisted.python.constants from the Twisted project."
HOMEPAGE = "https://github.com/twisted/constantly"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e393e4ddd223e3a74982efa784f89fd7"

SRC_URI[md5sum] = "f0762f083d83039758e53f8cf0086eef"
SRC_URI[sha256sum] = "586372eb92059873e29eba4f9dec8381541b4d3834660707faf8ba59146dfc35"

inherit pypi setuptools

RDEPENDS:${PN} += "${PYTHON_PN}-json"

PNBLACKLIST[python-constantly] ?= "${@bb.utils.contains('I_SWEAR_TO_MIGRATE_TO_PYTHON3', 'yes', '', 'python2 is out of support for long time, read https://www.python.org/doc/sunset-python-2/ https://python3statement.org/ and if you really have to temporarily use this, then set I_SWEAR_TO_MIGRATE_TO_PYTHON3 to "yes"', d)}"

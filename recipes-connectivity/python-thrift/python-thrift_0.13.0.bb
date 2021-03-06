SUMMARY = "Python bindings for the Apache Thrift RPC system"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=e95cd2f17c70d3180a2b361332319fe0"

SRC_URI[md5sum] = "c3bc8d9a910d2c9ce26f2ad1f7c96762"
SRC_URI[sha256sum] = "9af1c86bf73433afc6010ed376a6c6aca2b54099cc0d61895f640870a9ae7d89"

inherit pypi setuptools

# Use different filename to prevent conflicts with thrift itself
PYPI_SRC_URI:append = ";downloadfilename=${BP}.${PYPI_PACKAGE_EXT}"

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-stringold \
    ${PYTHON_PN}-threading \
"

PNBLACKLIST[python-thrift] ?= "${@bb.utils.contains('I_SWEAR_TO_MIGRATE_TO_PYTHON3', 'yes', '', 'python2 is out of support for long time, read https://www.python.org/doc/sunset-python-2/ https://python3statement.org/ and if you really have to temporarily use this, then set I_SWEAR_TO_MIGRATE_TO_PYTHON3 to "yes"', d)}"

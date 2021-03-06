SUMMARY = "Socket.IO integration for Flask applications"
HOMEPAGE = "https://github.com/miguelgrinberg/Flask-SocketIO/"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38cc21254909604298ce763a6e4440a0"

SRC_URI[md5sum] = "b23222fb7dd2f0676d78bbe24153fd80"
SRC_URI[sha256sum] = "2172dff1e42415ba480cee02c30c2fc833671ff326f1598ee3d69aa02cf768ec"

PYPI_PACKAGE = "Flask-SocketIO"

inherit pypi setuptools

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-flask \
    ${PYTHON_PN}-socketio \
    "

PNBLACKLIST[python-flask-socketio] ?= "${@bb.utils.contains('I_SWEAR_TO_MIGRATE_TO_PYTHON3', 'yes', '', 'python2 is out of support for long time, read https://www.python.org/doc/sunset-python-2/ https://python3statement.org/ and if you really have to temporarily use this, then set I_SWEAR_TO_MIGRATE_TO_PYTHON3 to "yes"', d)}"

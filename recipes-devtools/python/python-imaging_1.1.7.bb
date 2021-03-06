SUMMARY = "Python Imaging Library (PIL)"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;beginline=92;endline=120;md5=c4371af4579f1e489cf881c1443dd4ec"
DEPENDS = "freetype jpeg tiff"
SRCNAME = "Imaging"
PR = "r5"

SRC_URI = "http://effbot.org/downloads/Imaging-${PV}.tar.gz \
           file://0001-python-imaging-setup.py-force-paths-for-zlib-freetyp.patch \
           file://allow.to.disable.some.features.patch \
           file://fix-freetype-includes.patch \
           file://remove-host-libdir.patch \
           file://python-imaging-CVE-2016-2533.patch \
"

SRC_URI[md5sum] = "fc14a54e1ce02a0225be8854bfba478e"
SRC_URI[sha256sum] = "895bc7c2498c8e1f9b99938f1a40dc86b3f149741f105cf7c7bd2e0725405211"
S = "${WORKDIR}/${SRCNAME}-${PV}"

# There isn't enable/disable option, and lcms is in meta-oe, at least make it explicit when enabled
# setup.py already has FIXME: add mechanism to explicitly *disable* the use of a library
PACKAGECONFIG ??= ""
PACKAGECONFIG[lcms] = ",,lcms"

inherit distutils

do_compile() {
    export STAGING_LIBDIR=${STAGING_LIBDIR}
    export STAGING_INCDIR=${STAGING_INCDIR}
    export LCMS_ENABLED=${@bb.utils.contains('PACKAGECONFIG', 'lcms', 'True', 'False', d)}
    distutils_do_compile
}

do_install() {
    export STAGING_LIBDIR=${STAGING_LIBDIR}
    export STAGING_INCDIR=${STAGING_INCDIR}
    export LCMS_ENABLED=${@bb.utils.contains('PACKAGECONFIG', 'lcms', 'True', 'False', d)}
    distutils_do_install
    install -d ${D}${datadir}/doc/${BPN}/html/
    install -m 0644 ${S}/README ${D}${datadir}/doc/${BPN}/
    install -m 0644 ${S}/Docs/* ${D}${datadir}/doc/${BPN}/html/

    # get rid of #!/usr/local/bin/python
    sed -i -e 's:/usr/local/bin/:${bindir}/env :g' ${D}${bindir}/*
}

RDEPENDS:${PN} += "python-lang python-stringold"

PNBLACKLIST[python-imaging] ?= "${@bb.utils.contains('I_SWEAR_TO_MIGRATE_TO_PYTHON3', 'yes', '', 'python2 is out of support for long time, read https://www.python.org/doc/sunset-python-2/ https://python3statement.org/ and if you really have to temporarily use this, then set I_SWEAR_TO_MIGRATE_TO_PYTHON3 to "yes"', d)}"

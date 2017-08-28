SUMMARY = "Bag of goodies for NI Linux Realtime distribution"
LICENSE = "MIT"

inherit packagegroup

# These packages are only built for the nilrt-xfce fork of the older NILRT distro
# as opposed to nilrt-nxg which always enables x11 support. Also only for x64
# because NILRT ARM does not have a GUI.
RDEPENDS_${PN}_append_x64 = "\
	${@bb.utils.contains('DISTRO_FEATURES', 'x11', '\
		packagegroup-self-hosted \
		packagegroup-core-lsb \
		packagegroup-core-qt \
		packagegroup-xfce-extended \
		packagegroup-ni-mono-extra \
		consolekit \
		geany \
		gimp \
		gnuplot \
		gnuradio \
		gtk+3 \
		iceauth \
		florence \
		fltk \
		libvncserver \
		libwmf \
		lxdm \
		mesa-demos \
		modemmanager \
		networkmanager \
		numlockx \
		openjdk-7-jre \
		openbox \
		polkit \
		python-pyqt \
		sessreg \
		setxkbmap \
		sip \
		sysconfig-settings-ui \
		t1lib \
		toscoterm \
		tk \
		twm \
		udisks \
		upower \
		vte \
		x11vnc \
		xbitmaps \
		xchat \
		xclock \
		xcursorgen \
		xdotool \
		xfontsel \
		xlsfonts \
		xmag \
		xrdb \
		xterm \
		xwd \
	', '', d)} \
"

RDEPENDS_${PN} = "\
	packagegroup-core-sdk \
	packagegroup-core-standalone-sdk-target \
	packagegroup-core-basic \
	packagegroup-core-device-devel \
	packagegroup-core-buildessential \
	packagegroup-core-tools-debug \
	packagegroup-core-security \
"

# meta-nilrt
RDEPENDS_${PN} += "\
	packagegroup-ni-debug-kernel \
	packagegroup-ni-selinux \
"

RDEPENDS_${PN} += "\
	avahi \
	bind \
	cifs-utils \
	dhcp \
	libpcap \
	ofono \
	portmap \
	ppp \
	ppp-dialin \
	python-salttesting \
	resolvconf \
	salt \
"

RDEPENDS_${PN} += "\
	os-release \
	psplash \
	sysfsutils \
"

# openembedded-core/meta/recipes-bsp
RDEPENDS_${PN} += "\
	alsa-state \
	keymaps \
	lrzsz \
	setserial \
	usbutils \
"

# openembedded-core/meta/recipes-connectivity
RDEPENDS_${PN} += "\
	bluez5 \
"

# openembedded-core/meta/recipes-devtools
RDEPENDS_${PN} += "\
	apt \
	bison \
	bootchart2 \
	btrfs-tools \
	cmake \
	distcc \
	dosfstools \
	dpkg \
	expect \
	file \
	flex \
	gnu-config \
	i2c-tools \
	mkelfimage \
	mmc-utils \
	mtd-utils \
	opensp \
	ossp-uuid \
	patchelf \
	pax-utils \
	prelink \
	python-dbus \
	python-distribute \
	python-imaging \
	python-setuptools \
	python-docutils \
	python-mako \
	python-nose \
	python-numpy \
	python3 \
	python3-setuptools \
	rpm \
	rsync \
	sgml-common \
	unfs3 \
	vala \
	valgrind \
	xmlto \
"

# openembedded-core/meta/recipes-extended
RDEPENDS_${PN} += "\
	at \
	augeas \
	bc \
	blktool \
	byacc \
	cups \
	foomatic-filters \
	ghostscript \
	iputils \
	libarchive \
	lighttpd \
	ltp \
	mailx \
	msmtp \
	net-tools \
	pax \
	pigz \
	libconvert-asn1-perl \
	libtimedate-perl \
	libxml-namespacesupport-perl \
	libxml-sax-perl \
	procps \
	psmisc \
	shadow \
	slang \
	stat \
	sysklogd \
	tcp-wrappers \
	time \
"

# openembedded-core/meta/recipes-support
RDEPENDS_${PN} += "\
	apr \
	aspell \
	attr \
	acl \
	boost \
	ca-certificates \
	createrepo-c \
	curl \
	debianutils \
	enchant \
	gnupg \
	gnutls \
	gpgme \
	lz4 \
	lzop \
	nspr \
	nss \
	nss-myhostname \
	pinentry \
	ptest-runner \
	sqlite \
"

# openembedded-core/meta/recipes-kernel
RDEPENDS_${PN} += "\
	blktrace \
	dtc \
	kexec-tools \
	kmod \
	latencytop \
	linux-firmware \
	oprofile \
	perf \
	powertop \
	sysprof \
	systemtap \
	trace-cmd \
"

# openembedded-gore/meta/recipes-multimedia
RDEPENDS_${PN} += "\
	alsa-tools \
	alsa-utils \
	alsa-utils-scripts \
	flac \
	pulseaudio \
	speex \
"

# meta-openembedded/meta-oe/recipes-benchmark
RDEPENDS_${PN} += "\
	bonnie++ \
	dbench \
	fio \
	iozone3 \
	iperf \
	iperf3 \
	libhugetlbfs \
	lmbench \
	memtester \
	nbench-byte \
	phoronix-test-suite \
	tiobench \
"

# meta-openembedded/meta-oe/recipes-connectivity
RDEPENDS_${PN} += "\
	gammu \
	gnokii \
	hostapd \
	iw \
	krb5 \
	linuxptp \
	lirc \
	mosh \
	rarpd \
	rfkill \
	samba \
	ser2net \
	ti-wifi-utils \
	tiptop \
	umip \
	usbmuxd \
	zeromq \
	wireless-tools \
"

# meta-openembedded/meta-oe/recipes-core
RDEPENDS_${PN} += "\
	usleep \
"

# meta-openembedded/meta-oe/recipes-devtools
RDEPENDS_${PN} += "\
	bootchart \
	breakpad \
	cgdb \
	gdb \
	cppunit \
	ctags \
	cunit \
	dejagnu \
	i2c-tools \
	icon-slicer \
	jq \
	lemon \
	libedit \
	log4cplus \
	ltrace \
	lua \
	luajit \
	mcpp \
	memstat \
	nodejs \
	libio-pty-perl \
	php \
	pm-qa \
	protobuf \
	python-futures \
	python-pyopenssl \
	python-simplejson \
	python-tornado \
	swig \
	tclap \
	uw-imap \
	vala-dbus-binding-tool \
	yajl \
	yasm \
"

# meta-openembedded/meta-oe/recipes-devtools
RDEPENDS_${PN}_append_x64 += "\
	concurrencykit \
	msr-tools \
"

# meta-openembedded/meta-oe/recipes-extended
RDEPENDS_${PN} += "\
	acpica \
	collectd \
	corosync \
	dash \
	ddrescue \
	dialog \
	efivar \
	flashrom \
	haveged \
	hexedit \
	hplip \
	indent \
	iotop \
	jansson \
	leveldb \
	libcec \
	libconfig \
	liblockfile \
	liblognorm \
	libmodbus \
	lockfile-progs \
	logcheck \
	logwatch \
	md5deep \
	nana \
	net-snmp \
	nicstat \
	p7zip \
	redis \
	rrdtool \
	rsyslog \
	smartmontools \
	canutils \
	tcsh \
	tipcutils \
	tmux \
	zram \
"

# meta-openembedded/meta-oe/recipes-graphics
# meta-oe/recipes-graphics
RDEPENDS_${PN}_append_x64 += "\
	jasper \
	packagegroup-fonts-truetype \
	terminus-font \
	ttf-dejavu \
	ttf-droid \
	ttf-gentium \
	ttf-inconsolata \
	ttf-liberation \
	ttf-mplus \
	ttf-pt-sans \
	ttf-ubuntu-font-family \
	xorg-sgml-doctools \
"


# meta-openembedded/meta-oe/recipes-multimedia
RDEPENDS_${PN} += "\
	alsa-oss \
	audiofile \
	jack \
	media-ctl \
	opus-tools \
	v4l-utils \
	wavpack \
	libwebp \
"
# vlc, mplayer requires libmad which is on a non-whitelisted license

# meta-openembedded/meta-oe/recipes-support
RDEPENDS_${PN} += "\
	bash-completion \
	ccid \
	ckermit \
	cpufrequtils \
	cryptsetup \
	daemonize \
	daemontools \
	dstat \
	dfu-util \
	eject \
	enca \
	engine-pkcs11 \
	espeak \
	eventlog \
	farsight2 \
	fbset \
	fbset-modes \
	fftw \
	gd \
	glog \
	gperftools \
	gradm \
	gsl \
	gsoap \
	hddtemp \
	htop \
	imagemagick \
	libinih \
	iso-codes \
	joe \
	lcov \
	libcap-ng \
	libftdi \
	libutempter \
	links \
	lmsensors \
	lockdev \
	log4c \
	log4cpp \
	lzip \
	lvm2 \
	mailcap \
	mbuffer \
	mg \
	mime-support \
	mariadb \
	mysql-python \
	multipath-tools \
	nano \
	nmon \
	onig \
	openct \
	opencv \
	openldap \
	opensc \
	openwbem \
	p11-kit \
	p910nd \
	pam-passwdqc \
	pcsc-lite \
	picocom \
	pkcs11-helper \
	portaudio-v19 \
	postgresql \
	pps-tools \
	procmail \
	pv \
	read-edid \
	rng-tools \
	rsnapshot \
	sdparm \
	serial-forward \
	sg3-utils \
	sharutils \
	smem \
	soci \
	spitools \
	start-stop-daemon \
	strongswan \
	system-config-keyboard \
	system-setup-keyboard \
	libtalloc \
	tbb \
	tree \
	usb-modeswitch \
	usbpath \
	vim \
"

RDEPENDS_${PN}_append_x64 += "\
	edac-utils \
	lcms \
	mcelog \
	numactl \
	pidgin \
	poppler \
	xdelta3 \
	xmlstarlet \
	zile \
"

# meta-openembedded/meta-oe/recipes-kernel
RDEPENDS_${PN} += "\
	crash \
"

# meta-openembedded/meta-filesystems
RDEPENDS_${PN} += "\
	fuse-exfat \
	ntfs-3g-ntfsprogs \
	smbnetfs \
	sshfs-fuse \
	unionfs-fuse \
	fuse \
	physfs \
	aufs-util \
	exfat-utils \
	xfsprogs \
"

# meta-openembedded/meta-networking
RDEPENDS_${PN} += "\
	inetutils \
	polarssl \
	ufw \
	vlan \
	vpnc \
	atftp \
	autofs \
	cyrus-sasl \
	iscsi-initiator-utils \
	lldpd \
	ncftp \
	opensaf \
	postfix \
	radvd \
	tftp-hpa \
	conntrack-tools \
	ebtables \
	netkit-ftp \
	netkit-rpc \
	netkit-rsh \
	netkit-rusers \
	netkit-rwho \
	netkit-telnet \
	netkit-tftp \
	net-snmp \
	openflow \
	openl2tp \
	pptp-linux \
	quagga \
	radiusclient-ng \
	rp-pppoe \
	xl2tpd \
	zeroconf \
	arptables \
	bridge-utils \
	dnsmasq \
	dnssec-conf \
	dovecot \
	drbd-utils \
	esmtp \
	fetchmail \
	fping \
	geoip \
	ifenslave \
	iftop \
	ipsec-tools \
	macchanger \
	memcached \
	mtr \
	ndisc6 \
	ntp \
	netcat \
	netcat-openbsd \
	netcf \
	ypbind-mt \
	yp-tools \
	nuttcp \
	pimd \
	smcroute \
	ssmping \
	strongswan \
	stunnel \
	tcpdump \
	tnftp \
	traceroute \
	tunctl \
	wireshark \
	ptpd \
	snort \
"

# meta-oe/recipes-devtools/python (most now in meta-oe/meta-python)
RDEPENDS_${PN} += "\
	python-pycrypto \
	python-feedparser \
	python-requests \
	python-pyzmq \
	python-m2crypto \
	python-cheetah \
	python-lxml \
	python-numeric \
	python-matplotlib \
	python-pyudev \
	python-autobahn \
	python-pyalsaaudio \
	python-pip \
	python-pyusb \
	python-markupsafe \
	python-jinja2 \
	python-pexpect \
	python-cloudeebus \
	python-webdav \
	python-dateutil \
	python-ldap \
	python-epydoc \
	python-gevent \
	python-cython \
	python-smbus \
	python-snakefood \
	python-psutil \
	python-msgpack \
	python-vobject \
	python-twisted \
	python-pyserial \
	python-slip-dbus \
	python-gdata \
	pyrtm \
	python-sqlalchemy \
	python-pyyaml \
	python-decorator \
	python-greenlet \
	python-pytz \
	python-zopeinterface \
	python-pyparted \
	python-pyconnman \
"
# python-matplotlib: hang on do_install

# meta-openembedded/meta-ruby
RDEPENDS_${PN} += "ruby"

# meta-openembedded/meta-webserver
RDEPENDS_${PN} += "\
	apache2 \
	cherokee \
	fcgi \
	spawn-fcgi \
	hiawatha \
	monkey \
	nginx \
	nostromo \
	phpmyadmin \
	sthttpd \
"

# meta-nilrt/recipes-devtools
RDEPENDS_${PN} += " \
	debootstrap \
"

# meta-cloud-services/meta-openstack/recipes-devtools
RDEPENDS_${PN} += "\
	erlang \
	libconfig-general-perl \
	python-amqp \
	python-amqplib \
	python-anyjson \
	python-beautifulsoup4 \
	python-boto \
	python-cephclient \
	python-cliff \
	python-cmd2 \
	python-colorama \
	python-coverage \
	python-croniter \
	python-cssselect \
	python-decorator \
	python-django \
	python-django-appconf \
	python-django-compressor \
	python-django-nose \
	python-dnspython \
	python-eventlet \
	python-extras \
	python-falcon \
	python-fixtures \
	python-flake8 \
	python-futures \
	python-greenlet \
	python-html5lib \
	python-httplib2 \
	python-httpretty \
	python-ipaddr \
	python-iso8601 \
	python-jinja2 \
	python-jsonpatch \
	python-jsonpath-rw \
	python-jsonpointer \
	python-jsonschema \
	python-keyring \
	python-kombu \
	python-lesscpy \
	python-lockfile \
	python-logutils \
	python-lxml \
	python-mako \
	python-markupsafe \
	python-mccabe \
	python-memcache \
	python-memcached \
	python-mimeparse \
	python-mock \
	python-mox \
	python-mox3 \
	python-msgpack \
	python-netaddr \
	python-netifaces \
	python-networkx \
	python-requests \
	python-oauth2 \
	python-oauthlib \
	python-pam \
	python-paramiko \
	python-passlib \
	python-paste \
	python-pastedeploy \
	python-pbr \
	python-pep8 \
	python-ply \
	python-posix-ipc \
	python-prettytable \
	python-psycopg2 \
	python-py \
	python-pyasn1 \
	python-pycrypto \
	python-pyflakes \
	python-pymongo \
	python-pyparsing \
	python-pyscss \
	python-pysnmp \
	python-pysqlite \
	python-pytest \
	python-pytz \
	python-pyudev \
	python-requests \
	python-rfc3986 \
	python-rtslib-fb \
	python-setuptools-git \
	python-simplegeneric \
	python-singledispatch \
	python-six \
	python-sqlalchemy \
	python-stevedore \
	python-subunit \
	python-suds \
	python-sysv-ipc \
	python-taskflow \
	python-tempita \
	python-termcolor \
	python-testrepository \
	python-testresources \
	python-testscenarios \
	python-testtools \
	python-tooz \
	python-tox \
	python-virtualenv \
	python-waitress \
	python-warlock \
	python-websockify \
	python-webtest \
	python-wsgiref \
	python-wsme \
	python-xattr \
"

# meta-cloud-services/meta-openstack/recipes-devtools
RDEPENDS_${PN} += "\
	uwsgi \
"

# meta-nilrt/recipes-devtools
RDEPENDS_${PN} += "\
	libubox \
"

# meta-nilrt/recipes-connectivity
RDEPENDS_${PN} += "\
	relayd \
"

# meta-cloud-services/meta-openstack/recipes-support
RDEPENDS_${PN} += "\
	chef \
	hiera \
	mod-wsgi \
	nss-pam-ldapd \
	tgt \
"

RDEPENDS_${PN}_append_x64 += "\
	chef \
	puppet \
"

# meta-cloud-services/meta-openstack/recipes-extended
RDEPENDS_${PN} += "\
	qpid \
"

# meta-measured
RDEPENDS_${PN} += "\
	trousers \
	tpm-tools \
"

# meta-virtualization
RDEPENDS_${PN} += "\
	cgroup-lite \
	docker \
	lxc \
	multipath-tools \
	openvswitch \
"

RDEPENDS_${PN}_append_x64 += "\
	ipxe \
"

# Shared libraries needed to run FireFox
RDEPENDS_${PN} += "\
    alsa-lib \
"

# meta-security
RDEPENDS_${PN} += "\
	clamav \
	libhtp \
	python-pycrypto \
	samhain-server \
"

# meta-nilrt/recipes-ni
RDEPENDS_${PN} += "\
	sysconfig-settings \
"

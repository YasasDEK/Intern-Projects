USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

INSERT INTO module (name) VALUES ("customer-care-api"),("mnp-server"),("otp-server"),("tap-admin"),
								("sbl-server"),("sdp-server"),("soltura"),("prov-api"),
								("kite-mongo-db"),("kite-mysql-db"),("subscription-mongo-db"),
								("provisioning-v2"),("provisioning-mongo-db"),("pgw-server"),
								("mandate-server"),("common-registration"),("common-admin-mysql-db"),
								("pgw-mysql-db"),("mandate-mongo-db"),("cas"),("data-loader"),
								("reporting-viewer"),("reporting-api"),("reporting-db");

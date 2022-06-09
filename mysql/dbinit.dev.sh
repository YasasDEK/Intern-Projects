#!/bin/bash
set -e

if [ ! -z "$MYSQL_ROOT_PASSWORD" ]; then
    mysql -uroot -p$MYSQL_ROOT_PASSWORD <<EOSQL


    DROP DATABASE IF EXISTS release_note_db;
    CREATE DATABASE release_note_db;

    DROP DATABASE IF EXISTS release_note_db_test;
    CREATE DATABASE release_note_db_test;

    DROP DATABASE IF EXISTS keycloak;
    CREATE DATABASE keycloak;

    CREATE USER 'user'@'%' IDENTIFIED BY 'password';
    GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;


EOSQL
else
  echo "MYSQL_ROOT_PASSWORD is not set"
  exit 1
fi
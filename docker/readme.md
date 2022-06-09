## How to configure and maintaining docker-compose configuration

In this directory there are multiple docker-compose files. 

* docker-compose-dev-env.yml - This consists starting Mysql and Keycloak docker containers.
* docker-compose-db-migrate.yml - Runs the Database migration.

`docker-compose-dev-test.yml` and `docker-compose-release.yml` uses environmental variables for the configuration. 
There is a `.env` file with some common configuration and set of `XXXX.env` files with module specific configurations.
Sample values for the `.env` is kept in `.sample_env`. You have to take a copy of this and maintain your own `.env` file
in your development environment. 

Uncomment following lines when you deploying keycloak docker for the first time. Then after running for the first time comment them again.

```
#KEYCLOAK_USER: admin
#KEYCLOAK_PASSWORD: test123#
#KEYCLOAK_IMPORT: /tmp/enterprise-portal-realm.json
```

To start docker containers use following command.

```
docker-compose -f <COMPOSE YML> up
```

Eg:

```
docker-compose -f docker-compose-dev-test.yml up
```
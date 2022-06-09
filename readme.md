## HMS Release Note Manager

* Install Docker and Docker-Compose. 
* You need to set up Docker to able to run with normal user privileges. Refer [Docker Post-installation steps guide](https://docs.docker.com/install/linux/linux-postinstall/)


## Required configurations

* Add following to your `.profile`

```shell script
export DOCKER_REGISTRY=
export DOCKER_USER=
export DOCKER_PASSWORD=
```

### Setting up Mysql Database

* We use docker to setup the database. Run following to create required mysql docker container. 
(**Note:** This needs to be done only once.)

```shell script
./batect pushProjectInfrastructureImages
```

* Create new directory `/hms/data/rnm/mysql` to be used as mysql data volume

```shell script
sudo mkdir -p /hms/data/rnm/mysql
```

* Go to `docker` folder and create your `.env` file using `sample_env` file.

```shell script
cd docker
cp sample_env .env
```

* Start the container using 

```shell script
docker-compose -f docker-compose-dev-env.yml up -d
```

* Wait few seconds till MYSQL server starts. 
* Run the update scripts using 

```shell script
docker-compose -f docker-compose-db-migrate.yml up
```

* You can access mysql using following

```shell script
 mysql -h 127.0.0.1 -P 33080 -uuser -ppassword --local-infile=1 
```

### Managing DB Updates

* This uses **Flyway** to manage DB updates. 
* All the update scripts should be properly named and kept in directory `db-scripts`.
* Refer [Flyway Naming Guide](https://www.red-gate.com/blog/database-devops/flyway-naming-patterns-matter) 
on how to name the script files properly.
* Once update script is added use the following script to update the codebase to match with it.

### How to generate JOOQ code

* JOOQ is used for the data binding. After adding new update script you have to 
update the POJO classes related to them by running following command to regenerate 
JOOQ classes.

```shell script
./batect buildWithJooq
```
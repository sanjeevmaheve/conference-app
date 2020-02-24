# conference-app
This is a sample conference application developed using Java Spring Boot framework
My setup includes Postgres DB running as Docker container that is accessible via Windows host using following command:
```bash
$ docker-machine ip default
```
## Docker setup on Windows 7
* https://webme.ie/how-to-install-docker-on-windows-7/

## Creating database in Postgres DB
```bash
# Get into bash shell of the docker container
$ docker exec -it postgres-demo bash

bash-5.0# netstat -ltnp
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State
PID/Program name
tcp        0      0 0.0.0.0:5432            0.0.0.0:*               LISTEN -
tcp        0      0 :::5432                 :::*                    LISTEN -

# Get into the DB shell as postgres user
bash-5.0# psql -U postgres
postgres=#

# Create database of your choice
postgres=# CREATE DATABASE conference_app
postgres=# \q

# Assuming you have sql files in present working dir
# Set up the tables
bash-5.0# psql -d conference_app -f create_tables.sql

# Insert data into the created table
bash-5.0# psql -d conference_app -f insert_data.sql
```

## Reference(s):
### Bootstrap Application
*  https://start.spring.io/
### Video Course on Pluralsight.com
* Spring Framework: Creating Your First Spring Boot Application (Pluralsight)
### Sample Postgres database tables
* https://github.com/dlbunker/ps-first-spring-boot-app
### View Postgres in Browser
* https://www.pgadmin.org/download/ (use ```docker-machine ip default``` IP to connect to DB)

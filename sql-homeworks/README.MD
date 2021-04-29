# SQL Homeworks 
## SQL Selects Homeworks
Important! Please read our homework confluence page for details!
The homework is located: /homework-selects/sql_questions.sql 

## SQL Migration Homework
For this homework, you will be adding sql migration scripts in /src/main/db.migration

# Oracle 12c with Docker Environment Setup & Management
First of all, install Docker Desktop on your machine.

## Fresh install (No Oracle-DB container existing yet)
- `cd docker`
- `docker-compose up -d`

## Container Management
Since you booted the entire environment using docker-compose. The Oracle-db container will always start with your system. If you stopped it somehow, you can start it again with:  
`docker start oracle`

To stop your oracle container (if you feel that it eats too much of your RAM)
`docker stop oracle`

Use `docker ps` to see currently running containers. 
Use `docker ps -a` to see **all** containers. 


## Removing Oracle-DB Container
If you want to completely remove your Oracle-DB container
- `cd docker`
- `docker-compose down --rmi local`
- `docker volume prune`

For setup support questions, please contact Viorel Contu.
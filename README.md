# Sample Spring-boot "MyBLOG" Project for Gemalto Interview

This is a sample Java / Maven / Spring Boot (version 2.0.4) application that exposes a few web services for sample MyBLOG project.

This application is packaged as jar file which can be run on a docker container. The tomcat is embedded in the application set-up so no need to configure it separately. In order to run the application you must need to install few things as follows.

* Java 1.8 or higher
* Maven
* Docker


## About the Service

This API exposes the following services: 
* Create a new blog entry.
* Update a blog entry.
* Delete a blog entry.
* View a particular blog entry.
* View all the blog entry in the database.

Sample request:
For Create:
```http://localhost:8080/myblog/create```
```
{
  "createBy": "string",
  "data": "string",
  "lastUpdateBy": "string"
}
```


For Update:
```http://localhost:8080/myblog/update```
```
{
  "id": "2",
  "data": "string",
  "lastUpdateBy": "string"
}
```

For delete:
```http://localhost:8080/myblog/delete/{id}```

For View particular blog:
```http://localhost:8080/myblog/view/{id}```

For View all:
```http://localhost:8080/myblog/viewall```


## How to Run

Once the Java, maven and Docker are installed successfully you need to unzip[my-blog.jar] the source code provided in a suitable location.

* Open the cmd on windows/mac OS command line with Admin Privilages.
* Go to the path where you extracted the project [eg.: C:\springBoot\my-blog].
* Build the project and run the tests by running ```mvn clean package```
* It will create a JAR ```my-blog-1.0.jar``` inside the target folder.

Congrats you have build your application successfully!

Next step is to create a docker container and deploy the application on it.

* Open the Powershell on windows/mac OS command line with Admin Privilages.
* Go to the path where you extracted the project [C:\springBoot\my-blog].

Run the following commands to set-up and start mysql in docker. It will download the mysql 5.6 image from docker hub and run for you.
* ```docker pull mysql:5.6```
* ```docker run --name=mysql-db --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=myblog" mysql:5.6```

Now, once mysql is up and running, its time to start our REST Application. Run the following commands to start our application in a docker container:
It will create a docker image of our application with the name ```my-blog-1.0```.
* ```docker build -f Dockerfile -t my-blog-1.0 .```

It will link our application image with mysql image and start our application at PORT ```8080```.
* ```docker run -t --name my-blog-1.0 --link mysql-db:mysql -p 8080:8085 my-blog-1.0```

After running the above command you should see following output in the console:

```
2018-09-15 17:53:06.973  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8085 (http) with context path ''
2018-09-15 17:53:06.979  INFO 1 --- [           main] com.sb.myblog.LaunchApplication          : Started LaunchApplication in 8.054 seconds (JVM running for 9.083)
```

You can access the application at ```http://localhost:8080/swagger-ui.html#/```


## Afternote

You can access the mysql client with following docker command and access the database:
* ```docker run -it --link mysql-db:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -proot```

## Troubleshoot docker

If you get an error like below while starting the docker container

```Error response from daemon: Conflict. The container name "/my-blog-1.0" is already in use```


Than perform following commands to forcefully kill the container.
* Restart the docker engine.
* ```docker stop mysql-db```
* ```docker rm -f mysql-db```
* ```docker stop my-blog-1.0```
* ```docker rm -f my-blog-1.0```

Restart the containers:
* ```docker pull mysql:5.6```
* ```docker run --name=mysql-db --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=myblog" mysql:5.6```
* ```docker build -f Dockerfile -t my-blog-1.0 .```
* ```docker run -t --name my-blog-1.0 --link mysql-db:mysql -p 8080:8085 my-blog-1.0```

## Some additional things about Docker:

If you change the the JAR name of the application. Make sure you also update all the docker commands accordingly.
* ```docker build -f <docker-file-name> -t <project-jar-name> .```
* ```docker run -p <physical machine port>:<docker port> <imagename>```

If you change the name of mysql image. Make sure you update all the mysql related docker commands and DB properties in application.properties accordingly.  
* ```docker run --name <some-app> --link <mysql_db_name>:mysql -d <application-that-uses-mysql>```
* ```docker run -it --link <mysql_db_name>:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -proot```

Command to forcefully kill a container
* ```docker stop <image-name>```
* ```docker rm -f <image-name>```


Hope it helps!!

All © copyrights reserved by Parth Kansara
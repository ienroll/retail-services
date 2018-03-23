# Target Retail Web Service

## build

```shell
    mvn clean install 
```

## run
```shell
    java -jar rs-1.0-SNAPSHOT.jar -DLOG_PATH=
```

## Config

####<span style="color:red">application.properties, logback.xml must be in classpath</span>
 
###-DLOG_PATH=/home/user/logs

Mongo Db is expected to be running at localhost:27017 otherwise  modify application properties to point to right path

Once the application is started , we can use swagger UI to verify the services at localhost:8090
# Goal
Goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

RESTful services creted in this application will update and retrieve product and price details by ID: 

Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
  Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
  Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail)
  Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics
Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  
BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 



# Target Retail Web Service

## build

```shell
   first build -  mvn clean install -P rs-api
    
    subsequent builds - mvn clean install
```

## run
```shell
    java -jar rs/target/rs-1.0-SNAPSHOT.jar 
```

## Config

####<span style="color:red">application.properties, logback.xml must be in classpath</span>
 
###-DLOG_PATH=/home/user/logs

Mongo Db is expected to be running at localhost:27017 otherwise  modify application properties to point to right path, rebuild and run

Once the application is started , we can use swagger UI to verify the services at localhost:8090

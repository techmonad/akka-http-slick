# akka-http-slick-spray-json



#### Clone Project:
    $ git clone git@github.com:techmonad/akka-http-slick.git
    $ cd akka-http-slick


#### Compile & run unit test:
    $ sbt clean compile test

#### Run application:
    $ sbt run


#### Endpoint details:
    $ curl localhost:9000/api/employee/list
       [{"name":"jaz","email":"jaz@bar.com","id":1,"companyName":"ABC solution","position":"Senior Consultant"}]
 
    $ curl -XPOST -H 'Content-Type: application/json' localhost:9000/api/employee/create -d '{"name":"jay","email":"jay@bar.com","id":1,"companyName":"ABC solution","position":"Senior Consultant"}'
       Employee created successfully[id: 6] 

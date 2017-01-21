zipkin-sample-webapp
========

This is a tiny sample application of [Zipkin](http://zipkin.io/) tracing in Java servlet.

At first, start Zipkin server as following:

```
wget -O zipkin.jar 'https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec'
java -jar zipkin.jar
```

Then start this sample application:

```
mvn jetty:run
```

Open `http://localhost:8080/sample1` in your browser and check the tracing result of that call in Zipkin UI (`http://localhost:9411/`).

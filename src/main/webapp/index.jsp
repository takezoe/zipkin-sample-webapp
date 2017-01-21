<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>zipkin-sample-webapp</title>
</head>
<body>
<h1>zipkin-sample-webapp</h1>
<p>
This is a tiny sample application of <a href="http://zipkin.io/">Zipkin</a> tracing in Java servlet.
</p>
<p>
Start Zipkin server as following:
</p>
<pre>
wget -O zipkin.jar 'https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec'
java -jar zipkin.jar
</pre>
<p>
Open <a href="http://localhost:8080/sample1">http://localhost:8080/sample1</a> in your browser and check the tracing result of that call in Zipkin UI (<a href="http://localhost:9411/">http://localhost:9411/</a>).
</p>
</body>
</html>

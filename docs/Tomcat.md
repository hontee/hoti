# Tomcat 性能优化

## Connector优化，主要是针对吞吐量

修改conf/server.xml文件：

```
## 启动Tomcat线程池
<Executor name="tomcatThreadPool" namePrefix="ikyer-exec-" maxThreads="150" minSpareThreads="4"/>

## 配置Connector参数
<Connector port="8080" protocol="HTTP/1.1"
	executor="tomcatThreadPool"
	URIEncoding="UTF-8"
	connectionTimeout="20000"
	keepAliveTimeout="15000"
	maxHttpHeaderSize="8192"
	acceptCount="100"
	enableLookups="false"
	compressableMimeType="text/html,text/xml,text/javascript,text/css,text/plain"
	redirectPort="8443" />
```

## JVM优化

```
JAVA_OPTS="$JAVA_OPTS -Xmx128m -Xms128m -Xss512k -XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:+UseParallelGC -XX:MaxGCPauseMillis=100" 
```
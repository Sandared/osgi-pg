# Example for NPE in Apache Aries JAX-RS Whiteboard

## Solution
The enroute POMs reference an old version of the aries jax-rs whiteboard. Within your app's pom.xml you have to explicitly overwrite this dependency by adding this to the dependencies section of your pom.xml:

```xml
 <!-- JAX-RS Whiteboard specific version because of bugfix-->
 <dependency>
    <groupId>org.apache.aries.jax.rs</groupId>
    <artifactId>org.apache.aries.jax.rs.whiteboard</artifactId>
    <version>1.0.6</version>
    <scope>runtime</scope>
</dependency>
```


## Steps to reproduce
* Open example workspace in Gitpod
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/Sandared/osgi-pg/tree/jaxrs) 

* When server is up then execute: `
curl -X PUT localhost:8080/v0.4/traces -H "Content-Type: application/json" -d @input.json
`

**Content of input.json:**
```
[[
{
    "type": "sample",
    "error": 1,
    "duration": 123,
    "parent_id": 0,
    "start": 111,
    "resource": "Some resource",
    "name": "My name",
    "service": "My Service",
    "trace_id": 345,
    "span_id": 678
}
]]
```

**Response**
```xml
<ns1:XMLFault xmlns:ns1="http://cxf.apache.org/bindings/xformat"><ns1:faultstring xmlns:ns1="http://cxf.apache.org/bindings/xformat">java.lang.NullPointerException</ns1:faultstring></ns1:XMLFault>
```

**NPE in JAX-RS Whiteboard**
```
java.nio.channels.ClosedChannelException: null
        at org.eclipse.jetty.io.WriteFlusher.onClose(WriteFlusher.java:502)
        at org.eclipse.jetty.io.AbstractEndPoint.onClose(AbstractEndPoint.java:353)
        at org.eclipse.jetty.io.ChannelEndPoint.onClose(ChannelEndPoint.java:216)
        at org.eclipse.jetty.io.AbstractEndPoint.doOnClose(AbstractEndPoint.java:225)
        at org.eclipse.jetty.io.AbstractEndPoint.close(AbstractEndPoint.java:192)
        at org.eclipse.jetty.io.AbstractEndPoint.close(AbstractEndPoint.java:175)
        at org.eclipse.jetty.io.AbstractConnection.close(AbstractConnection.java:248)
        at org.eclipse.jetty.server.HttpChannelOverHttp.earlyEOF(HttpChannelOverHttp.java:234)
        at org.eclipse.jetty.http.HttpParser.parseNext(HttpParser.java:1551)
        at org.eclipse.jetty.server.HttpConnection.parseRequestBuffer(HttpConnection.java:360)
        at org.eclipse.jetty.server.HttpConnection.onFillable(HttpConnection.java:250)
        at org.eclipse.jetty.io.AbstractConnection$ReadCallback.succeeded(AbstractConnection.java:305)
        at org.eclipse.jetty.io.FillInterest.fillable(FillInterest.java:103)
        at org.eclipse.jetty.io.ChannelEndPoint$2.run(ChannelEndPoint.java:118)
        at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.runTask(EatWhatYouKill.java:333)
        at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.doProduce(EatWhatYouKill.java:310)
        at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.tryProduce(EatWhatYouKill.java:168)
        at org.eclipse.jetty.util.thread.strategy.EatWhatYouKill.produce(EatWhatYouKill.java:132)
        at org.eclipse.jetty.util.thread.QueuedThreadPool.runJob(QueuedThreadPool.java:765)
        at org.eclipse.jetty.util.thread.QueuedThreadPool$2.run(QueuedThreadPool.java:683)
        at java.base/java.lang.Thread.run(Thread.java:834)
```

If the return type of our endpoint is changed to something other than void, then the NPE still gets thrown internall, but the response does not show it to the caller, but instead the value we return.

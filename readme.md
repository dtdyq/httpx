### HttpX

- jdk21 and virtual thread
- no reflect
- very very fast
- simplest api and usage

##### demo:

###### rest api:

```java
public class Test {
    @Test
    public void testPostJson() throws Throwable {
        HttpX.create()
                .post("/up", new Handler() {
                    @Override
                    public Response handle(Context ctx) throws Throwable {
                        Map<String, String> formMap = ctx.forms();
                        return Json.ok(formMap);
                    }
                }).start();
    }
}
```

###### static dir:

```java
public class Test {
    @Test
    public void testStatic() throws Throwable {
        HttpX.create()
                .dir("/", "./").start();
    }
}
```


##### benchmark vs springboot
###### springboot
```shell
==========================BENCHMARK==========================
URL:                            http://127.0.0.1:8080/tex

Used Connections:               100
Used Threads:                   5
Total number of calls:          10000

===========================TIMINGS===========================
Total time passed:              1.31s
Avg time per request:           12.99ms
Requests per second:            7635.83
Median time per request:        5.84ms
99th percentile time:           76.34ms
Slowest time for request:       80.00ms

=============================DATA=============================
Total response body sizes:              1050000
Avg response body per request:          105.00 Byte
Transfer rate per second:               801762.35 Byte/s (0.80 MByte/s)
==========================RESPONSES==========================
20X Responses:          0       (0.00%)
30X Responses:          0       (0.00%)
40X Responses:          10000   (100.00%)
50X Responses:          0       (0.00%)
Errors:                 0       (0.00%)
```
###### httpx
```shell
==========================BENCHMARK==========================
URL:                            http://127.0.0.1:8080/up

Used Connections:               100
Used Threads:                   5
Total number of calls:          10000

===========================TIMINGS===========================
Total time passed:              0.30s
Avg time per request:           2.90ms
Requests per second:            32862.63
Median time per request:        2.79ms
99th percentile time:           6.75ms
Slowest time for request:       10.00ms

=============================DATA=============================
Total response body sizes:              20000
Avg response body per request:          2.00 Byte
Transfer rate per second:               65725.26 Byte/s (0.07 MByte/s)
==========================RESPONSES==========================
20X Responses:          10000   (100.00%)
30X Responses:          0       (0.00%)
40X Responses:          0       (0.00%)
50X Responses:          0       (0.00%)
Errors:                 0       (0.00%)
```
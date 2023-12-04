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
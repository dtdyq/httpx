package org.dyq.httpx.route;

import lombok.extern.slf4j.Slf4j;
import org.dyq.httpx.resp.Response;
import org.dyq.httpx.resp.impl.StaticFile;
import org.dyq.httpx.resp.impl.Stream;
import org.dyq.httpx.xh.Context;
import org.dyq.httpx.xh.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
final class StaticHandler implements Handler {
    private final String prefix;
    private final String path;
    private final Map<String, Path> enablePathMap = new HashMap<>();

    public StaticHandler(String prefix, String path) {
        this.prefix = prefix;
        this.path = path;
        setup();
    }

    private void setup() {
        Path p = Paths.get(path);
        if (Files.exists(p)) {
            if (!path.startsWith("/") && !p.isAbsolute()) {
                //普通文件系统
                if (Files.isDirectory(p)) {
                    try (var s = Files.walk(p)) {
                        s.forEach(new Consumer<Path>() {
                            @Override
                            public void accept(Path path) {
                                if (Files.isRegularFile(path)) {
                                    enablePathMap.put(prefix + normalize(path), path);
                                }
                            }
                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    //作为文件
                    enablePathMap.put(prefix + normalize(p), p);

                }
            }
        }
        log.info("static server path:{}", path);
    }

    private static String normalize(Path path) {
        String trail = path.toString().replaceAll("\\\\", "/");
        if (trail.startsWith("./")) trail = trail.substring(2);
        return trail;
    }

    @Override
    public Response handle(Context context) throws Throwable {
        String uri = context.uri();
        if (enablePathMap.containsKey(uri)) {
            //之前在，现在可不一定在
            Path tmp = enablePathMap.get(uri);
            if (Files.exists(tmp)) {
                return StaticFile.ok(tmp);
            } else {
                return context.next();
            }
        } else {
            //尝试作为resource jar
            String pfx = prefix + normalize(Path.of(path));
            pfx = pfx.replaceAll("//", "/");
            if (uri.startsWith(pfx)) {
                InputStream res = Thread.currentThread().getContextClassLoader().getResourceAsStream(uri.substring(pfx.length()));
                return Stream.ok(res, uri);
            } else {
                return context.next();
            }
        }
    }
}

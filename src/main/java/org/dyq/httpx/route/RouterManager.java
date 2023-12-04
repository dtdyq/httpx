package org.dyq.httpx.route;

import lombok.extern.slf4j.Slf4j;
import org.dyq.httpx.core.Session;
import org.dyq.httpx.exception.DirInvalidException;
import org.dyq.httpx.util.Pair;
import org.dyq.httpx.core.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

@Slf4j
public class RouterManager {
    private final Map<String, RouterSetup<Handler>> exactRouteTmp = new HashMap<>();
    private Map<String, Router<Handler>> exactRouteMap = new HashMap<>();
    private final Map<String, List<Handler>> methodRouteMap = new HashMap<>();
    private final List<Pair<Pattern, Handler>> regexHandlers = new ArrayList<>();
    private final Map<Class<? extends Throwable>, List<Handler>> exceptRouteMap = new HashMap<>();
    private final List<Handler> globalRoutes = new ArrayList<>();
    private final Map<String, Handler> staticRoutes = new HashMap<>();

    public static RouterManager inst() {
        return LH.ROUTER_MANAGER;
    }

    public RouterManager exact(String method, String path, Handler handler) {
        exactRouteTmp.compute(method, (m, setup) -> {
            if (setup == null) {
                setup = new RouterSetup<>();
            }
            setup.add(path, handler);
            return setup;
        });
        return this;
    }

    public RouterManager method(String method, Handler handler) {
        methodRouteMap.compute(method, (m, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(handler);
            return list;
        });
        return this;
    }

    public RouterManager regex(String regex, Handler handler) {
        Pattern p = Pattern.compile(regex);
        regexHandlers.add(new Pair<>(p, handler));
        return this;
    }

    public RouterManager except(Class<? extends Exception> clz, Handler handler) {
        exceptRouteMap.compute(clz, (m, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(handler);
            return list;
        });
        return this;
    }

    public RouterManager complete() {
        exactRouteMap = exactRouteTmp.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().build()
        ));
        return this;
    }

    public RouterManager global(Handler handler) {
        globalRoutes.add(handler);
        return this;
    }

    /**
     * "/static","./asset/index.html"  -->
     * http://example.com/static/asset/index.html
     *
     * @param prefix prefix
     * @param path   path
     * @return rm
     */
    public RouterManager dir(String prefix, String path) {
        if (prefix == null) {
            throw new DirInvalidException("invalid prefix:%s".formatted(prefix));
        }
        if (prefix.isBlank() || !prefix.endsWith("/")) {
            prefix = prefix.strip() + "/";
        }
        staticRoutes.put(prefix, new StaticHandler(prefix, path));
        return this;
    }

    public List<Handler> handlerFor(Session session) {
        List<Handler> handlers = new ArrayList<>();
        handlers.addAll(globalRoutes);
        handlers.addAll(methodRouteMap.getOrDefault(session.request().method(), List.of()));
        String uri = session.request().uri().getPath();
        var r = exactRouteMap.getOrDefault(session.request().method(), null);
        if (r != null) {
            var match = r.routeOrNull(uri);
            if (match != null) {
                session.request().addPathVars(match.variables());
                handlers.add(match.handler());
            }
        } else {
            staticRoutes.forEach(new BiConsumer<String, Handler>() {
                @Override
                public void accept(String s, Handler Handler) {
                    if (uri.startsWith(s)) {
                        handlers.add(Handler);
                    }
                }
            });
        }
        regexHandlers.forEach(new Consumer<Pair<Pattern, Handler>>() {
            @Override
            public void accept(Pair<Pattern, Handler> pair) {
                if (pair.key().matcher(uri).matches()) {
                    handlers.add(pair.val());
                }
            }
        });
        return handlers;
    }

    public Map<Class<? extends Throwable>, List<Handler>> exceptionHandlers() {
        return exceptRouteMap;
    }

    private static final class LH {
        public static final RouterManager ROUTER_MANAGER = new RouterManager();
    }

    private RouterManager() {

    }

    public boolean isValidRegex(String regex) {
        try {
            Pattern.compile(regex);
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }
    }
}

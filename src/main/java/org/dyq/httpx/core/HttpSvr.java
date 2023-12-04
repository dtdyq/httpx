package org.dyq.httpx.core;

import org.dyq.httpx.config.Config;
import org.dyq.httpx.util.ThreadPool;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpSvr {
    private volatile boolean running = true;
    private Config config;

    public void start() throws Throwable {
        try (ServerSocket server = new ServerSocket(Config.curr().port(), Config.curr().backlog())) {
            while (running) {
                Socket client = server.accept();
                ThreadPool.global().submit(new Session(client, config));
            }
        }
    }

    public void stop() {
        running = false;
    }

    public void config(Config config) {
        this.config = config;
    }
}

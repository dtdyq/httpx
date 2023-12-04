package org.dyq.httpx.core;

import org.dyq.httpx.util.Config;
import org.dyq.httpx.util.ThreadPool;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpSvr {
    private volatile boolean running = true;

    public void start() throws Throwable {
        try (ServerSocket server = new ServerSocket(Config.PORT.getInt(), Config.BACKLOG.getInt())) {
            while (running) {
                Socket client = server.accept();
                ThreadPool.global().submit(new Session(client));
            }
        }
    }

    public void stop() {
        running = false;
    }

}

package com.mycompany.realtimechatapplication;

import org.glassfish.tyrus.server.Server;

public class Main {

    public static void main(String[] args) {
        Server server = new Server("localhost", 8025, "/", ChatEndpoint.class);
        try {
            server.start();
            System.out.println("Server started at ws://localhost:8025/chat");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
            System.out.println("Server stopped.");
        }
    }
}

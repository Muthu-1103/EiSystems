package com.mycompany.realtimechatapplication;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chat/{roomId}/{username}")
public class ChatEndpoint {

    private static Set<Session> sessions = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String roomId, @PathParam("username") String username) {
        sessions.add(session);
        broadcast("User " + username + " joined room " + roomId);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("roomId") String roomId, @PathParam("username") String username) {
        broadcast("[" + username + "]: " + message);
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(session);
        broadcast("User " + username + " left the chat");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    private void broadcast(String message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package adapter;

import model.ChatRoom;
import model.Message;
import model.User;
import singleton.ChatRoomManager;
import observer.ChatRoomObserver;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/chat/{roomId}/{username}")
public class WebSocketAdapter {
    private ChatRoom chatRoom;
    private User user;

    @OnOpen
    public void onOpen(Session session, String roomId, String username) {
        chatRoom = ChatRoomManager.getInstance().getChatRoom(roomId);
        user = new User(username);
        chatRoom.join(user);

        ChatRoomObserver observer = new ChatRoomObserver(username);
        chatRoom.addObserver(observer);
        
        session.getUserProperties().put("observer", observer);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        chatRoom.sendMessage(new Message(user.getUsername(), message));
    }

    @OnClose
    public void onClose(Session session) {
        chatRoom.leave(user);
        ChatRoomObserver observer = (ChatRoomObserver) session.getUserProperties().get("observer");
        chatRoom.removeObserver(observer);
    }
}

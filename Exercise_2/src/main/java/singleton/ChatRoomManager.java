package singleton;

import model.ChatRoom;

import java.util.HashMap;
import java.util.Map;

public class ChatRoomManager {
    private static ChatRoomManager instance;
    private Map<String, ChatRoom> chatRooms;

    private ChatRoomManager() {
        chatRooms = new HashMap<>();
    }

    public static synchronized ChatRoomManager getInstance() {
        if (instance == null) {
            instance = new ChatRoomManager();
        }
        return instance;
    }

    public ChatRoom getChatRoom(String roomId) {
        return chatRooms.computeIfAbsent(roomId, ChatRoom::new);
    }
}

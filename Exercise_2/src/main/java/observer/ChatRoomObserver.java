package observer;
public class ChatRoomObserver implements Observer {
    private String observerName;

    public ChatRoomObserver(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(String message) {
        System.out.println(observerName + " received message: " + message);
    }
}

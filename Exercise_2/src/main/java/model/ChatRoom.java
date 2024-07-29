package model;

import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements Observable {
    private String roomId;
    private List<User> activeUsers;
    private List<Message> messageHistory;
    private List<Observer> observers;
    
    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.activeUsers = new ArrayList<>();
        this.messageHistory = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public String getRoomId() {
        return roomId;
    }

    public void join(User user) {
        activeUsers.add(user);
        notifyObservers("User joined: " + user.getUsername());
    }

    public void leave(User user) {
        activeUsers.remove(user);
        notifyObservers("User left: " + user.getUsername());
    }

    public void sendMessage(Message message) {
        messageHistory.add(message);
        notifyObservers(message.toString());
    }

    public List<User> getActiveUsers() {
        return activeUsers;
    }

    public List<Message> getMessageHistory() {
        return messageHistory;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

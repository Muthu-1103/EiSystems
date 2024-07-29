package exercise1;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Concrete Observers
class EmailNotification implements Observer {
    public void update(String message) {
        System.out.println("Email Notification: " + message);
    }
}

class SMSNotification implements Observer {
    public void update(String message) {
        System.out.println("SMS Notification: " + message);
    }
}

class PushNotification implements Observer {
    public void update(String message) {
        System.out.println("Push Notification: " + message);
    }
}

// Subject
class MessageSystem {
    private List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Main class
public class ObserverPatternDemo {
    public static void main(String[] args) {
        MessageSystem messageSystem = new MessageSystem();
        
        Observer email = new EmailNotification();
        Observer sms = new SMSNotification();
        Observer push = new PushNotification();
        
        messageSystem.addObserver(email);
        messageSystem.addObserver(sms);
        messageSystem.addObserver(push);
        
        messageSystem.notifyObservers("New Message Received!");
    }
}

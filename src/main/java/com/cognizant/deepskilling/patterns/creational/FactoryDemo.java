package com.cognizant.deepskilling.patterns.creational;

// ============================================================
// Factory Method Pattern
// Defines an interface for creating objects but lets subclasses
// decide which class to instantiate.
// Used in: Spring BeanFactory, Calendar.getInstance(), LoggerFactory
// ============================================================

// Product hierarchy
interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    public void send(String message) {
        System.out.println("  Sending EMAIL: " + message);
    }
}

class SMSNotification implements Notification {
    public void send(String message) {
        System.out.println("  Sending SMS: " + message);
    }
}

class PushNotification implements Notification {
    public void send(String message) {
        System.out.println("  Sending PUSH: " + message);
    }
}

// Factory - encapsulates object creation logic
class NotificationFactory {
    public static Notification create(String type) {
        return switch (type.toUpperCase()) {
            case "EMAIL" -> new EmailNotification();
            case "SMS"   -> new SMSNotification();
            case "PUSH"  -> new PushNotification();
            default -> throw new IllegalArgumentException("Unknown: " + type);
        };
    }
}

public class FactoryDemo {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern ===");
        System.out.println("-> Intent: Delegate object creation to factory");

        Notification email = NotificationFactory.create("EMAIL");
        Notification sms   = NotificationFactory.create("SMS");
        Notification push  = NotificationFactory.create("PUSH");

        email.send("Welcome!");
        sms.send("OTP: 1234");
        push.send("New message");

        System.out.println("Client code does NOT use 'new' — factory handles creation.\n");
    }
}

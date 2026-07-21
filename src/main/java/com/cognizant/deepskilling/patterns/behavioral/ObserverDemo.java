package com.cognizant.deepskilling.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;

// ============================================================
// Observer Pattern
// Defines a one-to-many dependency between objects so that when
// one object changes state, all its dependents are notified.
// Used in: Event handling, Spring @EventListener, MVC (Model-View)
// ============================================================

// Observer interface
interface Observer {
    void update(String stockName, double price);
}

// Subject (Observable)
class Stock {
    private String name;
    private double price;
    private List<Observer> observers = new ArrayList<>();

    public Stock(String name, double initialPrice) {
        this.name = name;
        this.price = initialPrice;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void setPrice(double newPrice) {
        System.out.println("\n  [Stock] " + name + " price changed: $" + price + " -> $" + newPrice);
        this.price = newPrice;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update(name, price);
        }
    }
}

// Concrete observers
class MobileApp implements Observer {
    private String userId;
    public MobileApp(String userId) { this.userId = userId; }
    public void update(String stockName, double price) {
        System.out.println("  [Mobile App - " + userId + "] Alert: " + stockName + " is now $" + price);
    }
}

class EmailNotifier implements Observer {
    private String email;
    public EmailNotifier(String email) { this.email = email; }
    public void update(String stockName, double price) {
        System.out.println("  [Email to " + email + "] " + stockName + " price update: $" + price);
    }
}

class TradingBot implements Observer {
    public void update(String stockName, double price) {
        if (price < 150) {
            System.out.println("  [Trading Bot] BUY signal for " + stockName + " at $" + price);
        }
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern ===");
        System.out.println("-> Intent: One-to-many notification on state change");

        Stock apple = new Stock("AAPL", 175.0);

        apple.attach(new MobileApp("user_001"));
        apple.attach(new EmailNotifier("trader@example.com"));
        apple.attach(new TradingBot());

        apple.setPrice(180.50);
        apple.setPrice(145.30);

        System.out.println("\nAll observers notified automatically. Loose coupling.\n");
    }
}

package com.cognizant.deepskilling.patterns.behavioral;

import java.util.List;

// ============================================================
// Strategy Pattern
// Encapsulates interchangeable algorithms and lets them vary independently.
// Used in: Comparator, Spring ResourceLoader, Payment processing
// ============================================================

// Strategy interface
interface PaymentMethod {
    void pay(double amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    public CreditCardPayment(String cardNumber) { this.cardNumber = cardNumber; }
    public void pay(double amount) {
        System.out.println("  Paid $" + amount + " via Credit Card ****" +
                cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentMethod {
    private String email;
    public PayPalPayment(String email) { this.email = email; }
    public void pay(double amount) {
        System.out.println("  Paid $" + amount + " via PayPal (" + email + ")");
    }
}

class CryptoPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("  Paid $" + amount + " via Cryptocurrency");
    }
}

// Context — uses the strategy
class ShoppingCart {
    private List<String> items;

    public ShoppingCart(List<String> items) {
        this.items = items;
    }

    public void checkout(PaymentMethod paymentMethod) {
        double total = items.size() * 10; // Simplified
        System.out.println("  Cart: " + items + " | Total: $" + total);
        paymentMethod.pay(total);
    }
}

public class StrategyDemo {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern ===");
        System.out.println("-> Intent: Encapsulate interchangeable algorithms");

        ShoppingCart cart = new ShoppingCart(List.of("Book", "Pen", "Notebook"));

        // Choose strategy at runtime
        cart.checkout(new CreditCardPayment("1234567890123456"));
        cart.checkout(new PayPalPayment("alice@email.com"));
        cart.checkout(new CryptoPayment());

        System.out.println("Algorithms interchangeable at runtime. OCP compliant.\n");
    }
}

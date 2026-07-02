package com.cognizant.deepskilling.patterns.structural;

// ============================================================
// Adapter Pattern
// Allows incompatible interfaces to work together.
// Acts as a translator between two systems.
// Used in: JDBC drivers, Arrays.asList(), InputStreamReader
// ============================================================

// Target interface — what the client expects
interface PaymentProcessor {
    void pay(double amount);
}

// Adaptee — existing class with incompatible interface
class OldPaymentGateway {
    public void makePayment(String currency, double amount) {
        System.out.println("  [OldGateway] Charged " + currency + " " + amount);
    }
}

// Adapter — bridges the gap
class PaymentAdapter implements PaymentProcessor {
    private final OldPaymentGateway oldGateway;

    public PaymentAdapter(OldPaymentGateway oldGateway) {
        this.oldGateway = oldGateway;
    }

    @Override
    public void pay(double amount) {
        // Translate new interface call to old interface
        oldGateway.makePayment("USD", amount);
    }
}

// Modern client using the new interface
class CheckoutService {
    private final PaymentProcessor processor;

    public CheckoutService(PaymentProcessor processor) {
        this.processor = processor;
    }

    public void checkout(double amount) {
        System.out.println("  [Checkout] Processing payment...");
        processor.pay(amount);
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern ===");
        System.out.println("-> Intent: Make incompatible interfaces work together");

        OldPaymentGateway legacy = new OldPaymentGateway();
        PaymentProcessor adapter = new PaymentAdapter(legacy);
        CheckoutService checkout = new CheckoutService(adapter);

        checkout.checkout(99.99);

        System.out.println("Old gateway works through adapter. No code changed in OldPaymentGateway.\n");
    }
}

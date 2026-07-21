package com.cognizant.deepskilling.patterns.creational;

// ============================================================
// Builder Pattern
// Separates construction of a complex object from its representation.
// Used when: 4+ constructor parameters, especially optional ones.
// Used in: StringBuilder, Spring UriComponentsBuilder, Lombok @Builder
// ============================================================

class Order {
    // Required
    private final String orderId;
    private final String customerEmail;
    // Optional
    private final String shippingAddress;
    private final String couponCode;
    private final boolean giftWrap;
    private final String deliveryInstructions;

    // Private constructor — only Builder can call it
    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.customerEmail = builder.customerEmail;
        this.shippingAddress = builder.shippingAddress;
        this.couponCode = builder.couponCode;
        this.giftWrap = builder.giftWrap;
        this.deliveryInstructions = builder.deliveryInstructions;
    }

    @Override
    public String toString() {
        return "Order[ID=" + orderId + ", email=" + customerEmail
                + ", ship=" + shippingAddress + ", coupon=" + couponCode
                + ", giftWrap=" + giftWrap + "]";
    }

    // Static nested Builder class
    public static class Builder {
        private final String orderId;
        private final String customerEmail;
        private String shippingAddress = "Default Address";
        private String couponCode = "";
        private boolean giftWrap = false;
        private String deliveryInstructions = "";

        public Builder(String orderId, String customerEmail) {
            this.orderId = orderId;
            this.customerEmail = customerEmail;
        }

        public Builder shippingAddress(String val) { this.shippingAddress = val; return this; }
        public Builder couponCode(String val)      { this.couponCode = val; return this; }
        public Builder giftWrap(boolean val)       { this.giftWrap = val; return this; }
        public Builder deliveryInstructions(String val) { this.deliveryInstructions = val; return this; }

        public Order build() {
            // Validation can go here
            if (orderId == null || customerEmail == null) {
                throw new IllegalStateException("Required fields missing");
            }
            return new Order(this);
        }
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern ===");
        System.out.println("-> Intent: Step-by-step construction of complex objects");

        // Only set what you need — readable and immutable
        Order order = new Order.Builder("ORD-001", "alice@email.com")
                .shippingAddress("123 Main St")
                .couponCode("WELCOME10")
                .giftWrap(true)
                .build();

        System.out.println("  " + order);

        Order simpleOrder = new Order.Builder("ORD-002", "bob@email.com")
                .shippingAddress("456 Oak Ave")
                .build();

        System.out.println("  " + simpleOrder);
        System.out.println("No telescoping constructors. Clear intent. Immutable result.\n");
    }
}

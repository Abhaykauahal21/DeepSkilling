package com.cognizant.deepskilling.solid;

import java.util.List;

// ============================================================
// Open/Closed Principle (OCP)
// "Software entities should be open for extension, closed for modification."
// ============================================================

// --- VIOLATION: Every new discount type requires modifying this class ---
class DiscountCalculatorBad {
    public double calculate(double price, String type) {
        if (type.equals("REGULAR"))  return price * 0.9;
        else if (type.equals("VIP")) return price * 0.8;
        else if (type.equals("SEASONAL")) return price * 0.7;
        else throw new IllegalArgumentException("Unknown type: " + type);
    }
}

// --- CORRECT: Strategy Pattern with OCP ---
interface DiscountStrategy {
    double apply(double price);
}

class RegularDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.9; }
}

class VIPDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.8; }
}

class SeasonalDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.7; }
}

// New discounts = new classes, no existing code modified!
class EmployeeDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.85; }
}

class PriceCalculator {
    private final List<DiscountStrategy> strategies;

    public PriceCalculator(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    public double calculate(double price) {
        double finalPrice = price;
        for (DiscountStrategy s : strategies) {
            finalPrice = s.apply(finalPrice);
        }
        return finalPrice;
    }
}

public class OCP_Demo {
    public static void main(String[] args) {
        System.out.println("=== OCP - Open/Closed Principle ===");
        var discounts = List.of(new VIPDiscount(), new EmployeeDiscount());
        PriceCalculator calc = new PriceCalculator(discounts);
        double result = calc.calculate(1000);
        System.out.println("Price after discounts: $" + result);
        System.out.println("New discount types? Just add a class. No existing code changed.\n");
    }
}

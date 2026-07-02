package com.cognizant.deepskilling.patterns.structural;

// ============================================================
// Decorator Pattern
// Attaches additional responsibilities to an object dynamically.
// Alternative to subclassing for adding behavior.
// Used in: java.io (BufferedReader wrapping FileReader),
//          Spring Security (filter chains), Collections.synchronizedXXX()
// ============================================================

// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete component
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple coffee"; }
    public double getCost() { return 2.0; }
}

// Abstract decorator
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() { return coffee.getDescription(); }
    public double getCost() { return coffee.getCost(); }
}

// Concrete decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return coffee.getDescription() + " + milk"; }
    public double getCost() { return coffee.getCost() + 0.5; }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return coffee.getDescription() + " + sugar"; }
    public double getCost() { return coffee.getCost() + 0.25; }
}

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return coffee.getDescription() + " + whipped cream"; }
    public double getCost() { return coffee.getCost() + 0.75; }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern ===");
        System.out.println("-> Intent: Add behavior dynamically at runtime");

        Coffee coffee = new SimpleCoffee();
        System.out.println("  " + coffee.getDescription() + " = $" + coffee.getCost());

        // Wrap at runtime — no new subclasses needed
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        coffee = new WhippedCreamDecorator(coffee);

        System.out.println("  " + coffee.getDescription() + " = $" + coffee.getCost());
        System.out.println("Combinations are infinite without subclass explosion.\n");
    }
}

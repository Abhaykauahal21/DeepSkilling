package com.cognizant.deepskilling.solid;

// ============================================================
// Liskov Substitution Principle (LSP)
// "Subtypes must be substitutable for their base types."
// ============================================================

// --- VIOLATION: Square extends Rectangle but breaks behavior ---
class Rectangle {
    protected int width, height;

    public void setWidth(int w) { this.width = w; }
    public void setHeight(int h) { this.height = h; }
    public int getArea() { return width * height; }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int w) {
        this.width = w;
        this.height = w; // Side effect!
    }

    @Override
    public void setHeight(int h) {
        this.height = h;
        this.width = h; // Side effect!
    }
}

// --- CORRECT: Use abstraction, not inheritance for different shapes ---
interface Shape {
    int getArea();
}

class GoodRectangle implements Shape {
    private int width, height;
    public GoodRectangle(int w, int h) { this.width = w; this.height = h; }
    public int getArea() { return width * height; }
}

class GoodSquare implements Shape {
    private int side;
    public GoodSquare(int s) { this.side = s; }
    public int getArea() { return side * side; }
}

public class LSP_Demo {
    public static void main(String[] args) {
        System.out.println("=== LSP - Liskov Substitution Principle ===");

        // Violation demo
        Rectangle r = new Square();
        r.setWidth(5);
        r.setHeight(10);
        System.out.println("Square as Rectangle -> area = " + r.getArea() + " (expected 50, got " + r.getArea() + ")");

        // Correct approach
        Shape s1 = new GoodRectangle(5, 10);
        Shape s2 = new GoodSquare(5);
        System.out.println("Rectangle area: " + s1.getArea() + " | Square area: " + s2.getArea());
        System.out.println("Both substitutable without surprises.\n");
    }
}

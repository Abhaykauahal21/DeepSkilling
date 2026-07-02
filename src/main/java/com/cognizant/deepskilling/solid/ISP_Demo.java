package com.cognizant.deepskilling.solid;

// ============================================================
// Interface Segregation Principle (ISP)
// "No client should be forced to depend on methods it does not use."
// ============================================================

// --- VIOLATION: Fat interface ---
interface Worker {
    void work();
    void eat();
    void sleep();
}

class Human implements Worker {
    public void work() { System.out.println("Human working"); }
    public void eat()  { System.out.println("Human eating"); }
    public void sleep(){ System.out.println("Human sleeping"); }
}

class Robot implements Worker {
    public void work() { System.out.println("Robot working"); }
    public void eat()  { throw new UnsupportedOperationException("Robots don't eat!"); }
    public void sleep(){ throw new UnsupportedOperationException("Robots don't sleep!"); }
}

// --- CORRECT: Segregated interfaces ---
interface Workable { void work(); }
interface Eatable  { void eat(); }
interface Sleepable{ void sleep(); }

class HumanWorker implements Workable, Eatable, Sleepable {
    public void work() { System.out.println("Human working"); }
    public void eat()  { System.out.println("Human eating"); }
    public void sleep(){ System.out.println("Human sleeping"); }
}

class RobotWorker implements Workable {
    public void work() { System.out.println("Robot working"); }
}

public class ISP_Demo {
    public static void main(String[] args) {
        System.out.println("=== ISP - Interface Segregation Principle ===");
        HumanWorker human = new HumanWorker();
        RobotWorker robot = new RobotWorker();
        human.work(); human.eat();
        robot.work(); // No forced eat()/sleep()
        System.out.println("Robot never forced to implement irrelevant methods.\n");
    }
}

package com.cognizant.deepskilling.solid;

// ============================================================
// Dependency Inversion Principle (DIP)
// "Depend on abstractions, not on concretions."
// "High-level modules should not depend on low-level modules."
// ============================================================

// --- VIOLATION: High-level depends on low-level directly ---
class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

class ServiceBad {
    private MySQLDatabase db = new MySQLDatabase(); // Tight coupling!

    public void process(String data) {
        db.save(data);
    }
}

// --- CORRECT: Both depend on abstraction ---
interface Database {
    void save(String data);
}

class MySQLDatabaseImpl implements Database {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

class PostgresDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to PostgreSQL: " + data);
    }
}

class Service {
    private final Database db; // Depends on abstraction!

    public Service(Database db) { // Dependency Injection via constructor
        this.db = db;
    }

    public void process(String data) {
        db.save(data);
    }
}

public class DIP_Demo {
    public static void main(String[] args) {
        System.out.println("=== DIP - Dependency Inversion Principle ===");

        // Switch databases without changing Service class
        Service mysqlService = new Service(new MySQLDatabaseImpl());
        Service pgService = new Service(new PostgresDatabase());

        mysqlService.process("Order #123");
        pgService.process("Order #456");

        System.out.println("Service depends on Database interface, not concrete class.\n");
    }
}

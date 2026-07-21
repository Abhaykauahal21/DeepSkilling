package com.cognizant.deepskilling.patterns.creational;

// ============================================================
// Singleton Pattern
// Ensures a class has only ONE instance and provides global access.
// Used in: Logger, DB Connection Pool, Spring Beans (default scope)
// ============================================================

// Thread-safe Singleton with Double-Checked Locking
class DatabaseConnectionPool {
    private static volatile DatabaseConnectionPool instance;

    private DatabaseConnectionPool() {
        // Private constructor prevents external instantiation
        System.out.println("  [DB Pool] Initializing connection pool...");
    }

    public static DatabaseConnectionPool getInstance() {
        if (instance == null) {                       // First check (no locking)
            synchronized (DatabaseConnectionPool.class) {
                if (instance == null) {               // Second check (with locking)
                    instance = new DatabaseConnectionPool();
                }
            }
        }
        return instance;
    }

    public void getConnection() {
        System.out.println("  [DB Pool] Returning pooled connection");
    }
}

// Eager initialization variant (simpler, loads at class load time)
class ConfigManager {
    private static final ConfigManager INSTANCE = new ConfigManager();
    private String appName = "DeepSkilling";

    private ConfigManager() {}

    public static ConfigManager getInstance() {
        return INSTANCE;
    }

    public String getAppName() { return appName; }
}

public class SingletonDemo {
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern ===");
        System.out.println("-> Intent: Ensure only one instance of a class");

        DatabaseConnectionPool pool1 = DatabaseConnectionPool.getInstance();
        DatabaseConnectionPool pool2 = DatabaseConnectionPool.getInstance();
        System.out.println("  Same instance? " + (pool1 == pool2)); // true

        ConfigManager config1 = ConfigManager.getInstance();
        ConfigManager config2 = ConfigManager.getInstance();
        System.out.println("  App name: " + config1.getAppName());
        System.out.println("  Same config instance? " + (config1 == config2)); // true

        System.out.println("Key: Private constructor + static factory method\n");
    }
}

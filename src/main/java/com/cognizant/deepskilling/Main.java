package com.cognizant.deepskilling;

import com.cognizant.deepskilling.solid.*;
import com.cognizant.deepskilling.patterns.creational.*;
import com.cognizant.deepskilling.patterns.structural.*;
import com.cognizant.deepskilling.patterns.behavioral.*;

/**
 * Cognizant Digital Nurture 5.0 - Java Track
 * Week 1, Topic 1: Design Patterns & Principles (SOLID)
 *
 * Run this class to see all examples in action.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║  Cognizant Digital Nurture 5.0 - Java Track    ║");
        System.out.println("║  Week 1 | Topic 1: Design Patterns & SOLID     ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        // === SOLID Principles ===
        SRP_Demo.main(args);
        OCP_Demo.main(args);
        LSP_Demo.main(args);
        ISP_Demo.main(args);
        DIP_Demo.main(args);

        // === Creational Patterns ===
        SingletonDemo.main(args);
        FactoryDemo.main(args);
        BuilderDemo.main(args);

        // === Structural Patterns ===
        AdapterDemo.main(args);
        DecoratorDemo.main(args);
        ProxyDemo.main(args);

        // === Behavioral Patterns ===
        StrategyDemo.main(args);
        ObserverDemo.main(args);
        TemplateMethodDemo.main(args);

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║  All examples executed successfully!            ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}

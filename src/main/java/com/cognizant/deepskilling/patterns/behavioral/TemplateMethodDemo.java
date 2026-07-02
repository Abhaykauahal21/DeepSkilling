package com.cognizant.deepskilling.patterns.behavioral;

// ============================================================
// Template Method Pattern
// Defines the skeleton of an algorithm in a method, deferring
// some steps to subclasses. Subclasses redefine certain steps
// without changing the algorithm's structure.
// Used in: Spring JdbcTemplate, JUnit (setUp/tearDown),
//          java.io (read/write streams)
// ============================================================

// Abstract class with template method
abstract class DataMiner {
    // Template method — defines the skeleton
    public final void mine(String filePath) {
        openFile(filePath);
        extractData();
        parseData();
        analyze();
        closeFile();
        sendReport();
    }

    // Steps with default implementation
    private void openFile(String path) {
        System.out.println("  Opening file: " + path);
    }

    private void closeFile() {
        System.out.println("  Closing file");
    }

    // Steps that vary — subclasses must implement
    protected abstract void extractData();
    protected abstract void parseData();

    // Hook — optional override
    protected void analyze() {
        System.out.println("  Running default analysis");
    }

    private void sendReport() {
        System.out.println("  Report sent");
    }
}

// Concrete subclass — PDF
class PDFDataMiner extends DataMiner {
    protected void extractData() {
        System.out.println("  Extracting text from PDF");
    }
    protected void parseData() {
        System.out.println("  Parsing PDF structure");
    }
}

// Concrete subclass — CSV
class CSVDataMiner extends DataMiner {
    protected void extractData() {
        System.out.println("  Reading CSV rows");
    }
    protected void parseData() {
        System.out.println("  Parsing comma-separated values");
    }
    @Override
    protected void analyze() {
        System.out.println("  Running CSV-specific statistical analysis");
    }
}

public class TemplateMethodDemo {
    public static void main(String[] args) {
        System.out.println("=== Template Method Pattern ===");
        System.out.println("-> Intent: Define algorithm skeleton, let subclasses fill steps");

        DataMiner pdfMiner = new PDFDataMiner();
        DataMiner csvMiner = new CSVDataMiner();

        System.out.println("--- Mining PDF ---");
        pdfMiner.mine("report.pdf");

        System.out.println("\n--- Mining CSV ---");
        csvMiner.mine("data.csv");

        System.out.println("\nAlgorithm structure is fixed. Steps vary by subclass.\n");
    }
}

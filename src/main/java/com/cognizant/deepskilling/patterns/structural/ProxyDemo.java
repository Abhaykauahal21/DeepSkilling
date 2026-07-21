package com.cognizant.deepskilling.patterns.structural;

// ============================================================
// Proxy Pattern
// Provides a surrogate or placeholder for another object to
// control access to it.
// Used in: Spring AOP (transaction/security proxies),
//          Hibernate lazy loading, Virtual proxies, Protection proxies
// ============================================================

// Subject interface
interface Image {
    void display();
}

// Real subject — expensive to create
class HighResolutionImage implements Image {
    private String filename;

    public HighResolutionImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("  [Loading] " + filename + " from disk... (expensive)");
    }

    public void display() {
        System.out.println("  [Display] Showing " + filename);
    }
}

// Proxy — controls access and adds lazy initialization
class ImageProxy implements Image {
    private final String filename;
    private HighResolutionImage realImage;

    public ImageProxy(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new HighResolutionImage(filename); // Lazy init
        }
        realImage.display();
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern ===");
        System.out.println("-> Intent: Control access to another object (lazy loading)");

        Image image1 = new ImageProxy("photo_1.jpg");
        Image image2 = new ImageProxy("photo_2.jpg");

        // Images not loaded yet — proxy holds references
        System.out.println("  Images created but NOT loaded yet...\n");

        // Only loaded when first accessed
        image1.display();
        System.out.println();

        // image2 still not loaded — on-demand only
        image2.display();

        System.out.println("\nProxy defers expensive creation until needed (lazy loading).\n");
    }
}

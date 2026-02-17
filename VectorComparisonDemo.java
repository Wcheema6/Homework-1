import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class VectorComparisonDemo {

    public static void run() {
        System.out.println("=== Vector vs ArrayList Comparison ===");

        int n = 10_000;
        int randomReads = 1_000;

        Vector<Product> vec = new Vector<>();
        ArrayList<Product> arr = new ArrayList<>();

        // Time add
        long t1 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            vec.add(new Product("VP" + i, "Prod" + i, "Category", i * 0.5, i % 50, "Supplier"));
        }
        long t2 = System.nanoTime();

        long t3 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arr.add(new Product("AP" + i, "Prod" + i, "Category", i * 0.5, i % 50, "Supplier"));
        }
        long t4 = System.nanoTime();

        // Time random access
        Random r = new Random(42);
        long t5 = System.nanoTime();
        double dummy1 = 0;
        for (int i = 0; i < randomReads; i++) {
            int idx = r.nextInt(n);
            dummy1 += vec.get(idx).getPrice();
        }
        long t6 = System.nanoTime();

        r = new Random(42);
        long t7 = System.nanoTime();
        double dummy2 = 0;
        for (int i = 0; i < randomReads; i++) {
            int idx = r.nextInt(n);
            dummy2 += arr.get(idx).getPrice();
        }
        long t8 = System.nanoTime();

        // Approx memory usage (VERY approximate)
        Runtime rt = Runtime.getRuntime();
        rt.gc();
        long before = rt.totalMemory() - rt.freeMemory();
        Vector<Product> vec2 = new Vector<>();
        for (int i = 0; i < n; i++) vec2.add(new Product("M" + i, "X", "C", 1.0, 1, "S"));
        rt.gc();
        long after = rt.totalMemory() - rt.freeMemory();
        long approxMemVec = after - before;

        System.out.println("Add 10,000 products:");
        System.out.printf("Vector:    %.2f ms%n", (t2 - t1) / 1_000_000.0);
        System.out.printf("ArrayList: %.2f ms%n", (t4 - t3) / 1_000_000.0);

        System.out.println("Access 1,000 random elements:");
        System.out.printf("Vector:    %.2f ms (dummy=%.2f)%n", (t6 - t5) / 1_000_000.0, dummy1);
        System.out.printf("ArrayList: %.2f ms (dummy=%.2f)%n", (t8 - t7) / 1_000_000.0, dummy2);

        System.out.println("Approx memory used (Vector extra test): " + approxMemVec + " bytes (very rough)");

        System.out.println("\nSummary:");
        System.out.println("- Vector is synchronized (thread-safe for individual method calls), so it can be a bit slower.");
        System.out.println("- ArrayList is not synchronized, usually faster in single-thread code.");
        System.out.println("- Choose Vector when you truly need the built-in synchronization (or legacy APIs).");
        System.out.println("- Choose ArrayList for most modern single-threaded or externally synchronized code.");
    }
}

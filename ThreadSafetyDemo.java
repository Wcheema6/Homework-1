import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ThreadSafetyDemo {

    public static void run() {
        System.out.println("=== Thread Safety Demo ===");

        final Vector<Product> sharedVector = new Vector<>();
        final ArrayList<Product> sharedArrayList = new ArrayList<>();

        int threads = 5;
        int addsPerThread = 2000;

        // Threads adding to Vector
        List<Thread> vecThreads = new ArrayList<>();
        long v1 = System.nanoTime();
        for (int t = 0; t < threads; t++) {
            int threadId = t;
            Thread th = new Thread(() -> {
                for (int i = 0; i < addsPerThread; i++) {
                    sharedVector.add(new Product("V-" + threadId + "-" + i, "X", "C", 1.0, 1, "S"));
                }
            });
            vecThreads.add(th);
            th.start();
        }
        joinAll(vecThreads);
        long v2 = System.nanoTime();

        // Threads adding to ArrayList (NOT thread-safe)
        List<Thread> arrThreads = new ArrayList<>();
        long a1 = System.nanoTime();
        for (int t = 0; t < threads; t++) {
            int threadId = t;
            Thread th = new Thread(() -> {
                for (int i = 0; i < addsPerThread; i++) {
                    // This may cause issues under concurrency (not guaranteed every run)
                    sharedArrayList.add(new Product("A-" + threadId + "-" + i, "X", "C", 1.0, 1, "S"));
                }
            });
            arrThreads.add(th);
            th.start();
        }
        joinAll(arrThreads);
        long a2 = System.nanoTime();

        int expected = threads * addsPerThread;

        System.out.println("Expected items: " + expected);
        System.out.println("Vector size: " + sharedVector.size());
        System.out.println("ArrayList size: " + sharedArrayList.size());

        System.out.printf("Vector add time: %.2f ms%n", (v2 - v1) / 1_000_000.0);
        System.out.printf("ArrayList add time: %.2f ms%n", (a2 - a1) / 1_000_000.0);

        System.out.println("\nReport:");
        System.out.println("- Vector synchronizes its methods, so single calls like add() are thread-safe.");
        System.out.println("- ArrayList is not synchronized, so concurrent modification can lead to wrong size or errors.");
        System.out.println("- Even with Vector, multi-step actions (check-then-act) may still need external locking.");
    }

    private static void joinAll(List<Thread> threads) {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

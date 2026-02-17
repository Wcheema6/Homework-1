import java.util.Enumeration;
import java.util.Vector;

public class ProductInventory {
    private final Vector<Product> products;

    public ProductInventory() {
        this.products = new Vector<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Cannot add null product.");
            return;
        }
        if (findProduct(product.getProductId()) != null) {
            System.out.println("Duplicate productId found. Not adding: " + product.getProductId());
            return;
        }
        products.add(product); // Vector.add
    }

    public boolean removeProduct(String productId) {
        Product p = findProduct(productId);
        if (p == null) return false;
        return products.remove(p); // Vector.remove(Object)
    }

    public Product findProduct(String productId) {
        if (productId == null) return null;
        for (int i = 0; i < products.size(); i++) { // size/get
            Product p = products.get(i);
            if (p.getProductId().equals(productId)) return p;
        }
        return null;
    }

    public Vector<Product> getProductsByCategory(String category) {
        Vector<Product> result = new Vector<>();
        if (category == null) return result;

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    public Vector<Product> getLowStockProducts(int threshold) {
        Vector<Product> result = new Vector<>();
        if (threshold < 0) threshold = 0;

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getQuantityInStock() < threshold) {
                result.add(p);
            }
        }
        return result;
    }

    public double getTotalInventoryValue() {
        double total = 0.0;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            total += p.getPrice() * p.getQuantityInStock();
        }
        return total;
    }

    public void updateStock(String productId, int quantityChange) {
        Product p = findProduct(productId);
        if (p == null) {
            System.out.println("Product not found: " + productId);
            return;
        }
        int newQty = p.getQuantityInStock() + quantityChange;
        if (newQty < 0) {
            System.out.println("Stock update would go negative. Update cancelled for " + productId);
            return;
        }
        p.setQuantityInStock(newQty);
    }

    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%-6s | %-20s | %-12s | %-10s | %-6s | %-15s%n",
                "ID", "Name", "Category", "Price", "Stock", "Supplier");
        System.out.println("-------------------------------------------------------------------------------------------");

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%-6s | %-20s | %-12s | $%-9.2f | %-6d | %-15s%n",
                    p.getProductId(),
                    trim(p.getName(), 20),
                    trim(p.getCategory(), 12),
                    p.getPrice(),
                    p.getQuantityInStock(),
                    trim(p.getSupplier(), 15));
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private String trim(String s, int max) {
        if (s == null) return "";
        if (s.length() <= max) return s;
        return s.substring(0, max - 3) + "...";
    }

    public int getTotalProducts() {
        return products.size();
    }

    public void printCapacityInfo() {
        System.out.println("Vector size: " + products.size());
        System.out.println("Vector capacity: " + products.capacity());
    }

    // Part 3.1 Capacity Management
    public void optimizeCapacity() {
        products.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < 0) minCapacity = 0;
        products.ensureCapacity(minCapacity);
    }

    public void printCapacityReport() {
        int size = products.size();
        int cap = products.capacity();

        double utilization = (cap == 0) ? 0.0 : (size * 100.0 / cap);
        int remaining = cap - size;

        System.out.println("=== Capacity Report ===");
        System.out.println("Current size: " + size);
        System.out.println("Current capacity: " + cap);
        System.out.printf("Capacity utilization: %.2f%%%n", utilization);
        System.out.println("Elements before resize (approx): " + remaining);
    }

    // Part 3.2 Enumeration Usage
    public void printProductsUsingEnumeration() {
        /*
         * Enumeration is a legacy way of iterating (older Java collections).
         * Today, you usually use Iterator or enhanced for-loop.
         * You might still see Enumeration in old code or APIs that still return it,
         * and Vector supports it via elements().
         */
        Enumeration<Product> en = products.elements();
        if (!en.hasMoreElements()) {
            System.out.println("Inventory is empty.");
            return;
        }
        while (en.hasMoreElements()) {
            System.out.println(en.nextElement());
        }
    }

    // Useful helper: return a COPY of products (safer than returning original)
    public Vector<Product> getAllProductsCopy() {
        return new Vector<>(products);
    }
}

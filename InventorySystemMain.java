import java.util.Vector;

public class InventorySystemMain {
    public static void main(String[] args) {
        // Part 1 demo
        ProductInventory inventory = new ProductInventory();

        inventory.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 10, "TechCorp"));
        inventory.addProduct(new Product("P002", "T-Shirt", "Clothing", 19.99, 50, "FashionInc"));
        inventory.addProduct(new Product("P003", "Mouse", "Electronics", 29.99, 5, "TechCorp"));

        inventory.printAllProducts();
        inventory.printCapacityInfo();

        Vector<Product> electronics = inventory.getProductsByCategory("Electronics");
        System.out.println("Electronics: " + electronics.size());

        Vector<Product> lowStock = inventory.getLowStockProducts(10);
        System.out.println("Low stock items: " + lowStock.size());

        System.out.printf("Total inventory value: $%.2f%n", inventory.getTotalInventoryValue());

        // Part 2 demo
        OrderManager orderManager = new OrderManager();

        Order order1 = new Order("O001", "Alice", "2024-01-15");
        order1.addItem(new OrderItem("P001", "Laptop", 1, 999.99));
        order1.addItem(new OrderItem("P003", "Mouse", 2, 29.99));
        orderManager.addOrder(order1);

        Order order2 = new Order("O002", "Bob", "2024-01-16");
        order2.addItem(new OrderItem("P002", "T-Shirt", 3, 19.99));
        orderManager.addOrder(order2);

        orderManager.printAllOrders();
        System.out.printf("Total revenue (Delivered only): $%.2f%n", orderManager.getTotalRevenue());

        // Mark one delivered so revenue changes
        order1.updateStatus("Delivered");
        System.out.printf("Total revenue (after delivery): $%.2f%n", orderManager.getTotalRevenue());

        // Part 3 capacity features
        inventory.printCapacityReport();
        inventory.ensureCapacity(100);
        inventory.printCapacityReport();
        inventory.optimizeCapacity();
        inventory.printCapacityReport();

        System.out.println("\nProducts using Enumeration:");
        inventory.printProductsUsingEnumeration();

        // Part 4 generics demo
        Vector<Integer> ints = new Vector<>();
        ints.add(10);
        ints.add(20);
        ints.add(30);
        System.out.println("\nSum: " + VectorUtils.sumNumbers(ints));
        System.out.println("Average: " + VectorUtils.averageNumbers(ints));
        System.out.println("Max int: " + VectorUtils.findMax(ints));

        Vector<Product> allProducts = inventory.getAllProductsCopy();
        Product maxProduct = VectorUtils.findMax(allProducts); // compares by price
        System.out.println("Most expensive product (by compareTo): " + maxProduct);

        Vector<Product> filtered = VectorUtils.filter(allProducts, p -> p.getCategory().equalsIgnoreCase("Electronics"));
        System.out.println("Filtered electronics count (generic filter): " + filtered.size());

        // GenericContainer demo
        GenericContainer<String> stringContainer = new GenericContainer<>();
        stringContainer.add("Hello");
        stringContainer.add("World");
        System.out.println("\nGenericContainer<String> size: " + stringContainer.size());

        GenericContainer<Product> productContainer = new GenericContainer<>();
        productContainer.add(new Product("P100", "Keyboard", "Electronics", 49.99, 20, "TechCorp"));
        System.out.println("GenericContainer<Product> contains P100? " +
                productContainer.contains(new Product("P100", "Anything", "X", 0, 0, "X")));

        // Part 3.3 comparison demo
        System.out.println();
        VectorComparisonDemo.run();

        // Bonus thread demo (optional to run)
        // ThreadSafetyDemo.run();
    }
}

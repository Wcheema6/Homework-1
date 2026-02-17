# Homework-1
Homework for CISC.

Inventory Management System (Vector + Generics)

Classes:
- Product: Represents a store product. Includes equals/hashCode by productId and Comparable by price.
- ProductInventory: Uses Vector<Product> to add/remove/find products, stock updates, reports, and Vector capacity tools.
- OrderItem: Represents one product line in an order.
- Order: Uses Vector<OrderItem> and supports add/remove/find item, totals, status, and printing.
- OrderManager: Uses Vector<Order> for finding orders, filtering by status/customer, and revenue totals.
- VectorUtils: Generic utility methods (swap, findMax, countMatches, filter) and bounded generics (sum/average for Numbers).
- GenericContainer<T>: Wrapper around Vector<T> with common operations.
- VectorComparisonDemo: Rough performance comparison between Vector and ArrayList.
- InventorySystemMain: Runs a full demo of the system.
- ThreadSafetyDemo (bonus): Demonstrates basic thread safety differences between Vector and ArrayList.

How to compile:
javac *.java

How to run:
java InventorySystemMain

Assumptions:
- orderDate is stored as a simple String "YYYY-MM-DD" (no Date parsing).
- Product compareTo compares by price then productId.

Challenges/learning:
- Vector is synchronized, so it’s safer for concurrent single method calls but can be slower than ArrayList.
- Capacity management: capacity() vs size(), trimToSize(), ensureCapacity().
- Generics allow type-safe reusable code (VectorUtils and GenericContainer).

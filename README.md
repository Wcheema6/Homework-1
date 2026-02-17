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

Solution:
-------------------------------------------------------------------------------------------
ID     | Name                 | Category     | Price      | Stock  | Supplier       
-------------------------------------------------------------------------------------------
P001   | Laptop               | Electronics  | $999.99    | 10     | TechCorp       
P002   | T-Shirt              | Clothing     | $19.99     | 50     | FashionInc     
P003   | Mouse                | Electronics  | $29.99     | 5      | TechCorp       
-------------------------------------------------------------------------------------------
Vector size: 3
Vector capacity: 10
Electronics: 2
Low stock items: 1
Total inventory value: $11149.35
====================================
Order ID: O001
Customer: Alice
Date: 2024-01-15
Status: Pending
------------------------------------
PID    | Name               | Qty   | Unit      | Subtotal 
------------------------------------
P001   | Laptop             | 1     | $999.99   | $999.99  
P003   | Mouse              | 2     | $29.99    | $59.98   
------------------------------------
Total: $1059.97
====================================
====================================
Order ID: O002
Customer: Bob
Date: 2024-01-16
Status: Pending
------------------------------------
PID    | Name               | Qty   | Unit      | Subtotal 
------------------------------------
P002   | T-Shirt            | 3     | $19.99    | $59.97   
------------------------------------
Total: $59.97
====================================
Total revenue (Delivered only): $0.00
Total revenue (after delivery): $1059.97
=== Capacity Report ===
Current size: 3
Current capacity: 10
Capacity utilization: 30.00%
Elements before resize (approx): 7
=== Capacity Report ===
Current size: 3
Current capacity: 100
Capacity utilization: 3.00%
Elements before resize (approx): 97
=== Capacity Report ===
Current size: 3
Current capacity: 3
Capacity utilization: 100.00%
Elements before resize (approx): 0

Products using Enumeration:
Product{id='P001', name='Laptop', category='Electronics', price=999.99, stock=10, supplier='TechCorp'}
Product{id='P002', name='T-Shirt', category='Clothing', price=19.99, stock=50, supplier='FashionInc'}
Product{id='P003', name='Mouse', category='Electronics', price=29.99, stock=5, supplier='TechCorp'}

Sum: 60.0
Average: 20.0
Max int: 30
Most expensive product (by compareTo): Product{id='P001', name='Laptop', category='Electronics', price=999.99, stock=10, supplier='TechCorp'}
Filtered electronics count (generic filter): 2

GenericContainer<String> size: 2
GenericContainer<Product> contains P100? true

=== Vector vs ArrayList Comparison ===
Add 10,000 products:
Vector:    19.45 ms
ArrayList: 9.19 ms
Access 1,000 random elements:
Vector:    0.48 ms (dummy=2426310.00)
ArrayList: 1.67 ms (dummy=2426310.00)
Approx memory used (Vector extra test): 920200 bytes (very rough)

Summary:
- Vector is synchronized (thread-safe for individual method calls), so it can be a bit slower.
- ArrayList is not synchronized, usually faster in single-thread code.
- Choose Vector when you truly need the built-in synchronization (or legacy APIs).
- Choose ArrayList for most modern single-threaded or externally synchronized code.


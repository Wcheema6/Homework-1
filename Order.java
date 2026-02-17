import java.util.Vector;

public class Order {
    private String orderId;
    private String customerName;
    private String orderDate; // "YYYY-MM-DD"
    private Vector<OrderItem> items;
    private String orderStatus; // Pending, Processing, Shipped, Delivered, Cancelled

    public Order(String orderId, String customerName, String orderDate) {
        if (orderId == null || orderId.trim().isEmpty()) throw new IllegalArgumentException("orderId required");
        if (customerName == null || customerName.trim().isEmpty()) throw new IllegalArgumentException("customerName required");
        if (orderDate == null || orderDate.trim().isEmpty()) throw new IllegalArgumentException("orderDate required");

        this.orderId = orderId.trim();
        this.customerName = customerName.trim();
        this.orderDate = orderDate.trim();
        this.items = new Vector<>();
        this.orderStatus = "Pending";
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getOrderDate() { return orderDate; }
    public String getOrderStatus() { return orderStatus; }

    public void addItem(OrderItem item) {
        if (item == null) {
            System.out.println("Cannot add null item.");
            return;
        }
        // If item already exists by productId, just increase qty
        OrderItem existing = findItem(item.getProductId());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
            existing.calculateSubtotal();
        } else {
            items.add(item);
        }
    }

    public boolean removeItem(String productId) {
        OrderItem it = findItem(productId);
        if (it == null) return false;
        return items.remove(it);
    }

    public OrderItem findItem(String productId) {
        if (productId == null) return null;
        for (int i = 0; i < items.size(); i++) {
            OrderItem it = items.get(i);
            if (it.getProductId().equals(productId)) return it;
        }
        return null;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).calculateSubtotal();
        }
        return total;
    }

    public int getTotalItems() {
        int qty = 0;
        for (int i = 0; i < items.size(); i++) {
            qty += items.get(i).getQuantity();
        }
        return qty;
    }

    public void updateStatus(String newStatus) {
        if (newStatus == null || newStatus.trim().isEmpty()) {
            System.out.println("Invalid status.");
            return;
        }
        this.orderStatus = newStatus.trim();
    }

    public void printOrder() {
        System.out.println("====================================");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Date: " + orderDate);
        System.out.println("Status: " + orderStatus);
        System.out.println("------------------------------------");
        if (items.isEmpty()) {
            System.out.println("(No items)");
        } else {
            System.out.printf("%-6s | %-18s | %-5s | %-9s | %-9s%n",
                    "PID", "Name", "Qty", "Unit", "Subtotal");
            System.out.println("------------------------------------");
            for (int i = 0; i < items.size(); i++) {
                OrderItem it = items.get(i);
                System.out.printf("%-6s | %-18s | %-5d | $%-8.2f | $%-8.2f%n",
                        it.getProductId(),
                        trim(it.getProductName(), 18),
                        it.getQuantity(),
                        it.getUnitPrice(),
                        it.getSubtotal());
            }
        }
        System.out.println("------------------------------------");
        System.out.printf("Total: $%.2f%n", calculateTotal());
        System.out.println("====================================");
    }

    private String trim(String s, int max) {
        if (s == null) return "";
        if (s.length() <= max) return s;
        return s.substring(0, max - 3) + "...";
    }

    public Vector<OrderItem> getItems() {
        return new Vector<>(items); // return COPY
    }
}

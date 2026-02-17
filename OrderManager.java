import java.util.Vector;

public class OrderManager {
    private final Vector<Order> orders;

    public OrderManager() {
        this.orders = new Vector<>();
    }

    public void addOrder(Order order) {
        if (order == null) {
            System.out.println("Cannot add null order.");
            return;
        }
        if (findOrder(order.getOrderId()) != null) {
            System.out.println("Duplicate orderId. Not adding: " + order.getOrderId());
            return;
        }
        orders.add(order);
    }

    public Order findOrder(String orderId) {
        if (orderId == null) return null;
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o.getOrderId().equals(orderId)) return o;
        }
        return null;
    }

    public Vector<Order> getOrdersByStatus(String status) {
        Vector<Order> result = new Vector<>();
        if (status == null) return result;

        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o.getOrderStatus().equalsIgnoreCase(status)) result.add(o);
        }
        return result;
    }

    public Vector<Order> getOrdersByCustomer(String customerName) {
        Vector<Order> result = new Vector<>();
        if (customerName == null) return result;

        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o.getCustomerName().equalsIgnoreCase(customerName)) result.add(o);
        }
        return result;
    }

    public double getTotalRevenue() {
        double total = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if ("Delivered".equalsIgnoreCase(o.getOrderStatus())) {
                total += o.calculateTotal();
            }
        }
        return total;
    }

    public void cancelOrder(String orderId) {
        Order o = findOrder(orderId);
        if (o == null) {
            System.out.println("Order not found: " + orderId);
            return;
        }
        o.updateStatus("Cancelled");
    }

    public void printAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).printOrder();
        }
    }

    public Vector<Order> getPendingOrders() {
        return getOrdersByStatus("Pending");
    }

    public int getOrderCount() {
        return orders.size();
    }

    public Vector<Order> getAllOrdersCopy() {
        return new Vector<>(orders);
    }
}

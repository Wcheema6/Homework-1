public class OrderItem {
    private String productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double subtotal;

    public OrderItem(String productId, String productName, int quantity, double unitPrice) {
        setProductId(productId);
        setProductName(productName);
        setQuantity(quantity);
        setUnitPrice(unitPrice);
        calculateSubtotal();
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) {
        if (productId == null || productId.trim().isEmpty()) throw new IllegalArgumentException("productId required");
        this.productId = productId.trim();
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) throw new IllegalArgumentException("productName required");
        this.productName = productName.trim();
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");
        this.quantity = quantity;
        calculateSubtotal();
    }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0) throw new IllegalArgumentException("unitPrice cannot be negative");
        this.unitPrice = unitPrice;
        calculateSubtotal();
    }

    public double getSubtotal() { return subtotal; }

    public double calculateSubtotal() {
        this.subtotal = this.quantity * this.unitPrice;
        return this.subtotal;
    }

    @Override
    public String toString() {
        return String.format("OrderItem{productId='%s', name='%s', qty=%d, unit=%.2f, subtotal=%.2f}",
                productId, productName, quantity, unitPrice, subtotal);
    }
}

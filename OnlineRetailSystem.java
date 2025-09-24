import java.time.LocalDate;

class Order {
    protected String orderId;
    protected LocalDate orderDate;
    protected double totalAmount;
    
    public Order(String orderId, LocalDate orderDate, double totalAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
    
    public String getOrderStatus() {
        return "Order Placed";
    }
    
    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Status: " + getOrderStatus());
    }
}

class ShippedOrder extends Order {
    protected String trackingNumber;
    protected LocalDate shippedDate;
    
    public ShippedOrder(String orderId, LocalDate orderDate, double totalAmount, 
                       String trackingNumber, LocalDate shippedDate) {
        super(orderId, orderDate, totalAmount);
        this.trackingNumber = trackingNumber;
        this.shippedDate = shippedDate;
    }
    
    @Override
    public String getOrderStatus() {
        return "Shipped";
    }
    
    @Override
    public void displayOrderDetails() {
        super.displayOrderDetails();
        System.out.println("Tracking Number: " + trackingNumber);
        System.out.println("Shipped Date: " + shippedDate);
    }
}

class DeliveredOrder extends ShippedOrder {
    private LocalDate deliveryDate;
    private String receivedBy;
    
    public DeliveredOrder(String orderId, LocalDate orderDate, double totalAmount,
                         String trackingNumber, LocalDate shippedDate,
                         LocalDate deliveryDate, String receivedBy) {
        super(orderId, orderDate, totalAmount, trackingNumber, shippedDate);
        this.deliveryDate = deliveryDate;
        this.receivedBy = receivedBy;
    }
    
    @Override
    public String getOrderStatus() {
        return "Delivered";
    }
    
    @Override
    public void displayOrderDetails() {
        super.displayOrderDetails();
        System.out.println("Delivery Date: " + deliveryDate);
        System.out.println("Received By: " + receivedBy);
    }
}

public class OnlineRetailSystem {
    public static void main(String[] args) {
        Order order1 = new Order("ORD001", LocalDate.of(2024, 1, 15), 149.99);
        ShippedOrder order2 = new ShippedOrder("ORD002", LocalDate.of(2024, 1, 10), 299.50,
                                             "TRK789456", LocalDate.of(2024, 1, 12));
        DeliveredOrder order3 = new DeliveredOrder("ORD003", LocalDate.of(2024, 1, 5), 89.99,
                                                 "TRK123456", LocalDate.of(2024, 1, 7),
                                                 LocalDate.of(2024, 1, 9), "John Smith");
        
        System.out.println("=== Order 1 (Placed) ===");
        order1.displayOrderDetails();
        System.out.println();
        
        System.out.println("=== Order 2 (Shipped) ===");
        order2.displayOrderDetails();
        System.out.println();
        
        System.out.println("=== Order 3 (Delivered) ===");
        order3.displayOrderDetails();
        System.out.println();
        
        System.out.println("=== Order Status Summary ===");
        System.out.println("Order " + order1.orderId + ": " + order1.getOrderStatus());
        System.out.println("Order " + order2.orderId + ": " + order2.getOrderStatus());
        System.out.println("Order " + order3.orderId + ": " + order3.getOrderStatus());
    }
}
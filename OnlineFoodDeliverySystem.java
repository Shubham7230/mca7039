import java.util.*;

abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemDetails() {
        return "Item: " + itemName + ", Price: " + price + ", Quantity: " + quantity;
    }

    public abstract double calculateTotalPrice();
}

interface Discountable {
    double applyDiscount(double amount);
    String getDiscountDetails();
}

class VegItem extends FoodItem implements Discountable {
    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }

    public double applyDiscount(double amount) {
        return amount * 0.9;
    }

    public String getDiscountDetails() {
        return "10% discount applied for Veg Item";
    }
}

class NonVegItem extends FoodItem implements Discountable {
    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    public double calculateTotalPrice() {
        double nonVegCharge = 20;
        return (getPrice() * getQuantity()) + nonVegCharge;
    }

    public double applyDiscount(double amount) {
        return amount * 0.85;
    }

    public String getDiscountDetails() {
        return "15% discount applied for Non-Veg Item";
    }
}

public class OnlineFoodDeliverySystem {
    public static void processOrder(List<FoodItem> items) {
        for (FoodItem item : items) {
            System.out.println(item.getItemDetails());
            double total = item.calculateTotalPrice();
            if (item instanceof Discountable) {
                Discountable d = (Discountable) item;
                double discounted = d.applyDiscount(total);
                System.out.println("Original Price: " + total);
                System.out.println("Final Price after Discount: " + discounted);
                System.out.println(d.getDiscountDetails());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<FoodItem> order = new ArrayList<>();
        order.add(new VegItem("Paneer Butter Masala", 250, 2));
        order.add(new NonVegItem("Chicken Biryani", 300, 1));

        processOrder(order);
    }
}

interface Worker {
    void performDuties();
    double calculateSalary();
}

class Person {
    protected String name;
    protected int id;
    protected String contactNumber;
    
    public Person(String name, int id, String contactNumber) {
        this.name = name;
        this.id = id;
        this.contactNumber = contactNumber;
    }
    
    public void displayBasicInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contactNumber);
    }
}

class Chef extends Person implements Worker {
    private String specialty;
    private String shift;
    private double hourlyRate;
    private int hoursWorked;
    
    public Chef(String name, int id, String contactNumber, String specialty, String shift, double hourlyRate) {
        super(name, id, contactNumber);
        this.specialty = specialty;
        this.shift = shift;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
    }
    
    @Override
    public void performDuties() {
        System.out.println(name + " is cooking " + specialty + " dishes during " + shift + " shift");
        hoursWorked += 8;
    }
    
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
    
    public void prepareDish(String dishName) {
        System.out.println("Chef " + name + " is preparing " + dishName);
    }
    
    public void displayChefInfo() {
        displayBasicInfo();
        System.out.println("Position: Chef");
        System.out.println("Specialty: " + specialty);
        System.out.println("Shift: " + shift);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Salary: $" + calculateSalary());
    }
}

class Waiter extends Person implements Worker {
    private int tablesAssigned;
    private String section;
    private double baseSalary;
    private double tips;
    
    public Waiter(String name, int id, String contactNumber, int tablesAssigned, String section, double baseSalary) {
        super(name, id, contactNumber);
        this.tablesAssigned = tablesAssigned;
        this.section = section;
        this.baseSalary = baseSalary;
        this.tips = 0;
    }
    
    @Override
    public void performDuties() {
        System.out.println(name + " is serving tables in " + section + " section (" + tablesAssigned + " tables)");
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary + tips;
    }
    
    public void takeOrder(String tableNumber, String order) {
        System.out.println("Waiter " + name + " took order from table " + tableNumber + ": " + order);
    }
    
    public void receiveTip(double amount) {
        tips += amount;
        System.out.println(name + " received a tip of $" + amount);
    }
    
    public void serveFood(String tableNumber, String dish) {
        System.out.println("Waiter " + name + " served " + dish + " to table " + tableNumber);
    }
    
    public void displayWaiterInfo() {
        displayBasicInfo();
        System.out.println("Position: Waiter");
        System.out.println("Section: " + section);
        System.out.println("Tables Assigned: " + tablesAssigned);
        System.out.println("Base Salary: $" + baseSalary);
        System.out.println("Tips: $" + tips);
        System.out.println("Total Salary: $" + calculateSalary());
    }
}

public class RestaurantManagementSystem {
    public static void main(String[] args) {
        Chef headChef = new Chef("Maria Rodriguez", 101, "555-0101", "Italian Cuisine", "Evening", 25.0);
        Waiter seniorWaiter = new Waiter("James Wilson", 201, "555-0102", 8, "Main Dining", 2000);
        
        System.out.println("=== RESTAURANT STAFF INFORMATION ===");
        System.out.println("\n--- HEAD CHEF ---");
        headChef.displayChefInfo();
        
        System.out.println("\n--- SENIOR WAITER ---");
        seniorWaiter.displayWaiterInfo();
        
        System.out.println("\n=== DAILY OPERATIONS ===");
        System.out.println("\nMorning Shift:");
        headChef.performDuties();
        headChef.prepareDish("Spaghetti Carbonara");
        
        seniorWaiter.performDuties();
        seniorWaiter.takeOrder("Table 5", "Margherita Pizza, Caesar Salad");
        seniorWaiter.serveFood("Table 5", "Margherita Pizza");
        seniorWaiter.receiveTip(15.50);
        
        System.out.println("\nEvening Shift:");
        headChef.performDuties();
        headChef.prepareDish("Lasagna");
        headChef.prepareDish("Tiramisu");
        
        seniorWaiter.performDuties();
        seniorWaiter.takeOrder("Table 3", "Risotto, Red Wine");
        seniorWaiter.serveFood("Table 3", "Risotto");
        seniorWaiter.receiveTip(25.00);
        
        System.out.println("\n=== END OF DAY SUMMARY ===");
        System.out.println("\nChef Summary:");
        headChef.displayChefInfo();
        
        System.out.println("\nWaiter Summary:");
        seniorWaiter.displayWaiterInfo();
        
        System.out.println("\n=== WORKER INTERFACE DEMONSTRATION ===");
        Worker[] restaurantWorkers = {headChef, seniorWaiter};
        
        for (Worker worker : restaurantWorkers) {
            if (worker instanceof Chef) {
                System.out.println("\nChef's Duties:");
            } else if (worker instanceof Waiter) {
                System.out.println("\nWaiter's Duties:");
            }
            worker.performDuties();
            System.out.println("Calculated Salary: $" + worker.calculateSalary());
        }
    }
}
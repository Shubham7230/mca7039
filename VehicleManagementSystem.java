interface Refuelable {
    void refuel(double amount);
    double getFuelLevel();
}

class Vehicle {
    protected String model;
    protected double maxSpeed;
    protected String manufacturer;
    
    public Vehicle(String model, double maxSpeed, String manufacturer) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.manufacturer = manufacturer;
    }
    
    public void start() {
        System.out.println(model + " is starting...");
    }
    
    public void stop() {
        System.out.println(model + " is stopping...");
    }
    
    public void displayInfo() {
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Model: " + model);
        System.out.println("Max Speed: " + maxSpeed + " km/h");
    }
}

class ElectricVehicle extends Vehicle {
    private double batteryCapacity;
    private double currentCharge;
    private double chargingRate;
    
    public ElectricVehicle(String model, double maxSpeed, String manufacturer, 
                          double batteryCapacity, double chargingRate) {
        super(model, maxSpeed, manufacturer);
        this.batteryCapacity = batteryCapacity;
        this.chargingRate = chargingRate;
        this.currentCharge = batteryCapacity;
    }
    
    public void charge(double hours) {
        double chargeAmount = hours * chargingRate;
        if (currentCharge + chargeAmount <= batteryCapacity) {
            currentCharge += chargeAmount;
            System.out.println(model + " charged for " + hours + " hours. Charge increased by " + chargeAmount + " kWh");
        } else {
            currentCharge = batteryCapacity;
            System.out.println(model + " fully charged!");
        }
        displayChargeLevel();
    }
    
    public void drive(double distance) {
        double energyUsed = distance * 0.2; // Simplified calculation
        if (energyUsed <= currentCharge) {
            currentCharge -= energyUsed;
            System.out.println(model + " drove " + distance + " km. Energy used: " + energyUsed + " kWh");
        } else {
            System.out.println("Insufficient charge! Please charge the vehicle.");
        }
        displayChargeLevel();
    }
    
    public void displayChargeLevel() {
        double chargePercentage = (currentCharge / batteryCapacity) * 100;
        System.out.println("Battery Level: " + String.format("%.1f", chargePercentage) + "%");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Electric Vehicle");
        System.out.println("Battery Capacity: " + batteryCapacity + " kWh");
        System.out.println("Charging Rate: " + chargingRate + " kWh/hour");
        displayChargeLevel();
    }
}

class PetrolVehicle extends Vehicle implements Refuelable {
    private double fuelCapacity;
    private double currentFuel;
    private double fuelEfficiency; // km per liter
    
    public PetrolVehicle(String model, double maxSpeed, String manufacturer, 
                        double fuelCapacity, double fuelEfficiency) {
        super(model, maxSpeed, manufacturer);
        this.fuelCapacity = fuelCapacity;
        this.fuelEfficiency = fuelEfficiency;
        this.currentFuel = fuelCapacity;
    }
    
    @Override
    public void refuel(double amount) {
        if (currentFuel + amount <= fuelCapacity) {
            currentFuel += amount;
            System.out.println(model + " refueled with " + amount + " liters");
        } else {
            currentFuel = fuelCapacity;
            System.out.println(model + " tank is now full!");
        }
        displayFuelLevel();
    }
    
    @Override
    public double getFuelLevel() {
        return currentFuel;
    }
    
    public void drive(double distance) {
        double fuelUsed = distance / fuelEfficiency;
        if (fuelUsed <= currentFuel) {
            currentFuel -= fuelUsed;
            System.out.println(model + " drove " + distance + " km. Fuel used: " + String.format("%.1f", fuelUsed) + " liters");
        } else {
            System.out.println("Insufficient fuel! Please refuel the vehicle.");
        }
        displayFuelLevel();
    }
    
    public void displayFuelLevel() {
        double fuelPercentage = (currentFuel / fuelCapacity) * 100;
        System.out.println("Fuel Level: " + String.format("%.1f", fuelPercentage) + "%");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Petrol Vehicle");
        System.out.println("Fuel Capacity: " + fuelCapacity + " liters");
        System.out.println("Fuel Efficiency: " + fuelEfficiency + " km/l");
        displayFuelLevel();
    }
}

public class VehicleManagementSystem {
    public static void main(String[] args) {
        ElectricVehicle tesla = new ElectricVehicle("Model S", 250, "Tesla", 100, 10);
        PetrolVehicle toyota = new PetrolVehicle("Camry", 180, "Toyota", 60, 15);
        
        System.out.println("=== VEHICLE INFORMATION ===");
        System.out.println("\n--- ELECTRIC VEHICLE ---");
        tesla.displayInfo();
        
        System.out.println("\n--- PETROL VEHICLE ---");
        toyota.displayInfo();
        
        System.out.println("\n=== DAILY OPERATIONS ===");
        System.out.println("\nMorning Commute:");
        tesla.start();
        tesla.drive(50);
        
        toyota.start();
        toyota.drive(50);
        
        System.out.println("\nMid-day Activities:");
        tesla.drive(30);
        toyota.drive(40);
        
        System.out.println("\nEvening Refueling/Charging:");
        tesla.charge(2);
        toyota.refuel(20);
        
        System.out.println("\nNight Drive:");
        tesla.drive(40);
        toyota.drive(60);
        
        System.out.println("\n=== END OF DAY STATUS ===");
        System.out.println("\nElectric Vehicle Status:");
        tesla.displayInfo();
        
        System.out.println("\nPetrol Vehicle Status:");
        toyota.displayInfo();
        
        System.out.println("\n=== REFUELABLE INTERFACE DEMONSTRATION ===");
        Refuelable refuelableVehicle = toyota;
        System.out.println("Current fuel level: " + refuelableVehicle.getFuelLevel() + " liters");
        refuelableVehicle.refuel(10);
        
        System.out.println("\n=== VEHICLE ARRAY DEMONSTRATION ===");
        Vehicle[] vehicles = {tesla, toyota};
        
        for (Vehicle vehicle : vehicles) {
            System.out.println("\n--- Operating " + vehicle.model + " ---");
            vehicle.start();
            
            if (vehicle instanceof ElectricVehicle) {
                ((ElectricVehicle) vehicle).drive(20);
            } else if (vehicle instanceof PetrolVehicle) {
                ((PetrolVehicle) vehicle).drive(20);
            }
            
            vehicle.stop();
        }
    }
}
interface GPS {
    String getCurrentLocation();
    void updateLocation(String newLocation);
}

abstract class Vehicle implements GPS {
    private String vehicleId;
    private String driverName;
    protected double ratePerKm;

    public Vehicle(String vehicleId, String driverName, double ratePerKm) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public abstract double calculateFare(double distance);

    public String getVehicleDetails() {
        return "Vehicle ID: " + vehicleId + ", Driver: " + driverName + ", Rate: " + ratePerKm + "/km";
    }

    public String getCurrentLocation() {
        return "Current location not specified";
    }

    public void updateLocation(String newLocation) {
    }
}

class Car extends Vehicle {
    public Car(String vehicleId, String driverName) {
        super(vehicleId, driverName, 10.0);
    }

    public double calculateFare(double distance) {
        return distance * getRatePerKm();
    }

    public String getCurrentLocation() {
        return "Car at urban area";
    }

    public void updateLocation(String newLocation) {
        System.out.println("Car location updated to: " + newLocation);
    }
}

class Bike extends Vehicle {
    public Bike(String vehicleId, String driverName) {
        super(vehicleId, driverName, 5.0);
    }

    public double calculateFare(double distance) {
        return distance * getRatePerKm() + 2.0;
    }

    public String getCurrentLocation() {
        return "Bike at city streets";
    }

    public void updateLocation(String newLocation) {
        System.out.println("Bike location updated to: " + newLocation);
    }
}

class Auto extends Vehicle {
    public Auto(String vehicleId, String driverName) {
        super(vehicleId, driverName, 7.0);
    }

    public double calculateFare(double distance) {
        return distance * getRatePerKm() + 5.0;
    }

    public String getCurrentLocation() {
        return "Auto at downtown";
    }

    public void updateLocation(String newLocation) {
        System.out.println("Auto location updated to: " + newLocation);
    }
}

public class RideHailingApp {
    public static double calculateFareDynamically(Vehicle vehicle, double distance) {
        return vehicle.calculateFare(distance);
    }

    public static void main(String[] args) {
        Car car = new Car("C001", "John Doe");
        Bike bike = new Bike("B001", "Jane Smith");
        Auto auto = new Auto("A001", "Bob Wilson");

        System.out.println(car.getVehicleDetails());
        System.out.println("Car fare for 10 km: " + calculateFareDynamically(car, 10.0));

        System.out.println(bike.getVehicleDetails());
        System.out.println("Bike fare for 10 km: " + calculateFareDynamically(bike, 10.0));

        System.out.println(auto.getVehicleDetails());
        System.out.println("Auto fare for 10 km: " + calculateFareDynamically(auto, 10.0));

        car.updateLocation("New York");
        System.out.println(car.getCurrentLocation());
    }
}

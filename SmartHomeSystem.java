class Device {
    protected String deviceId;
    protected boolean status;
    
    public Device(String deviceId, boolean status) {
        this.deviceId = deviceId;
        this.status = status;
    }
    
    public void displayStatus() {
        System.out.println("Device ID: " + deviceId);
        System.out.println("Status: " + (status ? "ON" : "OFF"));
    }
    
    public void turnOn() {
        this.status = true;
        System.out.println(deviceId + " turned ON");
    }
    
    public void turnOff() {
        this.status = false;
        System.out.println(deviceId + " turned OFF");
    }
}

class Thermostat extends Device {
    private double temperatureSetting;
    private double currentTemperature;
    
    public Thermostat(String deviceId, boolean status, double temperatureSetting, double currentTemperature) {
        super(deviceId, status);
        this.temperatureSetting = temperatureSetting;
        this.currentTemperature = currentTemperature;
    }
    
    @Override
    public void displayStatus() {
        super.displayStatus();
        if (status) {
            System.out.println("Current Temperature: " + currentTemperature + "°C");
            System.out.println("Temperature Setting: " + temperatureSetting + "°C");
        }
    }
    
    public void setTemperature(double temperature) {
        this.temperatureSetting = temperature;
        System.out.println(deviceId + " temperature set to " + temperature + "°C");
    }
    
    public void updateCurrentTemperature(double temperature) {
        this.currentTemperature = temperature;
        System.out.println(deviceId + " current temperature updated to " + temperature + "°C");
    }
}

public class SmartHomeSystem{
    public static void main(String[] args) {
        Device light = new Device("Living Room Light", false);
        Device speaker = new Device("Kitchen Speaker", true);
        
        Thermostat livingRoomThermostat = new Thermostat("Living Room Thermostat", true, 22.5, 21.8);
        Thermostat bedroomThermostat = new Thermostat("Bedroom Thermostat", false, 20.0, 19.5);
        
        System.out.println("=== Basic Devices ===");
        light.displayStatus();
        System.out.println();
        
        speaker.displayStatus();
        System.out.println();
        
        System.out.println("=== Thermostats ===");
        livingRoomThermostat.displayStatus();
        System.out.println();
        
        bedroomThermostat.displayStatus();
        System.out.println();
        
        System.out.println("=== Testing Device Controls ===");
        light.turnOn();
        bedroomThermostat.turnOn();
        System.out.println();
        
        livingRoomThermostat.setTemperature(23.0);
        bedroomThermostat.updateCurrentTemperature(20.2);
        System.out.println();
        
        System.out.println("=== Updated Status ===");
        light.displayStatus();
        System.out.println();
        
        bedroomThermostat.displayStatus();
    }
}
// VRMS stands for Vehicle Rental Management System

import java.util.Scanner;

abstract class Vehicle {
    protected String vehicleId;
    protected String model;
    protected double baseRentalRate;
    protected boolean isAvailable = true;

    public Vehicle(String vehicleId, String model, double baseRentalRate) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
    }

    public abstract double calculateRentalCost(int days);

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rentVehicle() {
        isAvailable = false;
    }

    public void returnVehicle() {
        isAvailable = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }
}

class Car extends Vehicle {

    public Car(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
    }

    @Override
    public double calculateRentalCost(int days) {
        return baseRentalRate * days;
    }
}

interface Rentable {
    void rent(Customer customer, int days);
    void returnVehicle();
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class RentalTransaction {
    private Vehicle vehicle;
    private Customer customer;
    private int days;

    public RentalTransaction(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public void processRental() {
        if (vehicle.isAvailable()) {
            vehicle.rentVehicle();
            double cost = vehicle.calculateRentalCost(days);
            System.out.println("Rental successful!");
            System.out.println("Customer: " + customer.getName());
            System.out.println("Vehicle: " + vehicle.getModel());
            System.out.println("Total Cost: " + cost);
        } else {
            System.out.println("Vehicle is not available.");
        }
    }

    public void processReturn() {
        vehicle.returnVehicle();
        System.out.println("Vehicle returned successfully.");
    }
}

public class VRMS {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter vehicle ID: ");
        String vehicleId = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        System.out.print("Enter base rental rate: ");
        double rate = scanner.nextDouble();
        scanner.nextLine(); // clear buffer

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter number of rental days: ");
        int days = scanner.nextInt();

        Car car = new Car(vehicleId, model, rate);
        Customer customer = new Customer(customerName);

        RentalTransaction transaction = new RentalTransaction(car, customer, days);
        transaction.processRental();

        scanner.close();
    }
}

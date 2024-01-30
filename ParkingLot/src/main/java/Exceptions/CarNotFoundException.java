package Exceptions;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException() {
        super("Car not found");
    }
}

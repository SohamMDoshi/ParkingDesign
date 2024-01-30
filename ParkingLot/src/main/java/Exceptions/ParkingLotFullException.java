package Exceptions;

public class ParkingLotFullException extends RuntimeException{
    public ParkingLotFullException () {
        super("ParkingLot is full");
    }
}

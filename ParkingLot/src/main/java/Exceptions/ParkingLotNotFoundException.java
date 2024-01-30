package Exceptions;

public class ParkingLotNotFoundException extends RuntimeException{

    public ParkingLotNotFoundException () {
        super("Parking Lot not found");
    }
}

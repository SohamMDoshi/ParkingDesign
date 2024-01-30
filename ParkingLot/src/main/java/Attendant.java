import Exceptions.CarNotFoundException;
import Exceptions.ParkingLotFullException;
import Exceptions.ParkingLotNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class Attendant {

    private String name;
    private Set<ParkingLot> assignedParkingLots;

    public Attendant(String name) {
        this.name = name;
        this.assignedParkingLots = new HashSet<>();
    }

    void assignParkingLot(ParkingLot parkingLot) {
        assignedParkingLots.add(parkingLot);
    }

    boolean isParkingLotAssigned(ParkingLot parkingLot) {
        return assignedParkingLots.contains(parkingLot);
    }

    public Ticket park (ParkingLot parkingLot, Car car) {
        try {
            if (isParkingLotAssigned(parkingLot)) return parkingLot.park(car);
        }catch (ParkingLotFullException e) {
            parkingLotsFullNotification();
            System.out.println(e.getMessage());
        }
        throw new  ParkingLotNotFoundException();
    }

    public Car unpark (ParkingLot parkingLot,Ticket ticket) {
        try {
            if (isParkingLotAssigned(parkingLot)) {
                if(parkingLot.isParkingLotFull()) {
                    parkingLotsAvailableNotification();
                }
                return parkingLot.unPark(ticket);
            }
        }catch (CarNotFoundException e) {
            System.out.println(e.getMessage());
        }
        throw new  ParkingLotNotFoundException();
    }

    public String parkingLotsFullNotification () {
        return "All Parking Lots are currently full";
    }

    public String parkingLotsAvailableNotification() {
        return "Parking lots are available";
    }


}

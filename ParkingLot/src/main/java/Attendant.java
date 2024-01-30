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
        if (isParkingLotAssigned(parkingLot)) return parkingLot.park(car);
        else throw new  ParkingLotNotFoundException();
    }

    public Car unpark (ParkingLot parkingLot,Ticket ticket) {
        if (isParkingLotAssigned(parkingLot)) return parkingLot.unPark(ticket);
        else throw new  ParkingLotNotFoundException();
    }

}

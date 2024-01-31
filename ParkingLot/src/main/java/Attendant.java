import Exceptions.CarNotFoundException;
import Exceptions.ParkingLotFullException;
import Exceptions.ParkingLotNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class Attendant {

    private String name;
    private Set<ParkingArea> assignedParkingArea;

    private ParkingStrategy strategy;

    public Attendant(String name) {
        this.name = name;
        this.assignedParkingArea = new HashSet<>();
        this.strategy = new NearestParkingStrategy();
    }

    void setStrategy(ParkingStrategy parkingStrategy) {
        this.strategy = parkingStrategy;
    }

    void assignParkingLot(ParkingLot parkingLot) {
        assignedParkingArea.add(parkingLot);
    }

    boolean isParkingLotAssigned(ParkingLot parkingLot) {
        return assignedParkingArea.contains(parkingLot);
    }

    boolean isParkingLotFull(ParkingLot parkingLot) {
        return parkingLot.isFull();
    }

    public Ticket park (ParkingLot parkingLot, Car car) {
        try {
            if (isParkingLotAssigned(parkingLot) && !isParkingLotFull(parkingLot)) return strategy.park(car,parkingLot);
        }catch (ParkingLotFullException e) {
            parkingLotsFullNotification();
            System.out.println(e.getMessage());
        }
        throw new  ParkingLotNotFoundException();
    }

    public Car unpark (ParkingLot parkingLot,Ticket ticket) {
        try {
            if (isParkingLotAssigned(parkingLot)) {
                if(parkingLot.isFull()) {
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
        return "All parking lots are currently full";
    }

    public String parkingLotsAvailableNotification() {
        return "Parking lots are available";
    }


}

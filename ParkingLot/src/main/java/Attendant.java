import Exceptions.CarNotFoundException;
import Exceptions.ParkingLotFullException;
import Exceptions.ParkingLotNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class Attendant implements ParkingLotSubscriber{

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
        Ticket ticket;
        try {
            if (isParkingLotAssigned(parkingLot) && !isParkingLotFull(parkingLot)) {
                ticket = strategy.park(car,parkingLot);
                if (parkingLot.isFull()) NotificationBus.instance().publish(this,ParkingLotEvent.FULL);
                return ticket;
            }
        }catch (ParkingLotFullException e) {
            System.out.println(e.getMessage());
        }
        if (parkingLot.isFull()) NotificationBus.instance().publish(this,ParkingLotEvent.FULL);
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

    public void parkingLotsFullNotification () {
        System.out.println("All parking lots are currently full");
    }

    public String parkingLotsAvailableNotification() {
        return "Parking lots are available";
    }
}

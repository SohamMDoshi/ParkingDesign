import Exceptions.CarNotFoundException;
import Exceptions.ParkingLotFullException;

public class ParkingLot {
    private ParkingFloor[] parkingFloors;

    ParkingLot (int numberOfParkingFloors) {
        if (numberOfParkingFloors <= 0) throw new IllegalArgumentException("Number of parking floor cannot be negative or 0");
        else this.parkingFloors = new ParkingFloor[numberOfParkingFloors];
    }

    void addParkingFloor (int floor, ParkingFloor parkingFloor) {
        if(floor >= parkingFloors.length) throw  new IllegalArgumentException("Cannot add more floor than capacity");
        parkingFloors[floor]=parkingFloor;
    }

    boolean isParkingLotFull () {
        for (int i = 0; i < parkingFloors.length; i++) {
            if(!parkingFloors[i].isParkingFloorFull()) return false;
        }
        return true;
    }


    ParkingFloor[] getParkingFloors() {
        return parkingFloors;
    }

    Ticket park(Car car) {
        for (int i = 0; i < parkingFloors.length; i++) {
            try {
                return parkingFloors[i].park(car);
            }catch (ParkingLotFullException e) {
                System.out.println("Parking floor is full. Trying the next floor");
            }
        }
        throw new ParkingLotFullException();
    }

    Car unPark(Ticket ticket) {
        try {
            return parkingFloors[ticket.getParkingFloor()].unpark(ticket.getParkingSlot(),ticket.getRegistrationNumber());
        }catch (CarNotFoundException e) {
            System.out.println(e.getMessage());
        }
        throw new CarNotFoundException();
    }
}

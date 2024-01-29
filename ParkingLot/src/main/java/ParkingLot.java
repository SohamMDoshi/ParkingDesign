public class ParkingLot {
    private ParkingFloor[] parkingFloors;

    public ParkingLot (int numberOfParkingFloors) {
        if (numberOfParkingFloors <= 0) throw new IllegalArgumentException("Number of parking floor cannot be negative or 0");
        else this.parkingFloors = new ParkingFloor[numberOfParkingFloors];
    }

    public void addParkingFloor (int floor, ParkingFloor parkingFloor) {
        if(floor >= parkingFloors.length) throw  new IllegalArgumentException("Cannot add more floor than capacity");
        parkingFloors[floor]=parkingFloor;
    }

    public ParkingFloor[] getParkingFloors() {
        return parkingFloors;
    }

    public Ticket park(Car car) {
        for (int i = 0; i < parkingFloors.length; i++) {
            try {
                int slotNumber = parkingFloors[i].park(car);
                return new Ticket(i,slotNumber, car.getRegistrationNumber());
            }catch (ParkingLotFullException e) {
                System.out.println("Parking floor is full. Trying the next floor");
            }
        }
        throw new ParkingLotFullException();
    }

    public Car unPark(Ticket ticket) {
        try {
            Car car = parkingFloors[ticket.getParkingFloor()].unpark(ticket.getParkingSlot(),ticket.getRegistrationNumber());
            return car;
        }catch (CarNotFoundException e) {
            System.out.println(e.getMessage());
        }
        throw new CarNotFoundException();
    }
}

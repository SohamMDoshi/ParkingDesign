public class DistributedParkingStrategy implements ParkingStrategy {

    @Override
    public Ticket park(Car car, ParkingLot parkingLot) {
        ParkingFloor floor = parkingLot.getCurrentFloor();
        return floor.park(car,floor.nearestSlotAvailable());
    }
}

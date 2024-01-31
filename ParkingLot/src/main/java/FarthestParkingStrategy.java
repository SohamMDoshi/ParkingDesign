public class FarthestParkingStrategy implements ParkingStrategy {
    @Override
    public Ticket park(Car car, ParkingLot parkingLot) {
        ParkingFloor floor = parkingLot.getFarthestAvailableFloor();
        return floor.park(car,floor.farthestSlotAvailable());
    }
}

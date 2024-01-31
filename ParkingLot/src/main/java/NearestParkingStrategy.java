public class NearestParkingStrategy implements ParkingStrategy{
    @Override
    public Ticket park(Car car, ParkingLot parkingLot) {
        ParkingFloor floor = parkingLot.getNearestAvailableFloor();
        return floor.park(car,floor.nearestSlotAvailable());
    }
}

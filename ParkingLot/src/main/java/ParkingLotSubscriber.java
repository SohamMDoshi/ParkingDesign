public interface ParkingLotSubscriber {
    default void notify(ParkingLotEvent event, Object publisher) {
        System.out.println(event.toString() + " " + publisher.toString());
    }
}

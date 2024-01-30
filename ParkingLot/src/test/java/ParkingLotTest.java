import Exceptions.CarNotFoundException;
import Exceptions.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void testCreatingNewParkingLotWithNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(-4));
    }

    @Test
    public void testAddingParkingFloorsToParkingLot() {
        ParkingLot parkingLot = new ParkingLot(2);
        Slot[] parkingFloorOneSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL),new Slot(SlotStatus.FULL)
        };
        Slot[] parkingFloorTwoSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloorOne = new ParkingFloor(1,parkingFloorOneSlots);
        ParkingFloor parkingFloorTwo = new ParkingFloor(2,parkingFloorTwoSlots);
        parkingLot.addParkingFloor(0,parkingFloorOne);
        parkingLot.addParkingFloor(1,parkingFloorTwo);
        ParkingFloor[] parkingFloors = parkingLot.getParkingFloors();
        assertNotNull(parkingFloors[0]);
    }

    @Test
    public void testAddingParkingFloorsToParkingLotMoreThanCapacity() {
        ParkingLot parkingLot = new ParkingLot(2);
        Slot[] parkingFloorOneSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL),new Slot(SlotStatus.FULL)
        };
        Slot[] parkingFloorTwoSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloorOne = new ParkingFloor(1,parkingFloorOneSlots);
        ParkingFloor parkingFloorTwo = new ParkingFloor(2,parkingFloorTwoSlots);
        parkingLot.addParkingFloor(0,parkingFloorOne);
        parkingLot.addParkingFloor(1,parkingFloorTwo);

        assertThrows(IllegalArgumentException.class, ()-> {
            parkingLot.addParkingFloor(2,parkingFloorOne);
        });
    }

    @Test
    public void testParkingCarWhenFirstFloorIsFullAndSecondIsEmpty() {
        ParkingLot parkingLot = new ParkingLot(2);
        Slot[] parkingFloorOneSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL),new Slot(SlotStatus.FULL)
        };
        Slot[] parkingFloorTwoSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloorOne = new ParkingFloor(1,parkingFloorOneSlots);
        ParkingFloor parkingFloorTwo = new ParkingFloor(2,parkingFloorTwoSlots);
        parkingLot.addParkingFloor(0,parkingFloorOne);
        parkingLot.addParkingFloor(1,parkingFloorTwo);

        Car car = new Car("TL23AH7007", "Black");
        Ticket ticket = parkingLot.park(car);
        Ticket expectedTicket = new Ticket(1,1,"TL23AH7007");
        assertEquals(expectedTicket,ticket);
    }

    @Test
    public void testParkingCarWhenAllFloorIsFull_ExpectException() {
        ParkingLot parkingLot = new ParkingLot(2);
        Slot[] parkingFloorOneSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL)
        };
        Slot[] parkingFloorTwoSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL)
        };
        ParkingFloor parkingFloorOne = new ParkingFloor(1, parkingFloorOneSlots);
        ParkingFloor parkingFloorTwo = new ParkingFloor(2, parkingFloorTwoSlots);
        parkingLot.addParkingFloor(0, parkingFloorOne);
        parkingLot.addParkingFloor(1, parkingFloorTwo);

        Car car = new Car("TL23AH7007", "Black");
        assertThrows(ParkingLotFullException.class, ()-> {
            parkingLot.park(car);
        });
    }

    @Test
    public void testUnparkingCarWithValidTicket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Slot[] parkingFloorOneSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY)
        };
        Slot[] parkingFloorTwoSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY), new Slot(SlotStatus.FULL)
        };
        ParkingFloor parkingFloorOne = new ParkingFloor(0, parkingFloorOneSlots);
        ParkingFloor parkingFloorTwo = new ParkingFloor(1, parkingFloorTwoSlots);
        parkingLot.addParkingFloor(0, parkingFloorOne);
        parkingLot.addParkingFloor(1, parkingFloorTwo);

        Car parkedCar = new Car("TL23AH7007", "Black");
        Ticket ticket = parkingLot.park(parkedCar);
        Car expectedCar = parkingLot.unPark(ticket);
        assertEquals(expectedCar,parkedCar);
    }

    @Test
    public void testUnparkingCarWithInvalidTicket() {
        ParkingLot parkingLot = new ParkingLot(2);
        Slot[] parkingFloorOneSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY)
        };
        Slot[] parkingFloorTwoSlots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY), new Slot(SlotStatus.FULL)
        };
        ParkingFloor parkingFloorOne = new ParkingFloor(1, parkingFloorOneSlots);
        ParkingFloor parkingFloorTwo = new ParkingFloor(2, parkingFloorTwoSlots);
        parkingLot.addParkingFloor(0, parkingFloorOne);
        parkingLot.addParkingFloor(1, parkingFloorTwo);

        Car parkedCar = new Car("TL23AH7007", "Black");
        parkingLot.park(parkedCar);
        Ticket ticket = new Ticket(1,2,"ZZ55DD0000");
        assertThrows(CarNotFoundException.class, () -> {
            parkingLot.unPark(ticket);
        });
    }
}

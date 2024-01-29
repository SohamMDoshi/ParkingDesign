import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingFloorTest {

    @Test
    public void testCreateParkingLotWithNoSlots () {
        Slot[] slots = {};
        assertThrows(IllegalArgumentException.class, () -> new ParkingFloor(2,slots));
    }

    @Test
    public void testNewParkingLotWithSl0tsArray() {
        Slot[] slot = {
                new Slot(SlotStatus.EMPTY), new Slot(SlotStatus.FULL),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloor = new ParkingFloor(1,slot);
        assertNotNull(parkingFloor);
    }

    @Test
    public void testCarParkingOnEmptySlot() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Slot[] slot = {
                new Slot(SlotStatus.EMPTY), new Slot(SlotStatus.FULL),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloor = new ParkingFloor(1,slot);
        Car car = new Car("MH12AR3434","black");
        int slotNumber = parkingFloor.park(car);
        assertEquals(0,slotNumber);
    }

    @Test
    public void testIsCarPresentWithValidRegistrationNumber() {
        Slot[] slot = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloor = new ParkingFloor(1,slot);
        Car car = new Car("MH12AR3434","black");
        parkingFloor.park(car);
        assertTrue(parkingFloor.isCarPresent("MH12AR3434"));
    }

    @Test
    public void testIsCarPresentWithInvalid() {
        Slot[] slot = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloor = new ParkingFloor(1,slot);
        Car car = new Car("MH12AR3434","black");
        parkingFloor.park(car);
        assertFalse(parkingFloor.isCarPresent("JK23YY5555"));
    }

    @Test
    public void testCarUnparkingCarFromSlotWithValidCar_ExpectedTrue() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Slot[] slot = {
                new Slot(SlotStatus.EMPTY), new Slot(SlotStatus.FULL),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloor = new ParkingFloor(1,slot);
        Car parkedCar = new Car("MH12AR3434","black");
        int slotNumber = parkingFloor.park(parkedCar);
        Car unParkedCar = parkingFloor.unpark(slotNumber,"MH12AR3434");

        assertEquals(parkedCar,unParkedCar);
    }

    @Test
    public void testCarUnparkingCarFromSlotWithInvalidCarNumber_ExpectedException() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Slot[] slots = {
                new Slot(SlotStatus.EMPTY), new Slot(SlotStatus.FULL),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloor = new ParkingFloor(1,slots);
        Car car = new Car("MH12AR3434","black");
        int slotNumber = parkingFloor.park(car);
        assertThrows(CarNotFoundException.class, () -> {
            parkingFloor.unpark(slotNumber,"KR00ZM4334");
        });
    }

    @Test
    public void testParkingCarsOnNearestSlot () {
        Slot[] slots = {
                new Slot(SlotStatus.EMPTY), new Slot(SlotStatus.FULL),new Slot(SlotStatus.EMPTY)
        };
        ParkingFloor parkingFloor = new ParkingFloor(1,slots);
        Car car1 = new Car("JK07AA1111","Red");
        Car car2 = new Car("WB01KK7777", "White");
        int car1SlotNumber = parkingFloor.park(car1);
        int car2SlotNumber = parkingFloor.park(car2);
        assertEquals(0,car1SlotNumber);
        assertEquals(2,car2SlotNumber);
    }

    @Test
    public void testParkingCarsIfNoSlotsAreEmpty_ExpectsException() {
        Slot[] slots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY),new Slot(SlotStatus.FULL)
        };
        ParkingFloor parkingFloor = new ParkingFloor(1,slots);
        Car car1 = new Car("JK07AA1111","Red");
        Car car2 = new Car("WB01KK7777", "White");
        int car1SlotNumber = parkingFloor.park(car1);
        assertEquals(1,car1SlotNumber);
        assertThrows(ParkingLotFullException.class, () -> {
            parkingFloor.park(car2);
        });
    }
}

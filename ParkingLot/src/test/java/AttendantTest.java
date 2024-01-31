import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AttendantTest {

    @Test
    public void testCreateAttendantAndAssignParkingLot() {
        Attendant attendant = new Attendant("ABD");
        Slot[] slots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL),new Slot(SlotStatus.FULL)
        };
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingFloor parkingFloor = new ParkingFloor(1,slots);
        parkingLot.addParkingFloor(0,parkingFloor);
        attendant.assignParkingLot(parkingLot);

        assertTrue(attendant.isParkingLotAssigned(parkingLot));
    }


    @Test
    public void testAttendantCanParkCar() {
        Attendant attendant = new Attendant("ABD");
        Slot[] slots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY),new Slot(SlotStatus.EMPTY)
        };
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingFloor parkingFloor = new ParkingFloor(1,slots);
        parkingLot.addParkingFloor(0,parkingFloor);

        attendant.assignParkingLot(parkingLot);

        Car car = new Car("MH12VV9999","Black");
        Ticket ticket = attendant.park(parkingLot,car);
        Ticket expectedTicket = new Ticket(1,1,"MH12VV9999");
        assertEquals(expectedTicket,ticket);
    }

    @Test
    public void testAssignTwoAttendantToSameParkingLotAndParkingTwoDifferentCar() {
        Attendant attendantOne = new Attendant("ABD");
        Attendant attendantTwo = new Attendant("XYZ");

        Slot[] slots1 = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY),new Slot(SlotStatus.FULL)
        };
        Slot[] slots2 = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL),new Slot(SlotStatus.EMPTY)
        };

        ParkingLot parkingLot = new ParkingLot(2);
        ParkingFloor parkingFloorOne = new ParkingFloor(1,slots1);
        ParkingFloor parkingFloorTwo = new ParkingFloor(2,slots2);

        parkingLot.addParkingFloor(0,parkingFloorOne);
        parkingLot.addParkingFloor(1,parkingFloorTwo);

        attendantOne.assignParkingLot(parkingLot);
        attendantTwo.assignParkingLot(parkingLot);

        Car car1 = new Car("MH12VV9999","Black");
        Ticket ticket = attendantOne.park(parkingLot,car1);
        Ticket expectedTicket = new Ticket(1,1,"MH12VV9999");
        assertEquals(expectedTicket,ticket);

        Car car2 = new Car("JK12RR1111","White");
        Ticket ticketOfCar2 = attendantTwo.park(parkingLot,car2);
        Ticket expectedTicketOfCar2 = new Ticket(2,2,"JK12RR1111");
        assertEquals(expectedTicketOfCar2,ticketOfCar2);
    }

    @Test
    public void testUnparkingCarParkedByAnotherAttendant() {
        Attendant attendantOne = new Attendant("ABD");
        Attendant attendantTwo = new Attendant("XYZ");
        Slot[] slots = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY),new Slot(SlotStatus.EMPTY)
        };
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingFloor parkingFloor = new ParkingFloor(0,slots);
        parkingLot.addParkingFloor(0,parkingFloor);

        attendantOne.assignParkingLot(parkingLot);
        attendantTwo.assignParkingLot(parkingLot);

        Car car = new Car("MH12VV9999","Black");
        Ticket ticket = attendantOne.park(parkingLot,car);
        Car unparkedCar = attendantTwo.unpark(parkingLot,ticket);
        assertEquals(car,unparkedCar);
    }


    @Test
    public void testParkingTwoCarWithTwoDifferentAttendantWhenParkingLotIsFull() {
        Attendant attendantOne = new Attendant("ABD");
        Attendant attendantTwo = new Attendant("XYZ");
        Attendant attendant = mock(Attendant.class);

        Slot[] slots1 = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.EMPTY),new Slot(SlotStatus.FULL)
        };
        Slot[] slots2 = {
                new Slot(SlotStatus.FULL), new Slot(SlotStatus.FULL),new Slot(SlotStatus.EMPTY)
        };

        ParkingLot parkingLot = new ParkingLot(2);
        ParkingFloor parkingFloorOne = new ParkingFloor(1,slots1);
        ParkingFloor parkingFloorTwo = new ParkingFloor(2,slots2);

        parkingLot.addParkingFloor(0,parkingFloorOne);
        parkingLot.addParkingFloor(1,parkingFloorTwo);

        attendantOne.assignParkingLot(parkingLot);
        attendantTwo.assignParkingLot(parkingLot);

        Car car1 = new Car("MH12VV9999","Black");
        Ticket ticket = attendantOne.park(parkingLot,car1);
        Ticket expectedTicket = new Ticket(1,1,"MH12VV9999");
        assertEquals(expectedTicket,ticket);

        verify(attendant, times(0)).parkingLotsFullNotification();

        Car car2 = new Car("JK12RR1111","White");
        Ticket ticketOfCar2 = attendantTwo.park(parkingLot,car2);
        Ticket expectedTicketOfCar2 = new Ticket(2,2,"JK12RR1111");
        assertEquals(expectedTicketOfCar2,ticketOfCar2);

        verify(attendant).parkingLotsFullNotification();
    }
}

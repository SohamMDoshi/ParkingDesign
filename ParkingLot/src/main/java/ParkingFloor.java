public class ParkingFloor {
    private int floorId;
    private Slot[] slots;

    public ParkingFloor(int floorId, Slot[] slots) {
        this.floorId = floorId;
        if(slots.length <= 0) throw new IllegalArgumentException("Number of slots cannot be less or equal to 0");
        this.slots = slots;
    }

     int park(Car car) {
        if (isCarPresent(car.getRegistrationNumber())) throw new IllegalArgumentException("Car is already parked");
        for (int i = 0; i < this.slots.length; i++) {
            if(this.slots[i].getStatus() == SlotStatus.EMPTY) {
                this.slots[i] = new Slot(SlotStatus.FULL,car);
                return i;
            }
        }
        throw new ParkingLotFullException();
    }

    boolean isCarPresent(String registrationNumber){
        for (Slot slot : slots) {
            if (slot.getCar() == null) continue;
            else if (slot.getCar().getRegistrationNumber().equals(registrationNumber)) return true;
        }
        return false;
    }

    Car unpark (int slotNumber,String registrationNumber) {
        if (slots[slotNumber].getCar() == null) throw new CarNotFoundException();
        if(slots[slotNumber].getCar().getRegistrationNumber().equals(registrationNumber)) {
            Car car = slots[slotNumber].getCar();
            slots[slotNumber] = new Slot(SlotStatus.EMPTY);
            return car;
        }
        throw new CarNotFoundException();
    }
}

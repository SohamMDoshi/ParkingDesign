public class Slot {
    private SlotStatus status;
    private Car car;

    public Slot(SlotStatus status,Car car) {
        this.status = status;
        this.car = car;
    }

    public Slot(SlotStatus slotStatus) {
        this.status = slotStatus;
    }

    public SlotStatus getStatus () {
        return status;
    }

    public Car getCar () {
        return car;
    }
}

import java.util.Objects;

public class Ticket {
    private int parkingFloor;
    private int parkingSlot;

    private String registrationNumber;

    public Ticket(int parkingFloor,int parkingSlot,String registrationNumber) {
        this.parkingFloor = parkingFloor;
        this.parkingSlot = parkingSlot;
        this.registrationNumber = registrationNumber;
    }

    public int getParkingFloor() {
        return parkingFloor;
    }

    public int getParkingSlot() {
        return parkingSlot;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return parkingFloor == ticket.parkingFloor && parkingSlot == ticket.parkingSlot && Objects.equals(registrationNumber, ticket.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingFloor, parkingSlot, registrationNumber);
    }
}

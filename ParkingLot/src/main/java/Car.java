import java.util.Objects;

public class Car {
    private String registrationNumber;
    private String color;

    public Car(String registrationNumber, String color) {
        if(!validRegistrationNumber(registrationNumber)) throw new IllegalArgumentException("Invalid car number format");
        else this.registrationNumber = registrationNumber;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(registrationNumber, car.registrationNumber) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, color);
    }

    private boolean validRegistrationNumber(String registrationNumber) {
        String regex = "^[A-Z]{2}[0-9]{1,2}[A-Z]{1,2}[0-9]{4}$";
        return registrationNumber.matches(regex);
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }
}

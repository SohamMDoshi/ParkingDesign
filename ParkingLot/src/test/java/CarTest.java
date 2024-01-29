import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarTest {

    @Test
    public void testCreateNewCarWithInvalidNumber_ExpectException() {
        assertThrows(IllegalArgumentException.class, () -> new Car("12MH1212","Black"));

    }

    @Test
    public void testCreateNewCarWithValidNumber_ExpectCarObject (){
        Car car = new Car("MH12AT3433","Black");
        assertNotNull(car,"Car should not be null");
    }
}

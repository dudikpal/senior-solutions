package dailytasks.usedcars.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTestIT {

    @Autowired
    CarController controller;

    @Test
    void allCars() {
        int age = controller.allCars().get(2).getAge();
        assertThat(age).isEqualTo(1);
    }

    @Test
    void allTypes() {
        String type = controller.allTypes().get(1);
        assertThat(type).isEqualTo("Nufuk");
    }
}
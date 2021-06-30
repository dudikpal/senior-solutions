package dailytasks.usedcars.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarServiceTest {

    CarService carService;

    @Test
    void allCars() {
        carService = new CarService();
        assertThat(carService.allCars())
                .hasSize(3);
    }

    @Test
    void getCarTypes() {
        carService = new CarService();
        assertThat(carService.allTypes().get(1))
                .isEqualTo("Nufuk");
    }
}
package dailytasks.usedcars.controller;

import dailytasks.usedcars.repository.Car;
import dailytasks.usedcars.repository.CarCondition;
import dailytasks.usedcars.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;

    @Test
    void allCars() {
        when(carService.allCars())
                .thenReturn(List.of(
                        new Car("Titi", "guga", 11, CarCondition.EXCELLENT, List.of())
                ));

        assertThat(carController.allCars())
                .extracting(Car::getBrand)
                .contains("Titi");
    }

    @Test
    void allTypes() {
        when(carService.allTypes())
                .thenReturn(List.of("Sitf", "Nufuk", "Gilbao"));

        assertThat(carController.allTypes())
                .contains("Sitf");
    }
}
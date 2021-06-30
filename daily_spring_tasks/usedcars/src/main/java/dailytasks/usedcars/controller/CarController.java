package dailytasks.usedcars.controller;

import dailytasks.usedcars.repository.Car;
import dailytasks.usedcars.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("cars")
    public List<Car> allCars() {
        return carService.allCars();
    }

    @GetMapping("types")
    public List<String> allTypes() {
        return carService.allTypes();
    }

    @GetMapping("proba")
    public String proba() {
        return """
                <h1>iy;;;j</h1>
                <ul>
                <li>egzes</li>
                <li>kettes</li>
                </ul>""";
    }
}

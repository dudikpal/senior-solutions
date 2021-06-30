package dailytasks.usedcars.service;

import dailytasks.usedcars.repository.Car;
import dailytasks.usedcars.repository.CarCondition;
import dailytasks.usedcars.repository.KmState;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars = List.of(
            new Car("Susiki", "Sitf", 18, CarCondition.OVERUSED,
                    List.of(
                            new KmState(LocalDate.of(2000, 01, 01), 130),
                            new KmState(LocalDate.of(2000, 01, 11), 230),
                            new KmState(LocalDate.of(2000, 01, 21), 330)
                    )),
            new Car("Gampirdo", "Nufuk", 8, CarCondition.NORMAL,
                    List.of(
                            new KmState(LocalDate.of(2000, 01, 01), 130),
                            new KmState(LocalDate.of(2000, 01, 11), 330),
                            new KmState(LocalDate.of(2000, 01, 21), 530)
                    )),
            new Car("Merasiti", "Gilbao", 1, CarCondition.EXCELLENT,
                    List.of(
                            new KmState(LocalDate.of(2000, 01, 01), 4130),
                            new KmState(LocalDate.of(2000, 01, 11), 5230),
                            new KmState(LocalDate.of(2000, 01, 21), 6330)
                    ))
    );


    public List<Car> allCars() {
        return cars;
    }

    public List<String> allTypes() {
        return cars.stream()
                .map(car -> car.getType())
                .collect(Collectors.toList());
    }
}

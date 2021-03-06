package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceDaoTest {

    private ParkingPlaceDao parkingPlaceDao;
    private EmployeeDao employeeDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        parkingPlaceDao = new ParkingPlaceDao(entityManagerFactory);
        employeeDao = new EmployeeDao(entityManagerFactory);
    }

    @Test
    void testSave() {
        parkingPlaceDao.saveParkingPlace(new ParkingPlace(100));
        ParkingPlace parkingPlace = parkingPlaceDao.findParkingPlaceNumber(100);
        assertEquals(100, parkingPlace.getNumber());
    }

    @Test
    void testSaveEmployeeWithParkingPlace() {
        ParkingPlace parkingPlace = new ParkingPlace(100);
        parkingPlaceDao.saveParkingPlace(parkingPlace);

        Employee employee = new Employee("John Doe");
        employee.setParkingPlace(parkingPlace);

        employeeDao.save(employee);

        Employee another = employeeDao.findById(employee.getId());
        assertEquals(100, another.getParkingPlace().getNumber());
    }


}
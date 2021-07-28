package employees;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {


    private EmployeeDao employeeDao;
    private ParkingPlaceDao parkingPlaceDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(entityManagerFactory);
        parkingPlaceDao = new ParkingPlaceDao(entityManagerFactory);
    }

    @Test
    void testSaveThenFindById() {
        Employee employee = new Employee("John Dao");
        employeeDao.save(employee);
        long id = employee.getId();
        Employee another = employeeDao.findById(id);
        assertEquals("John Dao", another.getName());
    }

    @Test
    void testSaveThanListAll() {
        employeeDao.save(new Employee("Ja Doe"));
        employeeDao.save(new Employee("Jo Doe"));

        List<Employee> employees = employeeDao.listAll();
        assertEquals(List.of("Ja Doe", "Jo Doe"), employees.stream()
        .map(Employee::getName).collect(Collectors.toList()));
    }

    @Test
    void testChangeName() {
        Employee employee = new Employee("John Dao");
        employeeDao.save(employee);
        long id = employee.getId();
        employeeDao.changeName(id, "Joco Doe");
        Employee another = employeeDao.findById(id);
        assertEquals("Joco Doe", another.getName());
    }

    @Test
    void testDelete() {
        Employee employee = new Employee("Johnd Sao");
        employeeDao.save(employee);
        long id = employee.getId();
        employeeDao.delete(id);

        List<Employee> employees = employeeDao.listAll();
        assertTrue(employees.isEmpty());
    }

    @Test
    void testEmployeeWithAttributes() {
        employeeDao.save(new Employee("jo Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000, 1, 1)));
        Employee employee = employeeDao.listAll().get(0);
        System.out.println(employee);
        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }

    @Test
    void testEmployeeWithAttributesId() {
        for (int i = 0; i < 10; i++) {
            employeeDao.save(new Employee("John Doe", Employee.EmployeeType.HALF_TIME,
                    LocalDate.of(2000, 1, 1)));
        }
        Employee employee = employeeDao.listAll().get(0);
        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }

    @Test
    void testSaveEmployeeChangeState() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);
        employee.setName("Jack Dao");
        Employee another = employeeDao.findById(employee.getId());

        assertEquals("John Doe", another.getName());
        assertFalse(employee == another);
    }

    @Test
    void testMerge() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        employee.setName("Jack Doe");
        employeeDao.updateEmployee(employee);

        assertEquals("Jack Doe", employeeDao.findById(employee.getId()).getName());
    }

    @Test
    void testFlush() {
        for (int i = 0; i < 10; i++) {
            employeeDao.save(new Employee("John Doe" + i));
        }
        employeeDao.updateEmployeeNames();

    }

    @Test
    void testNicknames() {
        Employee employee = new Employee("John Doe");
        employee.setNicknames(Set.of("nick1", "N"));
        employeeDao.save(employee);

        Employee another = employeeDao.findEmployeeByIdWithNicknames(employee.getId());
        //System.out.println(another.getNicknames());
        assertEquals(Set.of("nick1", "N"), another.getNicknames());
    }

    @Test
    void testVacations() {
        Employee employee = new Employee("John Doe");
        employee.setVacationBookings(Set.of(
                new VacationEntry(LocalDate.of(2018, 1, 1), 4),
                new VacationEntry(LocalDate.of(2018, 2, 15), 2)
        ));

        employeeDao.save(employee);
        Employee another = employeeDao.findEmployeeByIdWithVacations(employee.getId());
        System.out.println(another.getVacationBookings());
        assertEquals(2, another.getVacationBookings().size());
    }

    /*@Test
    void testPhonenumbers() {
        Employee employee = new Employee("John Doe");
        employee.setPhoneNumbers(Map.of(
                "home", "1234",
                "work", "43221"
        ));
        employeeDao.save(employee);
        Employee another = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());
        assertEquals("1234", another.getPhoneNumbers().get("home"));
    }*/

    @Test
    void testPhoneNumber() {
        PhoneNumber phoneNumberHome = new PhoneNumber("home", "1234");
        PhoneNumber phoneNumberWork = new PhoneNumber("work", "4321");

        Employee employee = new Employee("John Doe");
        employee.addPhoneNumber(phoneNumberWork);
        employee.addPhoneNumber(phoneNumberHome);
        employeeDao.save(employee);

        Employee another = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());
        //System.out.println(another.getPhoneNumbers());
        assertEquals(2, another.getPhoneNumbers().size());
        assertEquals("work", another.getPhoneNumbers().get(0).getType());
    }

    @Test
    void testAddPhoneNumber() {

        Employee employee = new Employee("Hon Dao");
        employeeDao.save(employee);

        employeeDao.addPhoneNumber(employee.getId(), new PhoneNumber("home", "1234"));
        Employee another = employeeDao.findEmployeeByIdWithPhoneNumbers(employee.getId());
        assertEquals(1, another.getPhoneNumbers().size());
    }


    @Test
    void testRemove() {
        Employee employee = new Employee("John Doe");
        employee.addPhoneNumber(new PhoneNumber("home", "1111"));
        employee.addPhoneNumber(new PhoneNumber("work", "2222"));

        employeeDao.save(employee);
        employeeDao.delete(employee.getId());

    }

    /*@Test
    void testEmployeeWithAddress() {
        Employee employee = new Employee("John Doe");
        Address address = new Address("H-1301", "Budapest", "F[ u. 6.");
        employee.setAddress(address);
        employeeDao.save(employee);

        Employee another = employeeDao.findById(employee.getId());
        assertEquals("H-1301", another.getAddress().getZip());
    }*/

    @Test
    void testEmployeeWithAddressAttributes() {
        Employee employee = new Employee("John Doe");
        employee.setZip("H-1301");
        employee.setCity("Budapest");
        employee.setLine1("Fo u. 5.");
        employeeDao.save(employee);

        Employee another = employeeDao.findById(employee.getId());

    }

    @Test
    void testFindEmployeeByName() {
        employeeDao.save(new Employee("John Doe"));

        Employee employee = employeeDao.findEmployeeByName("John Doe");
        assertEquals("John Doe", employee.getName());
    }

    @Test
    void testPaging() {
        for (int i = 100; i < 300; i++) {
            Employee employee = new Employee("John Doe" + i);
            employeeDao.save(employee);
        }

        List<Employee> employees = employeeDao.listEmployees(50, 20);
        assertEquals("John Doe150", employees.get(0).getName());
        assertEquals(20, employees.size());
    }

    @Test
    void testFindNumber() {
        Employee employee = new Employee("John Doe");
        ParkingPlace parkingPlace = new ParkingPlace(101);
        parkingPlaceDao.saveParkingPlace(parkingPlace);
        employee.setParkingPlace(parkingPlace);
        employeeDao.save(employee);

        int number = employeeDao.findParkingPlaceNumberByEmployeeName("John Doe");
        assertEquals(101, number);
    }

    @Test
    void testBaseData() {
        Employee employee = new Employee("John Doe");
        employeeDao.save(employee);

        List<Object[]> data = employeeDao.listEmployeeBaseData();
        assertEquals(1, data.size());
        assertEquals("John Doe", data.get(0)[1]);
    }

    @Test
    void testDto() {
        employeeDao.save(new Employee("John Doe"));
        employeeDao.save(new Employee("Jane Doe"));

        List<EmpBaseDataDto> data = employeeDao.listEmployeeDto();
        System.out.println(data);

        assertEquals(2, data.size());
        assertEquals("Jane Doe", data.get(0).getName());
    }

    @Test
    void testUpdateToType() {
        employeeDao.save(new Employee("John Doe",
                Employee.EmployeeType.FULL_TIME,
                LocalDate.of(1980, 1, 1)));
        employeeDao.save(new Employee("Jack Doe",
                Employee.EmployeeType.FULL_TIME,
                LocalDate.of(1990, 1, 1)));
        employeeDao.save(new Employee("Jane Doe",
                Employee.EmployeeType.FULL_TIME,
                LocalDate.of(2000, 1, 1)));

        employeeDao.updateToType(LocalDate.of(1985, 1, 1), Employee.EmployeeType.HALF_TIME);
        List<Employee> employees = employeeDao.listAll();
        System.out.println(employees);
        assertEquals(Employee.EmployeeType.HALF_TIME, employees.get(0).getEmployeeType());
        assertEquals(Employee.EmployeeType.FULL_TIME, employees.get(2).getEmployeeType());
    }

    @Test
    void testDeleteWithoutType() {
        employeeDao.save(new Employee("John Doe",
                Employee.EmployeeType.FULL_TIME,
                LocalDate.of(1980, 1, 1)));
        employeeDao.save(new Employee("Jack Doe",
                null,
                LocalDate.of(1990, 1, 1)));
        employeeDao.save(new Employee("Jane Doe",
                Employee.EmployeeType.FULL_TIME,
                LocalDate.of(2000, 1, 1)));

        employeeDao.deleteWithoutType();
        assertEquals(2, employeeDao.listAll().size());
    }
}
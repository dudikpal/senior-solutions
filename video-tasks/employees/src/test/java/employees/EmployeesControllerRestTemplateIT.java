package employees;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    EmployeesService employeesService;

    @Test
    @RepeatedTest(2)
    void testListLocations() {

        employeesService.deleteAllEmployees();

        EmployeeDto dto = template.postForObject("/api/employees", new CreateEmployeeCommand("John Doe"), EmployeeDto.class);

        assertEquals("John Doe", dto.getName());

        template.postForObject("/api/employees", new CreateEmployeeCommand("Jane Doe"), EmployeeDto.class);

        List<EmployeeDto> employees = template.exchange("/api/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeDto>>() {})
                .getBody();

        assertThat(employees)
                .extracting("name")
                .containsExactly("John Doe", "Jane Doe");
    }
}
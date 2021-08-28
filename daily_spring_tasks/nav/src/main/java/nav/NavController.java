package nav;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestControllerAdvice
@RequestMapping("/api")
@AllArgsConstructor
public class NavController {

    private final NavService navService;

    @GetMapping("/types")
    public List<Type> getTypes() {
        return navService.getTypes();
    }

    @PostMapping("/appointments")
    public void createAppointment(@Valid @RequestBody CreateAppointmentCommand command) {
        System.out.println(command);
    }

}

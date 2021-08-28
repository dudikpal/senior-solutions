package nav;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class IsValidCreateAppointmentCommandValidator implements ConstraintValidator<IsValidCreateAppointmentCommand, CreateAppointmentCommand> {

    @Override
    public boolean isValid(CreateAppointmentCommand value, ConstraintValidatorContext constraintValidatorContext) {
        /*LocalDateTime now = LocalDateTime.now();

        return value.getStart().isAfter(now) && value.getEnd().isAfter(value.getStart());*/

        return false;
    }
}

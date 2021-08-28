package nav;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class IsValidTypeValidator implements ConstraintValidator<IsValidType, String> {

    private NavService navService;



    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return navService.hasValidType(value);
    }
}

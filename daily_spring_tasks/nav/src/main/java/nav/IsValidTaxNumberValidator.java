package nav;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsValidTaxNumberValidator implements ConstraintValidator<IsValidTaxNumber, String> {

    TaxNumberValidator taxNumberValidator = new TaxNumberValidator();

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return taxNumberValidator.check(s);
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }
}

package nav;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsValidTaxNumberValidator.class)
public @interface IsValidTaxNumber {

    String message() default "Invalid tax number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

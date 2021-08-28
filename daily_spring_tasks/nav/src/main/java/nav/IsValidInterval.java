package nav;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = IsValidIntervalValidator.class)
public @interface IsValidInterval {

    String message() default "Invalid start or end time in validator";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

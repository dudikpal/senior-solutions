package nav;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsValidTypeValidator.class)
public @interface IsValidType {

    String message() default "Invalid tax type";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

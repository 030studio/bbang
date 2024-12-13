package studio.zero.bbang.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomPasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password must contain at least two of the following: " +
            "lowercase, uppercase, digit, special character, and be between 8 and 16 characters long.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

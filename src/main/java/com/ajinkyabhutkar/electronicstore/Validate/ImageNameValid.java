package com.ajinkyabhutkar.electronicstore.Validate;

import jakarta.persistence.Table;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {

    // error messege
    String message() default "Invalid image name";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

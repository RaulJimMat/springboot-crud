package com.raul.curso.springboot.app.springboot_crud.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistsByUsernameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUsername {
    String message() default "Ya existe en la base de datos, escoge un username distinto";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}

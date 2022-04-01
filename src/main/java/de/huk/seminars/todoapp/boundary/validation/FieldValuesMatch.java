package de.huk.seminars.todoapp.boundary.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldValuesMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValuesMatch {

  String message() default "Field values don’t match.";

  String field();

  String fieldToMatch();

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };
}

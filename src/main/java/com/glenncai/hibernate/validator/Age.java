package com.glenncai.hibernate.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The @Age validator
 *
 * @author Glenn Cai
 * @version 1.0 31/10/2023
 */
@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = AgeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {
  String message() default "{custom.invalidAge.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  int lower() default 18;

  int upper() default 60;
}

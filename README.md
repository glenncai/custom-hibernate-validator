# Custom Hibernate Validator

Here is a custom hibernate validator example.

`@Age` Annotation

```java
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

```

`@Age` Constraint Validator

```java
package com.glenncai.hibernate.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The @Age constraint validator
 *
 * @author Glenn Cai
 * @version 1.0 31/10/2023
 */
public class AgeValidator implements ConstraintValidator<Age, Integer> {
  private int ageLower;
  private int ageUpper;

  @Override
  public void initialize(Age age) {
    this.ageLower = age.lower();
    this.ageUpper = age.upper();
  }

  @Override
  public boolean isValid(Integer ageValue, ConstraintValidatorContext constraintValidatorContext) {
    if (ageValue == null) {
      return false;
    }
    return ageValue >= ageLower && ageValue <= ageUpper;
  }
}

```

Validator Configuration

```java
package com.glenncai.hibernate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Custom validator message configuration
 *
 * @author Glenn Cai
 * @version 1.0 1/11/2023
 */
@Configuration
public class ValidatorMessageConfig implements WebMvcConfigurer {
  @Override
  public Validator getValidator() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("validation-messages");

    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
    validatorFactoryBean.setValidationMessageSource(messageSource);
    return validatorFactoryBean;
  }
}

```

Validation Message Properties

```properties
custom.invalidAge.message=Age should between {lower} - {upper}
```
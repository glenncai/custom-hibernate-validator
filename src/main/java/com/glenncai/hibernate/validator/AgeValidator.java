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

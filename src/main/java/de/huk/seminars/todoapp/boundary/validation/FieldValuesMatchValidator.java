package de.huk.seminars.todoapp.boundary.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldValuesMatchValidator implements ConstraintValidator<FieldValuesMatch, Object> {

  private String field;
  private String fieldToMatch;


  @Override
  public void initialize(FieldValuesMatch constraintAnnotation) {
    this.field = constraintAnnotation.field();
    this.fieldToMatch = constraintAnnotation.fieldToMatch();
  }


  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

    Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
    Object fieldToMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldToMatch);

    if (fieldValue != null) {
      return fieldValue.equals(fieldToMatchValue);
    } else {
      return fieldToMatchValue == null;
    }
  }


}

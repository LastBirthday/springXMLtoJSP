package com.springapp.mvc.validation;

import com.springapp.mvc.model.Position;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Dds on 31.10.2015.
 */
@Component
public class FormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Position.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "required.price", "Price is required. Can not be empty!");

        Position position = (Position) o;

        if (position.getPrice() != null && position.getPrice() < 0) errors.rejectValue("price", "price.notValid", "Price can not be negative!");

    }
}

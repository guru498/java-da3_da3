package com.example.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ageValidator")
public class AgeValidator implements Validator {
    private static final int MIN_AGE = 16;
    private static final int MAX_AGE = 80;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null)
            return;
        int age;
        try {
            age = Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid age", "Age must be a number"));
        }
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Age out of range",
                    "Age must be between " + MIN_AGE + " and " + MAX_AGE));
        }
    }
}

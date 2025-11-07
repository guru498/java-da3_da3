package com.example.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null)
            return;
        String email = value.toString();
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid email format",
                    "Enter a valid email address");
            throw new ValidatorException(msg);
        }
    }
}

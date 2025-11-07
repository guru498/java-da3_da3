package com.example.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator {
    // Accepts digits, optional +, spaces, dashes. Simple pattern for demo.
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9\\-\\s]{7,20}$");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null)
            return;
        String phone = value.toString();
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid phone number",
                    "Enter a valid phone number");
            throw new ValidatorException(msg);
        }
    }
}

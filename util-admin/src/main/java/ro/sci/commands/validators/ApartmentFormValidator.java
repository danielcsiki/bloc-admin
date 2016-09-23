/**
 * 
 */
package ro.sci.commands.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ro.sci.commands.ApartmentForm;

/**
 * @author yellow
 *
 */
@Component
public class ApartmentFormValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return ApartmentForm.class.equals(aClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ApartmentForm apartmentForm = (ApartmentForm) target;
		if (!apartmentForm.getPasswordText().equals(apartmentForm.getPasswordTextConf())) {
			errors.rejectValue("passwordText", "PasswordsDontMatch.apartmentForm.passwordText",
					"Passwords Don't Match");
			errors.rejectValue("passwordTextConf", "PasswordsDontMatch.apartmentForm.passwordTextConf",
					"Passwords Don't Match");
		}
	}

}

/**
 * 
 */
package ro.sci.commands.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ro.sci.commands.UserForm;

/**
 * @author yellow
 *
 */
@Component
public class UserFormValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return UserForm.class.equals(aClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		UserForm userForm = (UserForm) target;
		if (!userForm.getPasswordText().equals(userForm.getPasswordTextConf())) {
			errors.rejectValue("passwordText", "PasswordsDontMatch.userForm.passwordText", "Passwords Don't Match");
			errors.rejectValue("passwordTextConf", "PasswordsDontMatch.userForm.passwordTextConf",
					"Passwords Don't Match");
		}
	}

}

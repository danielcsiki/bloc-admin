/**
 * 
 */
package ro.sci.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ro.sci.commands.UserForm;
import ro.sci.domain.Apartment;
import ro.sci.domain.User;

/**
 * @author yellow
 *
 */
@Component
public class UserFormToUser implements Converter<UserForm, User> {

	@Override
	public User convert(UserForm userForm) {

		User user = new User();

		user.setApartment(new Apartment());
		user.setId(userForm.getId());
		user.setVersion(userForm.getVersion());
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPasswordText());

		user.getApartment().setId(userForm.getApartmentId());
		user.getApartment().setVersion(userForm.getApartmentVersion());
		user.getApartment().setNr(userForm.getNr());
		user.getApartment().setNrResidents(userForm.getNrResidents());
		user.getApartment().setFirstName(userForm.getFirstName());
		user.getApartment().setLastName(userForm.getLastName());
		user.getApartment().setEmail(userForm.getEmail());
		user.getApartment().setPhoneNumber(userForm.getPhoneNumber());
		user.getApartment().setAddress(userForm.getAddress());

		return user;
	}

}

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
public class UserToUserForm implements Converter<User, UserForm> {

	@Override
	public UserForm convert(User user) {

		UserForm userForm = new UserForm();

		user.setApartment(new Apartment());
		userForm.setId(user.getId());
		userForm.setVersion(user.getVersion());
		userForm.setUsername(user.getUsername());
		userForm.setPasswordText(user.getPassword());

		userForm.setApartmentId(user.getApartment().getId());
		userForm.setApartmentVersion(user.getApartment().getVersion());
		userForm.setNr(user.getApartment().getNr());
		userForm.setNrResidents(user.getApartment().getNrResidents());
		userForm.setFirstName(user.getApartment().getFirstName());
		userForm.setLastName(user.getApartment().getLastName());
		userForm.setEmail(user.getApartment().getEmail());
		userForm.setPhoneNumber(user.getApartment().getPhoneNumber());
		userForm.setAddress(user.getApartment().getAddress());

		return userForm;
	}

}

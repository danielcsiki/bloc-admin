/**
 * 
 */
package ro.sci.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ro.sci.commands.ApartmentForm;
import ro.sci.domain.Apartment;
import ro.sci.domain.User;

/**
 * @author yellow
 *
 */
@Component
public class ApartmentFormToApartment implements Converter<ApartmentForm, Apartment> {

	@Override
	public Apartment convert(ApartmentForm apartmentForm) {

		Apartment apartment = new Apartment();

		apartment.setUser(new User());
		apartment.getUser().setId(apartmentForm.getUserId());
		apartment.getUser().setVersion(apartmentForm.getUserVersion());
		apartment.getUser().setUsername(apartmentForm.getUsername());
		apartment.getUser().setPassword(apartmentForm.getPasswordText());

		apartment.setId(apartmentForm.getId());
		apartment.setVersion(apartmentForm.getVersion());
		apartment.setNr(apartmentForm.getNr());
		apartment.setNrResidents(apartmentForm.getNrResidents());
		apartment.setFirstName(apartmentForm.getFirstName());
		apartment.setLastName(apartmentForm.getLastName());
		apartment.setEmail(apartmentForm.getEmail());
		apartment.setPhoneNumber(apartmentForm.getPhoneNumber());
		apartment.setAddress(apartmentForm.getAddress());

		return apartment;
	}

}

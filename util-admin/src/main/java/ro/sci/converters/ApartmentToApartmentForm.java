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
public class ApartmentToApartmentForm implements Converter<Apartment, ApartmentForm> {

	@Override
	public ApartmentForm convert(Apartment apartment) {

		ApartmentForm apartmentForm = new ApartmentForm();

		apartment.setUser(new User());
		apartmentForm.setUserId(apartment.getUser().getId());
		apartmentForm.setUserVersion(apartment.getUser().getVersion());
		apartmentForm.setUsername(apartment.getUser().getUsername());
		apartmentForm.setPasswordText(apartment.getUser().getPassword());

		apartmentForm.setId(apartment.getId());
		apartmentForm.setVersion(apartment.getVersion());
		apartmentForm.setNr(apartment.getNr());
		apartmentForm.setNrResidents(apartment.getNrResidents());
		apartmentForm.setFirstName(apartment.getFirstName());
		apartmentForm.setLastName(apartment.getLastName());
		apartmentForm.setEmail(apartment.getEmail());
		apartmentForm.setPhoneNumber(apartment.getPhoneNumber());
		apartmentForm.setAddress(apartment.getAddress());

		return apartmentForm;

	}

}

/**
 * 
 */
package ro.sci.services;

import ro.sci.commands.ApartmentForm;
import ro.sci.domain.Apartment;

/**
 * @author yellow
 *
 */

public interface ApartmentService extends CrudService<Apartment> {

	ApartmentForm saveApartmentForm(ApartmentForm apartmentForm);

}

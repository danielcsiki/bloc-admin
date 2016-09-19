/**
 * 
 */
package ro.sci.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sci.config.JpaIntegrationConfig;
import ro.sci.domain.Apartment;
import ro.sci.domain.User;

/**
 * @author yellow
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
public class ApartmentServiceImplTest {

	private ApartmentService apartmentService;

	@Autowired
	public void setApartmentService(ApartmentService apartmentService) {
		this.apartmentService = apartmentService;
	}

	@Test
	public void testApartmentServiceOperations() {

		Apartment apartment = new Apartment();
		apartment.setNr(8);
		apartment.setNrResidents(4);
		apartment.setFirstName("joe");
		apartment.setLastName("pesci");
		apartment.setEmail("joe.pesci@yahoo.com");
		apartment.setPhoneNumber("0749666999");
		apartment.setAddress("rasinari 3");

		assertNull(apartment.getId()); // null before save
		apartmentService.save(apartment);
		assertNotNull(apartment.getId()); // not null after save

		Apartment fetchedApartment = apartmentService.getById(apartment.getId());

		assertNotNull(fetchedApartment);

		assertEquals(apartment.getId(), fetchedApartment.getId());
		assertEquals(apartment.getNr(), fetchedApartment.getNr());

		// update and save
		fetchedApartment.setNr(9);
		apartmentService.save(fetchedApartment);

		// get from DB, should be updated
		Apartment fetchedUpdatedApartment = apartmentService.getById(fetchedApartment.getId());
		assertEquals(fetchedApartment.getNr(), fetchedUpdatedApartment.getNr());

	}

	@Test
	public void testSaveApartmentWithUser() {

		Apartment apartment = new Apartment();

		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		apartment.setUser(user);

		Apartment savedApartment = apartmentService.save(apartment);

		assert savedApartment.getId() != null;
		assert savedApartment.getVersion() != null;
		assert savedApartment.getUser() != null;
		assert savedApartment.getUser().getId() != null;
		assert savedApartment.getUser().getEncryptedPassword() != null;
	}

}

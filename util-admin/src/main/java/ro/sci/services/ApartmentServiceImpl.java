/**
 * 
 */
package ro.sci.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.domain.Apartment;
import ro.sci.repositories.ApartmentRepository;

/**
 * @author yellow
 *
 */
@Service
public class ApartmentServiceImpl implements ApartmentService {

	private ApartmentRepository apartmentRepository;

	@Autowired
	public void setApartmentRepository(ApartmentRepository apartmentRepository) {
		this.apartmentRepository = apartmentRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#listAll()
	 */
	@Override
	public List<?> listAll() {
		List<Apartment> apartments = new ArrayList<>();
		apartmentRepository.findAll().forEach(apartments::add);
		return apartments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#getById(java.lang.Integer)
	 */
	@Override
	public Apartment getById(Integer id) {
		return apartmentRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#save(java.lang.Object)
	 */
	@Override
	public Apartment save(Apartment apartment) {
		return apartmentRepository.save(apartment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		apartmentRepository.delete(id);
	}

}

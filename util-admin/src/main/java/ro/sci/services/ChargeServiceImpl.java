/**
 * 
 */
package ro.sci.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.domain.Charge;
import ro.sci.repositories.ChargeRepository;

/**
 * @author luff
 *
 */
@Service
public class ChargeServiceImpl implements ChargeService {

	private ChargeRepository chargeRepository;

	@Autowired
	public void setChargeRepository(ChargeRepository chargeRepository) {
		this.chargeRepository = chargeRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#listAll()
	 */
	@Override
	public List<Charge> listAll() {
		List<Charge> charges = new ArrayList<>();
		chargeRepository.findAll().forEach(charges::add);
		return charges;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#getById(java.lang.Integer)
	 */
	@Override
	public Charge getById(Integer id) {
		return chargeRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#save(java.lang.Object)
	 */
	@Override
	public Charge save(Charge charge) {
		return chargeRepository.save(charge);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		chargeRepository.delete(id);
	}

}

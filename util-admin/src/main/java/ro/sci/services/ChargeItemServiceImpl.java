/**
 * 
 */
package ro.sci.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.domain.ChargeItem;
import ro.sci.repositories.ChargeItemRepository;

/**
 * @author yellow
 *
 */
@Service
public class ChargeItemServiceImpl implements ChargeItemService {

	private ChargeItemRepository chargeItemRepository;

	@Autowired
	public void setChargeItemRepository(ChargeItemRepository chargeItemRepository) {
		this.chargeItemRepository = chargeItemRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#listAll()
	 */
	@Override
	public List<ChargeItem> listAll() {
		List<ChargeItem> chargeItems = new ArrayList<>();
		chargeItemRepository.findAll().forEach(chargeItems::add);
		return chargeItems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#getById(java.lang.Integer)
	 */
	@Override
	public ChargeItem getById(Integer id) {
		return chargeItemRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#save(java.lang.Object)
	 */
	@Override
	public ChargeItem save(ChargeItem chargeItem) {
		return chargeItemRepository.save(chargeItem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		chargeItemRepository.delete(id);
	}

}

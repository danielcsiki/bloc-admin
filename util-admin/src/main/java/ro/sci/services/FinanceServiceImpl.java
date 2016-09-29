/**
 * 
 */
package ro.sci.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.domain.Finance;
import ro.sci.repositories.FinanceRepository;

/**
 * @author yellow
 *
 */
@Service
public class FinanceServiceImpl implements FinanceService {

	private FinanceRepository financeRepository;

	@Autowired
	public void setFinanceRepository(FinanceRepository financeRepository) {
		this.financeRepository = financeRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#listAll()
	 */
	@Override
	public List<Finance> listAll() {
		List<Finance> finances = new ArrayList<>();
		financeRepository.findAll().forEach(finances::add);
		return finances;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#getById(java.lang.Integer)
	 */
	@Override
	public Finance getById(Integer id) {
		return financeRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#save(java.lang.Object)
	 */
	@Override
	public Finance save(Finance finance) {
		return financeRepository.save(finance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		financeRepository.delete(id);
	}

}

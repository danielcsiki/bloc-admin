/**
 * 
 */
package ro.sci.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.domain.Expense;
import ro.sci.repositories.ExpenseRepository;

/**
 * @author luff
 *
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {

	private ExpenseRepository expenseRepository;

	@Autowired
	public void setExpenseRepository(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#listAll()
	 */
	@Override
	public List<Expense> listAll() {
		List<Expense> expenses = new ArrayList<>();
		expenseRepository.findAll().forEach(expenses::add);
		return expenses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#getById(java.lang.Integer)
	 */
	@Override
	public Expense getById(Integer id) {
		return expenseRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#save(java.lang.Object)
	 */
	@Override
	public Expense save(Expense expense) {
		return expenseRepository.save(expense);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ro.sci.services.CrudService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		expenseRepository.delete(id);
	}

}

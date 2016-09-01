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

	@Override
	public List<?> listAll() {
		List<Expense> expenses = new ArrayList<>();
		expenseRepository.findAll().forEach(expenses::add);
		return expenses;

	}

	@Override
	public Expense getById(Integer id) {
		return expenseRepository.findOne(id);
	}

	@Override
	public Expense save(Expense abstractModel) {
		return expenseRepository.save(abstractModel);
	}

	@Override
	public void delete(Integer id) {
		expenseRepository.delete(id);
	}

}

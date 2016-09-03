/**
 * 
 */
package ro.sci.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sci.config.JpaIntegrationConfig;
import ro.sci.domain.Expense;
import ro.sci.repositories.ExpenseRepository;

/**
 * @author luff
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
public class ExpenseServiceImplTest {

	private ExpenseRepository expenseRepository;

	@Autowired
	public void setExpenseRepository(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	@Test
	public void testExpenseRepositoryOperations() {

		Expense expense = new Expense();
		expense.setItem("fond reparatii");
		expense.setCalcReference("apartament");
		expense.setCalcQuantum(1);
		expense.setPricePerCalcQuantum((float) 10.00);

		assertNull(expense.getId()); // null before save
		expenseRepository.save(expense);
		assertNotNull(expense.getId()); // not null after save

		Expense fetchedExpense = expenseRepository.findOne(expense.getId());

		assertNotNull(fetchedExpense);

		assertEquals(expense.getId(), fetchedExpense.getId());
		assertEquals(expense.getItem(), fetchedExpense.getItem());

		// update description and save
		fetchedExpense.setItem("belea");
		expenseRepository.save(fetchedExpense);

		// get from DB, should be updated
		Expense fetchedUpdatedExpense = expenseRepository.findOne(fetchedExpense.getId());
		assertEquals(fetchedExpense.getItem(), fetchedUpdatedExpense.getItem());

		long expenseCount = expenseRepository.count();
		assertEquals(expenseCount, 4);

		List<Expense> expenses = (List<Expense>) expenseRepository.findAll();
		assert expenses.size() == 4;

	}

}

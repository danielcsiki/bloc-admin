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

/**
 * @author luff
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
public class ExpenseServiceImplTest {

	private ExpenseService expenseService;

	@Autowired
	public void setExpenseRepository(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@Test
	public void testExpenseRepositoryOperations() {

		Expense expense = new Expense();
		expense.setItem("fond reparatii");
		expense.setUnitReference("apartament");
		expense.setUnitPrice((float) 10.00);

		assertNull(expense.getId()); // null before save
		expenseService.save(expense);
		assertNotNull(expense.getId()); // not null after save

		Expense fetchedExpense = expenseService.getById(expense.getId());

		assertNotNull(fetchedExpense);

		assertEquals(expense.getId(), fetchedExpense.getId());
		assertEquals(expense.getItem(), fetchedExpense.getItem());

		// update description and save
		fetchedExpense.setItem("belea");
		expenseService.save(fetchedExpense);

		// get from DB, should be updated
		Expense fetchedUpdatedExpense = expenseService.getById(fetchedExpense.getId());
		assertEquals(fetchedExpense.getItem(), fetchedUpdatedExpense.getItem());

		List<Expense> expenses = expenseService.listAll();
		assert expenses.size() == 4;// see JpaDataLoader...

	}

}

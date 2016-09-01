/**
 * 
 */
package ro.sci.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ro.sci.domain.Expense;
import ro.sci.services.ExpenseService;

/**
 * @author luff
 *
 */
@Component
public class JpaDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private ExpenseService expenseService;

	@Autowired
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		loadExpenses();
	}

	private void loadExpenses() {

		Expense expense1 = new Expense();
		expense1.setItem("apa rece");
		expense1.setCalcReference("index contor");
		expense1.setCalcQuantum(14);
		expense1.setPricePerCalcQuantum((float) 2.87);
		expenseService.save(expense1);

		Expense expense2 = new Expense();
		expense2.setItem("apa pluviala");
		expense2.setCalcReference("apartament");
		expense2.setCalcQuantum(1);
		expense2.setPricePerCalcQuantum((float) 3.28);
		expenseService.save(expense2);

		Expense expense3 = new Expense();
		expense3.setItem("salubritate");
		expense3.setCalcReference("rezidenti");
		expense3.setCalcQuantum(4);
		expense3.setPricePerCalcQuantum((float) 6.88);
		expenseService.save(expense3);

	}

}

/**
 * 
 */
package ro.sci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.sci.domain.Expense;
import ro.sci.services.ExpenseService;

/**
 * @author luff
 *
 */
@Controller
@RequestMapping("/expense")
public class ExpenseController {

	private ExpenseService expenseService;

	@Autowired
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@RequestMapping({ "/list", "/" })
	public String listExpenses(Model model) {
		model.addAttribute("expenses", expenseService.listAll());
		return "expense/list";
	}

	@RequestMapping("/show/{id}")
	public String showExpense(@PathVariable Integer id, Model model) {
		model.addAttribute("expense", expenseService.getById(id));
		return "expense/show";
	}

	@RequestMapping("/edit/{id}")
	public String editExpense(@PathVariable Integer id, Model model) {
		model.addAttribute("expense", expenseService.getById(id));
		return "expense/expenseform";
	}

	@RequestMapping("/add")
	public String addExpense(Model model) {
		model.addAttribute("expense", new Expense());
		return "expense/expenseform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveExpense(Expense expense) {
		Expense savedExpense = expenseService.save(expense);
		return "redirect:/expense/show" + savedExpense.getId();
	}

	@RequestMapping("/delete/{id}")
	public String deleteExpense(@PathVariable Integer id) {
		expenseService.delete(id);
		return "redirect:/expense/list";
	}

}

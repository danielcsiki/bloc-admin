/**
 * 
 */
package ro.sci.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.sci.domain.Finance;
import ro.sci.services.FinanceService;

/**
 * @author yellow
 *
 */
@Controller
@RequestMapping(value = "/user/finance", method = RequestMethod.GET)
public class FinanceController {

	private FinanceService financeService;

	@Autowired
	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}

	@RequestMapping("/list")
	public String listFinances(Model model) {
		model.addAttribute("finances", financeService.listAll());
		return "finance/list";
	}

	@RequestMapping("/show/{id}")
	public String getFinance(@PathVariable Integer id, Model model) {
		model.addAttribute("finance", financeService.getById(id));
		return "finance/show";
	}

	@RequestMapping("/edit/{id}")
	public String editFinance(@PathVariable Integer id, Model model) {
		model.addAttribute("finance", financeService.getById(id));
		return "finance/financeform";
	}

	@RequestMapping("/add")
	public String addFinance(Model model) {
		model.addAttribute("finance", new Finance());
		return "finance/financeform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveFinance(@Valid Finance finance, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "finance/financeform";
		}
		Finance savedFinance = financeService.save(finance);
		return "redirect:/finance/show/" + savedFinance.getId();
	}

	@RequestMapping("/delete/{id}")
	public String deleteFinance(@PathVariable Integer id) {
		financeService.delete(id);
		return "redirect:/finance/list";
	}

}

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

import ro.sci.domain.Charge;
import ro.sci.services.ChargeService;

/**
 * @author yellow
 *
 */
@Controller
@RequestMapping(value = "/user/charge", method = RequestMethod.GET)
public class ChargeController {

	private ChargeService chargeService;

	@Autowired
	public void setChargeService(ChargeService chargeService) {
		this.chargeService = chargeService;
	}

	@RequestMapping("/list")
	public String listCharges(Model model) {
		model.addAttribute("charges", chargeService.listAll());
		return "charge/list";
	}

	@RequestMapping("/show/{id}")
	public String getCharge(@PathVariable Integer id, Model model) {
		model.addAttribute("charge", chargeService.getById(id));
		return "charge/show";
	}

	@RequestMapping("/edit/{id}")
	public String editCharge(@PathVariable Integer id, Model model) {
		model.addAttribute("charge", chargeService.getById(id));
		return "charge/chargeform";
	}

	@RequestMapping("/add")
	public String addCharge(Model model) {
		model.addAttribute("charge", new Charge());
		return "charge/chargeform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveCharge(@Valid Charge charge, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "charge/chargeform";
		}
		Charge savedCharge = chargeService.save(charge);
		return "redirect:/charge/show/" + savedCharge.getId();
	}

	@RequestMapping("/delete/{id}")
	public String deleteCharge(@PathVariable Integer id) {
		chargeService.delete(id);
		return "redirect:/charge/list";
	}

}

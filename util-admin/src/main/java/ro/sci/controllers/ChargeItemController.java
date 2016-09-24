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

import ro.sci.domain.ChargeItem;
import ro.sci.services.ChargeItemService;

/**
 * @author yellow
 *
 */
@Controller
@RequestMapping("/user/chargeItem")
public class ChargeItemController {

	private ChargeItemService chargeItemService;

	@Autowired
	public void setChargeItemService(ChargeItemService chargeItemService) {
		this.chargeItemService = chargeItemService;
	}

	@RequestMapping("/list")
	public String listChargeItems(Model model) {
		model.addAttribute("chargeItems", chargeItemService.listAll());
		return "chargeItem/list";
	}

	@RequestMapping("/show/{id}")
	public String getChargeItem(@PathVariable Integer id, Model model) {
		model.addAttribute("chargeItem", chargeItemService.getById(id));
		return "chargeItem/show";
	}

	@RequestMapping("/edit/{id}")
	public String editChargeItem(@PathVariable Integer id, Model model) {
		model.addAttribute("chargeItem", chargeItemService.getById(id));
		return "chargeItem/chargeItemform";
	}

	@RequestMapping("/add")
	public String addChargeItem(Model model) {
		model.addAttribute("chargeItem", new ChargeItem());
		return "chargeItem/chargeItemform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveChargeItem(@Valid ChargeItem chargeItem, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "chargeItem/chargeItemform";
		}
		ChargeItem savedChargeItem = chargeItemService.save(chargeItem);
		return "redirect:/chargeItem/show/" + savedChargeItem.getId();
	}

	@RequestMapping("/delete/{id}")
	public String deleteChargeItem(@PathVariable Integer id) {
		chargeItemService.delete(id);
		return "redirect:/chargeItem/list";
	}

}

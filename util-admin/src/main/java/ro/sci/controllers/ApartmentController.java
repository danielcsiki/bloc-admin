/**
 * 
 */
package ro.sci.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.sci.commands.ApartmentForm;
import ro.sci.commands.validators.ApartmentFormValidator;
import ro.sci.converters.ApartmentToApartmentForm;
import ro.sci.services.ApartmentService;

/**
 * @author yellow
 *
 */
@Controller
@RequestMapping("/apartment")
public class ApartmentController {

	private ApartmentService apartmentService;

	private ApartmentToApartmentForm apartmentToApartmentForm;
	private ApartmentFormValidator apartmentFormValidator;

	@Autowired
	public void setApartmentService(ApartmentService apartmentService) {
		this.apartmentService = apartmentService;
	}

	@Autowired
	public void setApartmentToApartmentForm(ApartmentToApartmentForm apartmentToApartmentForm) {
		this.apartmentToApartmentForm = apartmentToApartmentForm;
	}

	@Autowired
	@Qualifier("apartmentFormValidator")
	public void setApartmentFormValidator(ApartmentFormValidator apartmentFormValidator) {
		this.apartmentFormValidator = apartmentFormValidator;
	}

	@RequestMapping("/list")
	public String listApartments(Model model) {
		model.addAttribute("apartments", apartmentService.listAll());
		return "apartment/list";
	}

	@RequestMapping("/show/{id}")
	public String getApartment(@PathVariable Integer id, Model model) {
		model.addAttribute("apartment", apartmentService.getById(id));
		return "apartment/show";
	}

	@RequestMapping("/edit/{id}")
	public String editApartment(@PathVariable Integer id, Model model) {
		model.addAttribute("apartmentForm", apartmentToApartmentForm.convert(apartmentService.getById(id)));
		return "apartment/apartmentform";
	}

	@RequestMapping("/add")
	public String addApartment(Model model) {
		model.addAttribute("apartmentForm", new ApartmentForm());
		return "apartment/apartmentform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveApartment(@Valid ApartmentForm apartmentForm, BindingResult bindingResult) {
		apartmentFormValidator.validate(apartmentForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "apartment/apartmentform";
		}
		ApartmentForm savedApartment = apartmentService.saveApartmentForm(apartmentForm);
		return "redirect:/apartment/show/" + savedApartment.getId();
	}

	@RequestMapping("/delete/{id}")
	public String deleteApartment(@PathVariable Integer id) {
		apartmentService.delete(id);
		return "redirect:/apartment/list";
	}

}

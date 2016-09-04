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

import ro.sci.domain.Apartment;
import ro.sci.services.ApartmentService;

/**
 * @author yellow
 *
 */
@Controller
@RequestMapping("/apartment")
public class ApartmentController {

	private ApartmentService apartmentService;

	@Autowired
	public void setApartmentService(ApartmentService apartmentService) {
		this.apartmentService = apartmentService;
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
		model.addAttribute("apartment", apartmentService.getById(id));
		return "apartment/apartmentform";
	}

	@RequestMapping("/add")
	public String addApartment(Model model) {
		model.addAttribute("apartment", new Apartment());
		return "apartment/apartmentform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveApartment(Apartment apartment) {
		Apartment savedApartment = apartmentService.save(apartment);
		return "redirect:/apartment/show/" + savedApartment.getId();
	}

	@RequestMapping("/delete/{id}")
	public String deleteApartment(@PathVariable Integer id) {
		apartmentService.delete(id);
		return "redirect:/apartment/list";
	}

}

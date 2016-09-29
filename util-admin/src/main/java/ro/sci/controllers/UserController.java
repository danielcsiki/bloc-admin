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

import ro.sci.commands.UserForm;
import ro.sci.commands.validators.UserFormValidator;
import ro.sci.converters.UserToUserForm;
import ro.sci.services.UserService;

/**
 * @author yellow
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	private UserToUserForm userToUserForm;
	private UserFormValidator userFormValidator;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setUserToUserForm(UserToUserForm userToUserForm) {
		this.userToUserForm = userToUserForm;
	}

	@Autowired
	@Qualifier("userFormValidator")
	public void setUserFormValidator(UserFormValidator userFormValidator) {
		this.userFormValidator = userFormValidator;
	}

	@RequestMapping("/list")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.listAll());
		return "user/list";
	}

	@RequestMapping("/show/{id}")
	public String getUser(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getById(id));
		return "user/show";
	}

	@RequestMapping("/edit/{id}")
	public String editUser(@PathVariable Integer id, Model model) {
		model.addAttribute("userForm", userToUserForm.convert(userService.getById(id)));
		return "user/userform";
	}

	@RequestMapping("/add")
	public String addUser(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "user/userform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveUser(@Valid UserForm userForm, BindingResult bindingResult) {
		userFormValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "user/userform";
		}
		UserForm savedUser = userService.saveUserForm(userForm);
		return "redirect:/user/show/" + savedUser.getId();
	}

	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
		userService.delete(id);
		return "redirect:/user/list";
	}

}

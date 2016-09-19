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

import ro.sci.domain.User;
import ro.sci.services.UserService;

/**
 * @author yellow
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
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
		model.addAttribute("user", userService.getById(id));
		return "user/userform";
	}

	@RequestMapping("/add")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "user/userform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveUser(User user) {
		User savedUser = userService.save(user);
		return "redirect:/user/show/" + savedUser.getId();
	}

	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
		userService.delete(id);
		return "redirect:/user/list";
	}

}

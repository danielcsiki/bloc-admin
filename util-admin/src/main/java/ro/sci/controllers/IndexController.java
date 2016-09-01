/**
 * 
 */
package ro.sci.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author luff
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

}

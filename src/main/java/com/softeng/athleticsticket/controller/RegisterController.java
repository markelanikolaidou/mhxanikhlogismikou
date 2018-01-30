package com.softeng.athleticsticket.controller;


/*import com.softeng.athleticsticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;*/
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController {

	//COMMENTED OUT FOR SECURITY REASONS
	//SHOULD ONLY BE USED TO CREATE NEW USERS
	
	/*@Autowired
	private UserService userService;*/

	public RegisterController() {
		// Default constructor
	}

	/*@PreAuthorize("isAnonymous()")
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	@PreAuthorize("isAnonymous()")
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}*/
}

package au.leon.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import au.leon.platform.bean.User;
import au.leon.platform.service.UserService;

@Controller
public class HelloController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/hellopage")
	public String hello(ModelMap modelMap) {
		List<User> userList = userService.getUsers();
		User one = userList.get(0);
		modelMap.put("user", one);
		return "hello";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "homepage/index";
	}
}

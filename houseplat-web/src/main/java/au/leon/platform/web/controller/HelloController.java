package au.leon.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import au.leon.platform.biz.service.UserService;
import au.leon.platform.common.bean.User;

@Controller
public class HelloController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/hellopage")
	public String hello(ModelMap modelMap) {
		List<User> userList = userService.getUsers();
		User one = userList.get(0);
		modelMap.put("user", one);
		int i = 1/0;
		return "hello";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "homepage/index";
	}
}

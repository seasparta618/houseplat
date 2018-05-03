package au.leon.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.leon.platform.bean.User;
import au.leon.platform.service.UserService;
/**
 * 
 * @author Leon Wang	
 * @email dev.leon618@gmail.com seasparta618@gmail.com
 * @date 2018年5月3日
 * @create 下午1:34:38
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/get/users")
	public List<User> getUsers() {

		return userService.getUsers();
	}
}

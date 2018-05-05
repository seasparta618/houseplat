package au.leon.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.leon.platform.biz.service.UserService;
import au.leon.platform.common.bean.User;
import au.leon.platform.common.result.ResultMsg;
import au.leon.platform.web.controller.helper.UserHelper;
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
	
	/**
	 * register form submission: 1, register verification 2, sending email 3,verification failed and redirect to the register page
	 * @param accountUser
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("account/register")
	public String accountRegister(User accountUser, ModelMap modelMap){
		if(accountUser == null || accountUser.getName() == null){
			return "/user/accounts/register";
		}
		
		ResultMsg resultMsg = UserHelper.validate(accountUser);
		
		if(resultMsg.isSuccess() && userService.addAccount(accountUser)){
			return "/user/accounts/registerSubmit";
		}else{
			return "redirect:/accounts/register?" + resultMsg.asUrlParams();
		}
		
		
	}
}

package au.leon.platform.web.controller.helper;

import org.apache.commons.lang3.StringUtils;

import au.leon.platform.common.bean.User;
import au.leon.platform.common.result.ResultMsg;

public class UserHelper {

	public static ResultMsg validate(User accountUser){
		if(StringUtils.isBlank(accountUser.getEmail())){
			return ResultMsg.errorMsg("Email有误");
		}
		if(StringUtils.isBlank(accountUser.getName())){
			return ResultMsg.errorMsg("用户名有误");
		}
		if(StringUtils.isBlank(accountUser.getConfirmPasswd()) || StringUtils.isBlank(accountUser.getPasswd())|| accountUser.getPasswd()!=accountUser.getConfirmPasswd()){
			return ResultMsg.errorMsg("密码有误");
		}
		if(accountUser.getPasswd().length()<6){
			return ResultMsg.errorMsg("密码必须大于6位");
		}
		return ResultMsg.successMsg("");
	}
}

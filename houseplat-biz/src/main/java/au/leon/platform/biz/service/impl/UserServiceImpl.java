package au.leon.platform.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.leon.platform.biz.mapper.UserMapper;
import au.leon.platform.biz.service.UserService;
import au.leon.platform.common.bean.User;
import au.leon.platform.common.utils.HashUtils;

/**
 * 
 * @author Leon Wang
 * @email dev.leon618@gmail.com seasparta618@gmail.com
 * @date 2018年5月3日
 * @create 下午1:45:41
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> getUsers() {
		return userMapper.selectUsers();
	}

	@Override
	public boolean addAccount(User account) {
		// TODO Auto-generated method stub
		account.setPasswd(HashUtils.encryptPassword(account.getPasswd()));
		return false;
	}
}

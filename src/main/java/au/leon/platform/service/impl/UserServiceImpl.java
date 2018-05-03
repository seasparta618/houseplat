package au.leon.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.leon.platform.bean.User;
import au.leon.platform.mapper.UserMapper;
import au.leon.platform.service.UserService;
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
}

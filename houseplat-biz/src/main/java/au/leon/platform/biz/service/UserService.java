package au.leon.platform.biz.service;

import java.util.List;

import au.leon.platform.common.bean.User;

/**
 * 
 * @author Leon Wang
 * @email dev.leon618@gmail.com seasparta618@gmail.com
 * @date 2018年5月3日
 * @create 下午1:45:17
 *
 */
public interface UserService {

	public List<User> getUsers();

	/**
	 * 1, 插入数据库，非激活状态
	 * 2, 对密码进行md5加盐
	 * 3, 保存头像到本地
	 * 4, 生成一个随机的key绑定email并且发送到用户邮箱中
	 * @param account
	 * @return
	 */
	public boolean addAccount(User account);

	public void registerNotify(String email);
}

package au.leon.platform.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import au.leon.platform.common.bean.User;

@Mapper
public interface UserMapper {

	public List<User> selectUsers();
	
	public int insert(User account);
	
	public int delete(String email);

}

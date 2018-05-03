package au.leon.platform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import au.leon.platform.bean.User;

@Mapper
public interface UserMapper {

	public List<User> selectUsers();
}

package cn.ncgd.dao;

import cn.ncgd.vo.User;

public interface UserDao {

	boolean saveUser(User user);

	User findUserByCode(String code);

	void updateUser(User user);

	User login(User user);

}

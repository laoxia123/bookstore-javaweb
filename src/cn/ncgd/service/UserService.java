package cn.ncgd.service;

import cn.ncgd.dao.UserDao;
import cn.ncgd.dao.UserDaoImpl;
import cn.ncgd.utils.MailUtil;
import cn.ncgd.utils.MyUUIDUtil;
import cn.ncgd.vo.User;

public class UserService {

	public boolean regist(User user) {
		//调用持久层注册用户
		UserDao dao = new UserDaoImpl();
		//生成主键
		user.setUid(MyUUIDUtil.getUUID());
		//设置状态码为0
		user.setState(0);
		//生成激活码
		String code = MyUUIDUtil.getUUID()+MyUUIDUtil.getUUID();
		user.setCode(code);
		//发送一封邮件
		//todo
		MailUtil.sendMail(user.getEmail(), code);
		return dao.saveUser(user);
		
	}

	public User findUserByCode(String code) {
		//调用持久层
		UserDao dao = new UserDaoImpl();
		return dao.findUserByCode(code);
	}

	public void updateUser(User user) {
		//调用持久层
		UserDao dao = new UserDaoImpl();
		dao.updateUser(user);
	}

	public User login(User user) {
		//调用持久层
		UserDao dao = new UserDaoImpl();
		return dao.login(user);
	}

}

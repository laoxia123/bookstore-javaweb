package cn.ncgd.service;

import cn.ncgd.dao.UserDao;
import cn.ncgd.dao.UserDaoImpl;
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
		
		return dao.saveUser(user);
		
	}

}

package cn.ncgd.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.ncgd.utils.MyJdbcUtil;
import cn.ncgd.vo.User;

public class UserDaoImpl implements UserDao {

	/**
	 * 注册用户
	 */
	@Override
	public boolean saveUser(User user) {
		boolean flag = false;
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?)";
		Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getState(),user.getCode()};
		
		try {
			int count = runner.update(sql, params);
			if(count>0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return flag;
	}

}

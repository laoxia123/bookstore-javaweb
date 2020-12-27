package cn.ncgd.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.ncgd.utils.MyJdbcUtil;
import cn.ncgd.vo.Category;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		
		String sql = "select * from category";
		try {
			return runner.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}

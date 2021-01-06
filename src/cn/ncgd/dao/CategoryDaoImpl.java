package cn.ncgd.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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

	@Override
	public void save(Category c) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "insert into category values (?,?)";
		try {
			runner.update(sql, c.getCid(),c.getCname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public Category findByCid(String cid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		
		String sql = "select * from category where cid = ?";
		try {
			return runner.query(sql, new BeanHandler<Category>(Category.class),cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Category c) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		
		String sql = "update category set cname= ? where cid=?";
		try {
			 runner.update(sql, c.getCname(),c.getCid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除分类
	 */
	public void delete(String cid) {
QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		
		String sql = "delete from category where cid=?";
		try {
			 runner.update(sql, cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}














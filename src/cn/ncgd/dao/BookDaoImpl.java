package cn.ncgd.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.ncgd.utils.MyJdbcUtil;
import cn.ncgd.vo.Book;
import cn.ncgd.vo.Category;

public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> findAll() {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		
		String sql = "select * from book where isdel=?";
		try {
			return runner.query(sql, new BeanListHandler<Book>(Book.class),0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Book> findByCid(String cid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		
		String sql = "select * from book where isdel=? and cid=?";
		try {
			return runner.query(sql, new BeanListHandler<Book>(Book.class),0,cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Book findByBid(String bid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		
		String sql = "select * from book where isdel=? and bid=?";
		try {
			return runner.query(sql, new BeanHandler<Book>(Book.class),0,bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

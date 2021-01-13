package cn.ncgd.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.ncgd.utils.MyJdbcUtil;
import cn.ncgd.vo.Book;
import cn.ncgd.vo.Category;
import cn.ncgd.vo.PageBean;

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

	@Override
	public void updateByCid(String cid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		
		String sql = "update book set cid = null where cid = ? and isdel = 1";
		try {
			runner.update(sql, cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Book> findByPage(int pageCode, int pageSize) {
		PageBean<Book> page = new PageBean<Book>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		try {
			String CountSql = "select count(*) from book where isdel=0";
			long count = (Long) runner.query(CountSql, new ScalarHandler());
			int totalCount = (int)count;
			page.setTotalCount(totalCount);
			String LimitSql = "select * from book where isdel=0 limit ?,?";
			List<Book> beanList = runner.query(LimitSql, new BeanListHandler<Book>(Book.class), (pageCode-1)*pageSize,pageSize);
			page.setBeanList(beanList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
				
		
		return page;
	}

	@Override
	public void save(Book book) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "insert into book values(?,?,?,?,?,?,?)";
		Object[] params = {book.getBid(),book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCid(),book.getIsdel()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateBook(Book book) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "update book set bname=?,price=?,author=?,image=?,cid=?,isdel=? where bid=?";
		Object[] params = {book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCid(),book.getIsdel(),book.getBid()};
		try {
			runner.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByBid(String bid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "update book set isdel=1 where bid=?";
		try {
			runner.update(sql, bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}





















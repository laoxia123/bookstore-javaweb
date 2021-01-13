package cn.ncgd.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.ncgd.utils.MyJdbcUtil;
import cn.ncgd.vo.Book;
import cn.ncgd.vo.Order;
import cn.ncgd.vo.OrderItem;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void saveOrder(Connection conn, Order order) {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?)";
		Object[] params = {order.getOid(),order.getTotal(),order.getOrdertime(),order.getState(),order.getAddress(),order.getUser().getUid()};
		try {
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void saveOrderItem(Connection conn, OrderItem orderItem) {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Object[] params = {orderItem.getItemid(),orderItem.getCount(),orderItem.getSubtotal(),orderItem.getBook().getBid(),orderItem.getOrder().getOid()};
		try {
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 查看订单
	 */
	@Override
	public List<Order> findByUid(String uid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "select * from orders where uid=?";
		List<Order> list;
		try {
			list = runner.query(sql, new BeanListHandler<Order>(Order.class), uid);
			for (Order order : list) {
				String oid = order.getOid();
				String sql2 = "select * from orderitem o,book b where o.bid=b.bid and oid = ?";
				List<Map<String, Object>> obList = runner.query(sql2, new MapListHandler(),oid);
				for (Map<String, Object> map : obList) {
					OrderItem orderItem = new OrderItem();
					BeanUtils.populate(orderItem, map);
					Book book = new Book();
					BeanUtils.populate(book, map);
					orderItem.setBook(book);
					order.getOrderItems().add(orderItem);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public Order findByOid(String oid) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "select * from orders where oid=?";
		Order order ;
		try {
			order = runner.query(sql, new BeanHandler<Order>(Order.class),oid);
			String sql2 = "select * from orderitem o,book b where o.bid=b.bid and oid = ?";
			List<Map<String, Object>> obList = runner.query(sql2, new MapListHandler(),oid);
			for (Map<String, Object> map : obList) {
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				Book book = new Book();
				BeanUtils.populate(book, map);
				orderItem.setBook(book);
				order.getOrderItems().add(orderItem);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return order;
	}

	public void updateOrder(Order order) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "update orders set address = ?,state = ? where oid = ?";
		try {
			runner.update(sql, order.getAddress(),order.getState(),order.getOid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查詢所有訂單
	 */
	public List<Order> findAllAdmin() {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "select * from orders";
		List<Order> list;
		try {
			list = runner.query(sql, new BeanListHandler<Order>(Order.class));
			for (Order order : list) {
				String oid = order.getOid();
				String sql2 = "select * from orderitem o,book b where o.bid=b.bid and oid = ?";
				List<Map<String, Object>> obList = runner.query(sql2, new MapListHandler(),oid);
				for (Map<String, Object> map : obList) {
					OrderItem orderItem = new OrderItem();
					BeanUtils.populate(orderItem, map);
					Book book = new Book();
					BeanUtils.populate(book, map);
					orderItem.setBook(book);
					order.getOrderItems().add(orderItem);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public List<Order> findByState(int state) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "select * from orders where state=?";
		List<Order> list;
		try {
			list = runner.query(sql, new BeanListHandler<Order>(Order.class),state);
			for (Order order : list) {
				String oid = order.getOid();
				String sql2 = "select * from orderitem o,book b where o.bid=b.bid and oid = ?";
				List<Map<String, Object>> obList = runner.query(sql2, new MapListHandler(),oid);
				for (Map<String, Object> map : obList) {
					OrderItem orderItem = new OrderItem();
					BeanUtils.populate(orderItem, map);
					Book book = new Book();
					BeanUtils.populate(book, map);
					orderItem.setBook(book);
					order.getOrderItems().add(orderItem);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public void updateByState(String oid, int state) {
		QueryRunner runner = new QueryRunner(MyJdbcUtil.getDataSource());
		String sql = "update orders set state = ? where oid = ?";
		try {
			runner.update(sql, state,oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}




















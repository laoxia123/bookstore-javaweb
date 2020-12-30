package cn.ncgd.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.ncgd.utils.MyJdbcUtil;
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

}

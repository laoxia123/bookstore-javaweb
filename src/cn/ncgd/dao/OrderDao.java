package cn.ncgd.dao;

import java.sql.Connection;
import java.util.List;

import cn.ncgd.vo.Order;
import cn.ncgd.vo.OrderItem;

public interface OrderDao {


	void saveOrder(Connection conn, Order order);

	void saveOrderItem(Connection conn, OrderItem orderItem);

	List<Order> findByUid(String uid);

	Order findByOid(String oid);

	void updateOrder(Order order);

	List<Order> findAllAdmin();

	List<Order> findByState(int state);

	void updateByState(String oid, int state);

}

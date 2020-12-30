package cn.ncgd.dao;

import java.sql.Connection;

import cn.ncgd.vo.Order;
import cn.ncgd.vo.OrderItem;

public interface OrderDao {


	void saveOrder(Connection conn, Order order);

	void saveOrderItem(Connection conn, OrderItem orderItem);

}

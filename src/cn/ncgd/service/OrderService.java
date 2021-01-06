package cn.ncgd.service;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import cn.ncgd.dao.OrderDao;
import cn.ncgd.dao.OrderDaoImpl;
import cn.ncgd.utils.MyJdbcUtil;
import cn.ncgd.vo.Order;
import cn.ncgd.vo.OrderItem;

public class OrderService {

	/**
	 * 保存订单
	 * @param order
	 */
	public void save(Order order) {
		Connection conn = null;
		try{
			conn = MyJdbcUtil.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//添加操作
			OrderDao dao = new OrderDaoImpl();
			//保存订单
			dao.saveOrder(conn,order);
			for (OrderItem orderItem : order.getOrderItems()) {
				//保存订单项
				dao.saveOrderItem(conn,orderItem);
			}
			//提交事务
			DbUtils.commitAndCloseQuietly(conn);
			
			
		}catch(Exception e){
			//遇到了问题回滚事务
			DbUtils.rollbackAndCloseQuietly(conn);
			e.printStackTrace();
		}
		
	}

	public List<Order> findByUid(String uid) {
		OrderDao dao = new OrderDaoImpl();
		return dao.findByUid(uid);
	}

	public Order findByOid(String oid) {
		OrderDao dao = new OrderDaoImpl();
		return dao.findByOid(oid);
	}

	public void updateOrder(Order order) {
		OrderDao dao = new OrderDaoImpl();
		dao.updateOrder(order);
	}

}

package cn.ncgd.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
	/**
	 * `oid` char(32) NOT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `uid` char(32) DEFAULT NULL,
	 */
	private String oid;
	private double total;
	private String ordertime;
	private int state;
	private String address;
	private User user;
	//一个订单包括多个订单项
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}













package cn.ncgd.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ncgd.service.OrderService;
import cn.ncgd.utils.MyUUIDUtil;
import cn.ncgd.vo.Cart;
import cn.ncgd.vo.CartItem;
import cn.ncgd.vo.Order;
import cn.ncgd.vo.OrderItem;
import cn.ncgd.vo.User;

public class OrderServlet extends BaseServlet {

	public String createOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 最终目的：需要把所有的数据封装到订单的对象中。
		 */
		Order order = new Order();
		// 设置订单的主键
		order.setOid(MyUUIDUtil.getUUID());
		// 地址
		order.setAddress(null);
		// 生成订单的时间
		order.setOrdertime(null);
		// 订单的状态	1：未付款  2：已付款，未发货 	3：已经发货，未确认收货 	4：订单结束
		order.setState(1);
		// total代表的是：总计 购物车中包含总计
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 设置总计
		if(cart == null){
			request.setAttribute("msg", "您的购物车为空");
			return "/jsps/msg.jsp";
		}
		order.setTotal(cart.getTotal());
		//存入用户信息
		//如果登录成功了，把用户信息存入到session中
		User existUser = (User) request.getSession().getAttribute("existUser");
		if(existUser == null){
			request.setAttribute("msg", "请您先登录");
			return "/jsps/msg.jsp";
		}
		order.setUser(existUser);
		//存入订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(MyUUIDUtil.getUUID());
			orderItem.setBook(cartItem.getBook());
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubTotal());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		
		//把订单保存到数据库中
		OrderService os = new OrderService();
		os.save(order);
		//清空购物车
		cart.clearCart();
		//把订单保存到域中，转发到页面上
		request.setAttribute("order", order);
		
		return "/jsps/order/desc.jsp";
	}

}

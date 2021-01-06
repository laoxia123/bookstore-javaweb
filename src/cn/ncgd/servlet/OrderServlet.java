package cn.ncgd.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ncgd.service.OrderService;
import cn.ncgd.utils.MyUUIDUtil;
import cn.ncgd.utils.PaymentUtil;
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
	
	public String findByUid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从session中获取用户的id
		User user = (User) request.getSession().getAttribute("existUser");
		String uid = user.getUid();
		//调用业务层代码
		OrderService os = new OrderService();
		List<Order> oList = os.findByUid(uid);
		
		//向request域中
		request.setAttribute("oList", oList);
		return "/jsps/order/list.jsp";
	}
	
	
	public String findByOid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		OrderService os = new OrderService();
		Order order = os.findByOid(oid);
		request.setAttribute("order", order);
		
		return "/jsps/order/desc.jsp";
	}
	
	/**
	 * 支付订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String payOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收一些参数
		String oid = request.getParameter("oid");
		
		String address = request.getParameter("address");
		// 送货的地址 -- 把该地址更新到订单数据中
		OrderService os = new OrderService();
		// order什么都有，就没有地址
		Order order = os.findByOid(oid);
		// 添加地址
		order.setAddress(address);
		// 更新订单
		os.updateOrder(order);
		
		// 接收银行
		String pd_FrpId = request.getParameter("pd_FrpId");
		
		// 能银行的页面就算成功了
		String p0_Cmd = "Buy";		// 业务类型
		String p1_MerId = "6212261502002593543";  // 商户编号
		String p2_Order = oid;	// 订单编号
		String p3_Amt = "0.01";	// 支付金额
		String p4_Cur = "CNY";	// 币种
		String p5_Pid = "";		// 商品名称
		String p6_Pcat = "";	//　商品种类
		String p7_Pdesc = "";	// 商品描述
		
		/**
		 * 回调函数
		 */
		String p8_Url = "http://localhost/bookstore/order?method=callBack";		// 回调函数
		
		
		String p9_SAF = "";		// 送货地址
		String pa_MP = "";		// 商户扩展信息
		
		// 支付通道编码
		
		String pr_NeedResponse = "1";	// 应答机制
		
		// 先生成hmac码，传到易宝
		// hmac = 参数 + 秘钥 + 算法
		// 秘钥：keyValue=
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		// 可以去重定向易宝 -- 传参数13个参数+hmact码
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return null;
	}

	
	/**
	 * 支付成功了，回调该方法
	 * @param request
	 * @param response
	 * @return
	 */
	public String callBack(HttpServletRequest request,HttpServletResponse response){
		// 应该接收全部的参数
		// request.getParameter("");
		// 接收全部的参数，集合keyValue和算法生成hmac码，
		// 接收易宝给你传过来的hmac 
		
		String r6_Order = request.getParameter("r6_Order");
		String r3_Amt = request.getParameter("r3_Amt");
		
		String r9_BType = request.getParameter("r9_BType");
		
		if("1".equals(r9_BType)){
			// 支付成功的，做什么操作，修改订单的状态 把1状态修改2状态
			OrderService os = new OrderService();
			// order什么都有，就没有地址
			Order order = os.findByOid(r6_Order);
			// 设置状态
			order.setState(2);
			// 再去修改数据库
			os.updateOrder(order);
		}
		
		request.setAttribute("msg", "亲，您的订单号为"+r6_Order+"已经付款成功了，金额为"+r3_Amt+"元");
		
		return "/jsps/msg.jsp";
	}
}

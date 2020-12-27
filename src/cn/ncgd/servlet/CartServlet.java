package cn.ncgd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ncgd.service.BookService;
import cn.ncgd.vo.Book;
import cn.ncgd.vo.Cart;
import cn.ncgd.vo.CartItem;

public class CartServlet extends BaseServlet {

	/**
	 * 把购物项添加到购物车中
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先去获取购物车
		// 从session中获取购物车
		Cart cart = getCart(request);
		CartItem item = new CartItem();
		//将购物项添加到购物车
		String bookId = request.getParameter("bookId");
		String scount = request.getParameter("count");
		//购买图书的数量
		int count = Integer.parseInt(scount);
		BookService bs = new BookService();
		Book book = bs.findByBid(bookId);
		item.setBook(book);
		item.setCount(count);
		cart.addCart(item);
		return "/jsps/cart/list.jsp";
	}
	
	public Cart getCart(HttpServletRequest request){
		// 从session中获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 删除购物项
	 * @param request
	 * @return
	 */
	public String removeCart(HttpServletRequest request){
		//获取购物车
		Cart cart = getCart(request);
		String bookId = request.getParameter("bookId");
		cart.removeCart(bookId);
		return "/jsps/cart/list.jsp";
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * @return
	 */
	public String clearCart(HttpServletRequest request){
		//获取购物车
		Cart cart = getCart(request);
		cart.clearCart();
		return "/jsps/cart/list.jsp";
	}

}

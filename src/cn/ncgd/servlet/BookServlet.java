package cn.ncgd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ncgd.service.BookService;
import cn.ncgd.vo.Book;

public class BookServlet extends BaseServlet {

	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = new BookService();
		List<Book> bookList = bs.findAll();
		request.setAttribute("bookList", bookList);
		return "/jsps/book/list.jsp";
	}
	
	public String findByCid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		BookService bs = new BookService();
		List<Book> bookList = bs.findByCid(cid);
		request.setAttribute("bookList", bookList);
		return "/jsps/book/list.jsp";
	}
	
	public String findByBid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		BookService bs = new BookService();
		Book book = bs.findByBid(bid);
		request.setAttribute("book", book);
		return "/jsps/book/desc.jsp";
	}
}

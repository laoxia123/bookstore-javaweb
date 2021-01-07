package cn.ncgd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ncgd.service.BookService;
import cn.ncgd.vo.Book;
import cn.ncgd.vo.PageBean;

public class BookServlet extends BaseServlet {

	/**
	 * 查看所有图书功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = new BookService();
		List<Book> bookList = bs.findAll();
		request.setAttribute("bookList", bookList);
		return "/jsps/book/list.jsp";
	}
	
	/**
	 * 通过分类查看图书功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		BookService bs = new BookService();
		List<Book> bookList = bs.findByCid(cid);
		request.setAttribute("bookList", bookList);
		return "/jsps/book/list.jsp";
	}
	
	/**
	 * 通过图书编号查询图书功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByBid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		BookService bs = new BookService();
		Book book = bs.findByBid(bid);
		request.setAttribute("book", book);
		return "/jsps/book/desc.jsp";
	}
	
	/**
	 * 查看图书实现分页功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = new BookService();
		//获取当前页
		int pageCode = getPageCode(request);
		//定义每页显示的条数
		int pageSize = 3;
		PageBean<Book> page = bs.findByPage(pageCode,pageSize);
		request.setAttribute("page", page);
		return "/adminjsps/admin/book/list.jsp";
	}
	/**
	 * 获取当前页
	 * @param request
	 * @return
	 */
	public int getPageCode(HttpServletRequest request){
		String pc = request.getParameter("pc");
		if(pc == null || pc.trim().isEmpty()){
			return 1;
		}
		return Integer.parseInt(pc);
	}
}

package cn.ncgd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ncgd.service.BookService;
import cn.ncgd.service.CategoryService;
import cn.ncgd.utils.MyUUIDUtil;
import cn.ncgd.vo.Book;
import cn.ncgd.vo.Category;

public class CategoryServlet extends BaseServlet {

	public String findAll(HttpServletRequest request, HttpServletResponse response){
		CategoryService cs = new CategoryService();
		List<Category> cList = cs.findAll();
		request.setAttribute("cList", cList);
		return "/jsps/left.jsp";
	}
	
	/**
	 * 后台查询分类
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAllAdmin(HttpServletRequest request, HttpServletResponse response){
		CategoryService cs = new CategoryService();
		List<Category> cList = cs.findAll();
		request.setAttribute("cList", cList);
		return "/adminjsps/admin/category/list.jsp";
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 */
	public String addCategoryAdmin(HttpServletRequest request, HttpServletResponse response){
		String cname = request.getParameter("cname");
		String cid = MyUUIDUtil.getUUID();
		Category c = new Category();
		c.setCid(cid);
		c.setCname(cname);
		CategoryService cs = new CategoryService();
		cs.save(c);
		return findAllAdmin(request,response);
	}
	
	/**
	 * 先查询需要修改的分类
	 * @param request
	 * @param response
	 * @return
	 */
	public String initUpdateAdmin(HttpServletRequest request, HttpServletResponse response){
		String cid = request.getParameter("cid");
		CategoryService cs = new CategoryService();
		Category category = cs.findByCid(cid);
		request.setAttribute("category",category);
		return "/adminjsps/admin/category/mod.jsp";
	}
	
	/**
	 * 开始修改分类
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateAdmin(HttpServletRequest request, HttpServletResponse response){
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		Category c = new Category();
		c.setCid(cid);
		c.setCname(cname);
		CategoryService cs = new CategoryService();
		cs.update(c);
		
		return findAllAdmin(request,response);
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteAdmin(HttpServletRequest request, HttpServletResponse response){
		String cid = request.getParameter("cid");
		BookService bs = new BookService();
		List<Book> bookList = bs.findByCid(cid);
		if(bookList != null && bookList.size()>0){
			request.setAttribute("msg", "亲，该分类下有图书，不能删除！");
		}else{
			CategoryService cs = new CategoryService();
			bs.updateByCid(cid);
			cs.delete(cid);
		}
		
		
		return findAllAdmin(request,response);
	}

}























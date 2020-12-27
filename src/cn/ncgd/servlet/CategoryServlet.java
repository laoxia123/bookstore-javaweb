package cn.ncgd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ncgd.service.CategoryService;
import cn.ncgd.vo.Category;

public class CategoryServlet extends BaseServlet {

	public String findAll(HttpServletRequest request, HttpServletResponse response){
		CategoryService cs = new CategoryService();
		List<Category> cList = cs.findAll();
		request.setAttribute("cList", cList);
		return "/jsps/left.jsp";
	}

}






package cn.ncgd.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.ncgd.service.UserService;
import cn.ncgd.vo.User;

public class UserServlet extends BaseServlet {

	public String registUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收数据
		//封装数据
		//处理数据
		//显示数据
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			UserService us = new UserService();
			boolean flag = us.regist(user);
			if(flag == false){
				request.setAttribute("msg", "注册失败了！！");
			}else{
				request.setAttribute("msg", "注册成功了！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "/jsps/msg.jsp";
	}

}

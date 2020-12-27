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
				request.setAttribute("msg", "注册成功了，请您去激活！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "/jsps/msg.jsp";
	}
	
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		
		UserService us = new UserService();
		
		User user = us.findUserByCode(code);
		if(user == null){
			request.setAttribute("msg", "激活失败了");
		}else{
			//修改用户的状态，状态默认值0，修改成1
			user.setState(1);
			//再调用UserService修改方法
			us.updateUser(user);
			request.setAttribute("msg", "激活成功了，请您去登录");
		}
		
		return "/jsps/msg.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			UserService us = new UserService();
			 User existUser = us.login(user);
			 if(existUser == null){
				 request.setAttribute("msg", "用户名或密码错误！！");
				 return "/jsps/msg.jsp";
			 }else{
				 request.getSession().setAttribute("existUser", existUser);
				 return "/jsps/main.jsp";
			 }
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		return "jsps/main.jsp";
		
	}

}

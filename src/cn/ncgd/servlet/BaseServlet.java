package cn.ncgd.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 7451901773718352886L;
	
	public void service(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		//设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html;charset=UTF-8");
		String methodName = request.getParameter("method");
		if(methodName == null || methodName.trim().isEmpty()){
			throw new RuntimeException("亲，您需要传入methodName参数");
		}
		//获取当前类
		Class clazz = this.getClass();
		Method method = null;
		try {
			 method = clazz.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("亲，您传入的"+method+"方法不存在！！");
		}
		try {
			String result = (String) method.invoke(this, request,response);
			if(result != null && !result.trim().isEmpty()){
				//进行转发
				request.getRequestDispatcher(result).forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("程序内部报错！！");
			throw new RuntimeException(e);
		} 
	}

}

package cn.ncgd.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.ncgd.service.BookService;
import cn.ncgd.utils.MyUUIDUtil;
import cn.ncgd.vo.Book;

public class AddBookServlet extends HttpServlet {

	/**
	 * 添加图书(上传图片)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//完成上传图片的功能
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//核心文件上传
			ServletFileUpload upload = new ServletFileUpload(factory);
			//上传文件中文乱码
			upload.setHeaderEncoding("UTF-8");
			//文件的真实名称
			String realFilename = "";
			Map<String, String> map = new HashMap<String, String>();
			try {
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem fileItem : list) {
					if(fileItem.isFormField()){
						//是普通项
						String key = fileItem.getFieldName();
						String value = fileItem.getString("UTF-8");
						map.put(key, value);
					}else{
						//文件上传项
						String filename = fileItem.getName();
						int index = filename.lastIndexOf(".");
						
						//文件的后缀名
						String lastname = filename.substring(index);
						//文件名
						realFilename = MyUUIDUtil.getUUID()+lastname;
						//获取输入流
						InputStream in = fileItem.getInputStream();
						//文件上传到哪
						//先获取book_img的绝对磁盘路径
						String path = getServletContext().getRealPath("/book_img");
						OutputStream os = new FileOutputStream(path+"/"+realFilename);
						int len = 0;
						byte[] b = new byte[1024];
						while((len=in.read(b))!=-1){
							os.write(b,0,len);
						}
						in.close();
						os.close();
					}
				}
				Book book = new Book();
				BeanUtils.populate(book, map);
				book.setBid(MyUUIDUtil.getUUID());
				book.setIsdel(0);
				book.setImage("book_img/"+realFilename);
				//保存到数据库中
				BookService bs = new BookService();
				bs.save(book);
				//转发或者重定向
				request.getRequestDispatcher("/book?method=findByPage").forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doGet(request, response);
	}

}
















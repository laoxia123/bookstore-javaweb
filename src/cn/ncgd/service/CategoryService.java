package cn.ncgd.service;

import java.util.List;

import cn.ncgd.dao.CategoryDao;
import cn.ncgd.dao.CategoryDaoImpl;
import cn.ncgd.vo.Category;

public class CategoryService {

	public List<Category> findAll() {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.findAll();
	}
	
}

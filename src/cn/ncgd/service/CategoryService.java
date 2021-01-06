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

	public void save(Category c) {
		CategoryDao dao = new CategoryDaoImpl();
		dao.save(c);
		
	}

	public Category findByCid(String cid) {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.findByCid(cid);
	}

	public void update(Category c) {
		CategoryDao dao = new CategoryDaoImpl();
		dao.update(c);
	}

	public void delete(String cid) {
		CategoryDao dao = new CategoryDaoImpl();
		dao.delete(cid);
		
	}
	
}

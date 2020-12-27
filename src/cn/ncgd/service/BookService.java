package cn.ncgd.service;

import java.util.List;

import cn.ncgd.dao.BookDao;
import cn.ncgd.dao.BookDaoImpl;
import cn.ncgd.vo.Book;

public class BookService {

	public List<Book> findAll() {
		BookDao dao = new BookDaoImpl();
		return dao.findAll();
	}

	public List<Book> findByCid(String cid) {
		BookDao dao = new BookDaoImpl();
		return dao.findByCid(cid);
	}

	public Book findByBid(String bid) {
		BookDao dao = new BookDaoImpl();
		return dao.findByBid(bid);
	}

}

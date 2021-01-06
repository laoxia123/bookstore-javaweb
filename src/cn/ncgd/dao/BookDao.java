package cn.ncgd.dao;

import java.util.List;

import cn.ncgd.vo.Book;

public interface BookDao {

	List<Book> findAll();

	List<Book> findByCid(String cid);

	Book findByBid(String bid);

	void updateByCid(String cid);

}

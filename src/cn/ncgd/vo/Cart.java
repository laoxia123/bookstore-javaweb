package cn.ncgd.vo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class Cart {
	//总计
	private double total;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	//将cart存到session中，用Map<String,CartItem>来处理，键是book的主键
	Map<String, CartItem> map = new HashMap<String, CartItem>();
	
	/**
	 * 方便前台页面遍历取出数据
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	/**
	 * 把购物项添加都购物车
	 * 把某一个购物项，添加到map中
	 * 点击某一本图书，添加都购物车中
	 * 		* 如果map中存在该购物项 -- 数量+ （小计算出来） ，总计变
	 * 		* 如果map中不存在，把书添加到map中，总计变
	 */
	public void addCart(CartItem cartItem){
		
		//获取图书主键
		String bookId = cartItem.getBook().getBid();
		//判断map中是否存在该购物项
		if(map.containsKey(bookId)){
			//购物车中存在的数量
			CartItem historyItem = map.get(bookId);
			historyItem.setCount(historyItem.getCount()+cartItem.getCount());
		}else{
			map.put(bookId, cartItem);
		}
		//总计要重新计算
		total += cartItem.getSubTotal();
	}
	
	/**
	 * 移除某一个购物项
	 * 根据图书的id把购物项从map中移除
	 * 总计：总计 = 总计 - 购物项的小计
	 */
	public void removeCart(String bookId){
		//从map中移除购物项
		CartItem item = map.remove(bookId);
		//重新计算总计
		total -= item.getSubTotal();
	}
	
	/**
	 * 清空购物车
	 * @param request
	 */
	public void clearCart(){
		//清空购物车
		map.clear();
		//重新计算总计
		total = 0;
	}
}

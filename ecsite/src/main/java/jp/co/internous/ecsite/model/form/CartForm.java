package jp.co.internous.ecsite.model.form;

import java.io.Serializable;
import java.util.List;

public class CartForm implements Serializable {
	public static final long serialVersionUID =1L;
	
	private long userId;
	private List<Cart> cartList;
	
	public long getUserId() {
		return userId;
	}
	public void serUserId(long id) {
		this.userId = id;
	}
	
	public List<Cart> getCartList() {
		return this.cartList;
	}
	
	public void setCartList(List<Cart> list) {
		this.cartList = list;
	}
}

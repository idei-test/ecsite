package jp.co.internous.ecsite.model.form;

import java.io.Serializable;

public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String goodsName;
	private long price;
	private long count;
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getGoodsName() {
		return this.goodsName;
	}
	public void setGoodsName(String goodsname) {
		this.goodsName = goodsname;
	}
	
	public long getPrice() {
		return this.price;
	}
	public void setPrice(long p) {
		this.price = p;
	}
	
	public long getCount() {
		return this.count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}

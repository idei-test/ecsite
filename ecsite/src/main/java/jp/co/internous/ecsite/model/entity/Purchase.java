package jp.co.internous.ecsite.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="user_id")
	private long userId;
	@Column(name="goods_id")
	private long goodsId;
	@Column(name="goods_name")
	private String goodsName;
	@Column(name="item_count")
	private long itemCount;
	@Column(name="total")
	private long total;
	@Column(name="created_at")
	private Timestamp createdAt;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return this.userId;
	}
	public void setUserId(long userid) {
		this.userId=userid;
	}
	
	public long getGoodsId() {
		return this.goodsId;
	}
	public void setGoodsId(long goodsid) {
		this.goodsId = goodsid;
	}
	
	public String getGoodsName() {
		return this.goodsName;
	}
	public void setGoodsName(String goodsname) {
		this.goodsName = goodsname;
	}
	
	public long getItemCount() {
		return this.itemCount;
	}
	public void setItemCount(long itemcount) {
		this.itemCount= itemcount;
	}
	
	public long getTotal() {
		return this.total;
	}
	public void setTotal(long total) {
		this.total= total;
	}
	
	public Timestamp getCreated_at() {
		return this.createdAt;
	}
	public void setCreated_at(Timestamp ts) {
		this.createdAt = ts;
	}
}

package jp.co.internous.ecsite.model.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import jp.co.internous.ecsite.model.entity.Purchase;

public class HistoryDto {
	private long id;
	private long userId;
	private long goodsId;
	private String goodsName;
	private long itemCount;
	private long total;
	private String createdAt;
	
	public HistoryDto() {}
	public HistoryDto(Purchase entity) {
		this.id = entity.getId();
		this.userId = entity.getUserId();
		this.goodsId = entity.getGoodsId();
		this.goodsName = entity.getGoodsName();
		this.itemCount = entity.getItemCount();
		this.total = entity.getTotal();
		
		Timestamp d = entity.getCreated_at();
		SimpleDateFormat f = new SimpleDateFormat("yyy/MM/dd hh:mm:ss");
		this.createdAt = f.format(d);
	}
		
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
		//  文字列
		public String getCreated_at() {
			return this.createdAt;
		}
		public void setCreated_at(String ts) {
			this.createdAt = ts;
		}
}

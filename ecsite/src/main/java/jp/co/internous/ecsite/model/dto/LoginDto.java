package jp.co.internous.ecsite.model.dto;

import jp.co.internous.ecsite.model.entity.User;

public class LoginDto {
	
	private long id;
	private String userName;
	private String password;
	private String fullName;
	//コンストラクタ
	public LoginDto() {}
	// 引数　ユーザー型　インスタンスの中身を取り出して作成
	public LoginDto(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.fullName = user.getFullName();
	}
	// 引数　中身の要素　順番に入れて作成
	public LoginDto(long id, String userName, String password, String fullName) {
		this.id = id;;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}
	// 注意userエンティティからコピー
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}

package jp.co.internous.ecsite.model.form;

import java.io.Serializable;

public class LoginForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String user) {
		this.userName = user;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String pass) {
		this.password = pass;
	}
}

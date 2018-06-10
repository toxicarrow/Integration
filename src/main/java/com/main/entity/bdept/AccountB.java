package com.main.entity.bdept;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class AccountB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -787859809615237805L;
	@Id@NotNull
	private String username;//账户名
	private String password;//密码
	private int level;//级别
	private String object;//客体
	
	public void setUsername(String un) {
		username = un;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String pw) {
		password = pw;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setLevel(int l) {
		level = l;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setObject(String o) {
		object = o;
	}
	
	public String getObject() {
		return object;
	
	}
}

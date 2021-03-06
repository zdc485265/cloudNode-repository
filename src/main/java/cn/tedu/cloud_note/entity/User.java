package cn.tedu.cloud_note.entity;

import java.io.Serializable;
import java.util.List;
/*
 * 类属性名和属性数据类型
 * 与数据库cn_user字段名及数据类型保持一致
 */

public class User implements Serializable{
	private String cn_user_id;
	private String cn_user_name;
	private String cn_user_password;
	private String cn_user_token;
	private String cn_user_desc;
	private List<Book> books;
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_user_name() {
		return cn_user_name;
	}
	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}
	public String getCn_user_password() {
		return cn_user_password;
	}
	public void setCn_user_password(String cn_user_password) {
		this.cn_user_password = cn_user_password;
	}
	public String getCn_user_token() {
		return cn_user_token;
	}
	public void setCn_user_token(String cn_user_token) {
		this.cn_user_token = cn_user_token;
	}
	public String getCn_user_desc() {
		return cn_user_desc;
	}
	public void setCn_user_desc(String cn_user_desc) {
		this.cn_user_desc = cn_user_desc;
	}
	@Override
	public String toString() {
		return "User [cn_user_id=" + cn_user_id + ", cn_user_name="
				+ cn_user_name + ", cn_user_password=" + cn_user_password
				+ ", cn_user_token=" + cn_user_token + ", cn_user_desc="
				+ cn_user_desc + ", books=" + books + "]";
	}

}

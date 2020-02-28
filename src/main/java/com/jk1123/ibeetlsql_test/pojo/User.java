package com.jk1123.ibeetlsql_test.pojo;

import org.beetl.sql.core.annotatoin.Table;

/*
* 
* gen by beetlsql 2020-02-27
*/
//@Table(name="interview.user")
public class User  {
	private long id;
	private String username;//用户名
	private String password;//密码


	private Integer pageSize;//分页每页数量
	private Integer tempPageNo;//记录起始查询数据位置

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTempPageNo() {
		return tempPageNo;
	}

	public void setTempPageNo(Integer tempPageNo) {
		this.tempPageNo = tempPageNo;
	}
}

package com.pojo.sys;

import java.io.Serializable;
import java.util.Date;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author childlikeman@gmail.com,http://t.qq.com/lostpig
	*@since 2015-03-25 18:23:38
	*/

public class Sys_user implements Serializable{
   private static final long serialVersionUID = -4694704485801673212L;
   private String user_id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private Date creatdate;
	private int flag;//1：正常状态\r\n2：停用状态
	private String descmsg;
	public String getUser_id(){
		return this.user_id;
	}
	public void setUser_id(String user_id){
		this.user_id=user_id;
	}
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public int getFlag(){
		return this.flag;
	}
	public void setFlag(int flag){
		this.flag=flag;
	}
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public Date getCreatdate() {
      return creatdate;
   }
   public void setCreatdate(Date creatDate) {
      this.creatdate = creatDate;
   }
   public String getDescmsg() {
      return descmsg;
   }
   public void setDescmsg(String descmsg) {
      this.descmsg = descmsg;
   }

}
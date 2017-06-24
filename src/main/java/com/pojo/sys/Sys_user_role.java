package com.pojo.sys;

import java.io.Serializable;

public class Sys_user_role implements Serializable{
   private static final long serialVersionUID = -3887571466453152536L;
   private String user_id;
	private String role_id;
	public String getUser_id(){
		return this.user_id;
	}
	public void setUser_id(String user_id){
		this.user_id=user_id;
	}
	public String getRole_id(){
		return this.role_id;
	}
	public void setRole_id(String role_id){
		this.role_id=role_id;
	}

}
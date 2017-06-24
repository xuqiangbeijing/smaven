package com.pojo.sys;

import java.io.Serializable;

public class Sys_role implements Serializable{
   private static final long serialVersionUID = -1411137109529805723L;
   private String role_id;
	private String role_name;
	private String role_desc;
	public String getRole_id(){
		return this.role_id;
	}
	public void setRole_id(String role_id){
		this.role_id=role_id;
	}
	public String getRole_name(){
		return this.role_name;
	}
	public void setRole_name(String role_name){
		this.role_name=role_name;
	}
	public String getRole_desc(){
		return this.role_desc;
	}
	public void setRole_desc(String role_desc){
		this.role_desc=role_desc;
	}

}
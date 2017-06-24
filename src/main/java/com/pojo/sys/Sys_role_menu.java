package com.pojo.sys;

import java.io.Serializable;

public class Sys_role_menu implements Serializable{
   private static final long serialVersionUID = 4055186083871613799L;
   private String role_id;
	private String menu_id;
	public String getRole_id(){
		return this.role_id;
	}
	public void setRole_id(String role_id){
		this.role_id=role_id;
	}
	public String getMenu_id(){
		return this.menu_id;
	}
	public void setMenu_id(String menu_id){
		this.menu_id=menu_id;
	}

}
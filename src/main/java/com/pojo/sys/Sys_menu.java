package com.pojo.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sys_menu implements Serializable{
   private static final long serialVersionUID = 312589703781351455L;
   private String menu_id;
	private String menu_name;
	private String menu_url;
	private String menu_parent_id;
	private String menu_weight;
	private String menu_desc;
	private String menu_flag;
	private List<Sys_menu> childs=new ArrayList<Sys_menu>();
	private String roleStatus="unselected";
	
	public String getMenu_id(){
		return this.menu_id;
	}
	public void setMenu_id(String menu_id){
		this.menu_id=menu_id;
	}
	public String getMenu_name(){
		return this.menu_name;
	}
	public void setMenu_name(String menu_name){
		this.menu_name=menu_name;
	}
	public String getMenu_url(){
		return this.menu_url;
	}
	public void setMenu_url(String menu_url){
		this.menu_url=menu_url;
	}
	public String getMenu_desc(){
		return this.menu_desc;
	}
	public void setMenu_desc(String menu_desc){
		this.menu_desc=menu_desc;
	}
   public String getMenu_parent_id() {
      return menu_parent_id;
   }
   public void setMenu_parent_id(String menu_parent_id) {
      this.menu_parent_id = menu_parent_id;
   }
   public String getMenu_weight() {
      return menu_weight;
   }
   public void setMenu_weight(String menu_weight) {
      this.menu_weight = menu_weight;
   }
   public String getMenu_flag() {
      return menu_flag;
   }
   public void setMenu_flag(String menu_flag) {
      this.menu_flag = menu_flag;
   }
   public List<Sys_menu> getChilds() {
      return childs;
   }
   public void setChilds(List<Sys_menu> childs) {
      this.childs = childs;
   }
   public String getRoleStatus() {
      return roleStatus;
   }
   public void setRoleStatus(String roleStatus) {
      this.roleStatus = roleStatus;
   }
}
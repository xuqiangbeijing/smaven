/**
 * 
 */
package com.business.dao.sys;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.business.dao.BaseDao;
import com.pojo.sys.Sys_role;
import com.util.Constants;
import com.util.MD5;

@Repository
public class SysRoleDao{
   @Autowired
   private BaseDao dao;
   
   public List<Sys_role> findRoleByName(String rolename,int pagenum) throws SQLException {
      HashMap map = new HashMap();
      map.put("rolename", rolename);
      int start=(pagenum-1)*Constants.records_perpage;
      map.put("start",start<0?0:start);
      map.put("len", Constants.records_perpage);
      List<Sys_role> list=dao.getMultiObjects("sys.findRoleByName", map);
      return list;
   }
   
   public List<Sys_role> getAllRole() throws SQLException {
      List<Sys_role> list=dao.getMultiObjects("sys.getAllRole", null);
      return list;
   }
   
   public Integer getRoleTotalNum(String rolename) throws Exception {
      HashMap map = new HashMap();
      map.put("rolename", rolename);
      return (Integer) dao.getObject("sys.getRoleTotalNum", map);
   }
   
   public Sys_role getRoleMsgByID(String RoleID) throws SQLException {
      HashMap map = new HashMap();
      map.put("roleid", RoleID);
      return (Sys_role) dao.getObject("sys.findRoleByID", map);
   }
   
   public void insertRole(Sys_role role) throws SQLException {
      dao.insert("sys.insertRole", role);
   }
   
   public void updateRole(Sys_role role) throws SQLException {
      dao.update("sys.updateRole", role);
   }
   
   public void deleteRole(Sys_role role) throws Exception {
      dao.delete("sys.deleteRole", role);
   }
   
   public void deleteRoleUser_byrole(Sys_role role) throws Exception {
      dao.delete("sys.deleteRoleUser_byrole", role);
   }
   
   public void deleteRoleMenu(Sys_role role) throws Exception {
      dao.delete("sys.deleteRoleMenu", role);
   }
}

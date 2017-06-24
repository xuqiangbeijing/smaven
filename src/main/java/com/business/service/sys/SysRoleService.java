/**
 * 
 */
package com.business.service.sys;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.dao.sys.LoginDao;
import com.business.dao.sys.SysRoleDao;
import com.pojo.sys.Sys_menu;
import com.pojo.sys.Sys_role;
import com.pojo.sys.Sys_user;
import com.util.Constants;

@Service
public class SysRoleService {
   
   @Autowired
   private SysRoleDao roleDao; 

   public List<Sys_role> roleQueryByName(String rolename,int pagenum) throws Exception {
      return roleDao.findRoleByName(rolename,pagenum);
   }
    
   public List<Sys_role> getAllRole() throws SQLException {
      return roleDao.getAllRole();
   }
   
   public int getRoleTotalNum(String rolename) throws Exception {
      return roleDao.getRoleTotalNum(rolename);
   }
   
   public Sys_role roleQueryByID(String roleid) throws SQLException {
      return roleDao.getRoleMsgByID(roleid);
   }
   
   public void insertRole(Sys_role role) throws SQLException {
      if(role!=null){
         roleDao.insertRole(role);
      }
   }
	
   public void updateRole(Sys_role role) throws SQLException {
      if(role!=null){
         roleDao.updateRole(role);
      }
   }
   
   public void deleteRole(Sys_role role) throws Exception{
      if(role!=null){
         roleDao.deleteRole(role);
         roleDao.deleteRoleUser_byrole(role);
         roleDao.deleteRoleMenu(role);
      }
   }
   
}

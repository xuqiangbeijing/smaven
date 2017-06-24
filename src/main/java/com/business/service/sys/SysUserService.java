/**
 * 
 */
package com.business.service.sys;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.business.dao.sys.SysUserDao;
import com.pojo.sys.Sys_role;
import com.pojo.sys.Sys_user;
import com.pojo.sys.Sys_user_role;
import com.util.Constants;

@Service
public class SysUserService {
   
   @Autowired
   private SysUserDao userDao; 

   public List<Sys_user> userQueryByName(String username, int currpage) throws SQLException {
      return userDao.findUserByName(username,currpage);
   }
   
   public Sys_user userQueryByID(String userid) throws SQLException {
      return userDao.findUserByID(userid);
   }
   
   public int getUserTotalNum(String queryname) throws SQLException {
      // TODO Auto-generated method stub
      return userDao.getUserTotalNum(queryname);
   }
   
   public void insertUser(Sys_user user) throws SQLException {
      if(user!=null){
         userDao.insertUser(user);
      }
   }
	
   public void updateUser(Sys_user user) throws SQLException {
      if(user!=null){
         userDao.updateUser(user);
      }
   }
   
   public void deleteUser(Sys_user user) throws Exception{
      if(user!=null){
         userDao.deleteUser(user);
         userDao.deleteRoleUser(user);
      }
   }
   
   public List<Sys_role> getUserRoleByUserID(String userid) throws Exception {
      return userDao.getUserRolesByUserID(userid);
   }
   
   public void updateUserRole(String[] roleid, String userid) throws Exception {
      Sys_user su = new Sys_user();
      su.setUser_id(userid);
      userDao.deleteRoleUser(su);
      List<Sys_user_role> list = new ArrayList<Sys_user_role>();
      for(String rr:roleid){
         Sys_user_role sur = new Sys_user_role();
         sur.setRole_id(rr);
         sur.setUser_id(userid);
         list.add(sur);
      }
      userDao.InsertUserRoleBatch(list);
   }
}

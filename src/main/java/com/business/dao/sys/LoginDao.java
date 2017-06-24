/**
 * 
 */
package com.business.dao.sys;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.business.dao.BaseDao;
import com.util.MD5;
import com.pojo.sys.Sys_menu;
import com.pojo.sys.Sys_user;

@Repository
public class LoginDao{
   @Autowired
   private BaseDao dao;

   public Sys_user findUserByNameAndPass(Sys_user user) throws SQLException {
      String pass=user.getPassword();
      MD5 md5=new MD5();
      
      user.setPassword(md5.getMD5ofStr(pass));
      return (Sys_user)dao.getObject("sys.checklogin", user);
   }
   
   //根据用户ID查询此用户对应的菜单
   public List<Sys_menu> getMenusByUserID(String userID) throws SQLException {
      return (List<Sys_menu>)dao.getMultiObjects("sys.getMenusByUserID", userID);
   }
   
   
   public Sys_menu getMenuById(String id) throws SQLException {
       return (Sys_menu)dao.getObject("sys.getMenuById", id);
   }

   //通过menuWeight排序菜单
   public List<Sys_menu> getChildMenusByParentId(String parentId) throws SQLException {
       return (List<Sys_menu>)dao.getMultiObjects("sys.getChildMenusByParentId", parentId);
   }
}

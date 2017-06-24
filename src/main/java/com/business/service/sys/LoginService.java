/**
 * 
 */
package com.business.service.sys;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.dao.sys.LoginDao;
import com.pojo.sys.Sys_menu;
import com.pojo.sys.Sys_user;

@Service
public class LoginService {
   
   @Autowired
   private LoginDao loginDao; 

   public Sys_user verify(Sys_user user) throws SQLException {
      return loginDao.findUserByNameAndPass(user);
   }

//   @Override
//   public SysUser findByName(SysUser user) {
//      // TODO Auto-generated method stub
//      List list=loginDao.findUserByName(user.getUserName(),1);
//      if(list!=null && list.size()>0){
//         return (SysUser) list.get(0);
//      }else return null;
//   }
//
   //根据用户ID查询此用户对应的菜单
   public List<Sys_menu> getUserMenu(Sys_user user) throws SQLException {
      List<Sys_menu> list=loginDao.getMenusByUserID(user.getUser_id());
      return list;
   }
//
//   //通过用户ID获取此用户对应的角色信息
//   @Override
//   public List<SysRole> getUserRole(String userID) {
//      return loginDao.getUserRolesByUserID(userID);
//   }
//
//   //通过角色ID获取当前角色对应菜单
//   @Override
//   public List<SysMenu> getMenuByRoleID(String roleID) {
//      // TODO Auto-generated method stub
//      return loginDao.getMenuMsgByRoleID(roleID);
//   }
	
}

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
import com.pojo.sys.Sys_user;
import com.pojo.sys.Sys_user_role;
import com.util.Constants;
import com.util.MD5;

@Repository
public class SysUserDao{
   @Autowired
   private BaseDao dao;
   
    public List<Sys_user> findUserByName(String username,int currpage) throws SQLException {
       HashMap map = new HashMap();
       map.put("username", username);
       int start=(currpage-1)*Constants.records_perpage;
       map.put("start",start<0?0:start);
       map.put("len", Constants.records_perpage);
       List<Sys_user> list=dao.getMultiObjects("sys.findUserByName", map);
       return list;
   }
   public Sys_user findUserByID(String userid) throws SQLException {
      HashMap map = new HashMap();
      map.put("userid", userid);
      return (Sys_user) dao.getObject("sys.findUserByID", map);
   }
   public Integer getUserTotalNum(String queryname) throws SQLException {
      HashMap map = new HashMap();
      map.put("username", queryname);
      return (Integer) dao.getObject("sys.getUserTotalNum", map);
   }
   
   public void insertUser(Sys_user user) throws SQLException {
      //事务
      MD5 md5=new MD5();
      user.setPassword(md5.getMD5ofStr(user.getPassword()));
      dao.insert("sys.insertUser", user);
   }
//
   public void deleteUser(Sys_user user) throws Exception {
      dao.delete("sys.deleteUser", user);
   }
   public void deleteRoleUser(Sys_user user) throws Exception {
      dao.delete("sys.deleteRoleUser", user);
   }
   
   
   public List<Sys_role> getUserRolesByUserID(String userID) throws Exception {
      HashMap map = new HashMap();
      map.put("userID", userID);
      List<Sys_role> list=dao.getMultiObjects("sys.getUserRolesByUserID", map);
      return list;
   }
   
   public void updateUser(Sys_user user) throws SQLException {
      MD5 md5=new MD5();
      user.setPassword(md5.getMD5ofStr(user.getPassword()));
      dao.update("sys.updateUser", user);
   }
   
   public void InsertUserRoleBatch(List<Sys_user_role> sur) throws Exception {
      dao.batchInsert("sys.insertUserRole", sur);
   }
//
//   public int getUserTotalNum(String username) {
//      String cond="".equals(username)?"":" and user.userName='"+username+"'";
//      String hql="from SysUser user where 1=1 "+cond;
//      List list=new ArrayList();
//      list=this.getHibernateTemplate().find(hql);
//      return list.size();
//   }
//
//   public List<SysRoleUser> getRoleUserByUserID(String userID) {
//      String hql="from SysRoleUser roleuser where roleuser.userId='"+userID+"'";
//      List<SysRoleUser> list=this.getHibernateTemplate().find(hql);
//      return list;
//   }
//
//
//   public SysUser findUserByID(String userid) {
//      SysUser sysuser= (SysUser) this.getHibernateTemplate().get(SysUser.class,userid);
//      return sysuser;
//   }
//
//
//   public SysRole getRoleMsgByID(String RoleID) {
//      SysRole sysrole= (SysRole) this.getHibernateTemplate().get(SysRole.class,RoleID);
//      return sysrole;
//   }
//
//
//   public SysRoleMenu getRoleMenuByRoleID(String RoleID) {
//      SysRoleMenu rolemenu= (SysRoleMenu) this.getHibernateTemplate().get(SysRoleMenu.class,RoleID);
//      return rolemenu;
//   }
//
//   
//
//   public SysMenu getMenuByMenuID(String MenuID) {
//      SysMenu menu= (SysMenu) this.getHibernateTemplate().get(SysMenu.class,MenuID);
//      return menu;
//   }
//
//   /* 
//    * 根据用户ID查询此用户对应的菜单
//    * @see com.sys.dao.UserDAO#getMenusByUserID(java.lang.String)
//    */
//
//   public List<SysMenu> getMenusByUserID(String userID) {
//      /*Query query=this.getSession().createSQLQuery("select * from SYS_MENU menu where menu.MENU_ID in("+
//            "select distinct rolemenu.MENU_ID from SYS_ROLE_MENU rolemenu,SYS_ROLE_USER roleuser "+
//            "where rolemenu.ROLE_ID=roleuser.ROLE_ID and roleuser.USER_ID='"+userID+"')");
//      List<SysMenu> list= query.list();*/
//      
//      String hql="from SysMenu menu where menu.menuId in(select rolemenu.menuId from SysRoleMenu rolemenu,SysRoleUser roleuser " +
//            " where rolemenu.roleId=roleuser.roleId and roleuser.userId='"+userID+"') and menu.menuFlag=1";
//      List<SysMenu> list=this.getHibernateTemplate().find(hql);
//      return list;
//   }
//
//   /* (non-Javadoc)
//    * 通过用户ID获取此用户对应的角色信息
//    * @see com.sys.dao.UserDAO#getUserRolesByUserID(java.lang.String)
//    */
//   @SuppressWarnings("unchecked")
//
//
//   /* 
//    * 通过角色ID获取此角色对应菜单
//    * @see com.sys.dao.UserDAO#getMenuMsgByRoleID(java.lang.String)
//    */
//
//   public List<SysMenu> getMenuMsgByRoleID(String roleID) {
//      List<SysMenu> menus=this.getHibernateTemplate().find("from SysMenu menu where menu.menuId in("+
//            "select rolemenu.menuId from SysRoleMenu rolemenu where rolemenu.roleId='"+roleID+"')");
//      
//      return menus;
//   }
//

//
//
//   public void insertRole(SysRole role) {
//      this.getHibernateTemplate().save(role);
//   }
//
//
//   public int deleteRolesByIDs(List list) {
//      // TODO Auto-generated method stub
//      return 0;
//   }
//
//
//   public void updateRole(SysRole role) {
//      this.getHibernateTemplate().update(role);
//   }
//
//
//   public List<SysRole> findRoleByName(String rolename,int pagenum) {
//      // TODO Auto-generated method stub
//      String cond="".equals(rolename)?"":" and role.roleName='"+rolename+"'";
//      String hql="from SysRole role where 1=1 "+cond+" order by role.roleName";
//      //List<SysRole> list=this.getHibernateTemplate().find(hql);
//      List<SysRole> list=findOnePage(hql,pagenum,SystemConstants.RECORDS_PerPage);
//      return list;
//   }
//
//
//   public int getRoleTotalNum(String rolename) {
//      String cond="".equals(rolename)?"":" and role.roleName='"+rolename+"'";
//      String hql="from SysRole role where 1=1 "+cond;
//      List list=new ArrayList();
//      list=this.getHibernateTemplate().find(hql);
//      return list.size();
//   }
//   
//
//   public int deleteRole(SysRole role) {
//      //事务控制    return 0:失败   1：成功
//      int ret=SystemConstants.RETURN_FAIL;
//      SessionFactory factory=this.getSessionFactory();
//      Session session=factory.openSession();
//      Transaction tx=session.beginTransaction();
//      try{
//         session.delete(role);
//         String del="delete from sys_role_user roleuser where roleuser.role_id=:id";
//         session.createSQLQuery(del).setParameter("id", role.getRoleId()).executeUpdate();
//         String delrolemenu="delete from SYS_ROLE_MENU rolemenu where rolemenu.role_id=:id";
//         session.createSQLQuery(delrolemenu).setParameter("id", role.getRoleId()).executeUpdate();
//         tx.commit();
//         ret=SystemConstants.RETURN_SUCCESS;
//      }catch (Exception e) {
//         tx.rollback();
//         ret=SystemConstants.RETURN_FAIL;
//      }finally{
//         session.close();
//         factory.close();
//      }
//      return ret;
//   }
//
//
//   public List<SysMenu> getAllMenu(String flag) {
//      List<SysMenu> list=new ArrayList<SysMenu>();
//      if("0".equals(flag)){//显示全部，包括停用的  菜单管理页面使用
//         list=this.getHibernateTemplate().find("from SysMenu menu where 1=1");
//      }else{//只显示启用的，角色权限管理页面使用
//         list=this.getHibernateTemplate().find("from SysMenu menu where menu.menuFlag=1");
//      }
//      return list;
//   }
//
//
//   public int updateRoleMenu(String roleid, String[] roleMenus) {
//      // TODO Auto-generated method stub
//      int ret=SystemConstants.RETURN_FAIL;
//      SessionFactory factory=this.getSessionFactory();
//      Session session=factory.openSession();
//      Transaction tx=session.beginTransaction();
//      try{
//         String del="delete from SYS_ROLE_MENU rolemenu where rolemenu.ROLE_ID=:roleid";
//         session.createSQLQuery(del).setParameter("roleid", roleid).executeUpdate();
//         
//         for(String menuid:roleMenus){
//            String insertSQL="insert into SYS_ROLE_MENU (keyid,ROLE_ID,MENU_ID) values(" +
//                  "'"+UUID.randomUUID().toString()+"','"+roleid+"','"+menuid+"')";
//            session.createSQLQuery(insertSQL).executeUpdate();
//         }
//         tx.commit();
//         ret=SystemConstants.RETURN_SUCCESS;
//      }catch (Exception e) {
//         tx.rollback();
//         ret=SystemConstants.RETURN_FAIL;
//      }finally{
//         session.close();
//         factory.close();
//      }
//      return ret;
//   }
//
//   public void updateMenu(SysMenu menu) {
//      this.getHibernateTemplate().update(menu);
//   }
//
//   public void insertMenu(SysMenu menu) {
//      // TODO Auto-generated method stub
//      this.getHibernateTemplate().save(menu);
//   }
//
//   public int deleteMenu(String menuid) {
//      // TODO Auto-generated method stub
//      int ret=SystemConstants.RETURN_FAIL;
//      SessionFactory factory=this.getSessionFactory();
//      Session session=factory.openSession();
//      Transaction tx=session.beginTransaction();
//      try{
//         String del="delete from SYS_MENU menu where menu.MENU_ID=:id or menu.MENU_PARENT_ID=:parentid";
//         session.createSQLQuery(del).setParameter("id", menuid).setParameter("parentid", menuid).executeUpdate();
//         String delrolemenu="delete from SYS_ROLE_MENU rolemenu where rolemenu.MENU_ID=:id";
//         session.createSQLQuery(delrolemenu).setParameter("id", menuid).executeUpdate();
//         tx.commit();
//         ret=SystemConstants.RETURN_SUCCESS;
//      }catch (Exception e) {
//         tx.rollback();
//         ret=SystemConstants.RETURN_FAIL;
//      }finally{
//         session.close();
//         factory.close();
//      }
//      return ret;
//   }
}

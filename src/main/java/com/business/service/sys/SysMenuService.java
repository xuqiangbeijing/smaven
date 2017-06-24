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
import com.pojo.sys.Sys_menu;
import com.pojo.sys.Sys_user;

@Service
public class SysMenuService {
   
   @Autowired
   private LoginDao userDao; 

   public Sys_menu getUserMenuTree(HashMap<String, Sys_menu> map) throws SQLException {
      //获取根节点  根节点在数据库中ID必须为0
      Sys_menu rootMenu = (Sys_menu) userDao.getMenuById("0");
      if (rootMenu == null) {
         //初始化菜单  待
         return null;
      } else {
         rootMenu = getMenuTree(rootMenu, map);
      }
      return rootMenu;
   }
   
   private Sys_menu getMenuTree(Sys_menu menu,HashMap map) throws SQLException{
      if (menu == null)
         return menu;
      List<Sys_menu> childs = userDao.getChildMenusByParentId(menu.getMenu_id());
      if (childs != null && childs.size() > 0) {
         for (Sys_menu sm : childs) {
            sm = getMenuTree(sm, map);
            if (map.get(sm.getMenu_id()) != null) {
               menu.getChilds().add(sm);
            } else {
               if (hasChildrenToShow(sm, map)) {
                  sm.setMenu_url("#");
                  menu.getChilds().add(sm);
               }
            }
         }
      }
      return menu;
   }
   
   private boolean hasChildrenToShow(Sys_menu menu, Map<String, Sys_menu> smm) throws SQLException {
      List<Sys_menu> childs = userDao.getChildMenusByParentId(menu.getMenu_id());
      if (childs == null || childs.isEmpty()) {
         return false;
      } else {
         for (Sys_menu sm : childs) {
            if (smm.get(sm.getMenu_id()) != null) {
               return true;
            }
         }
         for (Sys_menu sm : childs) {
            return hasChildrenToShow(sm, smm);
         }
      }
      return false;
   }

   /*
//    * 获取全部菜单    0:包括停用的  1:只取启用的  
//    * @see com.sys.service.Sys_menuService#getAllMenuTree()
//    */
//   public Sys_menu getAllMenuTree(String flag) {
//      List<Sys_menu> list = userDao.getAllMenu(flag);
//      HashMap allMenuMap=new HashMap();
//      for(Sys_menu menu:list){
//         allMenuMap.put(menu.getMenu_id(), menu);
//      }
//      Sys_menu allMenu=getUserMenuTree(allMenuMap);
//      
//      return allMenu;
//   }
//
//   
//   public List<HashMap<String, List<Sys_menu>>> getMenuForRole(Sys_menu menu,List<Sys_menu> selectedMenus) {
//      List<HashMap<String, List<Sys_menu>>> ret=new ArrayList<HashMap<String, List<Sys_menu>>>();
//      
//      HashMap selected=new HashMap();
//      for(Sys_menu currmenu:selectedMenus){
//         selected.put(currmenu.getMenu_name(), "selected");
//      }
//      
//      List<Sys_menu> list=menu.getChilds();
//      for(Sys_menu child:list){//每个一级菜单对应一个map
//         HashMap<String, List<Sys_menu>> map=new HashMap<String, List<Sys_menu>>();
//         if(child.getChilds().size()>0){//此节点下所有的叶子节点
//            List<Sys_menu> show=new ArrayList<Sys_menu>();
//            getMenuAllChild(child,show,selected);
//            map.put(child.getMenu_name(), show);
//         }else{//本身是叶子节点
//            List<Sys_menu> show=new ArrayList<Sys_menu>();
//            if(selected.get(child.getMenu_name())!=null && selected.get(child.getMenu_name()).toString().equals("selected")){
//               child.setRoleStatus("selected");
//            }
//            //if(!"#".equals(child.getMenuUrl())){
//               show.add(child);
//               map.put(child.getMenu_name(), show);
//            //}
//         }
//         
//         ret.add(map);
//      }
//      
//      return ret;
//   }
//   
//   private void getMenuAllChild(Sys_menu menu,List<Sys_menu> show, HashMap selected){
//      List<Sys_menu> list=menu.getChilds();
//      for(Sys_menu child:list){
//         if(child.getChilds().size()>0){//此节点下所有的叶子节点
//            getMenuAllChild(child,show,selected);
//         }else{//本身是叶子节点
//            if(selected.get(child.getMenu_name())!=null && selected.get(child.getMenu_name()).toString().equals("selected")){
//               child.setRoleStatus("selected");
//            }
//            //if(!"#".equals(child.getMenuUrl())){
//               show.add(child);
//            //}
//         }
//      }
//   }

   
//   public void formatJsonObject(List<Sys_menu> childs,
//         List<JSONObject> jsonObjectList) {
//      for(Sys_menu child : childs){
//         JSONObject jsonObject = new JSONObject();
//         jsonObject.put("id", child.getMenu_id());
//         jsonObject.put("pId", child.getMenu_parent_id());
//         jsonObject.put("name", child.getMenu_name());
//         jsonObjectList.add(jsonObject);
//         if(child.getChilds().size()>0){
//            formatJsonObject(child.getChilds(),jsonObjectList);
//         }
//      }
//   }

   
//   public void updateMenu(Sys_menu menu) {
//      userDao.updateMenu(menu);
//   }
//
//   
//   public void insertMenu(Sys_menu menu) {
//      userDao.insertMenu(menu);
//   }
//
//   
//   public int deleteMenu(String menuid) {
//      return userDao.deleteMenu(menuid);
//   }
//
//   public Sys_menu getMenuByID(String menuID) {
//      return userDao.getMenuByMenuID(menuID);
//   }
	
}

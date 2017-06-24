package com.business.controller.sys;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.business.dao.RedisUtil;
import com.business.dao.RestClient;
import com.business.service.sys.LoginService;
import com.business.service.sys.SysMenuService;
import com.pojo.sys.Sys_menu;
import com.pojo.sys.Sys_user;

@Controller
@RequestMapping(value="/login")
public class loginAction {
   private static Logger logger = Logger.getLogger(loginAction.class);
   @Autowired
   private LoginService loginService;
   @Autowired
   private SysMenuService menuService;

   /*@Autowired
   private RedisUtil redis;*/
   
   @RequestMapping(value="/enter",method=RequestMethod.POST)
   public String login(HttpServletRequest request,Sys_user user){
      try{
         if (user.getUsername() == null || user.getUsername().trim().length() == 0) {
            request.setAttribute("message", "用户名不能为空！");
            return "login";
         }
         if (user.getPassword() == null ||user.getPassword().trim().length() == 0) {
            request.setAttribute("message", "登录密码不能为空！");
            return "login";
         }
         
         /*System.out.print("redis get result:"+redis.get("xu", "11"));
         redis.del("xu");
         System.out.print("redis get result:"+redis.get("xu", "11"));
         
         redis.set("goal", "goal");
         redis.set("123", "123");
         System.out.print("redis get result:"+redis.get("123", "11"));
         System.out.print("redis get result:"+redis.get("goal", "11"));*/
         
         
         Sys_user sysuser=loginService.verify(user);
         if(sysuser!=null){
            request.getSession().setAttribute("_user", sysuser);
            //角色菜单等信息
            List<Sys_menu> list=loginService.getUserMenu(sysuser);

            if(list!=null && list.size()>0){
               request.getSession().setAttribute("_menu", list);
            }else{
               request.setAttribute("message","菜单获取失败！");
            }
            return "forward:/login/showMenuByRole";
         }else {
            request.setAttribute("message","用户名或密码不正确");
         }
      }catch(Exception e){
         e.printStackTrace();
         logger.error(e.getCause());
         request.setAttribute("message","网络问题或服务器不能连接上数据库，请先尝试重启服务器，再登录！");
      }
      return "login";
   }
   
   @RequestMapping(value="/showMenuByRole")
   public String showMenuByRole(HttpServletRequest request){
      List<Sys_menu> list=(List<Sys_menu>) request.getSession().getAttribute("_menu");
      HashMap<String,Sys_menu> map=new HashMap<String, Sys_menu>();
      if(null==list || list.size()==0){
         request.setAttribute("message","用户权限未设置，生成菜单失败！");
      }else{
         for(Sys_menu menu:list){
            map.put(menu.getMenu_id(), menu);
         }
      }
      try {
         Sys_menu sysmenu = menuService.getUserMenuTree(map);
         if(sysmenu!=null){
            request.getSession().setAttribute("_menuTree", sysmenu);
            request.getSession().removeAttribute("_menu");
         }else{
            request.setAttribute("message","生成菜单失败！");
         }
         request.setAttribute("menu_tree",sysmenu);
         return "main/menu";
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "login";
   }
   
   @RequestMapping(value="/loginout")
   @ResponseBody
   public String logout(HttpServletRequest request){
      HttpSession session =  request.getSession();
      session.removeAttribute("_user");
      session.removeAttribute("_menu");
      session.removeAttribute("_menuTree");
      return "true";
  }
}

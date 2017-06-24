package com.business.controller.sys;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.business.service.sys.SysRoleService;
import com.business.service.sys.SysUserService;
import com.pojo.sys.Sys_role;
import com.pojo.sys.Sys_user;
import com.util.Constants;

@Controller
@RequestMapping(value="/sys/user")
public class UserAction {
   private static Logger logger = Logger.getLogger(UserAction.class);
   @Autowired
   private SysUserService userService;
   @Autowired
   private SysRoleService roleService;
   
   @RequestMapping(value = "/userindex")
   public String userIndex() {
      return "manage/userManagerIndex";
   }
   
   @RequestMapping(value = "/userQuery", method = RequestMethod.POST, produces = "application/json")
   @ResponseBody
   public HashMap userQuery(@RequestBody Map<String,Object> map) {//@RequestBody 接收前台传的json
      String queryname=map.get("username")==null?"":map.get("username").toString();
      String currpage=map.get("currpage")==null?"1":map.get("currpage").toString();
      HashMap rst = new HashMap();
      try {
         List<Sys_user> list = userService.userQueryByName(queryname,Integer.parseInt(currpage));
         //应该避免每次都算total 前台传total，若无值便查询
         int total = userService.getUserTotalNum(queryname);
         int totalPageNum=total%Constants.records_perpage==0?total/Constants.records_perpage:total/Constants.records_perpage+1;
         rst.put("pagenum", currpage);
         rst.put("totalPageNum", totalPageNum);
         rst.put("users", list);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return rst;
   }
   
   @RequestMapping(value = "/addUserPage")
   public String addUserPage() {
      return "manage/addUserPage";
   }
   
   @RequestMapping(value = "/checkInsertUser", method = RequestMethod.POST, produces = "application/json")
   @ResponseBody
   public int checkInsertUser(HttpServletRequest request) throws Exception{
      String name=request.getParameter("parmvalue");
      if("".equals(name)){
         return 0;
      }else{
         List<Sys_user> list=userService.userQueryByName(name,1);
         int ret=list==null?0:list.size();
         return ret;
      }
   }
   
   @RequestMapping(value="/addUser",method=RequestMethod.POST)
   public String addUser(HttpServletRequest request,Sys_user user){
      user.setUser_id(UUID.randomUUID().toString());
      user.setCreatdate(new Date());      
      try {
         userService.insertUser(user);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "redirect:/sys/user/userindex";
   }
   
   @RequestMapping(value="/updateUserPage")
   public String updateUserPage(HttpServletRequest request){
      try {
         Sys_user sysuser = userService.userQueryByID(request.getParameter("updateUserID"));
         request.setAttribute("updateMsg", sysuser);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "manage/updateUserPage";
   }
   
   @RequestMapping(value="/updateUser",method=RequestMethod.POST)
   public String updateUser(Sys_user user){
      try {
         userService.updateUser(user);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "redirect:/sys/user/userindex";
   }
   
   @RequestMapping(value="/deleteUser",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
   @ResponseBody
   public String deleteUser(HttpServletRequest request){
      //删除用户关联的角色信息表相关内容
      String userid=request.getParameter("userid");
      Sys_user del=new Sys_user();
      del.setUser_id(userid);
      int ret = 1;
      try {
         userService.deleteUser(del);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         ret=0;
      }
      String msg=ret==Constants.RETURN_SUCCESS?"删除成功":"删除失败";
      return msg;
   }
   
   @RequestMapping(value="/userAddRole")
   public String userAddRole(HttpServletRequest request){
      String userid=request.getParameter("userID");
      try {
         //获取用户对应的角色
         List<Sys_role> userrole=userService.getUserRoleByUserID(userid);
         //剩余的角色
         List<Sys_role> allRole=roleService.getAllRole();
         
         request.setAttribute("userRoles", userrole);
         request.setAttribute("allRoles", allRole);
         request.setAttribute("userid", userid);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return "manage/userAddRole";
   }
   
   @RequestMapping(value="/userRoleUpdate",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
   @ResponseBody
   public String userRoleUpdate(HttpServletRequest request){
      String selectRoleStr=request.getParameter("userlistAll");
      String userid=request.getParameter("userid");
      
      if(selectRoleStr.length()>1){
         String[] roleids=selectRoleStr.split(":");
         try {
            userService.updateUserRole(roleids, userid);
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "更新失败！";
         }
         return "1";
      }else{//失败
         return "请至少选择一个角色！";
      }
   }
   
}

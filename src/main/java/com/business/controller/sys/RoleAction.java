package com.business.controller.sys;

import java.sql.SQLException;
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
import com.pojo.sys.Sys_role;
import com.util.Constants;

@Controller
@RequestMapping(value="/sys/role")
public class RoleAction {
   private static Logger logger = Logger.getLogger(RoleAction.class);
   @Autowired
   private SysRoleService roleService;
   
   @RequestMapping(value = "/roleindex")
   public String roleindex() {
      return "manage/roleManagerIndex";
   }
   
   @RequestMapping(value = "/queryRole", method = RequestMethod.POST, produces = "application/json")
   @ResponseBody
   public HashMap queryRole(@RequestBody Map<String,Object> map) {//@RequestBody 接收前台传的json
      String queryname=map.get("rolename")==null?"":map.get("rolename").toString();
      String currpage=map.get("currpage")==null?"1":map.get("currpage").toString();
      HashMap rst = new HashMap();
      try {
         List<Sys_role> list = roleService.roleQueryByName(queryname,Integer.parseInt(currpage));
         int total = roleService.getRoleTotalNum(queryname);
         int totalPageNum=total%Constants.records_perpage==0?total/Constants.records_perpage:total/Constants.records_perpage+1;
         rst.put("pagenum", currpage);
         rst.put("totalPageNum", totalPageNum);
         rst.put("roles", list);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return rst;
   }
   
   @RequestMapping(value = "/addRolePage")
   public String addRolePage() {
      return "manage/addRolePage";
   }
   
   @RequestMapping(value = "/checkInsertRole", method = RequestMethod.POST, produces = "application/json")
   @ResponseBody
   public int checkInsertRole(HttpServletRequest request) throws Exception{
      String name=request.getParameter("parmvalue");
      if("".equals(name)){
         return 0;
      }else{
         List<Sys_role> list=roleService.roleQueryByName(name,1);
         int ret=list==null?0:list.size();
         return ret;
      }
   }
   
   @RequestMapping(value="/addRole",method=RequestMethod.POST)
   public String addRole(HttpServletRequest request,Sys_role role){
      role.setRole_id(UUID.randomUUID().toString());
      try {
         roleService.insertRole(role);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "redirect:/sys/role/roleindex";
   }
   
   @RequestMapping(value="/updateRolePage")
   public String updateRolePage(HttpServletRequest request){
      try {
         Sys_role sysrole = roleService.roleQueryByID(request.getParameter("updateRoleID"));
         request.setAttribute("updateMsg", sysrole);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return "manage/updateRolePage";
   }
   
   @RequestMapping(value="/updateRole",method=RequestMethod.POST)
   public String updateRole(Sys_role role){
      try {
         roleService.updateRole(role);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return "redirect:/sys/role/roleindex";
   }
   
   @RequestMapping(value="/deleteRole",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
   @ResponseBody
   public String deleteRole(HttpServletRequest request){
      //删除用户关联的角色信息表相关内容
      String roleid=request.getParameter("roleid");
      Sys_role del=new Sys_role();
      del.setRole_id(roleid);
      int ret = 1;
      try {
         roleService.deleteRole(del);
      } catch (Exception e) {
         e.printStackTrace();
         ret=0;
      }
      String msg=ret==Constants.RETURN_SUCCESS?"删除成功":"删除失败";
      return msg;
   }
}

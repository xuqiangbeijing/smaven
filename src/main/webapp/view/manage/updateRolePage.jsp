<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="<%=basePath%>res/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

function updateRole(){
	$("#addRoleForm").attr("action","<%=basePath%>sys/role/updateRole");
	$("form").submit();
}

function back(){
	$("#addRoleForm").attr("action","<%=basePath%>sys/role/roleindex");
	$("form").submit();
}
</script>
  </head>
  
  <body>
  	<form id="addRoleForm" action="" method="post">
	    <input type="hidden" name="role_id" value="${updateMsg.role_id}"/>
	    <table width="90%" cellspacing="0" cellpadding="0"  border="0" >
	    	<tr>
	    		<td>角色名：</td>
	    		<td><input name="role_name" value="${updateMsg.role_name}"></td>
	    	</tr>
	    	<tr>
	    		<td>描述：</td>
	    		<td><textarea name="role_desc" rows="6">${updateMsg.role_desc}</textarea></td>
	    	</tr>
	    </table>
	    <input type="button" value="提交" onclick="updateRole()"/>&nbsp;&nbsp;
	    <input type="button" onclick="back()" value="返回"/>
    </form>
  </body>
</html>

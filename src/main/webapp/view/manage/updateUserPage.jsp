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

function updateUser(){
	if($("#password").val()==""){
		alert("密码不能为空！");
	}else{
		$("#addUserForm").attr("action","<%=basePath%>sys/user/updateUser");
		$("form").submit();
	}
}

function checkPass(){
	if($("#password").val()!=$("#confirmPassword").val()){
		alert("两次密码输入不一致，请重新输入！");
	}
}

function back(){
	$("#addUserForm").attr("action","<%=basePath%>sys/user/userindex");
	$("form").submit();
}
</script>
  </head>
  
  <body>
  	<form id="addUserForm" action="" method="post">
	    <input type="hidden" name="user_id" value="${updateMsg.user_id}"/>
	    <input type="hidden" name="creatdate" value="${updateMsg.creatdate}"/>
	    <table width="90%" cellspacing="0" cellpadding="0"  border="0" >
	    	<tr>
	    		<td>用户名：</td>
	    		<td><input id="username" name="username" readonly="readonly" value="${updateMsg.username}"/></td>
	    	</tr>
	    	<tr>
	    		<td>密码：</td>
	    		<td><input id="password" name="password" maxlength="36" type="password"></td>
	    	</tr>
	    	<tr>
	    		<td>确认密码：</td>
	    		<td><input id="confirmPassword" maxlength="36" onblur="checkPass()" type="password"></td>
	    	</tr>
	    	<tr>
	    		<td>Email：</td>
	    		<td><input name="email" maxlength="50" value="${updateMsg.email}"></td>
	    	</tr>
	    	<tr>
	    		<td>电话号码：</td>
	    		<td><input name="phone" maxlength="15" onkeyup="if(isNaN(value))execCommand('undo')" value="${updateMsg.phone}"></td>
	    	</tr>
	    	<tr>
	    		<td>描述：</td>
	    		<td><textarea name="descmsg" rows="6">${updateMsg.descmsg}</textarea></td>
	    	</tr>
	    </table>
	    <input type="button" value="提交" onclick="updateUser()"/>&nbsp;&nbsp;
	    <input type="button" onclick="back()" value="返回"/>
    </form>
  </body>
</html>

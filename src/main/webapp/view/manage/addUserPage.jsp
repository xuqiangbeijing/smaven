<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="<%=basePath%>res/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

function addNew(){
	$("#addUserForm").attr("action","<%=basePath%>sys/user/addUser");
	$("form").submit();
}

function check(){
	var parmName=$("#username").val();
	jQuery.ajax({
		url:"<%=basePath%>sys/user/checkInsertUser",
		cache:false,
		type:'post',
		dataType:'text',
		data:{
			parmvalue:parmName
		},
		success:showRst,
		error:function(){
			alert("查询出错！");
		}
	}); 
}

function showRst(ret){
	if(ret>0){
		alert("用户已存在！");
		$("#username").focus();
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
	    <table width="90%" cellspacing="0" cellpadding="0"  border="0" >
	    	<tr>
	    		<td>用户名：</td>
	    		<td><input id="username" name="username" onblur="check()" maxLength="20"></td>
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
	    		<td><input name="email" maxlength="50"></td>
	    	</tr>
	    	<tr>
	    		<td>电话号码：</td>
	    		<td><input name="phone" maxlength="15" onkeyup="if(isNaN(value))execCommand('undo')"></td>
	    	</tr>
	    	<tr>
	    		<td>描述：</td>
	    		<td><textarea name="descmsg" rows="6"></textarea></td>
	    	</tr>
	    </table>
	    <input type="button" value="提交" onclick="addNew()"/>&nbsp;&nbsp;
	    <input type="button" onclick="back()" value="返回"/>
    </form>
  </body>
</html>

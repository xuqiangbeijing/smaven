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
	$("#addRoleForm").attr("action","<%=basePath%>sys/role/addRole");
	$("form").submit();
}

function check(){
	var parmName=$("#role_name").val();
	jQuery.ajax({
		url:"<%=basePath%>sys/role/checkInsertRole",
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
		alert("角色已存在！");
		$("#role_name").focus();
	}
}

function back(){
	$("#addRoleForm").attr("action","<%=basePath%>sys/role/roleindex");
	$("form").submit();
}
</script>
  </head>
  
  <body>
  	<form id="addRoleForm" action="" method="post">
	    <table width="90%" cellspacing="0" cellpadding="0"  border="0" >
	    	<tr>
	    		<td>角色名：</td>
	    		<td><input id="role_name" name="role_name" maxlength="20"  onblur="check()"/></td>
	    	</tr>
	    	<tr>
	    		<td>描述：</td>
	    		<td><textarea name="role_desc" rows="6"></textarea></td>
	    	</tr>
	    </table>
	    <input type="button" value="提交" onclick="addNew()"/>&nbsp;&nbsp;
	    <input type="button" onclick="back()" value="返回"/>
    </form>
  </body>
</html>

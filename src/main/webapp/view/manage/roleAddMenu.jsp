<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.HashMap"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	HashMap selected = new HashMap();
	selected=(HashMap) request.getAttribute("selected");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.5.1.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/style.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>css/css.css" type="text/css">
<script type="text/javascript">

function updateRoleMenu(){
	var roleID=$("#roleid").val();
	var menus=document.getElementsByName("checkvalue");
	var checkedMenu="";
	for(var i=0;i<menus.length;i++){    
		if(menus(i).checked){
			checkedMenu+=menus(i).value+";";
		}
	}
    jQuery.ajax({
		url:"<%=basePath%>manager/manager_roleMenuUpdate.action",
		cache:false,
		type:'post',
		dataType:'text',
		data:{
			roleid:roleID,
			checkedMenu:checkedMenu
		},
		success:showUpdateRst,
		error:function(){
			alert("更新出错！");
		}
	}); 
}

function showUpdateRst(ret){
	if(ret==1){
		alert("更新成功！");
		back();
	}else{
		alert(ret);
	}
}

function currAll(startid){
	var curr=document.getElementById(startid);
	var menus=document.getElementsByName("checkvalue");
	for(var i=0;i<menus.length;i++){    
		if(menus(i).id.substr(0,startid.length)==startid){
			menus(i).checked=curr.checked;
		}
	}
}

function back(){
	$("#addMenuForm").attr("action","<%=basePath%>manager/manager_roleManagerIndex.action");
	$("form").submit();
}
</script>
  </head>
  
<body>
<div class="title_div"> <span class="title">角色详细权限配置</span> </div>
<form id="addMenuForm" name="addRoleForm" action="" method="post">
<%-- <input type="hidden" id="roleid" value="${roleid}"/>
 <div class="details2" align="center"> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="tableStyle01 luru">
      	<s:iterator value="#request.showMenu" id="lstMap">
	      	<s:iterator value="lstMap" id="childs">
		      	<tr class="itemize">
	        		<td align="center" class="TDstyle01" colspan="4"><s:property value="key"/>&nbsp;
	        		<input type="checkbox" id="<s:property value="key"/>" onclick="currAll('<s:property value="key"/>')"/>
	        		</td>
	       		</tr>
	       		<%int i=0; %>
					<s:iterator value="#childs.value">
						<%i++; if((i-1)%4==0 && i==1){%>	
						<tr class="itemize_xx">
						<%}else if((i-1)%4==0 && i>1){%>
						</tr><tr class="itemize_xx">
						<%}%>
						<td align="left" class="TDstyle01">&nbsp;
							<s:if test="roleStatus=='selected'">
							    <input type="checkbox" id="<s:property value="#childs.key"/>_<s:property value="menuId"/>" name="checkvalue" value='<s:property value="menuId"/>' checked/>
								<s:property value="menuName"/>
							</s:if><s:else>
							    <input type="checkbox" id="<s:property value="#childs.key"/>_<s:property value="menuId"/>" name="checkvalue" value='<s:property value="menuId"/>'/>
								<s:property value="menuName"/>
							</s:else>
						</td>
					</s:iterator>
					<%if(i%4!=0){for(int j=0;j<4-i%4;j++){%>	
					<td align="left" class="TDstyle01">&nbsp;</td>
					<%}}%>	
				</tr>
			</s:iterator>
		</s:iterator>
        
        <tr>
          <td align="center"  colspan="5" height="50" class="TDstyle01">
 				<input type="button" onclick="updateRoleMenu()" value="提交"/>&nbsp;&nbsp; 
 				<input type="button" onclick="back()" value="返回"/>
          </td>
        </tr>
      </table>
  </div> --%>
</form>
</body>
</html>

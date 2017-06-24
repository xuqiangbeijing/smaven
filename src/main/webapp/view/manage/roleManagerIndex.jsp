<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.util.Constants"%>
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
function query(page){
	$.ajax( {    
	    url:"<%=basePath%>sys/role/queryRole",
	    data: JSON.stringify({
	    	rolename:$("#rolename").val(),
	    	currpage:page
	    }),
	    type:'post',
	    contentType : 'application/json; charset=utf-8',
	    cache:false,    
        dataType : 'json',  
	    success:function(data) {  
	    	var str="<tr><td width='10%'>操作</td><td>角色名</td><td>角色描述</td></tr>";
	    	$.each(data.roles, function(i,item) {
	    		str+=item.username;
	    		str+="<tr><td>&nbsp;"
	    		+"<img alt='更新' src='<%=basePath%>res/images/table/edit.gif' onclick=\"updateRolePage('"+item.role_id+"')\"/>&nbsp;&nbsp;"
	    		+"<img alt='删除' src='<%=basePath%>res/images/table/delete.gif' onclick=\"deleteRole('"+item.role_id+"')\"/>&nbsp;&nbsp;"
	    		+"<img alt='权限更新' src='<%=basePath%>res/images/table/role_user.gif' onclick=\"updateRoleMenu('"+item.role_id+"')\"/>&nbsp;&nbsp;</td>"
	    		+"<td>&nbsp;"+item.role_name+"</td>"
	    		+"<td>&nbsp;"+(item.role_desc==null?"":item.role_desc)+"</td>"
	    		+"</tr>";
	    	});   
	    	$("#currpage").val(data.pagenum);
	    	$("#totalpage").html(data.totalPageNum);
	    	$("#result").html(str);
	     },    
	     error:function() {
	          alert("异常！");    
	     }    
	});  
}

function addNew(){
	var urlclick=document.getElementById("urlclick");
	urlclick.href = "<%=basePath%>sys/role/addRolePage";
	urlclick.click();
}

function updateRolePage(roleid){
	var urlclick=document.getElementById("urlclick");
	urlclick.href = "<%=basePath%>sys/role/updateRolePage?updateRoleID="+roleid;
	urlclick.click();
}

function deleteRole(roleid){
	if(confirm("确认删除此角色？")){
		jQuery.ajax({
			url:"<%=basePath%>sys/role/deleteRole",
			cache:false,
			type:'post',
			dataType:'text',
			data:{
				roleid:roleid
			},
			success:showRst,
			error:function(){
				alert("删除出错！");
			}
		}); 	
	}
}

function showRst(rst){
	alert(rst);
	query();
} 

function updateRoleMenu(roleid){
	var urlclick=document.getElementById("urlclick");
	urlclick.href = "<%=basePath%>sys/role/roleAddMenu?roleID="+roleid;
	urlclick.click();
}

var pagenum=1;
function goToPage(flag,allPage){
	if($("#currpage").val()>allPage || $("#currpage").val()<=0){
		alert("跳转页数必须大于零并且小于等于总页数！");
		$("#currpage").val(pagenum);
		return;
	}
	pagenum=$("#currpage").val();
	
	if(flag=="first"){
		pagenum=1;
	}else if(flag=="previous"){
		if(pagenum==1){
			alert("已经是第一页！");return;
		}else --pagenum;
	}else if(flag=="next"){
		if(pagenum>=allPage){
			alert("已是最后一页！");return;
		}else ++pagenum;
	}else if(flag=="last"){
		pagenum=allPage;
	}else if(flag=="num"){
		pagenum=$("#currpage").val();
	}
	$("#currpage").val(pagenum);
	query($("#currpage").val());
}
</script>
  </head>
  
  <body>
  <div id="all" style="width: 100%;height: 100%;position: absolute;overflow: hidden;">
     <div id="conditon" style="width: 100%;height: 30px;top:6px;">
     	<a id="urlclick" href=""></a>
     	<table width="100%" cellspacing="0" cellpadding="0"  border="0" >
	    	<tr>
	    		<td width="30%" align="center">角色名：<input id="rolename" type="text"/></td>
	    		<td>
	    			<input type="button" value="查询" onclick="query(1)">&nbsp;&nbsp;
	    			<input type="button" value="新建" onclick="addNew()">
	    		</td>
	    	</tr>
	    </table>
     </div>
     <div style="width: 97%;top:40px;">
     	<table width="100%" cellspacing="0" cellpadding="0"  border="0">
     		<tr>
     			<td><table id="result" width="100%" cellspacing="0" cellpadding="0"  border="0"> </table></td>
     		</tr>
     		<tr align="right">
     			<td>
		    		<a href="#" onclick="goToPage('first')">首页</a>
		    		<a href="#" onclick="goToPage('previous')">上一页</a>
		    		<a href="#" onclick="goToPage('next')">下一页</a>
		    		<a href="#" onclick="goToPage('last')">最后一页</a>&nbsp;
		    		当前页：<input id="currpage" name="currpage" type="text" size="3" onkeyup="if(isNaN(value))execCommand('undo')"/>
		    		<input type="button" value="跳转" onclick="goToPage('num')"/>&nbsp;<%=Constants.records_perpage%>条/页
		    		&nbsp;共<a id="totalpage"></a>页
	    		</td> 
     		</tr>
     	</table>
     </div>
   </div>
  </body>
  <script type="text/javascript">
  	query(1);
  </script>
</html>

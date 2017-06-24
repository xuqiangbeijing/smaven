<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
	<TITLE> ZTREE DEMO - beforeClick / onClick</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../pages/jQuery/jqueryZTree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="../pages/jQuery/jqueryZTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="../pages/jQuery/jqueryZTree/js/jquery.ztree.core-3.1.js"></script>
	<SCRIPT type="text/javascript">
		<!--
		var setting = {
			data: {
				key: {
					title:"t"
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};
		
		var zNodes =<%=request.getAttribute("allMenu")%>;

		var log, className = "dark";
		function beforeClick(treeId, treeNode, clickFlag) {
			className = (className === "dark" ? "":"dark");
			return (treeNode.click != false);
		}
		function onClick(event, treeId, treeNode, clickFlag) {
			//alert(treeNode.id);
			var menuID=treeNode.id;
			jQuery.ajax({
				url:"<%=basePath%>manager/manager_queryMenuByID.action",
				cache:false,
				type:'post',
				dataType:'text',
				data:{
					menuid:menuID
				},
				success:showRst,
				error:function(){
					alert("error！");
				}
			}); 
		}		

		function showRst(ret){
			var data=eval("(" + ret + ")");
			$("#menuId").val(data.menuId);
			$("#menuName").val(data.menuName);
			$("#menuUrl").val(data.menuUrl);
			$("#menuParentId").val(data.menuParentId);
			$("#menuLevel").val(data.menuLevel);
			$("#menuWeight").val(data.menuWeight);
			$("#menuDesc").val(data.menuDesc);
			$("#menuFlag").val(data.menuFlag);

			$("#updateDiv").attr("style","display:block");
			$("#insertDiv").attr("style","display:none");
		}
	
		$(document).ready(function(){
			$.fn.zTree.init($("#menuTree"), setting, zNodes);
		});
		
		function addChildTree(){
			var parentID=$("#menuId").val();
			var nextlevel=parseInt($("#menuLevel").val())+1;
			$("#menuId").val("");
			$("#menuName").val("");
			$("#menuUrl").val("");
			$("#menuParentId").val(parentID);
			$("#menuLevel").val(nextlevel);
			$("#menuWeight").val("");
			$("#menuDesc").val("");
			$("#updateDiv").attr("style","display:none");
			$("#insertDiv").attr("style","display:block");
		}
		
		function menuInsert(){
			jQuery.ajax({
				url:"<%=basePath%>manager/manager_menuInsert.action",
				cache:false,
				type:'post',
				dataType:'text',
				data:{
					menuid:$("#menuId").val(),
					menuName:$("#menuName").val(),
					menuUrl:$("#menuUrl").val(),
					menuParentId:$("#menuParentId").val(),
					menuLevel:$("#menuLevel").val(),
					menuWeight:$("#menuWeight").val(),
					menuDesc:$("#menuDesc").val(),
					menuFlag:$("#menuFlag").val()
				},
				success:showInsDelRst,
				error:function(){
					alert("error！");
				}
			});
		}
		
		function delThisChild(){
			if(confirm("确定删除此菜单及其子菜单？")){
				jQuery.ajax({
					url:"<%=basePath%>manager/manager_menuDelete.action",
					cache:false,
					type:'post',
					dataType:'text',
					data:{
						menuid:$("#menuId").val()
					},
					success:showInsDelRst,
					error:function(){
						alert("error！");
					}
				}); 
			}
		}
		
		function showInsDelRst(ret){
			if(ret==0){
				alert("操作失败！");
			}else if(ret==1){
				alert("操作成功！");
				var urlclick=document.getElementById("urlclick");
				urlclick.href = "<%=basePath%>manager/manager_menuManagerindex.action";
				urlclick.click();
			}else{
				alert(ret);
			}
		}
		
		function menuUpdate(){
			jQuery.ajax({
				url:"<%=basePath%>manager/manager_menuUpdate.action",
				cache:false,
				type:'post',
				dataType:'text',
				data:{
					menuid:$("#menuId").val(),
					menuName:$("#menuName").val(),
					menuUrl:$("#menuUrl").val(),
					menuParentId:$("#menuParentId").val(),
					menuLevel:$("#menuLevel").val(),
					menuWeight:$("#menuWeight").val(),
					menuDesc:$("#menuDesc").val(),
					menuFlag:$("#menuFlag").val()
				},
				success:UpdateRst,
				error:function(){
					alert("error！");
				}
			}); 
		}
		function UpdateRst(ret){
			var zTree = $.fn.zTree.getZTreeObj("menuTree"),
			nodes = zTree.getSelectedNodes();
			for (var i=0, l=nodes.length; i<l; i++) {
				nodes[i].name = $("#menuName").val();
				zTree.updateNode(nodes[i]);
			}
			if(ret==0){
				alert("操作失败！");
			}else if(ret==1){
				alert("操作成功！");
			}
		}
		
		
		
		
		//-->
	</SCRIPT>
</HEAD>

<BODY>
<a id="urlclick" href=""></a>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="menuTree" class="ztree"></ul>
	</div>
	<div class="right">
		<input type="hidden" id="menuId"/>
		<input type="hidden" id="menuParentId"/>
		<input type="hidden" id="menuLevel"/>
		<table width="100%" cellspacing="0" cellpadding="0"  border="1" >
			<tr>
	    		<td colspan="2"> 
	    			<input type="button" onclick="addChildTree()" value="添加子菜单"/>
	    			<input type="button" onclick="delThisChild()" value="删除此菜单"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>菜单名：</td>
	    		<td><input type="text" id="menuName" size="50"/></td>
	    	</tr>
	    	<tr>
	    		<td>URL：</td>
	    		<td><input type="text" id="menuUrl" size="50"/></td>
	    	</tr>
	    	<tr>
	    		<td>序号：</td>
	    		<td><input type="text" id="menuWeight" size="50" onkeyup="if(isNaN(value))execCommand('undo')"/>（菜单显示排序使用，从小到大排序）</td>
	    	</tr>
	    	<tr>
	    		<td>描述：</td>
	    		<td><input type="text" id="menuDesc" size="50"/></td>
	    	</tr>
	    	<tr>
	    		<td>状态：</td>
	    		<td>
	    			<select id="menuFlag">
	    				<option value="1">启用</option>
	    				<option value="2">停用</option>
	    			</select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan="2"> 
	    			<div id="updateDiv" style="display:block"><input id="btn" type="button" onclick="menuUpdate()" value="更新"/></div>
	    			<div id="insertDiv" style="display:none"><input id="btn" type="button" onclick="menuInsert()" value="提交"/></div>
	    		</td>
	    	</tr>
	    </table>
	</div>
</div>
</BODY>
</HTML>
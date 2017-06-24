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
    <link rel="stylesheet" href="<%=basePath%>res/css/style.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>res/css/css.css" type="text/css">
<script type="text/javascript">
function copyToList(from, to){
  var fromList = eval('document.addRoleForm.' + from);
  var toList = eval('document.addRoleForm.' + to);
  if (toList.options.length > 0 && toList.options[0].value == 'temp'){
      toList.options.length = 0;
  }
  var sel = false;
  for (var i = 0; i < fromList.options.length; i++){
      var current = fromList.options[i];
      if (current.selected){
          sel = true;
          txt = current.text;
          val = current.value;
          for (var j = 0; j < toList.length; j++){
              if (val == toList.options[j].value){
                  alert('此角色已经选择');
                  return;
              }
          }
          toList.options[toList.length] = new Option(txt, val);
      }
   }
	 if (!sel) alert('请至少选择一个角色');
}


function removeList(from, to){
	var fromList = eval('document.addRoleForm.' + from);
	var toList = eval('document.addRoleForm.' + to);
	if (toList.options.length > 0 && toList.options[0].value == 'temp'){
	    toList.options.length = 0;
	}
	var sel = false;
	for (var i = 0; i < fromList.options.length; i++){
	    var current = fromList.options[i];
	    if (current.selected){
	        sel = true;
	        fromList.options[i] = null;
	        i--;
	    }
	}
	if (!sel) alert('请至少选择一个角色');
}

function allSelect(){
    List = document.addRoleForm.userlists;
    if (List.length<=0) return;
    for (var i = 0; i < List.length; i++){
        List.options[i].selected = true;
    }
}

function validate_all() {
    allSelect();
}

function update(ulist){
  allSelect();
  userList = eval('document.addRoleForm.' + ulist);
  var userid=$("#userid").val();
  var list="";
   for (var i = 0; i < userList.options.length; i++){
      list += userList.options[i].value+":";
   }
   jQuery.ajax({
		url:"<%=basePath%>sys/user/userRoleUpdate",
		cache:false,
		type:'post',
		dataType:'text',
		data:{
			userlistAll:list,
			userid:userid
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
	}else{
		alert(ret);
	}
}

function back(){
	$("#addRoleForm").attr("action","<%=basePath%>sys/user/userindex");
	$("form").submit();
}
</script>
  </head>
  
<body>
<div class="title_div"> <span class="title">Assign User Role</span> </div>
<form id="addRoleForm" name="addRoleForm" action="" method="post">
<input type="hidden" id="userid" value="${userid}"/>
 <div class="details2"> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="tableStyle01 luru">
        <tr class="itemize">
        	 <td class="TDstyle01">&nbsp;</td>
         	 <td align="center" class="TDstyle01">所有角色</td>
          	 <td align="center" class="TDstyle01">&nbsp;</td>
         	 <td align="left"  class="TDstyle01">已选择的角色</td>
         	 <td class="TDstyle01">&nbsp;</td>
        </tr>
        <tr class="itemize">
        	 <td width="24%" class="TDstyle01">&nbsp;</td>
	         <td width="24%" align="right" class="TDstyle01">
	             <select id="roleuserlists" name="roleuserlists" size="15" multiple="multiple" style="width: 195px;margin:-5;" 
		           ondblclick="copyToList('roleuserlists','userlists')">
		           <c:forEach items="${allRoles}" var="role" > 
					<option value="${role.role_id }">${role.role_name }</option>
				   </c:forEach> 
		         </select>
	         </td>
	
	         <td width="5%" align="center" class="TDstyle01">
	         		<img src="<%=basePath%>res/images/button/btn_right.gif" onClick="copyToList('roleuserlists','userlists')" title="Add User"
	                                    border="0"><br><br>
	           	<img src="<%=basePath%>res/images/button/btn_left.gif" onClick="removeList('userlists','roleuserlists')" title="Remove User"
	                                    border="0"> 
	         </td>
	         
	         <td width="24%" align="left" class="TDstyle01">
		         <select id="userlists" name="userlists" size="15" multiple="multiple" style="width: 195px;margin:-5;" 
		           ondblclick="removeList('userlists','roleuserlists')">
		           <c:forEach items="${userRoles}" var="role" > 
					<option value="${role.role_id }">${role.role_name}</option>
				   </c:forEach> 
		         </select>
	         </td>
	         <td width="23%" class="TDstyle01">&nbsp;</td>
        </tr>
        
        <tr>
          <td align="center" colspan="5" height="50" class="TDstyle01">
 				<input type="button" onclick="update('userlists')" value="提交"/>&nbsp;&nbsp; 
 				<input type="button" onclick="back()" value="返回"/>
          </td>
        </tr>
      </table>
  </div>
</form>
</body>
</html>

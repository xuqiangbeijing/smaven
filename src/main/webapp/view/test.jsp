<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Spring MVC 3.0</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>res/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
	
	<%-- url:"<%=basePath%>user/queryTest" --%>
	var saveDataAry=[];  
    var data1={"userName":"test","address":"gz"};  
    var data2={"userName":"ququ","address":"gr"};  
    saveDataAry.push(data1);  
    saveDataAry.push(data2);         
    
	    function aclick(){
	    	alert($("#inid").val());
	        $.ajax( {    
	    	    url:"<%=basePath%>sys/queryTest",
	    	    data:JSON.stringify(saveDataAry),  
	    	    type:'post',    
	    	    contentType : 'application/json; charset=utf-8',
	    	    cache:false,    
	            dataType : 'json',  
	    	    success:function(data) {    
	    	        if(data.msg =="true" ){   
	    	        	$.each(data.val, function(i, item) {
	    	                $('#show').append("编号：" + item.s1 + "，姓名：" + item.s2);  
	    	            });
	    	        }
	    	     },    
	    	     error : function() {    
	    	          alert("异常！");    
	    	     }    
	    	});  
	    }
	</script>
  </head>
  
  <body>
   	${test}
   	<input id="inid" type="button" value="ajax click" onclick="aclick()"/>
   	<textarea rows="10" cols="20" id="show"></textarea>
  </body>
</html>

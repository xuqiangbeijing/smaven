<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@page import="com.pojo.sys.Sys_user"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	Sys_user user = (Sys_user) session.getAttribute("_user");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>    
	<title>测试系统</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>res/css/menuCss.css"></link>
	<script type="text/javascript" src="<%=basePath%>res/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		function showInIframe(url){
		    $("#mainIframe").attr("src","<%=basePath%>"+url);
		}
		 
		function logout(){
			window.location.href="<%=basePath%>";
		}
	</script>
</head>
  
<body>
  <div style="width: 100%; height: 26px; text-align: right; line-height: 26px">
  <span class="color_green">
						<%=user.getUsername()%>，您好&nbsp;&nbsp;&nbsp; 今天是 
						<script language="javascript">
							var date = new Date();
							document.write(date.toLocaleDateString());
						</script>  
						| <a href="javascript:logout()" class="a_red">签退</a> 
					</span>
  </div>
  	 <c:if test="${fn:length(menu_tree.childs)>0 }">
    	<ul id="nav">
			<c:forEach items="${menu_tree.childs }" var="m1" varStatus="ms1">
				<li>
					<c:if test="${m1.menu_url=='#'}"><a href="javascript:void('0')">${m1.menu_name }</a></c:if>
					<c:if test="${m1.menu_url!='#'}" >
						<a href="javascript:showInIframe('${m1.menu_url }')">${m1.menu_name }</a>
					</c:if>
					<c:if test="${fn:length(m1.childs)>0 }">
						<ul>
						<c:forEach items="${m1.childs }" var="m2" varStatus="ms2">
							<li>
							<c:if test="${m2.menu_url=='#'}"><a href="javascript:void('0')">${m2.menu_name }</a></c:if>
							<c:if test="${m2.menu_url!='#'}" >
								<a href="javascript:showInIframe('${m2.menu_url }')">${m2.menu_name }</a>
							</c:if>
							<c:if test="${fn:length(m2.childs)>0 }">
								<ul>
								<c:forEach items="${m2.childs }" var="m3" varStatus="ms3">
									<li>
									<c:if test="${m3.menu_url=='#'}"><a href="javascript:void('0')">${m3.menu_name }</a></c:if>
									<c:if test="${m3.menu_url!='#'}" >
										<a href="javascript:showInIframe('${m3.menu_url }')">${m3.menu_name }</a>
									</c:if>
									<c:if test="${fn:length(m3.childs)>0 }">
										<ul>
										<c:forEach items="${m3.childs }" var="m4" varStatus="ms4"><!--4级菜单  -->
											<li>
											<c:if test="${m4.menu_url=='#'}"><a href="javascript:void('0')">${m4.menu_name }</a></c:if>
											<c:if test="${m4.menu_url!='#'}" >
												<a href="javascript:showInIframe('${m4.menu_url }')">${m4.menu_name }</a>
											</c:if>
							    			</li>		
										</c:forEach>
										</ul>
									</c:if>
					    			</li>		
								</c:forEach>
								</ul>
							</c:if>
			    			</li>		
						</c:forEach>
						</ul>
					</c:if>
    			</li>			
  			</c:forEach>  						
		</ul>
	</c:if>
   		
   <iframe id="mainIframe" scrolling="auto" align="top" src="" frameborder="no" style="z-index:-1;position:absolute; top:82px; left:2px;width:99%;height:89%;"></iframe>
</body>
</html>

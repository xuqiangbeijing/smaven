package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器
 * @author qiangx
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

   
   /**
    * Controller之前执行
    */
   @Override
   public boolean preHandle(HttpServletRequest request,
         HttpServletResponse response, Object handler) throws Exception {
      HttpSession session = request.getSession();  
      // 从session 里面获取用户名的信息  
      Object obj = session.getAttribute("user");  
      // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
      if (obj == null || "".equals(obj.toString())) {  
         String path = request.getContextPath();
         String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
         response.sendRedirect(basePath+"login/index");
         return false;
      }
      return super.preHandle(request, response, handler);
   }
	
}

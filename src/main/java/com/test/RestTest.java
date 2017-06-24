package com.test;

import java.util.HashMap;
import org.springframework.web.client.RestTemplate;

public class RestTest {

   /**
    * 单独运行需要的jar包如下
    * commons-logging-1.0.4.jar jackson-all-1.9.0.jar  org.springframework.beans-3.1.0.RELEASE.jar
    * org.springframework.context-3.1.0.RELEASE.jar    org.springframework.core-3.1.0.RELEASE.jar
    * org.springframework.web-3.1.0.RELEASE.jar        retrotranslator-runtime-1.2.3.jar
    */ 
   public static void main(String[] args) {
      RestTemplate restTemplate = new RestTemplate();
//      HashMap map = restTemplate.postForObject("http://10.117.169.133:8080/SpringPro/sys/user/rest", null,  HashMap.class);
//      System.out.println("users:    " + map.get("totalPageNum"));
      
      
      HashMap parm=new HashMap();
      parm.put("username", "");
      parm.put("currpage", "1");
      HashMap m = restTemplate.postForObject("http://10.117.169.133:8080/SpringPro/sys/user/userQuery", parm,  HashMap.class);
      
      System.out.println("m:    " + m.get("totalPageNum"));
      
      
   }

}

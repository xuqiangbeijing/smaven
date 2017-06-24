package com.business.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

public class RestClient {
//   public String getHostUri() {
//      String serverIP = _sessionHandler.getServerIp();
//      if (CommandsUtils.isBlank(serverIP)) {
//         return null;
//      }
//      hostUri = Constants.HTTPS_CONNECTION_PREFIX + serverIP + Constants.DEFAULT_PORT
//            + Constants.HTTPS_CONNECTION_LOGIN_SUFFIX;
//      return hostUri;
//   }
    public String getHostUri() {
       return "http://10.117.169.133:8090/SpringPro/";
   }

   private HttpHeaders buildHeaders() throws Exception {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      List<MediaType> acceptedTypes = new ArrayList<MediaType>();
      acceptedTypes.add(MediaType.APPLICATION_JSON);
      acceptedTypes.add(MediaType.TEXT_HTML);
      headers.setAccept(acceptedTypes);

      return headers;
   }

   //entityName 传递的参数
//   public <T> T restUserQuery(final Class<T> entityType, String path, HttpMethod httpverb,  Object entityName)
//         throws Exception {
//      String targetUri = getHostUri() + path;
//      HttpHeaders headers = buildHeaders();
//      HttpEntity<Object> entity = new HttpEntity<Object>(entityName, headers);
//      ResponseEntity<T> tt = restTemplate.exchange(targetUri, httpverb, entity, entityType);
//      return tt.getBody();
//   }

   public HashMap restUserQuery(String path, Object entityName)
         throws Exception {
      return new RestTemplate().postForObject( getHostUri() + path, null, HashMap.class, entityName);
   }
}

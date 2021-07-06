package com.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @ClassName : IdentityAnthenticationUtils
 * @Description : 从cookie获取token
 * @Author : fmx
 * @Date: 2021-05-26 11:57
 */
public class IdentityAnthenticationUtils {

    /**
     * 从Cookie获取token,再根据token获取想要的数据
     * @param request
     * @return
     * @throws Exception
     */
    public String getToken(HttpServletRequest request) throws Exception {
        try{
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie: cookies) {
                    if("token".equals(cookie.getName())){
                        String token = cookie.getValue();
                        HttpHeaders headers = new HttpHeaders();
                        RestTemplate restTemplate = new RestTemplate();
                        headers.put(HttpHeaders.COOKIE, Collections.singletonList("token=" + token));
                        HttpEntity<String> requestEntity = new HttpEntity<>("",headers);
                        String accessTokenUrl = null;
                        ResponseEntity<String> responseEntity = restTemplate.exchange(accessTokenUrl, HttpMethod.GET,requestEntity,String.class, (Object) null);
                        String body = responseEntity.getBody();
                        JSONObject bodyObj = JSON.parseObject(body);
                        Integer code = bodyObj.getInteger("code");
                        if(code ==200){
                            JSONObject object = bodyObj.getJSONObject("member");
                            String uid = object.getString("uid");
                            return uid;
                        }
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

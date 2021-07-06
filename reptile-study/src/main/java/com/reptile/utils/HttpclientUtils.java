package com.reptile.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName : HttpclientUtils
 * @Description : httpclient+正则表达式实现提取
 * @Author : fmx
 * @Date: 2021-05-10 16:43
 */
public class HttpclientUtils {

    /**
     * httpclient + 正则表达式,获取虎扑新闻列表页
     * @param url 虎扑新闻列表页面url
     */
    public static void httpClientList(String url){
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity,"utf-8");
                if(body != null){
                    /**
                     * 替换掉换行符、制表符、回车符、去掉这些符号，正则表达式写起来更简单一些
                     * 只有空格符号和其他正常字体
                     */
                    Pattern p = Pattern.compile("\t|\r|\n");
                    Matcher m = p.matcher(body);
                    body = m.replaceAll("");
                    /**
                     * 提取列表页的正则表达式
                     * 去掉换行符之后的li
                     * <div class="list-hd"><h4><a href="https://voice.hupu.com/2485167.html" target="_blank">与球迷亲切互动！卡尔特人官方晒球队开放训练日照片</a></h4></>
                     */
                    Pattern pattern = Pattern.compile("<div class=\"list-hd\">\\s* <h4>\\s* <a href=\"(.*?)\"\\s* target=\"_blank\">(.*?)</a>\\s* </h4>\\s* </div>");

                    Matcher matcher = pattern.matcher(body);
                    //批评为出所有符合正则表达式的数据
                    while (matcher.find()){
                        System.out.println("详情页链接："+matcher.group(1)+"，详情页标题："+matcher.group(2));
                    }
                }else{
                    System.out.println("处理失败！！获取正文内容为空");
                }
            }else{
                System.out.println("处理失败！！！返回状态码："+response.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        /*//1、httpclient + 正则表达式简单实现抽取
        String url = "https://voice.hupu.com/nba";
        HttpclientUtils.httpClientList(url);*/

        //2、模拟登录方式登录个人中心
        String user_info_url = "https://www.douban.com/people/237850086/";
        String login_url = "https://accounts.douban.com/j/mobile/login/basic";
        HttpclientUtils.httpClientLogin(login_url,user_info_url);
        /*Calendar beforeTime = Calendar.getInstance();
        Date newTime = beforeTime.getTime();
        System.out.println(beforeTime.getTime());*/
    }

    /**
     * httclient 的方式模拟登录豆瓣
     * httpclient 跟jsoup差不多，不同的地方在于httpclient有session的概念
     * 在同一个httpclient内不需要设置cookies，会默认缓存下来
     * @param loginUrl
     * @param userInfoUrl
     * @throws Exception
     */
    public static void httpClientLogin(String loginUrl, String userInfoUrl) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpUriRequest login = RequestBuilder.post()
                .setUri(new URI(loginUrl))//登录uri
                .setHeader("Upgrade-Insecure-Requests","1")
                .setHeader("Accept","aaplication/json")
                .setHeader("Content-Type","application/x-www-form-urlencoded")
                .setHeader("X-Requested-With","XMLHttpRequest")
                .setHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
                //设置账号信息
                .addParameter("name","19910201275")
                .addParameter("password","Fmxx0116")
                .addParameter("remember","false")
                .addParameter("ticket","")
                .addParameter("ck","")
                .build();
        //模拟登录
        CloseableHttpResponse response = httpClient.execute(login);
        if(response.getStatusLine().getStatusCode() == 200){
            //构造访问个人中心请求
            HttpGet httpGet = new HttpGet(userInfoUrl);
            CloseableHttpResponse user_response = httpClient.execute(httpGet);
            HttpEntity entity = user_response.getEntity();

            String body = EntityUtils.toString(entity,"utf-8");

            System.out.println("天亮了是否查找到？"+(body.contains("天亮了")));
        }else {
            System.out.println("httpclient 模拟登录豆瓣失败了！！！！");
        }
    }
}

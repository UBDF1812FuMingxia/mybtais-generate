package com.reptile.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : JsoupUtils
 * @Description : Jsoup方式提取信息
 * @Author : fmx
 * @Date: 2021-05-10 13:36
 */
public class JsoupUtils {

    /**
     * 简单的抽取
     * jsoup方式，获取虎扑新闻列表页
     * @param url  虎扑新闻列表页url
     */
    public static void jsoupList(String url){
        try{
            Document document = Jsoup.connect(url).get();
            //使用css选择器，提取列表新闻a标签
            Elements elements = document.select("div.news-list > ul > li > div.list-hd > h4 > a");
            for (Element element:elements) {
                //获取详情页链接
                String d_url = element.attr("href");
                //获取标题
                String title = element.ownText();

                System.out.println("详情页链接："+d_url+"，详情页标题："+title);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
       /* 1、简单抽取
        String url = "https://voice.hupu.com/nba";
        JsoupUtils.jsoupList(url);*/

        /*//2、手动设置cookie模拟登录个人中心url
        String user_info_url = "https://www.douban.com/people/237850086/";
        JsoupUtils.setCookies(user_info_url);*/

        //3、模拟登录方式登录个人中心
        String user_info_url = "https://www.douban.com/people/237850086/";
        String login_url = "https://accounts.douban.com/j/mobile/login/basic";
        JsoupUtils.jsoupLogin(login_url,user_info_url);
    }

    /**
     * 涉及到的登录页面的抽取方法1：
     * 手动设置cookies
     * 先从网站上登录，然后查看request headers里面的的cookies
     * @param url
     * @throws IOException
     */
    public static void setCookies(String url) throws IOException {
        /**
         * 403是一种在网站网文的过程中，常见的错误提示。便是资源不可用,
         * 服务器理解客户的请求，但是拒绝处理它，通常由服务器上文件或者目录的权限设置导致的web访问错误
         * 解决方法无非是从：useragent,referer,toker,cookie
         */
        Document document = Jsoup.connect(url)
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Encoding","gzip, deflate, sdch")
                .header("Accept-Language", "zh-CN,zh;q=0.8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .header("Cookie", "ll=\"108288\"; bid=QP_3pRBxUoo; dbcl2=\"237850086:hYh14gROBlY\"; push_noty_num=0; push_doumail_num=0; __utmv=30149280.23785; __yadk_uid=zvcjKkRlPTmYVPgLZAvES9wqLxohJbnO; ck=uLNJ; ap_v=0,6.0; __utma=30149280.453757028.1620639979.1620639979.1620697153.2; __utmc=30149280; __utmz=30149280.1620697153.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __gads=ID=5bf9ad3bf5ca99a4-22d104a0f3c70047:T=1620697153:RT=1620697153:S=ALNI_MbclB-CSFpsJdajiBOMfQTMWfnzUg; _pk_ref.100001.8cb4=[\"\",\"\",1620697166,\"https://movie.douban.com/subject/30223994/\"]; _pk_ses.100001.8cb4=*; __utmt=1; _pk_id.100001.8cb4=dbb712e51986b189.1620639977.2.1620697178.1620640054.; __utmb=30149280.6.10.1620697153")
                .get();//手动设置Cookie

        if(document != null){
            //获取豆瓣昵称节点
            Element element = document.select(".info h1").first();
            if (element == null) {
                System.out.println("没有找到 .info h1 标签");
                return;
            }
            //取出豆瓣节点昵称
            String userName = element.ownText();
            String uri = element.baseUri();
            System.out.println("豆瓣我的网名为："+ userName);
            System.out.println("豆瓣我的链接："+ uri);
        } else {
            System.out.println("出错啦！！！！");
        }
    }

    /**
     * Jsoup 模拟登录豆瓣 访问个人中心
     * 在豆瓣登录时先输入一个错误的账号密码，查看到登录所需要的参数
     * 先构造登录请求参数，成功后获取到cookies
     * 设置request cookies，再次请求
     * （功能没有彻底实现，登录之后需要验证图片，为实现所以登录未能成功）
     * @param loginUrl
     * @param userInfoUrl
     * @throws IOException
     */
    public static void jsoupLogin(String loginUrl,String userInfoUrl) throws IOException {
        //构造登录参数
        Map<String, String> data = new HashMap<>();
        data.put("name","19910201275");
        data.put("password","Fmxx0116");
        data.put("remember","false");
        data.put("ticker","");
        data.put("ck","");
        Connection.Response login = Jsoup.connect(loginUrl)
                .ignoreContentType(true)//忽略类型验证
                .followRedirects(false)//禁止重定向
                .postDataCharset("utf-8")
                .header("Upgrade-Insecure-Requests","1")
                .header("Accept","application/json")
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("X-Requested-With","XMLHttpRequest")
                .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
                .data(data)
                .method(Connection.Method.POST)
                .execute();
        login.charset("UTF-8");
        //login中已经获取到登陆成功后的cookies
        //构造访问个人中心的请求
        Document document = Jsoup.connect(userInfoUrl)
                .cookies(login.cookies())
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Encoding","gzip, deflate, sdch")
                .header("Accept-Language", "zh-CN,zh;q=0.8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .get();
        if(document != null) {
            //Element element = document.selectFirst(".info h1");
            Element element = document.select(".info h1").first();
            if(element == null){
                System.out.println("没有找到 .info h1标签");
                return;
            }
            String userName = element.ownText();
            System.out.println("豆瓣我的网名："+userName);
        }else{
            System.out.println("出错啦！！！！");
        }
    }
}

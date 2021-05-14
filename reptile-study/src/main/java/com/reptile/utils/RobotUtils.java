package com.reptile.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName : RobotUtils
 * @Description : java实现爬虫，简单爬取地址写到文件中
 * @Author : fmx
 * @Date: 2021-05-10 09:58
 */
public class RobotUtils {
    public static void main(String[] args) {
        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        //url的匹配规则
        //String regex = "http://[\\w+\\.?/?]+\\.[A-Zs-z]+";
        String regex = "https://[\\w+\\.?/?]+\\.[A-Za-z]+";
        Pattern p = Pattern.compile(regex);
        try{
            //爬取的网址、这里爬取的是一个生物网站
            url = new URL("https://www.rndsystems.com/cn");
            urlConnection = url.openConnection();
            pw = new PrintWriter(new FileWriter("D:/reptileTest/SiteURL.txt"),true);
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String buf = null;
            while((buf = br.readLine()) != null ){
                Matcher buf_m = p.matcher(buf);
                while(buf_m.find()){
                    pw.println(buf_m.group());
                }
            }
            System.out.println("爬取成功-----");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                br.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            pw.close();
        }
    }
}

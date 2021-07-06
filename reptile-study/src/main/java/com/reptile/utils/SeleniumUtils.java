package com.reptile.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName : SeleniumUtils
 * @Description : 1、通过内置浏览器Selenium的方式实现爬虫
 *                2、通过反向解析法解决数据异步加载的问题
 * @Author : fmx
 * @Date: 2021-05-21 14:53
 */
public class SeleniumUtils {
        /*
        selenium解决数据异步加载问题
        https://npm.taobao.org/mirrors/chromedriver
        需要注意的问题：chrome和chromeDriver的版本需要一致
         */
        public void selenium(String url){
                //设置chromedirver的存放位置
                System.getProperties()
                        .setProperty("webdriver.chrome.driver",
                                "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
                //设置无头浏览器，这样就不会弹出浏览器窗口
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");

                WebDriver webDriver = new ChromeDriver(chromeOptions);
                webDriver.get(url);
                //获取到要闻新闻列表
                List<WebElement> webElements = webDriver.findElements(By.xpath("//div[@class='news_title']/h3/a"));
                for (WebElement webElement:webElements) {
                        //提取新闻连接
                        String article_url = webElement.getAttribute("href");
                        //提取新闻标题
                        //String title = webElement.getTagName();
                        String title = webElement.getText();
                        if (article_url.contains("https://www.163.com/")) {
                                System.out.println("文章标题：" + title + "，文章链接：" + article_url);
                        }
                }
                webDriver.close();
        }

        public static void main(String[] args) throws IOException {
                /*String url = "https://news.163.com/";
                new SeleniumUtils().selenium(url);*/

                String httpclientUrl = "https://temp.163.com/special/00804KVA/cm_yaowen.js?callback=data_callback";
                new SeleniumUtils().httpclientMethod(httpclientUrl);
        }

        /**
         * 使用反向解析法，解决数据异步加载的问题
         * 将数据转换成json或者list
         */
        public void httpclientMethod(String url) throws IOException{
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = response.getEntity();
                        String body = EntityUtils.toString(entity, "GBK");
                        //先替换掉最前面的data_callback(
                        body = body.replace("data_callback(","");
                        //过滤掉最后面一个)右括号
                        body = body.substring(0,body.lastIndexOf(")"));
                        //将body转换成JSONArray
                        JSONArray jsonArray = JSON.parseArray(body);
                        for (int i = 0; i < jsonArray.size(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                System.out.println("文章标题：" + data.getString("title") + "，文章标题：" + data.getString("docurl"));
                        }
                }else {
                        System.out.println("处理失败！！！返回状态码：" + response.getStatusLine().getStatusCode());
                }
        }

        /**
         * 使用正则表达式解析出ur和标题(未作出，后续继续整改)
         * @param url
         * @throws IOException
         */
        public void httpclientMethod1(String url) throws IOException{
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = response.getEntity();
                        String body = EntityUtils.toString(entity, "GBK");
                        //String p = "/<a.*href/s*=/s*(?:"+"(?<url>[^""]*)""|'(?<url>[^']*)'|(?<url>[^/>^/s]+)).*/>(?<title>[^/<^/>]*)/<[^/</a/>]*/a/>";
                }else {
                        System.out.println("处理失败！！！返回状态码：" + response.getStatusLine().getStatusCode());
                }
        }

}

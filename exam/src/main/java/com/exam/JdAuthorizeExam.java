package com.exam;

import com.utils.DateHelper;
import com.utils.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Date;

/**
 * @ClassName : JdAuthorizeExam
 * @Description : 测试京东自动授权的实现
 * @Author : fmx
 * @Date: 2021-07-12 16:06
 */
public class JdAuthorizeExam {
    public static void main(String[] args) throws URISyntaxException {

        //获取code
        String code_uri = "https://open-oauth.jd.com/oauth2/to_login";
        String app_key = "785328604FB086B84CB6C7A03517106F";
        String response_type = "code";
        String redirect_uri = "https://test.emateglobal.com";
        String state = DateHelper.getFormatStringFromDate("yyyyMMdd",new Date());
        String scope = "snsapi_base";

        code_uri = code_uri + "?app_key=" + app_key + "&response_type="
                + response_type + "&redirect_uri=" + redirect_uri + "&state=" + state
                + "&scope=" + scope;
        //URI uri = new URI(code_uri);
        jsoupList(code_uri);

        /*//打开页面
        try {
            Desktop.getDesktop().browse(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*try {
            HttpURLConnection connection =
                    (HttpURLConnection) new URL(code_uri)
                            .openConnection();
            System.out.println(connection);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setAllowUserInteraction(false);
            connection.setUseCaches(false);
            connection.setRequestProperty("Accept-Charset","GBK");
            BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(connection
                            .getOutputStream());
            System.out.println("bufferedOutputStream==>"
                    + bufferedOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //授权，获取accessToken
    }

    public static void jsoupList(String url){
        try{
            Document document = Jsoup.connect(url).get();
            //使用css选择器，提取列表新闻a标签
            Elements elements = document.select("button");
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


}

package com.chinapex.ms.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtils {


    public static String doPost(String url) {

        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String result = "";
        try {
            //创建post请求方式
            HttpPost post = new HttpPost(url);
            //设置参数

            //

            //执行请求操作
            CloseableHttpResponse response = httpClient.execute(post);
            result = (String) EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}

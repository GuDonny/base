package org.donny.base.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * HttpHelper
 *
 * @author 1792998761@qq.com
 * @date 2020/7/29 20:26
 */
public class HttpHelper {

    private HttpHelper() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 返回UUID_System.currentTimeMillis()
     *
     * @return String
     */
    public static String getUuidTimeMillis() {
        String uuid = UUID.randomUUID().toString();
        long currentTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder append = stringBuilder.append(uuid).append("_").append(currentTime);
        return append.toString();
    }

    /**
     * @param url     带上参数的完整路径
     * @param headers 请求头
     * @return String
     * @throws IOException    IOException
     * @throws ParseException ParseException
     */
    public static String doGet(String url, Header... headers)
            throws IOException, ParseException {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeaders(headers);
        result = doExecute(httpClient, httpGet);
        return result;
    }

    /**
     * POST请求
     *
     * @param url     完整的URL请求路径不带参数
     * @param obj     参数对象
     * @param headers 请求头的数组
     * @return String
     * @throws IOException    IOException
     * @throws ParseException ParseException
     */
    public static String doPost(String url, Object obj, Header... headers)
            throws IOException, ParseException {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        String jsonString = JSON.toJSONString(obj);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeaders(headers);
        result = doExecute(httpClient, httpPost);
        return result;
    }

    /**
     * @param httpClient      http客户端
     * @param httpRequestBase http请求
     * @return String
     * @throws IOException    IOException
     * @throws ParseException ParseException
     */
    private static String doExecute(CloseableHttpClient httpClient, HttpRequestBase httpRequestBase)
            throws IOException, ParseException {
        String result = "";
        // 响应模型
        CloseableHttpResponse response = null;
        // 由客户端执行(发送)Post请求
        response = httpClient.execute(httpRequestBase);
        // 从响应模型中获取响应实体
        HttpEntity responseEntity = response.getEntity();
        if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode() && responseEntity != null) {
            result = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
        }

        return result;
    }
}

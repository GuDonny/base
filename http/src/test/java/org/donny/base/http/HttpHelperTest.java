package org.donny.base.http;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 19057578
 * @date 2020/7/30 16:49
 */
public class HttpHelperTest {

    @Test
    public void testDoPost() {
        BasicHeader h0 = new BasicHeader("Content-Type", "application/json;charset=utf8");
        BasicHeader h2 = new BasicHeader("appid", "SMC190712000001");
        BasicHeader h1 = new BasicHeader("gsbizno", HttpHelper.getUuidTimeMillis());
        Header[] headers = new Header[3];
        headers[0] = h0;
        headers[1] = h1;
        headers[2] = h2;
        Map<String, String> map = new HashMap<>(16);
        map.put("systemCode", "SMC161222000002");
        map.put("serviceName", "MySQL");
        map.put("applicant", "19043193");
        map.put("contactUser", "10065437");
        map.put("envId", "14240");
        map.put("productCode", "ipnjs");
        map.put("orderSource", "PRIVATE_CLOUD");
        map.put("applicantName", "陈铭");
        String r = "";
        try {
            r = HttpHelper.doPost("https://www.baidu.com/", map, headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotEquals("", r);
    }

    @Test
    public void testGetUuidTimeMillis() {
        Assert.assertNotEquals("", HttpHelper.getUuidTimeMillis());
    }

    @Test
    public void testDoGet() {
        BasicHeader h0 = new BasicHeader("Content-Type", "application/json;charset=utf8");
        Header[] headers = new Header[1];
        headers[0] = h0;
        String r = "";
        try {
            r = HttpHelper.doGet("https://www.baidu.com/", headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotEquals("", r);
    }
}
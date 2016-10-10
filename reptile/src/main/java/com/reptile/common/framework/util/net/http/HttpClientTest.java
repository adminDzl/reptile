package com.reptile.common.framework.util.net.http;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.reptile.common.framework.util.net.util.CustomConstantsPropertiesUtils;
import com.reptile.common.framework.util.string.StringUtils;

/****
 * *
 * 类名称：		HttpClient.java 
 * 类描述：   		简单的http请求，非框架结构使用---本工具类是唯一一个单独使用
 * 创建人：		
 * 创建时间：		2016-9-7上午11:18:10 
 * 修改人：		liuxing
 * 修改时间：		2016-9-7上午11:18:10 
 * 修改备注：   		
 * @version
 */
public class HttpClientTest {
	
	private static Logger log = Logger.getLogger( HttpClientTest.class ) ;
	private static  String HTTP_SERVER_URL="http://192.168.1.111:8289/ultbak/serverConfigController/permUpdate.do" ;

	private static HttpClient httpClient ;

	static{
		String httpServerUrlBackApp = CustomConstantsPropertiesUtils.getInstance().getKey("HTTP_SERVER_URL");
		if( StringUtils.isNotEmpty(httpServerUrlBackApp) ){
			HTTP_SERVER_URL = httpServerUrlBackApp;
		}
	}
	
	/**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
        //post请求返回结果
        httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    log.info(str) ;
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.parseObject(str) ;
                } catch (JSONException e) {
                	log.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
        	log.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }


    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.parseObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                log.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            log.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }
    
    public static void main(String[] args) {
    	JSONObject response = HttpClientTest.httpPost( HTTP_SERVER_URL,JSONObject.parseObject( "{'status':20}" ), false ) ;
    	System.out.println( response == null );
	}
}

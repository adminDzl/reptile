package com.reptile.common.framework.util.net.http.httpclient.httpsync;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;



/**
 * http参数类
 * 
 * 
 */
public class HttpRequestParams {

	// 编码格式
	private static String ENCODING = "UTF-8";
	protected ConcurrentHashMap<String, Object> urlParams;

	/**
	 * 构造器
	 */
	public HttpRequestParams() {
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		urlParams = new ConcurrentHashMap<String, Object>();
	}

	/**
	 * 给ConcurrentHashMap添加数据
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		if (key != null && value != null) {
			urlParams.put(key, value);
		}
	}
	
	 /**
     * Adds a key/value string pair to the request.
     * @param key the key name for the new param.
     * @param value the value string for the new param.
     */
    public void put(String key, Object value){
        if(key != null && value != null) {
            urlParams.put(key, value);
        }
    }

	/**
	 * 给ConcurrentHashMap添加数据
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, int value) {
		if (key != null) {
			urlParams.put(key, String.valueOf(value));
		}
	}

	/**
	 * 参数格式转化
	 * 
	 * @return UrlEncodedFormEntity
	 */
	public UrlEncodedFormEntity getEntity() {
		UrlEncodedFormEntity entity = null;
		if (urlParams.size() == 0) {
			return null;
		}
		try {
			entity = new UrlEncodedFormEntity(getParamsList(), ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * 获取参数列表
	 * 
	 * @return List<NameValuePair>
	 */
	private List<NameValuePair> getParamsList() {
		List<NameValuePair> lparams = new LinkedList<NameValuePair>();
		for (ConcurrentHashMap.Entry<String, Object> entry : urlParams
				.entrySet()) {
			lparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue() == null ? null : entry.getValue().toString()));
		}
		return lparams;
	}

}

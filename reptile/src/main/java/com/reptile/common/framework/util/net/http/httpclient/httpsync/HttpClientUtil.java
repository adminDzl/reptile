package com.reptile.common.framework.util.net.http.httpclient.httpsync;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.reptile.common.framework.util.net.AppResponseMessage;
import com.reptile.common.framework.util.net.exception.AppHttpRequestException;
import com.reptile.common.framework.util.net.util.AppMessageMenum;

/**
 * 
 * http网络请求工具类
 * 
 * 
 */
public class HttpClientUtil {

	private HttpClient httpClient = null;

	/**
	 * 单例模式
	 * 
	 * @return HttpClient
	 */
	public HttpClientUtil() {
		if (httpClient == null) {
			httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
		}
	}

	public InputStream getInputStream(String url) {
		InputStream is = null;
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * HttpPost无参请求
	 * 
	 * @param url
	 *            请求Url
	 * @return String
	 */
	public String postRequest(String url) {
		HttpPost httpPost = new HttpPost(url);
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String strResult = new String(getHttpResponse(httpResponse));
			return strResult;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null)
				httpClient.getConnectionManager().closeExpiredConnections();
		}
		return null;
	}

	/**
	 * 通过HttpGet请求获取响应字符串
	 * 
	 * @param url
	 *            服务器数据接口
	 * @param params
	 *            参数列表
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public String getRequest(String url, Map<?, ?> params) {
		String paramStr = "";
		Iterator iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			paramStr += paramStr = "&" + key + "=" + val;
		}
		// 拼接Url字符串
		if (!paramStr.equals("")) {
			paramStr = paramStr.replaceFirst("&", "?");
			url += paramStr;
		}

		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			String strResult = new String(getHttpResponse(httpResponse));
			return strResult;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过HttpPost请求获取响应字符串
	 * 
	 * @param url
	 *            请求Url
	 * @param listnvp
	 *            参数列表
	 * @return String
	 * @throws AppHttpRequestException
	 */
	public String postRequest(String url, HttpRequestParams params) throws AppHttpRequestException {
		String strResult = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(params.getEntity());
			HttpResponse httpResponse = httpClient.execute(httpPost);
			strResult = new String(getHttpResponse(httpResponse));
		} catch (Exception ex) {
			throw new AppHttpRequestException(new AppResponseMessage(AppMessageMenum.httpRequestError.getSt(), AppMessageMenum.httpRequestError.getMsg()));
		} finally {
			if (httpClient != null)
				httpClient.getConnectionManager().closeExpiredConnections();
		}
		return strResult;
	}

	/**
	 * 通过HttpResponse响应获取字节
	 * 
	 * @param response
	 *            响应请求
	 * @return byte[]
	 */
	public byte[] getHttpResponse(HttpResponse httpResponse) {
		byte[] bytes = null;
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			HttpEntity entity = httpResponse.getEntity();
			try {
				bytes = EntityUtils.toByteArray(entity);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}

	/**
	 * 通过服务器地址获取到一组Json字符串
	 * 
	 * @param url
	 *            服务器数据接口
	 * @return String
	 */
	public String post(String url) {
		return postRequest(url);
	}

	/**
	 * 通过服务器地址获取到一组Json字符串
	 * 
	 * @param url
	 *            服务器数据接口
	 * @param params
	 * @return String
	 * @throws AppHttpRequestException
	 */
	public String post(String url, HttpRequestParams params) throws AppHttpRequestException {
		return postRequest(url, params);
	}

}

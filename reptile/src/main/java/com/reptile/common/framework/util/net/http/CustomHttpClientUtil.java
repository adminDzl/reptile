/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.http;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.reptile.common.framework.util.net.AppResponseMessage;
import com.reptile.common.framework.util.net.dto.BaseDto;
import com.reptile.common.framework.util.net.dto.ResponseHeadDto;
import com.reptile.common.framework.util.net.exception.AppDaoException;
import com.reptile.common.framework.util.net.exception.AppHttpRequestException;
import com.reptile.common.framework.util.net.exception.JsonAndBeanSwitchException;
import com.reptile.common.framework.util.net.http.httpclient.RequestHead;
import com.reptile.common.framework.util.net.http.httpclient.httpasy.AsyHttpClientUtil;
import com.reptile.common.framework.util.net.http.httpclient.httpsync.HttpRequestParams;
import com.reptile.common.framework.util.net.util.AppMessageMenum;
import com.reptile.common.framework.util.net.util.CustomConstantsPropertiesUtils;
import com.reptile.common.framework.util.net.util.JsonAndBeanSwitchUtil;
import com.reptile.common.framework.util.net.util.JsonRequestParams;
import com.reptile.common.framework.util.string.StringUtils;

/**
 * *
 * 类名称：		CustomHttpClientUtil.java 
 * 类描述：   		http 请求工具类
 * 创建人：		
 * 创建时间：		2016-9-7上午11:07:59 
 * 修改人：		liuxing
 * 修改时间：		2016-9-7上午11:07:59 
 * 修改备注：   		
 * @version
 */
public class CustomHttpClientUtil {
	
	private static  String HTTP_SERVER_URL="http://192.168.1.111:8080/ult/app/command.do" ;
	
	static{
		String httpServerUrlBackApp = CustomConstantsPropertiesUtils.getInstance().getKey("HTTP_SERVER_URL");
		if( StringUtils.isNotEmpty(httpServerUrlBackApp) ){
			HTTP_SERVER_URL = httpServerUrlBackApp;
		}
	}
	

	/**
	 * 处理同步请求
	 * @param cmd
	 *            命令字
	 * @param paramType
	 *            参数类型 ，false - 处理paramMap 参数 ，true - 处理appReqParam 参数
	 * @param paramMap
	 *            请求参数
	 * @param sync
	 *            调用第三方的处理方式，0-同步方式 ，1-异步方式
	 * @param className
	 *            响应结果，继承BaseDto 的类的class
	 * @param appReqParam
	 *            请求参数 封装好的请求参数对象
	 * @return
	 * @throws JsonAndBeanSwitchException
	 * @throws AppHttpRequestException
	 * @throws AppDaoException
	 */
	public static BaseDto handleSyncRequest(Integer cmd, boolean paramType, Map<String, Object> paramMap, BaseDto requestParam, int sync, Class className )
			throws JsonAndBeanSwitchException, AppHttpRequestException, AppDaoException {

		String content = syncRequestHandle( cmd, paramType, paramMap, requestParam, sync );
		JSONObject jsonResponse = JsonAndBeanSwitchUtil.jsonStrToJson(content);
		JSONObject head = jsonResponse.optJSONObject("head");

		ResponseHeadDto responseHeadDto = JsonAndBeanSwitchUtil.jsonToBean(head.toString(), ResponseHeadDto.class);

		/* 判断调用第三方接口响应结果是否成功 */

		isResponseSussess(responseHeadDto);

		JSONObject body = jsonResponse.optJSONObject("body");
		
		
		String resultStr = null;
		if (body.length() != 0) {
			resultStr = body.toString();
		}

		return JsonAndBeanSwitchUtil.jsonToBean(resultStr, className);

	}

	/**
	 * 同步处理
	 * @param cmd
	 *            命令字
	 * @param paramType
	 *            参数类型 ，false - 处理paramMap 参数 ，true - 处理appReqParam 参数
	 * @param paramMap
	 *            请求参数
	 * @param requestParam
	 *            请求参数 封装好的请求参数对象
	 * @param sync
	 *            调用第三方的处理方式，0-同步方式 ，1-异步方式
	 * @return 响应结果 JSON 字符串
	 * @throws JsonAndBeanSwitchException
	 * @throws AppHttpRequestException
	 */
	private static String syncRequestHandle(Integer cmd, boolean paramType, Map<String, Object> paramMap, BaseDto requestParam, int sync)
			throws JsonAndBeanSwitchException, AppHttpRequestException {
		if (cmd == null ) {
			return null;
		}
		RequestHead requestHead = wrapReqHead(cmd, 0);

		HttpRequestParams params = new HttpRequestParams();

		params.put("head", JsonAndBeanSwitchUtil.beanToJson(requestHead));
		if (paramType) {
			params.put("body", JsonAndBeanSwitchUtil.beanToJson(requestParam));
		} else {
			JsonRequestParams requestParams = disposeParamMap(paramMap);
			params.put("body", requestParams.getBody());
		}
		
		return AsyHttpClientUtil.getInstance().post( CustomHttpClientUtil.HTTP_SERVER_URL , params );
		

	}

	/**
	 * 封装请求head
	 * 
	 * @param cmd
	 *            调用第三方接口的命令字
	 * @param sync
	 *            调用第三方的处理方式，0-同步方式 ，1-异步方式
	 * @return
	 * @throws JsonAndBeanSwitchException
	 */
	public static RequestHead wrapReqHead(Integer cmd, int sync) {
		RequestHead requestHead = new RequestHead();
		requestHead.setCmd(cmd);
		requestHead.setSync(sync);
		return requestHead;
	}

	public static JsonRequestParams disposeParamMap(Map<String, Object> paramMap) {
		JsonRequestParams requestParams = new JsonRequestParams();

		if (paramMap != null && !paramMap.isEmpty()) {
			Set<String> keySet = paramMap.keySet();
			Iterator<String> ite = keySet.iterator();
			while (ite.hasNext()) {
				String key = ite.next();
				if (!StringUtils.isEmpty(key)) {
					requestParams.putBody(key, paramMap.get(key));
				}
			}
		}

		return requestParams;
	}

	/**
	 * 判断调用第三方接口响应结果是否成功
	 * 
	 * @param responseHeadDto
	 * 
	 * @throws AppDaoException
	 *             如果返回 不成功则抛出异常
	 */
	public static void isResponseSussess(ResponseHeadDto responseHeadDto) throws AppDaoException {
		if (responseHeadDto == null) {
			throw new AppDaoException(new AppResponseMessage(AppMessageMenum.MINUS_ONE.getSt(), AppMessageMenum.MINUS_ONE.getMsg()));
		} else if (responseHeadDto.getSt() == null || responseHeadDto.getSt().intValue() != AppMessageMenum.ONE.getSt()) {
			throw new AppDaoException(new AppResponseMessage(responseHeadDto.getSt(), responseHeadDto.getMsg()));
		}
	}

}

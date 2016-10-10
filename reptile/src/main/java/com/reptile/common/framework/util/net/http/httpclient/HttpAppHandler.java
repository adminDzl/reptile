/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.http.httpclient;

import org.apache.log4j.Logger;

import com.reptile.common.framework.util.net.dto.RequestHeadDto;
import com.reptile.common.framework.util.net.exception.AppCommandException;
import com.reptile.common.framework.util.net.exception.AppCommandFactoryException;
import com.reptile.common.framework.util.net.exception.AppHttpParseBodyException;
import com.reptile.common.framework.util.net.exception.AppHttpParseHeadException;
import com.reptile.common.framework.util.net.exception.AppServiceException;
import com.reptile.common.framework.util.net.exception.JsonAndBeanSwitchException;
import com.reptile.common.framework.util.net.util.AppCommand;
import com.reptile.common.framework.util.net.util.AppCommandFactory;
import com.reptile.common.framework.util.net.util.AppParseRequestUtil;
import com.reptile.common.framework.util.net.util.AppWrapResponseUtil;


/**
 * @className:AppHandle.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-5 上午11:41:10
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class HttpAppHandler {
	private static Logger log=Logger.getLogger(HttpAppHandler.class);
	private AppCommand appCommand;
	private RequestHeadDto requestHeadDto;
	
	

	/**
	 * 处理请求
	 * 
	 * @param headParam
	 *            请求head 参数
	 * @param bodyParam
	 *            请求body 参数
	 * @return
	 * @throws AppWrapResponseException
	 */
	public String handleRequest(String headParam, String bodyParam) {
		String responseStr = "";
		long startTime=System.currentTimeMillis();
		try {

			parseRequestHead(headParam);
			appCommand = AppCommandFactory.createAppCommand(requestHeadDto.getCmd());

			responseStr = receiveCommand(bodyParam, requestHeadDto);
		} catch (Exception e) {
			if (e instanceof AppHttpParseHeadException) {
				AppHttpParseHeadException appException = (AppHttpParseHeadException) e;
				responseStr = AppWrapResponseUtil.wrapResponseToStr(appException.getAppResponseMessage(), null);

			} else if (e instanceof AppCommandFactoryException) {
				AppCommandFactoryException appException = (AppCommandFactoryException) e;
				responseStr = AppWrapResponseUtil.wrapResponseToStr(appException.getAppResponseMessage(), null);
			} else if (e instanceof AppCommandException) {
				AppCommandException appException = (AppCommandException) e;
				responseStr = AppWrapResponseUtil.wrapResponseToStr(appException.getAppResponseMessage(), null);

			} else if (e instanceof AppHttpParseBodyException) {
				AppHttpParseBodyException appException = (AppHttpParseBodyException) e;
				responseStr = AppWrapResponseUtil.wrapResponseToStr(appException.getAppResponseMessage(), null);

			} else if (e instanceof AppServiceException) {
				AppServiceException appException = (AppServiceException) e;
				responseStr = AppWrapResponseUtil.wrapResponseToStr(appException.getAppResponseMessage(), null);

			} else {
				responseStr = AppWrapResponseUtil.wrapResponseToStr(null, null);
			}

		}

		long endTime=System.currentTimeMillis();
		long hsTime=endTime - startTime;
//		if(requestHeadDto !=null){
//		   log.error("==== mobileMac:"+requestHeadDto.getSid()+" LocateTime = "+ hsTime+"  || startTime = "+startTime+" || endTime = "+endTime+"  ||  requestCount = "+CustomConstantsUrl.requestCount +"  ||  reponseCount = "+CustomConstantsUrl.reponseCount);
//		}else{
//			log.error("==== mobileMac: Error "+" LocateTime = "+ hsTime+"  || startTime = "+startTime+" || endTime = "+endTime+"  ||  requestCount = "+CustomConstantsUrl.requestCount +"  ||  reponseCount = "+CustomConstantsUrl.reponseCount);
//		}
		
		return responseStr;

	}
	

	/**
	 * 命令方法
	 * 
	 * @param appRequestHead
	 *            TODO
	 * 
	 * @throws AppCommandException
	 * @throws JsonAndBeanSwitchException
	 * @throws AppHttpParseBodyException
	 */
	private String receiveCommand(String bodyParam, RequestHeadDto appRequestHead) throws AppCommandException, AppHttpParseBodyException,
			JsonAndBeanSwitchException {
		return (String) appCommand.receiveCommand(bodyParam, appRequestHead, false);
	}

	/**
	 * 解析请求参数head
	 * 
	 * @throws AppHttpParseHeadException
	 * @throws JsonAndBeanSwitchException
	 */
	private void parseRequestHead(String headParam) throws AppHttpParseHeadException, JsonAndBeanSwitchException {
		requestHeadDto = AppParseRequestUtil.parseHead(headParam);
	}

}

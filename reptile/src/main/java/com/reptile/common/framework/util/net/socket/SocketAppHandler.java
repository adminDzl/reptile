/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.socket;

import org.apache.log4j.Logger;

import com.reptile.common.framework.util.net.AppResponseMessage;
import com.reptile.common.framework.util.net.dto.BaseDto;
import com.reptile.common.framework.util.net.dto.RequestHeadDto;
import com.reptile.common.framework.util.net.dto.SocketRequestDto;
import com.reptile.common.framework.util.net.dto.SocketResponseDto;
import com.reptile.common.framework.util.net.exception.AppCommandException;
import com.reptile.common.framework.util.net.exception.AppCommandFactoryException;
import com.reptile.common.framework.util.net.exception.AppHttpParseBodyException;
import com.reptile.common.framework.util.net.exception.AppHttpParseHeadException;
import com.reptile.common.framework.util.net.exception.AppServiceException;
import com.reptile.common.framework.util.net.exception.JsonAndBeanSwitchException;
import com.reptile.common.framework.util.net.util.AppCommand;
import com.reptile.common.framework.util.net.util.AppCommandFactory;
import com.reptile.common.framework.util.net.util.AppMessageMenum;
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
public class SocketAppHandler {
	private static Logger log = Logger.getLogger(SocketAppHandler.class);

	private static SocketAppHandler instance;

	private SocketAppHandler() {
		super();
	}

	public synchronized static SocketAppHandler getInstance() {
		if (instance == null) {
			instance = new SocketAppHandler();
		}

		return instance;
	}

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
	public SocketResponseDto handleRequest(SocketRequestDto socketRequestDto) {

		long startTime = System.currentTimeMillis();
		RequestHeadDto requestHeadDto =null;
		SocketResponseDto socketResponseDto =null;
		try {

			if (socketRequestDto == null) {
				
				AppResponseMessage appResponseMessage = new AppResponseMessage(AppMessageMenum.MINUS_FOUR.getSt(), AppMessageMenum.MINUS_FOUR.getMsg());

				socketResponseDto =AppWrapResponseUtil.wrapResponse(appResponseMessage, null);
				return socketResponseDto;

			}

			requestHeadDto = socketRequestDto.getRequestHeadDto();

			AppCommand appCommand = AppCommandFactory.createAppCommand(requestHeadDto.getCmd());

			socketResponseDto = receiveCommand(appCommand, socketRequestDto.getRequestBodyDto(), requestHeadDto);
		} catch (Exception e) {
			if (e instanceof AppHttpParseHeadException) {
				AppHttpParseHeadException appException = (AppHttpParseHeadException) e;
				socketResponseDto = AppWrapResponseUtil.wrapResponse(appException.getAppResponseMessage(), null);

			} else if (e instanceof AppCommandFactoryException) {
				AppCommandFactoryException appException = (AppCommandFactoryException) e;
				socketResponseDto = AppWrapResponseUtil.wrapResponse(appException.getAppResponseMessage(), null);
			} else if (e instanceof AppCommandException) {
				AppCommandException appException = (AppCommandException) e;
				socketResponseDto = AppWrapResponseUtil.wrapResponse(appException.getAppResponseMessage(), null);

			} else if (e instanceof AppHttpParseBodyException) {
				AppHttpParseBodyException appException = (AppHttpParseBodyException) e;
				socketResponseDto = AppWrapResponseUtil.wrapResponse(appException.getAppResponseMessage(), null);

			} else if (e instanceof AppServiceException) {
				AppServiceException appException = (AppServiceException) e;
				socketResponseDto = AppWrapResponseUtil.wrapResponse(appException.getAppResponseMessage(), null);

			} else {
				socketResponseDto = AppWrapResponseUtil.wrapResponse(null, null);
			}

		}
		
		if(requestHeadDto !=null){
			socketResponseDto.getResponseHeadDto().setCmd(requestHeadDto.getCmd());
		}

		long endTime = System.currentTimeMillis();
		long hsTime = endTime - startTime;
		if (requestHeadDto != null ) {
			log.error("==== mobileMac:" + requestHeadDto.getSid() + " LocateTime = " + hsTime + "  || startTime = " + startTime + " || endTime = " + endTime
					);
		} else {
			log.error("==== mobileMac: Error " + " LocateTime = " + hsTime + "  || startTime = " + startTime + " || endTime = " + endTime
					);
		}

		return socketResponseDto;

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
	private SocketResponseDto receiveCommand(AppCommand appCommand, BaseDto requestBodyDto, RequestHeadDto appRequestHead) throws AppCommandException,
			AppHttpParseBodyException, JsonAndBeanSwitchException {
		return (SocketResponseDto) appCommand.receiveCommand(requestBodyDto, appRequestHead, true);
	}

}

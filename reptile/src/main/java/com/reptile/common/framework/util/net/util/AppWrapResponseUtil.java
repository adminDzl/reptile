/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.util;

import com.alibaba.fastjson.JSONObject;
import com.reptile.common.framework.util.net.AppResponseMessage;
import com.reptile.common.framework.util.net.dto.BaseDto;
import com.reptile.common.framework.util.net.dto.ResponseHeadDto;
import com.reptile.common.framework.util.net.dto.SocketResponseDto;

/**
 * @className:AppWrapResponseUtil.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-5 下午1:54:38
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppWrapResponseUtil {

	/**
	 * 封装响应
	 * 
	 * @param appResponseMessage
	 * @param bodyObj
	 * @return
	 */
	public static String wrapResponseToStr(AppResponseMessage appResponseMessage, Object bodyObj) {
		if (appResponseMessage == null) {
			appResponseMessage = new AppResponseMessage(AppMessageMenum.MINUS_ONE.getSt(), AppMessageMenum.MINUS_ONE.getMsg());
		}

		JsonRequestParams jsonRequestParams = new JsonRequestParams();

		ResponseHeadDto responseHead = new ResponseHeadDto();
		responseHead.setSt(appResponseMessage.getSt());
		responseHead.setMsg(appResponseMessage.getMsg());

		jsonRequestParams.putHead(responseHead);

		if (bodyObj != null) {
			jsonRequestParams.putBody(bodyObj);
		} else {
			jsonRequestParams.putBody(new JSONObject());
		}

		return jsonRequestParams.getRequestJson();
	}

	/**
	 * 封装响应
	 * 
	 * @param appResponseMessage
	 * @param baseDto
	 * @return
	 */
	public static SocketResponseDto wrapResponse(AppResponseMessage appResponseMessage, BaseDto baseDto) {
		if (appResponseMessage == null) {
			appResponseMessage = new AppResponseMessage(AppMessageMenum.MINUS_ONE.getSt(), AppMessageMenum.MINUS_ONE.getMsg());
		}

		ResponseHeadDto responseHead = new ResponseHeadDto();
		responseHead.setSt(appResponseMessage.getSt());
		responseHead.setMsg(appResponseMessage.getMsg());

		return new SocketResponseDto(responseHead, baseDto);
	}
}

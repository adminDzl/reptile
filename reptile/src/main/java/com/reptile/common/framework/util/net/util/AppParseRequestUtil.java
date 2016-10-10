/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.util;

import org.apache.commons.lang3.StringUtils;

import com.reptile.common.framework.util.net.AppResponseMessage;
import com.reptile.common.framework.util.net.dto.BaseDto;
import com.reptile.common.framework.util.net.dto.RequestHeadDto;
import com.reptile.common.framework.util.net.exception.AppHttpParseBodyException;
import com.reptile.common.framework.util.net.exception.AppHttpParseHeadException;
import com.reptile.common.framework.util.net.exception.JsonAndBeanSwitchException;

/**
 * @className:AppHttpParseUtil.java
 * @classDescription: appHttp 参数解析类
 * @author: hugx
 * @createTime:2014-8-5 上午11:10:08
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppParseRequestUtil {

	/**
	 * 解析请求参数head
	 * 
	 * 解析错误抛出解析head异常
	 */
	public static RequestHeadDto parseHead(String headParam) throws AppHttpParseHeadException, JsonAndBeanSwitchException {
		if (StringUtils.isEmpty(headParam)) {
			
			throw new AppHttpParseHeadException(new AppResponseMessage(AppMessageMenum.MINUS_FOUR.getSt(), AppMessageMenum.MINUS_FOUR.getMsg()));
			
		}

		return JsonAndBeanSwitchUtil.jsonToBean(headParam, RequestHeadDto.class);

	}

	/**
	 * 解析请求参数Body
	 * 
	 * 解析错误抛出解析Body异常
	 */
	public static BaseDto parseBody(String bodyParam, Class baseDtoClass) throws AppHttpParseBodyException, JsonAndBeanSwitchException {
		if (StringUtils.isEmpty(bodyParam)) {
			throw new AppHttpParseBodyException(new AppResponseMessage(AppMessageMenum.MINUS_TWO.getSt(), AppMessageMenum.MINUS_TWO.getMsg()));
		}

		return JsonAndBeanSwitchUtil.jsonToBean(bodyParam, baseDtoClass);
	}

}

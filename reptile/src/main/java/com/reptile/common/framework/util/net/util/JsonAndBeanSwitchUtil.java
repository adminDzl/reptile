/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.util;

import java.lang.reflect.Type;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.reptile.common.framework.util.net.AppResponseMessage;
import com.reptile.common.framework.util.net.exception.JsonAndBeanSwitchException;
import com.reptile.common.framework.util.string.StringUtils;


/**
 * json 与 javaBean 的转换工具
 * 
 * @author hugx
 * 
 */
public class JsonAndBeanSwitchUtil {

	/**
	 * json 对象 转换为JavaBean 对象
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param t
	 *            JavaBean类
	 * @return
	 */
	public static <T> T jsonToBean(String jsonStr, Type t) throws JsonAndBeanSwitchException {
		try {
			return JSON.parseObject(jsonStr, t);
		} catch (Exception e) {
			throw new JsonAndBeanSwitchException(new AppResponseMessage(AppMessageMenum.jsonToBean.getSt(), AppMessageMenum.jsonToBean.getMsg()));
		}
	}

	public static <T> String beanToJson(T t) throws JsonAndBeanSwitchException {
		try {
			return JSON.toJSONString(t);
		} catch (Exception e) {
			throw new JsonAndBeanSwitchException(new AppResponseMessage(AppMessageMenum.beanToJson.getSt(), AppMessageMenum.beanToJson.getMsg()));
		}
	}

	/**
	 * json 字符串 解析为json对象
	 * 
	 * @param jsonStr
	 * @return
	 * @throws JsonAndBeanSwitchException
	 */
	public static JSONObject jsonStrToJson(String jsonStr) throws JsonAndBeanSwitchException {
		if (!StringUtils.isJsonStr(jsonStr) ) {
			throw new JsonAndBeanSwitchException(new AppResponseMessage(AppMessageMenum.strToJson.getSt(), AppMessageMenum.strToJson.getMsg()));
		}

		JSONObject json = null;
		try {
			json = new JSONObject(jsonStr);
		} catch (Exception e) {
			throw new JsonAndBeanSwitchException(new AppResponseMessage(AppMessageMenum.strToJsonParse.getSt(), AppMessageMenum.strToJsonParse.getMsg()));
		}

		return json;
	}
}

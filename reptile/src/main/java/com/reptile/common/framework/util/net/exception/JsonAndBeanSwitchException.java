/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.exception;

import com.reptile.common.framework.util.net.AppResponseMessage;



/**
 * @className:JsonAndBeanSwitchException.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-5 下午5:07:43
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class JsonAndBeanSwitchException extends Exception {
	private static final long serialVersionUID = -542221114225029525L;
	private AppResponseMessage appResponseMessage;

	/**
	 * Constructs .
	 */
	public JsonAndBeanSwitchException() {
		super();
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 */
	public JsonAndBeanSwitchException(String message) {
		super(message);
	}

	public JsonAndBeanSwitchException(AppResponseMessage appResponseMessage) {
		super();
		this.appResponseMessage = appResponseMessage;
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 * @param cause
	 */
	public JsonAndBeanSwitchException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs
	 * 
	 * @param cause
	 */
	public JsonAndBeanSwitchException(Throwable cause) {
		super(cause);
	}

	public AppResponseMessage getAppResponseMessage() {
		return appResponseMessage;
	}
}

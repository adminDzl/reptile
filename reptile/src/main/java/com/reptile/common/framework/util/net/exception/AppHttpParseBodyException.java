/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.exception;

import com.reptile.common.framework.util.net.AppResponseMessage;


/**
 * @className:AppHttpParseBodyException.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-5 下午4:51:02
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppHttpParseBodyException extends Exception {

	private static final long serialVersionUID = 1108636763047502075L;
	private AppResponseMessage appResponseMessage;

	/**
	 * Constructs .
	 */
	public AppHttpParseBodyException() {
		super();
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 */
	public AppHttpParseBodyException(String message) {
		super(message);
	}

	public AppHttpParseBodyException(AppResponseMessage appResponseMessage) {
		super();
		this.appResponseMessage = appResponseMessage;
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 * @param cause
	 */
	public AppHttpParseBodyException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs
	 * 
	 * @param cause
	 */
	public AppHttpParseBodyException(Throwable cause) {
		super(cause);
	}

	public AppResponseMessage getAppResponseMessage() {
		return appResponseMessage;
	}

}

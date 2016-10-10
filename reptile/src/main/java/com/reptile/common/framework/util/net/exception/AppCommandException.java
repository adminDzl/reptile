/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.exception;

import com.reptile.common.framework.util.net.AppResponseMessage;





/**
 * @className:AppCommandException.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-5 下午4:47:35
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppCommandException extends Exception {
	private static final long serialVersionUID = 7066915944592537793L;
	private AppResponseMessage appResponseMessage;

	/**
	 * Constructs .
	 */
	public AppCommandException() {
		super();
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 */
	public AppCommandException(String message) {
		super(message);
	}

	public AppCommandException(AppResponseMessage appResponseMessage) {
		super();
		this.appResponseMessage = appResponseMessage;
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 * @param cause
	 */
	public AppCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs
	 * 
	 * @param cause
	 */
	public AppCommandException(Throwable cause) {
		super(cause);
	}

	public AppResponseMessage getAppResponseMessage() {
		return appResponseMessage;
	}
}

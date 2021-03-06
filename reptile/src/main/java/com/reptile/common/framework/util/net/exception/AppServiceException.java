/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.exception;

import com.reptile.common.framework.util.net.AppResponseMessage;

/**
 * @className:AppServiceException.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-13 下午3:32:54
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppServiceException extends RuntimeException {
	private static final long serialVersionUID = -8464448478436227385L;
	private AppResponseMessage appResponseMessage;

	/**
	 * Constructs .
	 */
	public AppServiceException() {
		super();
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 */
	public AppServiceException(String message) {
		super(message);
	}

	public AppServiceException(AppResponseMessage appResponseMessage) {
		super();
		this.appResponseMessage = appResponseMessage;
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 * @param cause
	 */
	public AppServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs
	 * 
	 * @param cause
	 */
	public AppServiceException(Throwable cause) {
		super(cause);
	}

	public AppResponseMessage getAppResponseMessage() {
		return appResponseMessage;
	}

}

package com.reptile.common.framework.util.net.exception;

import com.reptile.common.framework.util.net.AppResponseMessage;

/**
 * AppHttpParseException 异常类
 * 
 * @author waterborn
 */
public class AppHttpParseHeadException extends Exception {

	private static final long serialVersionUID = -5229914743408864700L;
	private AppResponseMessage appResponseMessage;

	/**
	 * Constructs .
	 */
	public AppHttpParseHeadException() {
		super();
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 */
	public AppHttpParseHeadException(String message) {
		super(message);
	}

	public AppHttpParseHeadException(AppResponseMessage appResponseMessage) {
		super();
		this.appResponseMessage = appResponseMessage;
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 * @param cause
	 */
	public AppHttpParseHeadException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs
	 * 
	 * @param cause
	 */
	public AppHttpParseHeadException(Throwable cause) {
		super(cause);
	}

	public AppResponseMessage getAppResponseMessage() {
		return appResponseMessage;
	}

}

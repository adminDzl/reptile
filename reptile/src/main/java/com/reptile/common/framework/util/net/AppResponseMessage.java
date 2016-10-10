/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net;

/**
 * @className:AppErrorMessage.java
 * @classDescription:  错误信息
 * @author: hugx
 * @createTime:2014-8-5 下午4:33:25
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppResponseMessage {
	private Integer st;
	private String msg;

	public AppResponseMessage(Integer st, String msg) {
		super();
		this.st = st;
		this.msg = msg;
	}

	public Integer getSt() {
		return st;
	}

	public String getMsg() {
		return msg;
	}

}

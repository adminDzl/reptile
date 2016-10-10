/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.socket.server;

import java.net.InetSocketAddress;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.reptile.common.framework.util.net.socket.handler.CustomServicesHandler;
import com.reptile.common.framework.util.net.util.CustomConstantsPropertiesUtils;

/**
 * @className:CustomMinaService.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-11-11 上午11:01:07
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class CustomMinaService {
	
	private static final Logger logger = Logger.getLogger(CustomMinaService.class);
	private int PORT = 8080;
	private int readerIdleTimeOut = 2 * 1000; 		// 响应超时 单位 毫秒
	private IoAcceptor acceptor = null;

	private static CustomMinaService instance;

	private CustomMinaService() {
		super();
/*		String portStr = CustomConstantsPropertiesUtils.getInstance().getKey("ServerSocket_Port");
		if (StringUtils.isNotEmpty(portStr)) {
			PORT = Integer.valueOf(portStr);
		}
		String inputStreamTimeOutStr = CustomConstantsPropertiesUtils.getInstance().getKey("ServerSocket_inputStreamTimeOut");
		if (StringUtils.isNotEmpty(inputStreamTimeOutStr)) {
			readerIdleTimeOut = Integer.valueOf(inputStreamTimeOutStr);
		}
		readerIdleTimeOut /= 1000;*/
	}

	public static synchronized CustomMinaService getInstance() {
		if (instance == null) {
			instance = new CustomMinaService();
		}

		return instance;
	}

	/**
	 * 停止Socket 服务
	 */
	public void stopMinaServer() {
		if (acceptor != null) {
			acceptor.dispose();
			acceptor=null;
		}
	}

	/**
	 * 启动Socket 服务
	 */
	public void startMinaServer() {
		service();
	}

	private void service() {

		try {
			// 创建一个非阻塞的server端的Socket
			acceptor = new NioSocketAcceptor();
			// 直接发送对象
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

			// 获得IoSessionConfig对象
			IoSessionConfig cfg = acceptor.getSessionConfig();
			// 读写通道readerIdleTimeOut秒内无操作进入空闲状态，单位：秒
			cfg.setIdleTime(IdleStatus.BOTH_IDLE, readerIdleTimeOut);

			// 绑定逻辑处理器
			acceptor.setHandler(new CustomServicesHandler());
			// 绑定端口
			acceptor.bind(new InetSocketAddress(PORT));

			logger.info("Mina 服务端启动成功...     端口号为：" + PORT);
		} catch (Exception e) {
			logger.error("Mina服务端启动异常....", e);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		CustomMinaService.getInstance().startMinaServer();
	}
}

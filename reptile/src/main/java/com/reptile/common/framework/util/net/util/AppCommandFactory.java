/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.reptile.common.framework.util.net.AppResponseMessage;
import com.reptile.common.framework.util.net.exception.AppCommandFactoryException;



/**
 * @className:AppCommandFactory.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-5 下午2:39:11
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version  V1.0
 */
public class AppCommandFactory {
	
	public static ApplicationContext applicationContext;
	
	public static AppCommand createAppCommand(Integer cmd)throws AppCommandFactoryException{
		
		if(applicationContext == null){
			initApplicationContext();
		}
		
		String commandName=AppCommandPropertiesUtils.getInstance().getKey(cmd);
		if(StringUtils.isEmpty(commandName)){
			/* 错误码ST = -3:没有找到命令处理类 */
			throw new AppCommandFactoryException(new AppResponseMessage(AppMessageMenum.MINUS_THREE.getSt(), AppMessageMenum.MINUS_THREE.getMsg()));
		}
		
		return (AppCommand) applicationContext.getBean(commandName);
		
	}
	
	private static void initApplicationContext(){
		applicationContext = new ClassPathXmlApplicationContext(new String[] {"classpath:spring.xml","classpath:spring-mybatis.xml"});
	}

}


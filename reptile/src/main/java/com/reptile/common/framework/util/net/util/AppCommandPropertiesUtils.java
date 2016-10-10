package com.reptile.common.framework.util.net.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @className:PropertiesUtils.java
 * @classDescription: 属性文件工具类，本工具类从AppCommand.properties 的配置信息中根据传入的KEY
 *                    值获取对应的VALUE 值
 * @author: hugx
 * @createTime:2014-7-7 下午03:30:22
 * @updateauthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppCommandPropertiesUtils {
	private static Logger logger = Logger.getLogger(AppCommandPropertiesUtils.class);

	private Properties props = null;

	private static AppCommandPropertiesUtils instace;

	private AppCommandPropertiesUtils() {
		init();
	}

	public static synchronized AppCommandPropertiesUtils getInstance() {
		if (instace == null) {
			instace = new AppCommandPropertiesUtils();
		}

		return instace;
	}

	public String getKey(String key) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}

		return props.getProperty(key);
	}

	public String getKey(Integer key) {
		if (key == null) {
			return null;
		}

		String keyStr = String.valueOf(key);
		return getKey(keyStr);
	}

	public static void main(String[] args) {

		String str = AppCommandPropertiesUtils.getInstance().getKey("RESOURCE_FILE_PATH");
		logger.debug("str = " + str);
	}

	private void init() {
		try {
			props = PropertiesLoaderUtils.loadAllProperties("AppCommand.properties");
		} catch (IOException ioEx) {
			logger.error("", ioEx);
		} catch (Exception ex) {
			logger.error("", ex);
		}
	}

}

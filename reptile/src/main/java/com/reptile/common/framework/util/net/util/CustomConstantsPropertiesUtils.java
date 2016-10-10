/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.reptile.common.framework.util.net.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @className:CustomConstantsPropertiesUtils.java
 * @classDescription:
 * @author: hugx
 * @createTime:23 Oct 2014 14:45:42
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version  V1.0
 */
public class CustomConstantsPropertiesUtils {
	private static Logger logger = Logger.getLogger(CustomConstantsPropertiesUtils.class);

	private Properties props = null;

	private static CustomConstantsPropertiesUtils instace;

	private CustomConstantsPropertiesUtils() {
		init();
	}

	public static synchronized CustomConstantsPropertiesUtils getInstance() {
		if (instace == null) {
			instace = new CustomConstantsPropertiesUtils();
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
			props = PropertiesLoaderUtils.loadAllProperties("CustomConstants.properties");
		} catch (IOException ioEx) {
			logger.error("", ioEx);
		} catch (Exception ex) {
			logger.error("", ex);
		}
	}

}


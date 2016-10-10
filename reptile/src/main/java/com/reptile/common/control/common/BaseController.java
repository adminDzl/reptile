package com.reptile.common.control.common;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基础控制器,其他控制器继承此控制器获得日期字段类型转换的功能
 * @author wzs
 * @version 1.0
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		
		/**
		 * 将前台传递过来的日期格式的字符串，自动转化为Date类型格式
		 */
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}
}

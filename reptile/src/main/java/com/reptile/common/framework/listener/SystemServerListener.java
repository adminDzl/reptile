package com.reptile.common.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import com.reptile.common.framework.SystemParamCache;
import com.reptile.common.reptile.DianYing4567Processor;
import com.reptile.common.reptile.DianYingProcessor;
import com.reptile.common.reptile.pipeline.DataBasePipeline;

/***
 * *
 * 类名称：		SystemServerListener.java 
 * 类描述：   		系统自动启动后加载爬虫进行数据爬取
 * 创建人：		
 * 创建时间：		2016-8-30下午2:06:13 
 * 修改人：		liuxing
 * 修改时间：		2016-8-30下午2:06:13 
 * 修改备注：   		
 * @version
 */
public class SystemServerListener implements ServletContextListener{
	
	private Spider dianyingSpider ;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		dianyingSpider.stop() ;
		dianyingSpider.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		SystemParamCache.applicationContext =	WebApplicationContextUtils.getRequiredWebApplicationContext(context.getServletContext());
		dianyingSpider = Spider.create( new DianYingProcessor() ) ;
		dianyingSpider.addRequest( new Request( "http://www.zxba.cc/" ) ).addPipeline( new DataBasePipeline() ).thread( 50 ).start() ;
		//dianyingSpider = Spider.create( new DianYing4567Processor() ) ;
		//dianyingSpider.addRequest( new Request( "http://www.4567.tv/" ) ).addPipeline( new DataBasePipeline() ).thread( 50 ).start() ;
	}
	

}

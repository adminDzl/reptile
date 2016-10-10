package com.reptile.common.reptile;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

import com.reptile.common.reptile.pipeline.DataBasePipeline;

/**
 * *
 * 类名称：		DianYingProcessor.java 
 * 类描述：   		从电影网拿出豆瓣评分超过八分的电影数据
 * 创建人：		
 * 创建时间：		2016-8-30上午10:23:46 
 * 修改人：		liuxing
 * 修改时间：		2016-8-30上午10:23:46 
 * 修改备注：   		
 * @version
 */
public class DianYing4567Processor implements PageProcessor {
	
	private static String basePath = "//div[@id='main']/div[@class='endpage clearfix']/div[@class='l']/div[@class='info']" ;
	
	private static String path = "//div[@id='main']/div[@class='endpage clearfix']" ;
	
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().regex(".*film.*").all();
        page.addTargetRequests(requests);		//每一次都抽取当前页面得其他请求地址加入抓取列表
        page.putField("抓取平台", 2 );
        page.putField("电影名称",page.getHtml().xpath( basePath + "/h1/text()") );
        
        //抓取上映年份
        Selectable year = page.getHtml().xpath( basePath + "/ul/li[1]/text()" ) ;
        if( year != null && year.toString() != null ){
        	page.putField("上映年份", year.toString().subSequence(5, 9) );
        }
        //获取发行地区信息，然后做处理后再放入爬出来的数据项之中
        Selectable company = page.getHtml().xpath( basePath + "/ul/li[4]/text()") ;
        if( company != null && company.toString() != null ){
        	page.putField("电影发行区域", company.toString().subSequence( 3, company.toString().length() -1 ) );
        }
        page.putField("电影主要参影演员",page.getHtml().xpath( basePath + "/ul/li[3]/a[1]/text()" ));
        
        page.putField("豆瓣评分",page.getHtml().xpath("//div[@class='v_txt']/p[2]/text()"));
        //page.putField("电影来源链接：",page.getHtml().xpath("//div[@class='play_list clearfix']/ul[@class='playlist']/li/a"));
        
        page.putField("影片头像",page.getHtml().xpath( path + "/div[@class='l']/div[@class='pic']/img/@src"));
        //page.putField("下载链接：", page.getHtml().xpath("//div[@class='bd']/ul[1]/script[@type='text/javascript']/text()") ); 	//读取页面内的js对象的值
        
        Selectable script = page.getHtml().xpath( "//div[@class='mox']/div[@class='ndownlist']/script[2]" ) ;
        if( script != null && script.toString() != null ){
        	String[] array = script.toString().split("\"") ;
        	//System.out.println( array.length > 1 ? array[1] : array[0] ); 
        	page.putField("下载链接", array.length > 1 ? array[1].split("###")[0] : "" );
        }
    }

    @Override
    public Site getSite() {
    	//抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    	return Site.me().setRetryTimes(10).setSleepTime(1000).setDomain("www.4567.tv") ; //.addStartUrl("http://www.zxba.cc/");
    }

    public static void main(String[] args) {
        Spider.create(new DianYing4567Processor())
        	.addRequest( new Request( "http://www.4567.tv/" ) )
        	//.addPipeline( new DataBasePipeline() )
        	//.thread(5)
        	.run();
    }

}

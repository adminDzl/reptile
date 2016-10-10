package com.reptile.common.reptile;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

import org.apache.log4j.Logger;

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
public class DianYingProcessor implements PageProcessor {
	
	private static Logger logger = Logger.getLogger( DianYingProcessor.class ) ;
	
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().regex(".*movie.*").all();
        page.addTargetRequests(requests);
        
        page.putField("抓取平台", 1 );
        
        Selectable info = page.getHtml().xpath("//div[@class='v_txt']/h2/text()") ;
        if( info != null && info.toString() != null){
        	String infos = info.toString() ;
            page.putField("电影名称", infos.substring( 0,infos.length()-5 ) );
            page.putField("上映年份", infos.substring( infos.length()-5 , infos.length()-1 ) );
        }
        page.putField("电影发行区域",page.getHtml().xpath("//div[@class='v_txt']/p[4]/text()"));
        page.putField("电影主要参影演员",page.getHtml().xpath("//div[@class='v_txt']/p[5]/a/text()"));
        //抓趋数据时，将精准数据抓取到
        Selectable pingFen = page.getHtml().xpath("//div[@class='v_txt']/p[2]/text()") ;
        if( pingFen != null && pingFen.toString() != null ){
        	String[] array = pingFen.toString().split("分钟") ;
        	if( array.length > 1 ){
        		try {
					page.putField("豆瓣评分", array[1] );
				} catch (Exception e) {
					logger.error( "电影评分未抓取到,此电影数据将被放弃抓取" + e.getMessage() );
				}
        	}
        }
        //page.putField("电影来源链接：",page.getHtml().xpath("//div[@class='play_list clearfix']/ul[@class='playlist']/li/a"));
        page.putField("影片头像",page.getHtml().xpath("//div[@class='v_test win960']/div[@class='v_pic']/img/@src"));
        //page.putField("下载链接：", page.getHtml().xpath("//div[@class='bd']/ul[1]/script[@type='text/javascript']/text()") ); 	//读取页面内的js对象的值
        //Selectable script = page.getHtml().xpath("//div[@class='bd']/ul[1]/script[@type='text/javascript']") ;
        //Selectable script = page.getHtml().xpath("//body/script[4]") ;
        Selectable downloadUrl = page.getHtml().xpath("//div[@class='bd']/ul[1]/script[@type='text/javascript']") ;
        if( downloadUrl != null && downloadUrl.toString() != null ){
        	String[] downloadUrlArray = downloadUrl.toString().split("http") ;
			if( downloadUrlArray.length > 1 ){
				 page.putField("下载链接", "http" + downloadUrlArray[1].split("\";")[0] ) ;
			}
        }
        //System.out.println( "" + script.regex("GvodUrls1").toString() );
        //page.putField("下载链接", page.getHtml().xpath("//div[@class='bd']/ul[1]/script[@type='text/javascript']") ); 	//读取页面内的js对象的值
        //点击链接加入群【webmagic探讨】：http://jq.qq.com/?_wv=1027&k=2DsC9hp  //@class='download-group fn-clear downurl'
        //download-group fn-clear downurl
    }

    @Override
    public Site getSite() {
    	//抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    	return Site.me().setRetryTimes(10).setSleepTime(1000).setDomain("www.zxba.cc") ; //.addStartUrl("http://www.zxba.cc/");
    	//return Site.me().setDomain("www.zxba.cc").addStartUrl("http://www.zxba.cc/");
    }

    public static void main(String[] args) {
        Spider.create(new DianYingProcessor())
        	.addRequest( new Request( "http://www.zxba.cc/" ) )
        	//.addPipeline( new DataBasePipeline() )
        	//.thread(5)
        	.run();
    }

}

package com.reptile.common.reptile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class DealerCmsArticleCrawler implements PageProcessor {


	@Override
	public void process(Page page) {
		//List<String> requests =  page.getHtml().links().regex(".news_7667524*").all();
		//page.addTargetRequests(requests);
		
		File f=new File("D:\\345.txt");//新建一个文件对象
        FileWriter fw;
        try {
		  fw=new FileWriter(f);//新建一个FileWriter
		  fw.write( page.getHtml().toString() );//将字符串写入到指定的路径下的文件中
		  fw.close();
        } catch (IOException e) {
        	   // TODO Auto-generated catch block
        	   e.printStackTrace();
        }
		  
		page.putField("title",
				page.getHtml().xpath("//p[@class='title-text']/text()").get());
		page.putField("activityDate",
				page.getHtml().xpath("//p//span[@class='fn-right']/html()"));
		page.putField(
				"activityType",
				page.getHtml()
						.xpath("//p[@class='title-explain']//span[@class='explain-info'][2]/a/html()"));
		Selectable script = page.getHtml().xpath( "//div[@class='mox']/div[@class='ndownlist']/script[2]" ) ;
	        if( script != null && script.toString() != null ){
	        	String[] array = script.toString().split("\"") ;
	        	//System.out.println( array.length > 1 ? array[1] : array[0] ); 
	        	page.putField("下载链接", array.length > 1 ? array[1].split("###")[0] : "" );
	        }
		System.out.println( "212121" );
		// String content =
		// Xsoup.compile("//div[@class='dealermain']//html()").evaluate(document).get();
		// String content = Xsoup.select(document,
		// "//div[@class='dealermain']|div[@class='inforcont-cont']//p[1]|div[@class='inforcont-cont']//p[2] ").get();

	}
	
	@Override
	public Site getSite() {
		return  Site.me()
				.setCharset("gb2312")
				.setRetryTimes(10)
				.setSleepTime(1000)
				.setDomain("dealer.autohome.com.cn") ;
				//.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	}


	public static void main(String[] args) {
		Spider.create(new DealerCmsArticleCrawler())
				.addUrl("http://dealer.autohome.com.cn/15772/news_76675248.html")
				.addPipeline(new ConsolePipeline())
				// 开启5个线程抓取
				//.thread(1)
				// 启动爬虫
				.run();
	}

}

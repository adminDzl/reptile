package com.reptile.common.reptile;

import java.util.List;

import com.reptile.common.reptile.pipeline.DataBasePipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/***
 * *
 * 类名称：		OschinaBlogPageProcesser.java 
 * 类描述：   		第一次爬数据
 * 创建人：		
 * 创建时间：		2016-8-26上午10:54:56 
 * 修改人：		liuxing
 * 修改时间：		2016-8-26上午10:54:56 
 * 修改备注：   		这里通过page.addTargetRequests()方法来增加要抓取的URL，并通过page.putField()来保存抽取结果。
 * 				page.getHtml().xpath()则是按照某个规则对结果进行抽取，这里抽取支持链式调用。调用结束后，toString()表示转化为单个String，all()则转化为一个String列表。
 * 				Spider是爬虫的入口类。Pipeline是结果输出和持久化的接口，这里ConsolePipeline表示结果输出到控制台。
 * 				执行这个main方法，即可在控制台看到抓取结果。webmagic默认有3秒抓取间隔，请耐心等待。你可以通过site.setSleepTime(int)修改这个值。site还有一些修改抓取属性的方法。
 * @version
 */
public class OschinaBlogPageProcesser implements PageProcessor {

    private Site site = Site.me().setDomain("my.oschina.net");

    @Override
    public void process(Page page) {
    	page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //Spider.create(new OschinaBlogPageProcesser()).addUrl("https://github.com/code4craft")
        //     .addPipeline(new ConsolePipeline()).run();		//输出规则之后的数据到到控制台
    	//Spider.create(new OschinaBlogPageProcesser()).addUrl("https://github.com/code4craft")
        //    .addPipeline(new DataBasePipeline()).run();			//输出规则之后的数据到数据库
    	Spider.create(new OschinaBlogPageProcesser()).addUrl("https://github.com/code4craft")
             .addPipeline(new JsonFilePipeline("D:\\bigData\\webmagic"))
            // .thread( 5 )
             .run() ;	//输出规则之后的数据到文件
    }

}

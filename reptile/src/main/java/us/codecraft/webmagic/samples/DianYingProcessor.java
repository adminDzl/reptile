package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

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
public class DianYingProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().regex(".*movie.*").all();
        page.addTargetRequests(requests);
        page.putField("电影名称",page.getHtml().xpath("//div[@class='v_txt']/h2/text()"));
        page.putField("电影发行区域",page.getHtml().xpath("//div[@class='v_txt']/p[4]/text()"));
        page.putField("电影主要参影演员",page.getHtml().xpath("//div[@class='v_txt']/p[5]/a/text()"));
        page.putField("豆瓣评分",page.getHtml().xpath("//div[@class='v_txt']/p[2]/text()"));
        page.putField("电影来源链接：",page.getHtml().xpath("//div[@class='play_list clearfix']/ul[@class='playlist']/li/a"));
        page.putField("影片头像：",page.getHtml().xpath("//div[@class='v_test win960']/div[@class='v_pic']/img"));
        //page.putField("迅雷下载链接：", page.getHtml().jsonPath("$.GvodUrls3").get() ); 	//读取页面内的js对象的值
        //点击链接加入群【webmagic探讨】：http://jq.qq.com/?_wv=1027&k=2DsC9hp
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.zxba.cc").addStartUrl("http://www.zxba.cc/");
    }

    public static void main(String[] args) {
        Spider.create(new DianYingProcessor())
        	.addPipeline( new DataBasePipeline() )
        	//.thread(5)
        	.run();
    }

}

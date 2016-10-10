package us.codecraft.webmagic.samples;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import com.reptile.common.reptile.pipeline.DataBasePipeline;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-5-20
 * Time: 下午5:31
 * 百度云爬虫抓取算法
 * 
 */
public class BaiduyunProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //从页面上获取列表的链接---然后对单个链接做读取
        List<String> requests = page.getHtml().xpath("//a[@class=\"area_link flat_btn\"]/@href").all();
        if (requests.size() > 2) {
            requests = requests.subList(0, 2);
        }
        page.addTargetRequests(requests);
        page.addTargetRequests(page.getHtml().links().regex("(.*/restaurant/[^#]+)").all());
        //page.putField("items", page.getHtml().xpath("//ul[@class=\"dishes menu_dishes\"]/li/span[@class=\"name\"]/text()"));
        //page.putField("prices", page.getHtml().xpath("//ul[@class=\"dishes menu_dishes\"]/li/span[@class=\"price_outer\"]/span[@class=\"price\"]/text()"));
        //page.putField("店铺名称：", page.getHtml().xpath("//div[@class=\"dishes menu_dishes slides_container\"]/div/h1[@class=\"name\"]/text()"));
        page.putField("电影名称：", page.getHtml().xpath("//h1[@id=\"page_title\"]/text()"));
        page.putField("电影下载INFO：", page.getHtml().xpath("//ul[@class=\"simple_restaurant_list not_index\"]/li/h1[@class=\"name\"]/a[@class=\"restaurant_link\"]/text()"));
   }

    @Override
    public Site getSite() {
        return Site.me().setDomain("zxba.cc").addStartUrl("http://www.zxba.cc/1-2016-------.html").setCharset("utf-8").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }

    //http://www.zxba.cc/1-2016-------.html
    public static void main(String[] args) {
        Spider.create(new BaiduyunProcessor()).addPipeline(new DataBasePipeline()).run();
    }
    
}

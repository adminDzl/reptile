package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * 虎嗅
 * 通过这个实例可以发现：target 可以指向目标URL 下面子页面只要包含这个要素均可以被查出来
 */
public class HuxiuProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().regex(".*article.*").all();
        page.addTargetRequests(requests);
        page.putField("title",page.getHtml().xpath("//div[@class='article-wrap']/h1/text()"));
        page.putField("content",page.getHtml().xpath("//div[@id='neirong_box']/tidyText()"));
        page.putField("发出时间：",page.getHtml().xpath("//div[@class='article-author']/span[@class='article-time']/text()"));
        page.putField("作者：",page.getHtml().xpath("//div[@class='article-author']/span[@class='author-name']/a/text()"));
        page.putField("文章链接：",page.getHtml().xpath("//div[@class='article-author']/span[@class='author-name']/a"));  //整个A标签拿出来，下面是只拿A标签的  href属性
        page.putField("文章链接：",page.getHtml().xpath("//div[@class='article-author']/span[@class='author-name']/a[@target='_blank']/@href"));///@href
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.huxiu.com").addStartUrl("http://www.huxiu.com/");
    }

    public static void main(String[] args) {
        Spider.create(new HuxiuProcessor()).thread(5).run();
    }

}

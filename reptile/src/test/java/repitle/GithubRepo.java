package repitle;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/***
 * *
 * 类名称：		GithubRepo.java 
 * 类描述：   		
 * 创建人：		
 * 创建时间：		2016-8-29下午1:52:43 
 * 修改人：		liuxing
 * 修改时间：		2016-8-29下午1:52:43 
 * 修改备注：   		在抽取最后，我们会得到这个类的一个或者多个实例，这就是爬虫的结果
 * @version
 */
@TargetUrl("https://github.com/\\w+/\\w+")
@HelpUrl("https://github.com/\\w+")
public class GithubRepo {
	
	 @ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
	    private String name;

	    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
	    private String author;

	    @ExtractBy("//div[@id='readme']/tidyText()")
	    private String readme;
	    
	    public static void main(String[] args) {
	        OOSpider.create(Site.me().setSleepTime(1000)
	                , new ConsolePageModelPipeline(), GithubRepo.class)
	                .addUrl("https://github.com/code4craft").thread(5).run();
	    }
}

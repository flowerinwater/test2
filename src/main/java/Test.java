import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;

import cn.edu.hfut.dmic.htmlbot.contentextractor.ContentExtractor;


public class Test {
	public static void main(String[] args) throws Exception {
//		String url = "http://blog.csdn.net/rongyongfeikai2/article/details/6163849";
//        String content=ContentExtractor.getContentByURL(url);
//        FileCopyUtils.copy(content, new FileWriter(new File("d:/test.html")));
//        System.out.println(content);
        
//		Document d = getDocument("http://www.baidu.com/s?wd=webcollector");
		Document d = getDocument("http://blog.csdn.net/salonzhou/article/details/8178891");
		System.out.println(d.html());
//		Element content = d.getElementById("content"); 
System.exit(0);

		
//        Document d = Jsoup.parse(new URL("http://www.baidu.com/s?wd=webcollector"),60000);
        Element e = d.getElementById("content_left");
        System.out.println(d.getElementById(e.html()));
        Elements results = d.select("div.c-container");
        Iterator<Element> its = results.iterator();
        int i = 0;
        while (its.hasNext()) {
        	i++;
			Element element = (Element) its.next();
			Elements hrefElements = element.getElementsByTag("a");
			Iterator<Element> it = hrefElements.iterator();
			if(it.hasNext()){
				String href = it.next().attr("href");
				System.out.println(href);
				
				try{
				FileCopyUtils.copy(getDocument(href).html(),new FileWriter(new File("d:/test" + i + ".html")));
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
//			System.out.println(element.text());
		}

        System.out.println(results.size());
        
        FileCopyUtils.copy(d.html(), new FileWriter(new File("d:/test.html")));
}

	private static Document getDocument(String url) throws IOException {
		Document d = Jsoup.connect(url) 
//		  .data("query", "Java")   // 请求参数
		  .userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.29 Safari/525.13") // 设置 User-Agent 
//		  .cookie("auth", "token") // 设置 cookie 
		  .timeout(3000)           // 设置连接超时时间
		  .get();                 // 使用 POST 方法访问 URL 
		return d;
	}
}

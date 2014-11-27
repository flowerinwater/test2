import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;

import com.ssm.mongodb.dao.IArticleDao;
import com.ssm.mongodb.dao.IHistoryDao;
import com.ssm.mongodb.entity.Article;
import com.ssm.mongodb.entity.History;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.util.ConnectionConfig;


public class ZhihuCrawler extends BreadthCrawler{
	static int count;
//	String keyWord = "MongoDB";//iOS
//	static String seed = "http://blog.csdn.net/salonzhou/";
//	static String regex = ".*";
//	static String visitRegex = "^http://blog.csdn.net/salonzhou/article/details/[0-9]+";
//	static String matchBody = "span[class=link_title]";
	
//	String keyWord = "spring";//iOS
//	static String seed = "http://www.iteye.com/problems/forums/Java";
//	static String regex = "http://www.iteye.com/problems/.*";
//	static String visitRegex = "^http://www.iteye.com/problems/[0-9]";
//	static String matchBody = null;
	
//	String keyWord = "习近平";//
//	static String seed = "http://news.sina.com.cn/";
//	static String regex = "http://news.sina.com.cn/.*";
//	static String visitRegex = "^http://news.sina.com.cn/[a-zA-Z]/2014-11-09/[0-9]+.shtml";
//    static String matchBody = "h1[id=artibodyTitle]";
	
	String keyWord = "京东商城";//iOS
	static String seed = "http://www.baidu.com/s?wd=webcollector";
	static String regex = "http://www.baidu.com/link\\?url=.*";
	static String visitRegex = "^http://www.baidu.com/link\\?url=.*";
	static String matchBody = null;
	
    String path = "d:/" + "ssm/";
    
	static int pageProcess = 0;
	static Set<String> visitedUrl = new HashSet<String>();
    /**
     * This function is called when a page is fetched and
     * ready to be processed by your program.       
    */
	
	@Override
	public void failed(Page page) {
		System.out.println("failed "+page.getUrl());
    }
	
    @Override
    public void visit(Page page) {
//        String question_regex="^http://www.zhihu.com/question/[0-9]+";   
    	System.out.println("visiting "+page.getUrl());
    	try {
			FileCopyUtils.copy(page.getDoc().html(), new FileWriter(new File("d:/"+Calendar.getInstance().getTimeInMillis()+".html")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	if(visitedUrl.contains(page.getUrl()))
    		return;
    	
    	if(Pattern.matches(visitRegex, page.getUrl())){              
            System.out.println("processing "+page.getUrl());
//            System.out.println(page.getDoc().html());
//            System.out.println(page.getDoc().text());
            /*extract title of the page*/
//            String title=page.getDoc().title();
//            System.out.println(title);
//
//            /*extract the content of question*/
//            String question=page.getDoc().select("div[id=zh-question-detail]").text();
//            System.out.println(question);
//    csdn      String title=page.getDoc().select("span[class=link_title]").text();
            String title=matchBody!=null?page.getDoc().select(matchBody).text():"非预定";
            System.out.println("process :" + page.getUrl() + "[" + title + "]");
          
          if(title == null || title.equalsIgnoreCase(""))
        	  return;
          
            pageProcess++;
            visitedUrl.add(page.getUrl());
//            if(page.getDoc().text().indexOf(keyWord) >= 0 ){
            if(title.indexOf(keyWord) >= 0 ){
                
	            Article a = new Article();
	            a.setUrl(page.getUrl());
	            a.setHtml(page.getDoc().html());
	            a.setText(page.getDoc().text());
	            a.setTitle(page.getDoc().title());
	            a.setDigest("?");
	            
	            articleDao.insert(a);
	            
	            
	            try {
	            	File f = new File(path);
	            	if(!f.exists())
	            		f.mkdir();
	            	
	            	f = new File(path + keyWord);
	            	if(!f.exists())
	            		f.mkdir();
	            	
	            	String shortFileName = Calendar.getInstance().getTimeInMillis() +"";
	            	String fileName = path +  keyWord + "/" + shortFileName + ".html";
	            	
	            	FileCopyUtils.copy(a.getHtml(), new FileWriter(new File(fileName)));
	            	
//	            	new Url2ThumbMain(page.getUrl(),false,1024,768,path+keyWord+"/",shortFileName).url2Thumb();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            
	            count++;
//	            
//	            if(count>5)
//	            	System.exit(STOPED);
            }
        }
    }

    /**
     * start crawling
    */
    public static void main(String[] args) throws IOException{  
    	getDao();
    	
    	ZhihuCrawler crawler=new ZhihuCrawler();
//        String seed = "http://www.zhihu.com/question/21003086";
//        String regex = "http://www.zhihu.com/.*";
        
        crawler.addSeed(seed);
        crawler.addRegex(regex);
//        crawler.
        crawler.addRegex("-.*#.*");
        crawler.addRegex("-.*png.*");
        crawler.addRegex("-.*jpg.*");
        crawler.addRegex("-.*gif.*");
        crawler.addRegex("-.*js.*");
        crawler.addRegex("-.*css.*");
        
        crawler.setUseragent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.29 Safari/525.13"); // 设置 User-Agent 
//		  )
        /*start the crawler with depth=5*/
        long c1 = Calendar.getInstance().getTimeInMillis();
        try {
			crawler.start(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
        long c2 = Calendar.getInstance().getTimeInMillis();
        System.out.println("***************time :" + (c2-c1)/(3600*1000));
        System.out.println("***************pages :" + pageProcess);
        System.out.println("***************found :" + count);
        
        History h = new History();
        h.setUrl(seed);
        h.setVisitedUrl(visitedUrl);
        historyDao.insert(h);
    }

    
    public static IArticleDao getArticleDao() {
		String configLocations = "applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
		IArticleDao articleDao = applicationContext.getBean("articleDao", IArticleDao.class);
		return articleDao;
	}
    
    public static IHistoryDao getHistoryDao() {
		String configLocations = "applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
		IHistoryDao historyDao = applicationContext.getBean("historyDao", IHistoryDao.class);
		return historyDao;
	}
    
    public static void getDao() {
		String configLocations = "applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
		articleDao = applicationContext.getBean("articleDao", IArticleDao.class);
		historyDao = applicationContext.getBean("historyDao", IHistoryDao.class);
	}
    
    static IHistoryDao historyDao;
    static IArticleDao articleDao;
}
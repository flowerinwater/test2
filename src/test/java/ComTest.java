import java.util.regex.Pattern;


import org.junit.Assert;
import org.junit.Test;

public class ComTest {
	@Test
	public void testInsert() {
//		String visitRegex = "http://news.sina.com.cn/[a-zA-Z]/2014-11-09/[0-9]+.shtml";
//		String targetUrl = "http://news.sina.com.cn/w/2014-11-09/112531117618.shtml";
		
		String visitRegex = "^http://www.baidu.com/link\\?url=.*";
		String targetUrl = "http://www.baidu.com/link?url=gXjJtFPrdzoXTgRk6ARyvPn98rujPYvuRwfW_kFVpJroWOPzG-pGVh3jDxTAT-2V";
		
		Assert.assertTrue(Pattern.matches(visitRegex, targetUrl));  
	}
}

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.mongodb.dao.IArticleDao;
import com.ssm.mongodb.entity.Article;

public class ArticleDaoTest {
	
	/**
	 * 新增 
	 * <br>------------------------------<br>
	 */
	@Test
	public void testInsert() {
		getArticleDao().insert(new Article(null,"url","html", "text","title","digest"));
	}
	
	/**
	 * 批量新增 
	 * <br>------------------------------<br>
	 */
	@Test
	public void testInsertAll() {
		List<Article> list = new ArrayList<Article>();
		for (int i = 0; i < 10; i++) {
			list.add(new Article(null,"url"+i,"html", "text","title","digest"));
		}
		getArticleDao().insertAll(list);
	}
	
	/**
	 * 根据主键删除 
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDeleteById() {
		String id = "5058184ec85607e42c4bfad8";
		getArticleDao().deleteById(id);
	}
	
	/**
	 * 条件删除 
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDelete() {
		//删除年龄大于25的
		Article a = new Article();
		a.setUrl("url1");
		getArticleDao().delete(a);
	}
	
	/**
	 * 删除全部
	 * <br>------------------------------<br>
	 */
	@Test
	public void testDeleteAll() {
		getArticleDao().deleteAll();
	}
	
	/**
	 * 修改  根据id修改
	 * <br>------------------------------<br>
	 */
	@Test
	public void testUpdateById() {
		getArticleDao().updateById(new Article("50581c08c856346f02e9842c"));
	}
	
	/**
	 * 修改多个
	 * <br>------------------------------<br>
	 */
//	@Test
//	public void update() {
//		//修改年龄大于29岁的 姓名为“王五“
//		Article criteriaArticle = new Article(null,"url-update","html", "text","title","digest")
//		Article article = new Article();
//		article.setUrl("url-update");
//		getArticleDao().update(criteriaArticle, article);
//	}
	
	/**
	 * 按主键查询, 如果不存在 返回null
	 * <br>------------------------------<br>
	 */
	@Test
	public void testFindById() {
		Article article = getArticleDao().findById("50581c08c856346f02e98425");
		print(article);
	}
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 */
	@Test
	public void testFindAll() {
		List<Article> list = getArticleDao().findAll();
		print(list);
	}
	
	@Test
	public void testFindAll2() {
		List<Article> list = getArticleDao().findAll("iOS");
		print(list.size());
	}
	
	/**
	 * 按条件查询
	 * <br>------------------------------<br>
	 */
	@Test
	public void testFind() {
		//查询25岁以上的, 分页
		Article criteriaArticle = new Article();
		List<Article> list = getArticleDao().find(criteriaArticle, 1, 10);
		print(list);
	}
	
	/**
	 * 查询出来后  修改
	 * <br>------------------------------<br>
	 */
//	@Test
//	public void testFindAndModify() {
//		Article criteriaArticle = new Article("50581c08c856346f02e9842d", null, 0);
//		Article updateArticle = new Article(null, "张三", 100);
//		updateArticle = getArticleDao().findAndModify(criteriaArticle, updateArticle);
//		print(updateArticle);
//	}
	
	/**
	 * 查询出来后 删除
	 * <br>------------------------------<br>
	 */
	@Test
	public void testFindAndRemove() {
		Article criteriaArticle = new Article("50581c08c856346f02e9842d");
		criteriaArticle = getArticleDao().findAndRemove(criteriaArticle);
		print(criteriaArticle);
	}
	
	/**
	 * count
	 * <br>------------------------------<br>
	 */
	@Test
	public void testCount() {
		Article criteriaArticle = new Article();
		long count = getArticleDao().count(criteriaArticle);
		print(count);
	}
	
	public void print(Object object) {
		if (object == null || !(object instanceof Collection)) {
			System.out.println(object);
			return;
		}
		List<?> list = (List<?>) object;
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
	
	public IArticleDao getArticleDao() {
		String configLocations = "applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocations);
		IArticleDao articleDao = applicationContext.getBean("articleDao", IArticleDao.class);
		return articleDao;
	}
}
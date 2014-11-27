package com.ssm.mongodb.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBList;
import com.mongodb.CommandResult;
import com.mongodb.DBObject;

import com.ssm.mongodb.dao.IArticleDao;
import com.ssm.mongodb.dao.support.AbstractBaseMongoTemplete;
import com.ssm.mongodb.entity.Article;

/**
 * @author <a href="http://blog.csdn.net/java2000_wl">java2000_wl</a>
 * @version <b>1.0</b>
 */
public class ArticleDao extends AbstractBaseMongoTemplete implements IArticleDao {

	/**
	 * 新增
	 * <br>------------------------------<br>
	 * @param article
	 */
	public void insert(Article article) {
		mongoTemplate.insert(article);
	}
	
	/**
	 * 批量新增
	 * <br>------------------------------<br>
	 * @param articles
	 */
	public void insertAll(List<Article> articles) {
		mongoTemplate.insertAll(articles);
	}
	
	/**
	 * 删除,按主键id, 如果主键的值为null,删除会失败
	 * <br>------------------------------<br>
	 * @param id
	 */
	public void deleteById(String id) {
		Article article = new Article(id);
		mongoTemplate.remove(article);
	}
	
	/**
	 * 按条件删除
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 */
	public void delete(Article criteriaArticle) {
		Criteria criteria = Criteria.where("id").is(criteriaArticle.getId());
		
		Query query = new Query(criteria);
		mongoTemplate.remove(query, Article.class);
	}
	
	/**
	 * 删除全部
	 * <br>------------------------------<br>
	 */
	public void deleteAll() {
		mongoTemplate.dropCollection(Article.class);
	}
	
	/**
	 * 按主键修改,
	 * 如果文档中没有相关key 会新增 使用$set修改器
	 * <br>------------------------------<br>
	 * @param article
	 */
	public void updateById(Article article) {
		Criteria criteria = Criteria.where("id").is(article.getId());
		Query query = new Query(criteria);
		Update update = Update.update("url", article.getUrl())
				.set("html", article.getHtml())
				.set("text", article.getText())
				.set("title", article.getTitle())
				.set("digest", article.getDigest())
				.set("updateTime", article.getUpdateTime());
		mongoTemplate.updateFirst(query, update, Article.class);
	}
	
	/**
	 * 修改多条
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 * @param article
	 */
//	public void update(Article criteriaArticle, Article article) {
//		Criteria criteria = Criteria.where("age").gt(criteriaArticle.getAge());;
//		Query query = new Query(criteria);
//		Update update = Update.update("name", article.getName()).set("age", article.getAge());
//		mongoTemplate.updateMulti(query, update, Article.class);
//	}
	
	/**
	 * 根据主键查询
	 * <br>------------------------------<br>
	 * @param id
	 * @return
	 */
	public Article findById(String id) {
		return mongoTemplate.findById(id, Article.class);
	}
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 * @return
	 */
	public List<Article> findAll() {
		return mongoTemplate.findAll(Article.class);
	}
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 * @return
	 */
	public List<Article> findAll(String textMatch) {
        Query query = new Query(new Criteria("text").regex(".*?" + textMatch + ".*"));
//		Criteria criteria = Criteria.where("text").elemMatch(Criteria.).is(criteriaArticle.getId());
//		query.addCriteria(criteria);
		
		return mongoTemplate.find(query, Article.class);
	}
	
	/**
	 * 按条件查询, 分页
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 * @param skip
	 * @param limit
	 * @return
	 */
	public List<Article> find(Article criteriaArticle, int skip, int limit) {
		Query query = getQuery(criteriaArticle);
		query.skip(skip);
		query.limit(limit);
		return mongoTemplate.find(query, Article.class);
	}
	
	/**
	 * 根据条件查询出来后 再去修改
	 * <br>------------------------------<br>
	 * @param criteriaArticle  查询条件
	 * @param updateArticle    修改的值对象
	 * @return
	 */
//	public Article findAndModify(Article criteriaArticle, Article updateArticle) {
//		Query query = getQuery(criteriaArticle);
//		Update update = Update.update("age", updateArticle.getAge()).set("name", updateArticle.getName());
//		return mongoTemplate.findAndModify(query, update, Article.class);
//	}
	
	/**
	 * 查询出来后 删除
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 * @return
	 */
	public Article findAndRemove(Article criteriaArticle) {
		Query query = getQuery(criteriaArticle);
		return mongoTemplate.findAndRemove(query, Article.class);
	}
	
	/**
	 * count
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 * @return
	 */
	public long count(Article criteriaArticle) {
		Query query = getQuery(criteriaArticle);
		return mongoTemplate.count(query, Article.class);
	}

	/**
	 *
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 * @return
	 */
	private Query getQuery(Article criteriaArticle) {
		if (criteriaArticle == null) {
			criteriaArticle = new Article();
		}
		Query query = new Query();
		if (criteriaArticle.getId() != null) {
			Criteria criteria = Criteria.where("id").is(criteriaArticle.getId());
			query.addCriteria(criteria);
		}
		if (criteriaArticle.getUrl() != null) {
			Criteria criteria = Criteria.where("url").gt(criteriaArticle.getUrl());
			query.addCriteria(criteria);
		}
//		if (criteriaArticle.getName() != null) {
//			Criteria criteria = Criteria.where("name").regex("^" + criteriaArticle.getName());
//			query.addCriteria(criteria);
//		}
		return query;
	}
}

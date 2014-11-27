package com.ssm.mongodb.dao;

import java.util.List;

import com.ssm.mongodb.entity.Article;

public interface IArticleDao {
	
	/**
	 * 新增
	 * <br>------------------------------<br>
	 * @param article
	 */
	void insert(Article arcitle);
	
	/**
	 * 新增
	 * <br>------------------------------<br>
	 * @param articles
	 */
	void insertAll(List<Article> articles);
	
	/**
	 * 删除,主键id, 如果主键的值为null,删除会失败
	 * <br>------------------------------<br>
	 * @param id
	 */
	void deleteById(String id);
	
	/**
	 * 按条件删除
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 */
	void delete(Article criteriaArticle);
	
	/**
	 * 删除全部
	 * <br>------------------------------<br>
	 */
	void deleteAll();
	
	/**
	 * 修改
	 * <br>------------------------------<br>
	 * @param Article
	 */
	void updateById(Article article);
	
	/**
	 * 更新多条
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 * @param Article
	 */
//	void update(Article criteriaArticle, Article article);
	
	/**
	 * 根据主键查询
	 * <br>------------------------------<br>
	 * @param id
	 * @return
	 */
	Article findById(String id);
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 * @return
	 */
	List<Article> findAll();
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 * @return
	 */
	List<Article> findAll(String textMatch);
	
	/**
	 * 按条件查询
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 * @param skip
	 * @param limit
	 * @return
	 */
	List<Article> find(Article criteriaArticle, int skip, int limit);
	
	/**
	 * 根据条件查询出来后 在去修改
	 * <br>------------------------------<br>
	 * @param criteriaArticle  查询条件
	 * @param updateArticle    修改的值对象
	 * @return
	 */
//	Article findAndModify(Article criteriaArticle, Article updateArticle);
	
	/**
	 * 查询出来后 删除
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 * @return
	 */
	Article findAndRemove(Article criteriaArticle);
	
	/**
	 * count
	 * <br>------------------------------<br>
	 * @param criteriaArticle
	 * @return
	 */
	long count(Article criteriaArticle);
}

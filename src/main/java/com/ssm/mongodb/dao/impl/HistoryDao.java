package com.ssm.mongodb.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBList;
import com.mongodb.CommandResult;
import com.mongodb.DBObject;

import com.ssm.mongodb.dao.IHistoryDao;
import com.ssm.mongodb.dao.support.AbstractBaseMongoTemplete;
import com.ssm.mongodb.entity.History;

/**
 * @version <b>1.0</b>
 */
public class HistoryDao extends AbstractBaseMongoTemplete implements IHistoryDao {

	/**
	 * 新增
	 * <br>------------------------------<br>
	 * @param history
	 */
	public void insert(History history) {
		mongoTemplate.insert(history);
	}
	
	/**
	 * 批量新增
	 * <br>------------------------------<br>
	 * @param historys
	 */
	public void insertAll(List<History> historys) {
		mongoTemplate.insertAll(historys);
	}
	
	/**
	 * 删除,按主键id, 如果主键的值为null,删除会失败
	 * <br>------------------------------<br>
	 * @param id
	 */
	public void deleteById(String id) {
		History history = new History(id);
		mongoTemplate.remove(history);
	}
	
	/**
	 * 按条件删除
	 * <br>------------------------------<br>
	 * @param criteriaHistory
	 */
	public void delete(History criteriaHistory) {
		Criteria criteria = Criteria.where("id").is(criteriaHistory.getId());
		
		Query query = new Query(criteria);
		mongoTemplate.remove(query, History.class);
	}
	
	/**
	 * 删除全部
	 * <br>------------------------------<br>
	 */
	public void deleteAll() {
		mongoTemplate.dropCollection(History.class);
	}
	
	/**
	 * 按主键修改,
	 * 如果文档中没有相关key 会新增 使用$set修改器
	 * <br>------------------------------<br>
	 * @param history
	 */
	public void updateById(History history) {
		Criteria criteria = Criteria.where("id").is(history.getId());
		Query query = new Query(criteria);
		Update update = Update.update("url", history.getUrl())
				.set("visitedUrl", history.getVisitedUrl());
		mongoTemplate.updateFirst(query, update, History.class);
	}
	
	/**
	 * 根据主键查询
	 * <br>------------------------------<br>
	 * @param id
	 * @return
	 */
	public History findById(String id) {
		return mongoTemplate.findById(id, History.class);
	}
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 * @return
	 */
	public List<History> findAll() {
		return mongoTemplate.findAll(History.class);
	}
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 * @return
	 */
	public List<History> findAllByUrl(String urlMatch) {
        Query query = new Query(new Criteria("url").regex(".*?" + urlMatch + ".*"));
//		Criteria criteria = Criteria.where("text").elemMatch(Criteria.).is(criteriaHistory.getId());
//		query.addCriteria(criteria);
		
		return mongoTemplate.find(query, History.class);
	}
	
	/**
	 * 按条件查询, 分页
	 * <br>------------------------------<br>
	 * @param criteriaHistory
	 * @param skip
	 * @param limit
	 * @return
	 */
	public List<History> find(History criteriaHistory, int skip, int limit) {
		Query query = getQuery(criteriaHistory);
		query.skip(skip);
		query.limit(limit);
		return mongoTemplate.find(query, History.class);
	}
	
	/**
	 * 根据条件查询出来后 再去修改
	 * <br>------------------------------<br>
	 * @param criteriaHistory  查询条件
	 * @param updateHistory    修改的值对象
	 * @return
	 */
	public History findAndModify(History criteriaHistory, History updateHistory) {
		Query query = getQuery(criteriaHistory);
		Update update = Update.update("url", updateHistory.getUrl()).set("visitedUrl", updateHistory.getVisitedUrl());
		return mongoTemplate.findAndModify(query, update, History.class);
	}
	
	/**
	 * 查询出来后 删除
	 * <br>------------------------------<br>
	 * @param criteriaHistory
	 * @return
	 */
	public History findAndRemove(History criteriaHistory) {
		Query query = getQuery(criteriaHistory);
		return mongoTemplate.findAndRemove(query, History.class);
	}
	
	/**
	 * count
	 * <br>------------------------------<br>
	 * @param criteriaHistory
	 * @return
	 */
	public long count(History criteriaHistory) {
		Query query = getQuery(criteriaHistory);
		return mongoTemplate.count(query, History.class);
	}

	/**
	 *
	 * <br>------------------------------<br>
	 * @param criteriaHistory
	 * @return
	 */
	private Query getQuery(History criteriaHistory) {
		if (criteriaHistory == null) {
			criteriaHistory = new History();
		}
		Query query = new Query();
		if (criteriaHistory.getId() != null) {
			Criteria criteria = Criteria.where("id").is(criteriaHistory.getId());
			query.addCriteria(criteria);
		}
		if (criteriaHistory.getUrl() != null) {
			Criteria criteria = Criteria.where("url").gt(criteriaHistory.getUrl());
			query.addCriteria(criteria);
		}
//		if (criteriaHistory.getName() != null) {
//			Criteria criteria = Criteria.where("name").regex("^" + criteriaHistory.getName());
//			query.addCriteria(criteria);
//		}
		return query;
	}
}

package com.ssm.mongodb.dao;

import java.util.List;

import com.ssm.mongodb.entity.History;

public interface IHistoryDao {
	
	/**
	 * 新增
	 * <br>------------------------------<br>
	 * @param history
	 */
	void insert(History history);
	
	/**
	 * 新增
	 * <br>------------------------------<br>
	 * @param historys
	 */
	void insertAll(List<History> historys);
	
	/**
	 * 删除,主键id, 如果主键的值为null,删除会失败
	 * <br>------------------------------<br>
	 * @param id
	 */
	void deleteById(String id);
	
	/**
	 * 按条件删除
	 * <br>------------------------------<br>
	 * @param criteriaHistory
	 */
	void delete(History criteriaHistory);
	
	/**
	 * 删除全部
	 * <br>------------------------------<br>
	 */
	void deleteAll();
	
	/**
	 * 修改
	 * <br>------------------------------<br>
	 * @param History
	 */
	void updateById(History history);
	
	/**
	 * 根据主键查询
	 * <br>------------------------------<br>
	 * @param id
	 * @return
	 */
	History findById(String id);
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 * @return
	 */
	List<History> findAll();
	
	/**
	 * 查询全部
	 * <br>------------------------------<br>
	 * @return
	 */
	List<History> findAllByUrl(String urlMatch);
	
	/**
	 * 按条件查询
	 * <br>------------------------------<br>
	 * @param criteriaHistory
	 * @param skip
	 * @param limit
	 * @return
	 */
	List<History> find(History criteriaHistory, int skip, int limit);
	
	/**
	 * 根据条件查询出来后 在去修改
	 * <br>------------------------------<br>
	 * @param criteriaHistory  查询条件
	 * @param updateHistory    修改的值对象
	 * @return
	 */
	History findAndModify(History criteriaHistory, History updateHistory);
	
	/**
	 * 查询出来后 删除
	 * <br>------------------------------<br>
	 * @param criteriaHistory
	 * @return
	 */
	History findAndRemove(History criteriaHistory);
	
	/**
	 * count
	 * <br>------------------------------<br>
	 * @param criteriaHistory
	 * @return
	 */
	long count(History criteriaHistory);
}

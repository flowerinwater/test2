package com.ssm.mongodb.dao.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author <a href="http://blog.csdn.net/java2000_wl">java2000_wl</a>
 * @version <b>1.0</b>
 */
public abstract class AbstractBaseMongoTemplete implements ApplicationContextAware {
	
	protected MongoTemplate mongoTemplate;

	/**
	 * 设置mongoTemplate
	 * @param mongoTemplate the mongoTemplate to set
	 */
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		MongoTemplate mongoTemplate = applicationContext.getBean("mongoTemplate", MongoTemplate.class);
		setMongoTemplate(mongoTemplate);
	}
}

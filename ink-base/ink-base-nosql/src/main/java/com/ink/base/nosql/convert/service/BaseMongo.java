package com.ink.base.nosql.convert.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * mongo 基础服务
 *
 */
@Service("baseMongo")
public class BaseMongo {

//	@Autowired
	MongoTemplate mongoTemplate;
	
	/**
	 * 获取指定collection的自增Id（需要初始化）
	 * @param collection
	 * @return
	 */
	public Integer getAutoIncrementId(String collection){
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("name").is(collection));
		
		BasicDBObject object = new BasicDBObject("$inc", new BasicDBObject("id", 1));
		
		Update update = Update.fromDBObject(object);
		
		DBObject obj = mongoTemplate.findAndModify(query, update, DBObject.class, "ids");
		
		Integer id = Integer.parseInt(obj.get("id").toString());

		return id;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}

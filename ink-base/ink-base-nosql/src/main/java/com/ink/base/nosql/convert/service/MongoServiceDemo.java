package com.ink.base.nosql.convert.service;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;
/**
 * User: kingstar
 * Date: 16-3-15
 * Time: 下午8:03
 */
public class MongoServiceDemo {

//    @Autowired
    MongoTemplate mongoTemplate;

//    @Autowired
    MongoConverter mongoConverter;

//    @Autowired
    BaseMongo baseMongo;

    public void addmongoPo(MongoPo mongoPo)
            throws Exception {
        Integer id = baseMongo.getAutoIncrementId("COLLECTION_NAME");
        mongoPo.setId(id);
        mongoTemplate.insert(mongoPo, "COLLECTION_NAME");
    }

    public int deletemongoPo(Integer id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        WriteResult writeResult = mongoTemplate.remove(query,  "COLLECTION_NAME");
        return writeResult.getN();
    }

    public int updatemongoPo(MongoPo mongoPo)
            throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(mongoPo.getId()));
        //全量更新
        BasicDBObject dbObject = new BasicDBObject();
        mongoConverter.write(mongoPo, dbObject);
        Update update = Update.fromDBObject(dbObject, "_id");
        WriteResult  writeResult = mongoTemplate.upsert(query, update,  "COLLECTION_NAME");
        return writeResult.getN();
    }

    public MongoPo getmongoPoById(Integer id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, MongoPo.class,  "COLLECTION_NAME");
    }

    public Page<MongoPo> getmongoPoPage(PageRequest  request ) {
        int offset = request.getOffset();
        int pageSize = request.getPageSize();
        Page page=new Page(request,0);
        Query query = new Query();
        Criteria.where("createTime").lt(new Date());
        Criteria.where("createTime").gte("2016-04-19 10:00:00");
        query.addCriteria(Criteria.where("request").is(request.getSortInfos()));
        query.with(new Sort(Sort.Direction.DESC,"createTime"));
        int count = (int) mongoTemplate.count(query,  "COLLECTION_NAME");
        page.setTotalCount(count);
        query.skip(offset);
        query.limit(pageSize);
        List<MongoPo> list =null;
        if (count>0) list= mongoTemplate.find(query, MongoPo.class,  "COLLECTION_NAME");
        page.setResult(list);
        return page;
    }

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void setMongoConverter(MongoConverter mongoConverter) {
		this.mongoConverter = mongoConverter;
	}

	public void setBaseMongo(BaseMongo baseMongo) {
		this.baseMongo = baseMongo;
	}
}

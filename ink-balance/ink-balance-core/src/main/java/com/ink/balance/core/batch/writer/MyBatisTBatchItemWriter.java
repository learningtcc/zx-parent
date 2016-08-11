package com.ink.balance.core.batch.writer;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ink.balance.api.constants.LoggerCnst;
import com.ink.base.log.util.YinkerLogger;

/**
 * @author bo.chen
 * @Description 写渠道数据到数据库中
 * @date 2016年4月11日 上午11:03:07
 */
@Transactional
public class MyBatisTBatchItemWriter<T> implements ItemWriter<T>,
        InitializingBean {

    protected static final Log logger = LogFactory.getLog(MyBatisBatchItemWriter.class);
    
    YinkerLogger loger = YinkerLogger.getLogger(MyBatisTBatchItemWriter.class);
    
    private SqlSessionTemplate sqlSessionTemplate;

    private String statementId;

    private boolean assertUpdates = true;

    /**
     * Public setter for the flag that determines whether an assertion is made
     * that all items cause at least one row to be updated.
     *
     * @param assertUpdates the flag to set. Defaults to true;
     */
    public void setAssertUpdates(boolean assertUpdates) {
        this.assertUpdates = assertUpdates;
    }

    /**
     * Public setter for {@link SqlSessionFactory} for injection purposes.
     *
     * @param sqlSessionFactory
     */
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (sqlSessionTemplate == null) {
            this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory,
                    ExecutorType.BATCH);
        }
    }

    /**
     * Public setter for the {@link SqlSessionTemplate}.
     *
     * @param sqlSessionTemplate
     */
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * Public setter for the statement id identifying the statement in the
     * SqlMap configuration file.
     *
     * @param statementId the id for the statement
     */
    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    /**
     * Check mandatory properties - there must be an SqlSession and a
     * statementId.
     */
    public void afterPropertiesSet() {
        // notNull(sqlSessionTemplate,
        // "A SqlSessionFactory or a SqlSessionTemplate is required.");
        // isTrue(ExecutorType.BATCH == sqlSessionTemplate.getExecutorType(),
        // "SqlSessionTemplate's executor type must be BATCH");
        // notNull(statementId, "A statementId is required.");
    }
    public static <T> List<List<T>> getSubList(List<T> listObj, int groupNum) { 
    	List<List<T>> resultList = new ArrayList<List<T>>(); 
    	// 获取需要拆分的List个数 
    	int loopCount = (listObj.size() % groupNum == 0) ? (listObj.size() / groupNum) 
    	: ((listObj.size() / groupNum) + 1); 
    	// 开始拆分 
    	for (int i = 0; i < loopCount; i++) { 
    	// 子List的起始值 
    	int startNum = i * groupNum; 
    	// 子List的终止值 
    	int endNum = (i + 1) * groupNum; 
    	// 不能整除的时候最后一个List的终止值为原始List的最后一个 
    	if (i == loopCount - 1) { 
    	endNum = listObj.size(); 
    	} 
    	// 拆分List 
    	List<T> listObjSub = listObj.subList(startNum, endNum); 
    	// 保存差分后的List 
    	resultList.add(listObjSub); 
    	} 
    	return resultList; 

    	}
    /**
     * {@inheritDoc}
     */
    public void write(final List<? extends T> items) {
        if (!items.isEmpty()) {

            if (logger.isDebugEnabled()) {
                logger.debug("Executing batch with " + items.size() + " items.");
            }
           
            List<?> lists=getSubList(items,1500);
            for(int i=0;i<lists.size();i++){
            	List<?> list=(List<?>) lists.get(i);
            	sqlSessionTemplate.insert(statementId, list);
            }
            	
            try {
            	loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                        "开始插入渠道数据。。。。", null);
            	sqlSessionTemplate.flushStatements();
            } catch (Exception e) {
            	 loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                         "渠道数据插入异常", e, null);
            	 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
            }
        }
    }

}

package com.ink.base.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.util.JdbcUtils;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;

public class BaseIbatisDao<E, PK extends Serializable> extends SqlSessionDaoSupport implements EntityDao<E, PK> {
	private static YinkerLogger log = YinkerLogger.getLogger(BaseIbatisDao.class);
	@Autowired
	private SqlSessionTemplate sqlSessionSlave;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	protected void checkDaoConfig() {
		// notNull(this.sqlSession, "Property 'sqlSessionFactory' or
		// 'sqlSessionTemplate' are required");
	}

	public E getById(PK primaryKey, boolean masterMark) {
		Object object = null;
		if (masterMark)
			object = getSqlSession().selectOne(getFindByPrimaryKeyStatement(), primaryKey);
		return (E) object;
	}

	public E getById(PK primaryKey) {
		Object object = sqlSessionSlave.selectOne(getFindByPrimaryKeyStatement(), primaryKey);
		return (E) object;
	}

	public E getById(PK primaryKey, String tbSuffix) {
		Map param = new HashMap();
		if (primaryKey != null)
			param.put("id", primaryKey);
		if (tbSuffix != null)
			param.put("tbSuffix", tbSuffix);
		Object object = sqlSessionSlave.selectOne(getFindByPrimaryKeyStatement(), param);
		return (E) object;
	}

	public int deleteById(PK id) {
		Configuration config = getSqlSession().getConfiguration();
		if (config.hasStatement(getFindByPrimaryKeyStatement())) {
			logData(id, "DELETE");
		}
		int affectCount = getSqlSession().delete(getDeleteStatement(), id);
		return affectCount;
	}

	public void deleteById(PK id, String tbSuffix) {
		Configuration config = getSqlSession().getConfiguration();
		if (config.hasStatement(getFindByPrimaryKeyStatement())) {
			logData(id, "DELETE");
		}
		Map param = new HashMap();
		if (id != null)
			param.put("id", id);
		if (tbSuffix != null)
			param.put("tbSuffix", tbSuffix);
		int affectCount = getSqlSession().delete(getDeleteStatement(), param);
	}

	public int save(E entity) {
		prepareObjectForSave(entity);
		int affectCount = getSqlSession().insert(getInsertStatement(), entity);
		
		logData(entity, "INSERT");
		return affectCount;
	}

	public int saveBatch(List<E> list) throws DataAccessException {
		for (E e : list) {
			prepareObjectForSave(e);
		}
		int sct = getSqlSession().insert(getInsertBatchStatement(), list);
		return sct;
	}

	public int update(E entity) {
		prepareObjectForUpdate(entity);
		int upct = getSqlSession().update(getUpdateStatement(), entity);
		logData(entity,"UPDATE");
		return upct;
	}

	private void logData(Object ob, String operType) {
		
//		HashMap<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("OP_TYPE", operType);
//		dataMap.put("OP_DATA", ob);
//		dataMap.put("TABLE", getTableName(ob, operType));
//		if(ob instanceof BaseEntity){
//			BaseEntity be = (BaseEntity)ob;
//			dataMap.put("ENTITY", ob.getClass().getName());
//			dataMap.put("PK_VALUE", be.getPkValue());
//			dataMap.put("KEY_VALUE", be.getKeyValue());
//		}else {
//			dataMap.put("PK_VALUE", ob);
//		}
//		log.data(JSON.toJSONString(dataMap));
	}
	
	private String getTableName(Object ob, String operType){
		
		MappedStatement ms = null;
		BoundSql boundSql = null;
		SQLStatementParser sp = null;
		
		if("INSERT".equalsIgnoreCase(operType)){
			ms = getSqlSession().getConfiguration().getMappedStatement(getInsertStatement());
			boundSql = ms.getBoundSql(ob);
			sp = SQLParserUtils.createSQLStatementParser(boundSql.getSql(), JdbcUtils.MYSQL);
			MySqlInsertStatement mysqlInsert = (MySqlInsertStatement)sp.parseInsert();
			
			return mysqlInsert.getTableName().getSimpleName();
		}else if("UPDATE".equalsIgnoreCase(operType)){
			ms = getSqlSession().getConfiguration().getMappedStatement(getUpdateStatement());
			boundSql = ms.getBoundSql(ob);
			sp = SQLParserUtils.createSQLStatementParser(boundSql.getSql(), JdbcUtils.MYSQL);
			SQLUpdateStatement update = sp.parseUpdateStatement();
			return update.getTableName().getSimpleName();
		}else if("DELETE".equalsIgnoreCase(operType)){
			ms = getSqlSession().getConfiguration().getMappedStatement(getDeleteStatement());
			boundSql = ms.getBoundSql(ob);
			sp = SQLParserUtils.createSQLStatementParser(boundSql.getSql(), JdbcUtils.MYSQL);
			SQLDeleteStatement delete = sp.parseDeleteStatement();
			return delete.getTableName().getSimpleName();
		}
		
		return "";
	}

	protected void prepareObjectForSave(E o) {
	}

	protected void prepareObjectForUpdate(E o) {
	}

	public String getFindByPrimaryKeyStatement() {
		return getIbatisSqlMapNamespace() + ".getById";
	}

	public String getInsertStatement() {
		return getIbatisSqlMapNamespace() + ".insert";
	}

	public String getInsertBatchStatement() {
		return getIbatisSqlMapNamespace() + ".insertBatch";
	}

	public String getUpdateStatement() {
		return getIbatisSqlMapNamespace() + ".update";
	}

	public String getDeleteStatement() {
		return getIbatisSqlMapNamespace() + ".delete";
	}

	public String getFindStatement() {
		return getIbatisSqlMapNamespace() + ".find";
	}

	public String getFindPageStatement() {
		return getIbatisSqlMapNamespace() + ".findPage";
	}

	public String getCountStatementForPaging(String statementName) {
		return statementName + "Count";
	}

	public String getIbatisSqlMapNamespace() {
		throw new RuntimeException("not yet implement");
	}

	protected Page pageQuery(String statementName, PageRequest pageRequest) {
		if (pageRequest.getMasterMark()) {
			return pageQuery(getSqlSession(), statementName, getCountStatementForPaging(statementName), pageRequest);
		} else {
			return pageQuery(sqlSessionSlave, statementName, getCountStatementForPaging(statementName), pageRequest);
		}

	}

	public static Page pageQuery(SqlSession sqlSession, String statementName, String countStatementName,
			PageRequest pageRequest) {
		Number totalCount = (Number) sqlSession.selectOne(countStatementName, pageRequest);
		if ((totalCount == null) || (totalCount.longValue() <= 0L)) {
			return new Page(pageRequest, 0);
		}

		// if(p.getPageSize()<=0 && p.getLimit()>0){
		/***
		 * limit 如果设置了值 ，pagesiz=limit;
		 */
		if (pageRequest.getLimit() > 0) {
			pageRequest.setPageSize(pageRequest.getLimit());
		}
		Page page = new Page(pageRequest, totalCount.intValue());

		if (pageRequest.getLimit() == 0) {
			pageRequest.setLimit(page.getPageSize());
		}
		if (pageRequest.getOffset() == 0) {
			pageRequest.setOffset(page.getFirstResult());
		}

		RowBounds rowBounds = new RowBounds(page.getFirstResult(), page.getPageSize());

		List list = sqlSession.selectList(statementName, pageRequest, rowBounds);

		// List list = sqlSession.selectList(statementName, pageRequest);

		page.setResult(list);

		return page;
	}

	protected List<E> listQuery(String statementName, PageRequest pageRequest) {
		if (pageRequest.getMasterMark()) {
			return getSqlSession().selectList(statementName, pageRequest);
		} else {
			return sqlSessionSlave.selectList(statementName, pageRequest);
		}
	}

	protected List<E> listQuery(String statementName, Map paramMap) {
		if ("true".equals(paramMap.get("masterMark"))) {
			return getSqlSession().selectList(statementName, paramMap);
		} else {
			return sqlSessionSlave.selectList(statementName, paramMap);
		}

	}

	@Deprecated
	protected List<E> listQuery(String statementName, PageRequest pageRequest, int limit) {
		pageRequest.setLimit(limit);
		List list = null;
		if (pageRequest.getMasterMark()) {
			list = getSqlSession().selectList(statementName, pageRequest);
		} else {
			return sqlSessionSlave.selectList(statementName, pageRequest);
		}
		return list;
	}

	public List<E> find(PageRequest query) {
		return listQuery(getFindStatement(), query);
	}

	public List<E> find(PageRequest query, int limit) {
		return listQuery(getFindStatement(), query, limit);
	}

	public Page<E> findPage(PageRequest query) {
		return pageQuery(getFindPageStatement(), query);
	}

	public E getByEntity(E entity) {
		Object object = getSqlSession().selectOne(getFindByPrimaryKeyStatement(), entity);
		return (E) object;
	}

	public void deleteByEntity(E entity) throws DataAccessException {
		Configuration config = getSqlSession().getConfiguration();
		if (config.hasStatement(getFindByPrimaryKeyStatement())) {
			logData(entity, "DELETE");
		}
		getSqlSession().delete(getDeleteStatement(), entity);
	}

	public boolean isEmpty(Object id) {
		if (id == null)
			return true;
		if (id instanceof Long) {
			return (Long) id <= 0L;
		} else if (id instanceof String) {
			return StringUtils.isEmpty(id);
		}
		return false;
	}

	public SqlSessionTemplate getSqlSessionSlave() {
		return sqlSessionSlave;
	}
	
	public static void main(String[] args) {
		
	}
}
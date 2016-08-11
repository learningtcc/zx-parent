package com.ink.base.mybatis.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.mybatis.interceptor.dialect.Dialect;
import com.ink.base.mybatis.interceptor.dialect.MySQLDialect;
import com.ink.base.mybatis.interceptor.dialect.OracleDialect;

/**
 * @ClassName: PaginationInterceptor 
 * @Description: 数据库物理分页拦截器
 * @author zongtt
 * @date 2016年4月15日13:16:28
 * @version V1.0 
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	protected static YinkerLogger log = YinkerLogger.getLogger(PaginationInterceptor.class);

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
		
		BoundSql boundSql = statementHandler.getBoundSql();
		
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			if(log.isDebugEnabled()){
				String sql = boundSql.getSql();
				sql = sql.replaceAll("\n", " ");
				sql = sql.replaceAll("\t", " ");
				log.debug("生成SQL : " + sql);
				log.debug("SQL参数   : " + JSON.toJSONString(boundSql.getParameterObject()));
			}
			return invocation.proceed();
		}

		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			
		}
		if (databaseType == null) {
			throw new RuntimeException(
					"the value of the dialect property in configuration.xml is not defined : "
							+ configuration.getVariables().getProperty("dialect"));
		}
		Dialect dialect = null;
		switch (databaseType) {
		case ORACLE:
			dialect = new OracleDialect();
			break;
		case MYSQL:
			dialect = new MySQLDialect();
			break;
		}

		metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		
		if(log.isDebugEnabled()){
			String sql = boundSql.getSql();
			sql = sql.replaceAll("\n", " ");
			sql = sql.replaceAll("\t", " ");
			log.debug("生成分页SQL : " + sql);
			log.debug("分页SQL参数   : " + JSON.toJSONString(boundSql.getParameterObject()));
		}
		
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties prop) {
		
	}

}


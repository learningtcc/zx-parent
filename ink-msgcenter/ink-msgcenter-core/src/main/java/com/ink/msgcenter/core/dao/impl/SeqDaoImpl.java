package com.ink.msgcenter.core.dao.impl;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.msgcenter.core.dao.ISeqDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository("seqDao")
public class SeqDaoImpl extends BaseIbatisDao<Object, Long> implements ISeqDao {

	private YinkerLogger logger = YinkerLogger.getLogger(getClass());
	
	@Override
	public String get5Seq(String seqCode) {
		
		int result = 0;
		SqlSessionTemplate st = (SqlSessionTemplate) getSqlSession();

		Connection connection = SqlSessionUtils
				.getSqlSession(st.getSqlSessionFactory(), st.getExecutorType(), st.getPersistenceExceptionTranslator())
				.getConnection();
		CallableStatement cstmt = null;
		try {
			cstmt = connection.prepareCall("{call P_SEQ_F5(?,?)}");
			cstmt.setString(1, seqCode);
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			cstmt.execute();
			int i = cstmt.getInt(2);
			result = i;
		} catch (SQLException e) {
			logger.error("存储过程P_SEQ_F5执行异常!",e);
		}finally {
			try {
				if(cstmt!=null){
					cstmt.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("存储过程P_SEQ_F5关闭连接异常!",e);
			}
		}
		
		if (result == 0) {
			return "";
		}
		
		String nextSeq = String.valueOf(result);
		
		StringBuilder sb = new StringBuilder("00000");
		
		nextSeq = sb.substring(0,5 - nextSeq.length()) + nextSeq;
		
		return nextSeq;
	}
}
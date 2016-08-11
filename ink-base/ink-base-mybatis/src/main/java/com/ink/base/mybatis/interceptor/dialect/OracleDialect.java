package com.ink.base.mybatis.interceptor.dialect;


public class OracleDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit){
		
//		sql = sql.trim();
//		
//		String lowSql = sql.toLowerCase();
//		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
//		
//		int fromIndex = lowSql.indexOf("from");
//		
//		if(lowSql.replaceAll(" ", "").contains("orderby")){
//			if(offset <= 0){
//				pagingSelect.append("SELECT row_.* FROM (");
//				pagingSelect.append(sql).append(") row_");
//				pagingSelect.append(" WHERE ROWNUM <=").append(offset + limit);
//				
//			}else {
//				pagingSelect.append("SELECT * FROM ( SELECT row_.*, ROWNUM rownum_ from ( ");    
//				pagingSelect.append(sql);    
//				pagingSelect.append(" ) row_ ) WHERE rownum_ > ").append(offset).append(" AND rownum_ <= ").append(offset + limit);    
//			}
//			
//		}else {
//			StringBuffer sqlsb = new StringBuffer(sql.trim());
//			
//			sqlsb.insert(fromIndex, ", ROWNUM rownum_ ");
//			
//			if(offset <= 0){
//				pagingSelect.append(sqlsb.toString());
//			}else {
//				pagingSelect.append("SELECT * FROM ( ");
//				pagingSelect.append(sqlsb.toString());
//			}
//			
//			if(lowSql.contains("where")){
//				pagingSelect.append(" AND ROWNUM <=").append(offset + limit);
//			}else {
//				pagingSelect.append(" WHERE ROWNUM <=").append(offset + limit);
//			}
//			
//			if(offset > 0){
//				pagingSelect.append(" )  WHERE rownum_ > ").append(offset);
//			}
//		}
//		
//		return pagingSelect.toString();
		
		sql = sql.trim();    
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);    
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");    
		pagingSelect.append(sql);    
		pagingSelect.append(" ) row_ ) where rownum_ > ").append(offset).append(" and rownum_ <= ").append(offset + limit);    
		return pagingSelect.toString();    
	}
}
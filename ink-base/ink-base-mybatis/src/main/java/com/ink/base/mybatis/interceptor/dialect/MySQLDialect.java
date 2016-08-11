package com.ink.base.mybatis.interceptor.dialect;

public class MySQLDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
		
		String limitSQL = sql.toLowerCase().replaceAll("\n", " ").replaceAll("\t", " ");
		
		if(limitSQL.contains(" limit ")){
			return sql;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 25);    
		pagingSelect.append(sql);    
		String page = offset > 0 ? " limit " + offset + ", " + limit : " limit " + limit;
		pagingSelect.append(page);
		return pagingSelect.toString();    
	}
	
	public static void main(String[] args) {
		String x = "SELECT       id, amt, arrived_amt, channel_no,channel_merchant_no, platform_order_no,   trans_no, pay_time,   arrived_time,   pay_status, pay_method, check_status,   reside_flag, reside_to_date, create_date,   update_date,   del_flag,   version, remark,batch_id      FROM platform_data        WHERE 1=1       ORDER BY update_date        limit ? , ?";
		MySQLDialect md = new MySQLDialect();
		System.out.println(md.getLimitString(x, 0, 10));
	}

}

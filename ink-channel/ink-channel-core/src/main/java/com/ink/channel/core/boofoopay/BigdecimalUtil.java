package com.ink.channel.core.boofoopay;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;


/**
 * ClassName: BigdecimalUtil 
 * @Description: s
 * @author peijiahe
 * @date 2016年1月14日
 */

public class BigdecimalUtil {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;
	
	/**
	 * 提供精确的加法运算。
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	
	/**
	 * 提供精确的减法运算。
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2  除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	/**
	 * @Description: 格式化金额
	 * @param @param money
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author peijiahe
	 * @date 2016年1月14日
	 */
	public static String formatMoney(BigDecimal money)throws Exception{
		if(money == null){
			return "";
		}
		java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
		String str = myformat.format(money);
		return str;
	}
	
	/**
	 * @Description: 格式化字符串类型的金额
	 * @param @param money 收益字符串
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author peijiahe
	 * @date 2016年1月14日
	 */
	public static String formatMoneyStr(String money)throws Exception{
		if(StringUtils.isBlank(money) || "null".equals(money)){
			return "";
		}
		BigDecimal bigDecimal = new BigDecimal(money);
		java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
		String str = myformat.format(bigDecimal);
		return str;
	}
	
	/**
	 * @Description: 格式化字符串类型的金额
	 * @param @param money 收益字符串
	 * @param @param num  后面要保留小数的个数
	 * @param @return
	 * @param @throws Exception   
	 * @return String  
	 * @throws
	 * @author peijiahe
	 * @date 2016年1月14日
	 */
	public static String formatMoneyStr(String money,int num)throws Exception{
		if(StringUtils.isBlank(money) || "null".equals(money)){
			return "";
		}
		BigDecimal bigDecimal = new BigDecimal(money);
		StringBuffer buf = new StringBuffer("0.");
		if(num == 0){
			buf = new StringBuffer("0");
		}
		for(int i = 0;i<num;i++){
			buf.append("0");
		}
		java.text.DecimalFormat myformat = new java.text.DecimalFormat(buf.toString());
		String str = myformat.format(bigDecimal);
		return str;
	}
	
	public static void main(String[] args) {
//		try {
//			String formatMoneyStr = BigdecimalUtil.formatMoneyStr("0.12", 6);
//			System.out.println(formatMoneyStr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}

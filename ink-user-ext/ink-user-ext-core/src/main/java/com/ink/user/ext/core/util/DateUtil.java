package com.ink.user.ext.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author BYX
 */
public class DateUtil {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 将时间转成"yyyyMMdd"格式的字符串
	 * 
	 * @param date
	 *            时间
	 */
	public static String formatToYYYYMMDDStr(Date date) {
		if (date != null) {
			return DateFormat.getInstance("yyyyMMdd").format(date);
		} else {
			return "null";
		}
	}

	/**
	 * 将时间转成"yyyy-MM-dd"格式的字符串
	 * 
	 * @param date时间
	 * @return
	 */
	public static String formatToYYYYMMDD(Date date) {
		if (date != null) {
			return DateFormat.getInstance("yyyy-MM-dd").format(date);
		} else {
			return "null";
		}
	}

	public static Date formatToDayByYYYYMMDDMMHH(String str)
			throws ParseException {
		DateFormat format = DateFormat.getInstance("yyyy-MM-dd HH:mm");
		return format.parse(str);
	}
	
	public static Date formatToyyyyMMddHHmmss(String str)
			throws ParseException {
		DateFormat format = DateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		return format.parse(str);
	}

	public static Date formatToDayByYYYYMMDD(String str) throws ParseException {
		DateFormat format = DateFormat.getInstance("yyyy-MM-dd");
		return format.parse(str);
	}

	public static String formatToYYYYMMDDMMHHSS(Date date) {
		DateFormat format = DateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * @date 2016年4月27日 上午11:41:38
	 * @Description: 返回yyyyMMddHHmmss
	 * @author wanghao
	 * @param date
	 * @return
	 */
	public static String formatToYYYYMMDDMMHHSSStr(Date date) {
		DateFormat format = DateFormat.getInstance("yyyyMMddHHmmss");
		return format.format(date);
	}

	/**
	 * 两日期相差天数
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {

		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 两日期相差月数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getMonth(Date startDate, Date endDate) {
		long monthday;
		try {
			startDate = sdf.parse(sdf.format(startDate));
			endDate = sdf.parse(sdf.format(endDate));

			Calendar starCal = Calendar.getInstance();
			starCal.setTime(startDate);

			int sYear = starCal.get(Calendar.YEAR);
			int sMonth = starCal.get(Calendar.MONTH);
			int sDay = starCal.get(Calendar.DATE);

			Calendar endCal = Calendar.getInstance();
			endCal.setTime(endDate);
			int eYear = endCal.get(Calendar.YEAR);
			int eMonth = endCal.get(Calendar.MONTH);
			int eDay = endCal.get(Calendar.DATE);

			monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));

			if (sDay < eDay) {
				monthday = monthday + 1;
			}
			return Integer.parseInt(String.valueOf(monthday));
		} catch (ParseException e) {
			monthday = 0;
		}
		return Integer.parseInt(String.valueOf(monthday));
	}

	/**
	 * 比较两个日期之间的大小
	 * 
	 * @param d1
	 * @param d2
	 * @return 前者大于后者返回true 反之false
	 */
	public static boolean compareDate(Date d1, Date d2) {
		if (d1 == null) {
			d1 = new Date();
		}
		if (d2 == null) {
			d2 = new Date();
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);

		int result = c1.compareTo(c2);
		if (result >= 0)
			return true;
		else
			return false;
	}
	public static long getSenconds(Date start, Date end) throws ParseException{
	    java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = df.parse(df.format(start));
        Date d2 = df.parse(df.format(end));
        long diff = d2.getTime() - d1.getTime();
        long seconds = diff/1000;
        return seconds;
	}
}

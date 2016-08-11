package com.ink.user.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName: DateUtils
 * @Description: 时间工具类
 * @author guojie.gao
 * @date 2015年10月19日 下午4:58:30
 *
 */
public class DateUtils {

	/**
	 * 
	 * @Title: getCurrentDate
	 * @Description: 得到当天时间字符串
	 * @param @return
	 * @return String
	 * @throws
	 *
	 */
	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	public static String getCurrTimeStr() {
		return new SimpleDateFormat("hhmmss").format(new Date());
	}

	/**
	 * 
	 * @Title: parseDateToStrByFormat
	 * @Description: 将时间转化为符合要求的字符串
	 * @param @param date
	 * @param @param dateFormat
	 * @param @return
	 * @return String
	 * @throws
	 *
	 */
	public static String parseDateToStrByFormat(Date date, String dateFormat) {
		if (date != null && StringUtils.isNotBlank(dateFormat)) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 
	 * @Title: getCurrentGapTime
	 * @Description: TODO
	 * @param @param minutes
	 * @param @return
	 * @return Date
	 * @throws
	 *
	 */
	public static Date getCurrentGapTime(int minutes) {
		Calendar afterTime = Calendar.getInstance();
		afterTime.add(Calendar.MINUTE, minutes);
		Date gapDate = (Date) afterTime.getTime();
		return gapDate;
	}

	public static Date StringToDateByFormat(String dateStr, String formatStr) {
		SimpleDateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	
//	public static Date getCurrentDateTime() {
//		try {
//			long currentTime = System.currentTimeMillis();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date date = new Date(currentTime);
//			if (date != null) {
//				 return formatter.format(date);
//			}
//		} catch (Exception e) {
//			return new Date();
//		}
//	}

	public static String getDate(Date date) {
		try {
			if (date != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				return sdf.format(date);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static String getDateTime() {
		try {
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date(currentTime);
			String dateTime = sdf.format(date);
			return dateTime;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getCurrentTime() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			long time = sdf.parse(sdf.format(new Date())).getTime();
			return time + "";
		} catch (Exception e) {
			return null;
		}
	}

	public static Date strToDateLong(String dateStr) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date strtodate = formatter.parse(dateStr);
		return strtodate;
	}

	public static Date getDate(String dateStr) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		java.util.Date cDate = df.parse(dateStr);
		return cDate;
	}

	public static Date getStrToDate(String dateStr) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date cDate = df.parse(dateStr);
		return cDate;
	}

	public static String getDateTime(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getSqlDateTime(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getTime() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			return sdf.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getTime(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date strToTime(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date strToDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			return df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			java.util.Date d = sdf.parse(dateStr);
			return new Date(d.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date formateDateToSs(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateStr = sdf.format(date);
			Date d = sdf.parse(dateStr);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String dateToStr(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	/**
	 * 
	 * @Title: isValidBirthDate
	 * @Description: 检查生日字符串是否合法
	 * @param @param dateStr
	 * @param @param pattern
	 * @param @return
	 * @return boolean
	 * @throws
	 *
	 */
	public static boolean isValidBirthDate(String dateStr, String pattern) {

		if (dateStr == null || dateStr.trim().length() == 0) {
			return true;
		}

		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(pattern);
		df.setLenient(false);
		try {
			Date date = df.parse(dateStr);
			int year = date.getYear();
			if (year < 0 || year > new Date().getYear()) // 如果生日小于1900年或大于当前日，认为错误
			{
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static String getCurrDateTimeStr() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	/**
	 * 
	 * @Title: addDays
	 * @Description: 按日加减日期
	 * @param @param date
	 * @param @param num
	 * @param @return
	 * @return Date
	 * @throws
	 *
	 */
	public static Date addDays(Date date, int num) {
		if (date == null) {
			return null;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, num);

		return c.getTime();
	}

	/**
	 * 
	 * @FunctionName getBetweenDays
	 * @Description 取得两个时间段的时间间隔
	 * @author color
	 * @param t1
	 *            时间1
	 * @param t2
	 *            时间2
	 * @return t2 与t1的间隔天数
	 * @throws ParseException
	 *             如果输入的日期格式不是0000-00-00 格式抛出异常
	 * @author guojie.gao
	 * @date 2015年12月9日 下午3:20:14
	 * @version 1.0
	 * @history guojie.gao, 2015年12月9日 下午3:20:14 create
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 * @throws ParseException
	 */
	public static int getBetweenDays(String t1, String t2)
			throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		int betweenDays = 0;
		Date d1 = df.parse(t1);
		Date d2 = df.parse(t2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		// if (c1.after(c2)) {
		// c1 = c2;
		// c2.setTime(d1);
		// }
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)
				- c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}

	public static Long dateDiff(String startTime, String endTime) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddhhmmss");
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			hour = diff % nd / nh + day * 24;// 计算差多少小时
			min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
			sec = diff % nd % nh % nm / ns;// 计算差多少秒
			System.out.println(startTime);
			System.out.println(endTime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long tatalMin = day*24*60 + hour*60 +min;
		System.out.println(tatalMin);
		return tatalMin;
	}
}

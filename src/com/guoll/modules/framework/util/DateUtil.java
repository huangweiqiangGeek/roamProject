package com.guoll.modules.framework.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间帮助�?
 * 
 * @version $Id: DateUtil.java,v 1.1 2008/05/28 04:29:52 guoliangliang Exp $
 * 
 *          author guoliangliang
 */
public class DateUtil{

	private static Calendar calendar = Calendar.getInstance();

	/**
	 * 得到当前的时间，时间格式yyyy-MM-dd
	 * 
	 * @return
	 * 
	 *         author guoliangliang
	 */
	public static String getCurrentDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	/**
	 * 得到当前的时间，时间格式yyyy-MM-dd
	 * 
	 * @return
	 * 
	 *         author guoliangliang
	 */
	public static String getCurrentFullDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	/**
	 * 得到当前的时�?自定义时间格�?y �?M �?d �?H �?m �?s �?
	 * 
	 * @param dateFormat
	 *            输出显示的时间格�?
	 * @return
	 * 
	 *         author guoliangliang
	 */
	public static String getCurrentDateStr(String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}

	/**
	 * 
	 * @return Date (yyyy-MM-dd)
	 * 
	 *         author guoliangliang
	 */
	public static Date getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(format.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @return Date (yyyy-MM-dd HH:mm:ss)
	 * 
	 *         author guoliangliang
	 */
	public static Date getCurrentDateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(format.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期格式化，默认日期格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getFormatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 日期格式化，默认日期格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getFormatFullDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	
	/**
	 * 日期格式化，自定义输出日期格�?
	 * 
	 * @param date
	 * @return
	 * 
	 *         author guoliangliang
	 */
	public static String getFormatDate(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 返回当前日期的前�?��时间日期，amount为正�?当前时间后的时间 为负�?当前时间前的时间 默认日期格式yyyy-MM-dd
	 * 
	 * @param field
	 *            日历字段 y �?M �?d �?H �?m �?s �?
	 * @param amount
	 *            数量
	 * @return �?��日期
	 * 
	 *         author guoliangliang
	 */
	public static String getPreDate(String field, int amount) {
		calendar.setTime(new Date());
		if (field != null && !field.equals("")) {
			if (field.equals("y")) {
				calendar.add(calendar.YEAR, amount);
			} else if (field.equals("M")) {
				calendar.add(calendar.MONTH, amount);
			} else if (field.equals("d")) {
				calendar.add(calendar.DAY_OF_MONTH, amount);
			} else if (field.equals("H")) {
				calendar.add(calendar.HOUR, amount);
			}
		} else {
			return null;
		}
		return getFormatDate(calendar.getTime());
	}

	/**
	 * 某一个日期的前一个日�?
	 * 
	 * @param d
	 *            ,某一个日�?
	 * @param field
	 *            日历字段 y �?M �?d �?H �?m �?s �?
	 * @param amount
	 *            数量
	 * @return �?��日期
	 * 
	 *         author guoliangliang
	 */
	public static String getPreDate(Date d, String field, int amount) {
		calendar.setTime(d);
		if (field != null && !field.equals("")) {
			if (field.equals("y")) {
				calendar.add(calendar.YEAR, amount);
			} else if (field.equals("M")) {
				calendar.add(calendar.MONTH, amount);
			} else if (field.equals("d")) {
				calendar.add(calendar.DAY_OF_MONTH, amount);
			} else if (field.equals("H")) {
				calendar.add(calendar.HOUR, amount);
			}
		} else {
			return null;
		}
		return getFormatDate(calendar.getTime());
	}

	/**
	 * 某一个时间的前一个时�?
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 * 
	 *             author guoliangliang
	 */
	public static String getPreDate(String date) throws ParseException {
		Date d = new SimpleDateFormat().parse(date);
		String preD = getPreDate(d, "d", 1);
		Date preDate = new SimpleDateFormat().parse(preD);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(preDate);
	}

	/**
	 * 将符合格式的时间字符串转换成Date
	 * 
	 * @param str
	 * @return Date
	 * 
	 *         author guoliangliang
	 */
	public static Date StrToFullDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将符合格式的时间字符串转换成Date
	 * 
	 * @param str
	 * @return Date
	 * 
	 *         author guoliangliang
	 */
	public static Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 过滤非工作日 �?周末
	 * 
	 * @param date
	 *            日期 ,formatStr 日期格式化字符串
	 * 
	 * @return String 日期字符�?
	 * 
	 *         author guoliangliang
	 */
	public static String getDateStrFilterHoliday_weekend(Date date,
			String formatStr) {

		if (date.getDay() == 6) {

			date = DateUtil.StrToDate(getPreDate(date, "d", 2));

		} else if (date.getDay() == 0) {

			date = DateUtil.StrToDate(getPreDate(date, "d", 1));
		}
		return getFormatDate(date, formatStr);
	}

	/**
	 * 过滤非工作日 �?周末
	 * 
	 * @param date
	 *            日期 ,formatStr 日期格式化字符串
	 * 
	 * @return String 日期字符�?
	 * 
	 *         author guoliangliang
	 */
	public static Date getDateFilterHoliday_weekend(Date date) {

		if (date.getDay() == 6) {

			date = DateUtil.StrToDate(getPreDate(date, "d", 2));

		} else if (date.getDay() == 0) {

			date = DateUtil.StrToDate(getPreDate(date, "d", 1));
		}
		return date;
	}

	/**
	 * 计算日期 过滤掉非工作�?周末
	 * 
	 * @param date
	 *            日期 ,formatStr 日期格式化字符串
	 * 
	 * @return String 日期字符�?
	 * 
	 *         author guoliangliang
	 */
	public static String getPreDateStrFilterHoliday_weekend(Date date,
			int amount, String formatStr) {

		// 首先处理�?��本身是否是周�?
		date = getDateFilterHoliday_weekend(date);

		// 日期基数 的周几数
		int dateDay = date.getDay();

		// 临时应用 当前周几�?加上 要加的天�?用于判断是否跨越周数 之后会因每跨越一周�?累加2
		int temp = amount + dateDay;

		// 跨域的周�?
		int weeks = 1;

		// 当跨越一�?amount+2 计算出最终要加的天数
		while (temp > 6) {

			// 跨越�?��要加的天数加2
			amount = amount + 2;

			// 岁amount增二 并减去一周的7�?�?
			temp = amount + dateDay - weeks * 7;

			weeks = weeks + 1;
		}
		// 当最终获得的日期为周六时 (经以上处理已过滤掉周日的情况)
		if ((amount + dateDay) % 7 == 6) {

			amount = amount + 2;
		}

		// 加上实际应该加的天数
		date = DateUtil.StrToDate(getPreDate(date, "d", amount));

		return getFormatDate(date, formatStr);
	}

	/**
	 * 计算日期 过滤掉非工作�?周末
	 * 
	 * @param date
	 *            日期
	 * 
	 * @return String 日期字符�?
	 * 
	 *         author guoliangliang
	 */
	public static Date getPreDateFilterHoliday_weekend(Date date, int amount) {

		// 首先处理�?��本身是否是周�?
		date = getDateFilterHoliday_weekend(date);

		// 日期基数 的周几数
		int dateDay = date.getDay();

		// 临时应用 当前周几�?加上 要加的天�?用于判断是否跨越周数 之后会因每跨越一周�?累加2
		int temp = amount + dateDay;

		// 跨域的周�?
		int weeks = 1;

		// 当跨越一�?amount+2 计算出最终要加的天数
		while (temp > 6) {

			// 跨越�?��要加的天数加2
			amount = amount + 2;

			// 岁amount增二 并减去一周的7�?�?
			temp = amount + dateDay - weeks * 7;

			weeks = weeks + 1;
		}
		// 当最终获得的日期为周六时 (经以上处理已过滤掉周日的情况)
		if ((amount + dateDay) % 7 == 6) {

			amount = amount + 2;
		}

		// 加上实际应该加的天数
		date = DateUtil.StrToDate(getPreDate(date, "d", amount));

		return date;
	}

	public static int getQuot(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(quot+"");
	}

	public static void main(String[] args) {

		try {

			String date1 = "2012-04-02";
			String date2 = "2012-04-01";
			long day = getQuot(date1, date2);
			System.out.println("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * 
     * 格式化金钱
     *
     * @param d
     * @return 描述 
     *
     */
    public static String getNumber(Double d){
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String numberString = numberFormat.format(d);
        return numberString;  
    }
}

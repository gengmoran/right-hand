package com.misterfat.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

/**
 * 日期时间工具类 <br>
 * 提供一些常用的日期时间操作方法，所有方法都为静态，不用实例化该类即可使用。 <br>
 * <br>
 */

public class DateUtil {

	protected static final String DEFAULT_FULLYEAR_FORMAT = "yyyy";

	protected static final String DEFAULT_YEARMONTH_FORMAT = "yyyy-MM";

	/**
	 * 缺省的日期显示格式： yyyy-MM-dd
	 */
	protected static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 缺省的时间显示格式： yyyy-MM-dd
	 */
	protected static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	/**
	 * 缺省的日期时间显示格式：yyyy-MM-dd HH:mm:ss
	 */
	protected static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	protected static final String DATE_HOUR_MINUTE = "yyyy-MM-dd HH:mm";;

	protected static final String[] WEEKDAY = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 缺省的时区GMT+8
	 */
	static final String DEFAULT_TIMEZONE_ID = "GMT+8";

	static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone(DEFAULT_TIMEZONE_ID);

	/**
	 * 私有构造方法，禁止对该类进行实例化
	 */
	private DateUtil() {
	}

	public static Date toDate(DateTime dateTime) {
		Date date = new Date(dateTime.getMilliseconds(DEFAULT_TIMEZONE));
		return date;
	}

	public static DateTime toDateTime(Date date) {
		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);
		calendar.setTime(date);
		DateTime dateTime = new DateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND), calendar.get(Calendar.MILLISECOND));
		return dateTime;
	}

	/**
	 * 得到系统当前日期时间
	 * 
	 * @return 当前日期时间
	 */
	public static Date getNow() {
		return Calendar.getInstance(DEFAULT_TIMEZONE).getTime();
	}

	/**
	 * 得到用缺省方式格式化的当前日期
	 * 
	 * @return 当前日期字符串
	 */
	public static String getDate() {

		return getDateTime(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 得到用缺省方式格式化的给定日期
	 * 
	 * @return 给定日期字符串
	 */
	public static String getDate(Date date) {
		return getDate(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 得到系统当前日期及时间，并用指定的方式格式化
	 * 
	 * @param pattern
	 *            显示格式
	 * @return 当前日期字符串
	 */
	public static String getDate(String pattern) {
		return getDateTime(pattern);
	}

	/**
	 * 得到用指定方式格式化的日期
	 * 
	 * @param date
	 *            需要进行格式化的日期
	 * @param pattern
	 *            显示格式
	 * @return 日期字符串
	 */
	public static String getDate(Date date, String pattern) {
		if (null == pattern || "".equals(pattern)) {
			pattern = DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(pattern);
		return formater.format(date);
	}

	/**
	 * 得到用缺省方式格式化的当前日期及时间
	 * 
	 * @return 当前日期及时间字符串
	 */
	public static String getDateTime() {
		return getDateTime(DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 得到用缺省方式格式化的给定日期时间
	 * 
	 * @return 给定的日期时间字符串
	 */
	public static String getDateTime(Date date) {
		return getDateTime(date, DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 得到系统当前日期及时间，并用指定的方式格式化
	 * 
	 * @param pattern
	 *            显示格式
	 * @return 当前日期及时间字符串
	 */
	public static String getDateTime(String pattern) {
		// Date datetime = Calendar.getInstance(DEFAULT_TIMEZONE).getTime();
		return getDateTime(getNow(), pattern);
	}

	/**
	 * 得到用指定方式格式化的日期
	 * 
	 * @param date
	 *            需要进行格式化的日期
	 * @param pattern
	 *            显示格式
	 * @return 日期时间字符串
	 */
	public static String getDateTime(Date date, String pattern) {
		if (null == pattern || "".equals(pattern)) {
			pattern = DEFAULT_DATETIME_FORMAT;
		}
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(pattern);
		return formater.format(date);
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param date
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return WEEKDAY[w];
	}

	/**
	 * 
	 * 功能描述：取得当前日期以后若干小时的日期。如果要得到以前的日期，参数用负数。
	 *
	 * @param hours
	 *            增加的小时数
	 * 
	 * @return 增加以后的日期
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date plusHours(int hours) {
		return plusHours(getNow(), hours);
	}

	/**
	 * 
	 * 功能描述：取得当前日期以后若干分钟的日期。如果要得到以前的日期，参数用负数。
	 *
	 * @param hours
	 *            增加的分钟数
	 * 
	 * @return 增加以后的日期
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date plusMinutes(int minutes) {
		return plusMinutes(getNow(), minutes);
	}

	/**
	 * 
	 * 功能描述：取得当前日期以后若干秒的日期。如果要得到以前的日期，参数用负数。
	 *
	 * @param hours
	 *            增加的秒数
	 * 
	 * @return 增加以后的日期
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date plusSeconds(int seconds) {
		return plusSeconds(getNow(), seconds);
	}

	/**
	 * 
	 * 功能描述：取得当前日期以后若干毫秒的日期。如果要得到以前的日期，参数用负数。
	 *
	 * @param hours
	 *            增加的毫秒数
	 * 
	 * @return 增加以后的日期
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date plusMilliseconds(int milliseconds) {
		return plusMilliseconds(getNow(), milliseconds);
	}

	/**
	 * 
	 * 功能描述：取得指定日期以后若干小时的日期。如果要得到以前的日期，参数用负数。
	 *
	 * @param date
	 *            指定日期
	 * @param milliseconds
	 *            增加的小时数
	 * 
	 * @return 增加以后的日期
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date plusHours(Date date, int hours) {
		return plusMinutes(date, hours * 60);
	}

	/**
	 * 
	 * 功能描述：取得指定日期以后若干分钟的日期。如果要得到以前的日期，参数用负数。
	 *
	 * @param date
	 *            指定日期
	 * @param milliseconds
	 *            增加的分钟数
	 * 
	 * @return 增加以后的日期
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date plusMinutes(Date date, int minutes) {
		return plusSeconds(date, minutes * 60);
	}

	/**
	 * 
	 * 功能描述：取得指定日期以后若干秒的日期。如果要得到以前的日期，参数用负数。
	 *
	 * @param date
	 *            指定日期
	 * @param milliseconds
	 *            增加的秒数
	 * 
	 * @return 增加以后的日期
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date plusSeconds(Date date, int seconds) {
		return plusMilliseconds(date, seconds * 1000);
	}

	/**
	 * 
	 * 功能描述：取得指定日期以后若干毫秒的日期。如果要得到以前的日期，参数用负数。
	 *
	 * @param date
	 *            指定日期
	 * @param milliseconds
	 *            增加的毫秒数
	 * 
	 * @return 增加以后的日期
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年2月8日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date plusMilliseconds(Date date, int milliseconds) {
		long time = date.getTime() + milliseconds;
		return new Date(time);
	}

	/**
	 * 取得当前日期以后若干天的日期。如果要得到以前的日期，参数用负数。 例如要得到上星期同一天的日期，参数则为-7
	 * 
	 * @param days
	 *            增加的日期数
	 * @return 增加以后的日期
	 */
	public static Date plusDays(int days) {
		return plus(getNow(), days, Calendar.DATE);
	}

	/**
	 * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
	 * 
	 * @param date
	 *            基准日期
	 * @param days
	 *            增加的日期数
	 * @return 增加以后的日期
	 */
	public static Date plusDays(Date date, int days) {
		return plus(date, days, Calendar.DATE);
	}

	/**
	 * 取得当前日期以后某月的日期。如果要得到以前月份的日期，参数用负数。
	 * 
	 * @param months
	 *            增加的月份数
	 * @return 增加以后的日期
	 */
	public static Date plusMonths(int months) {
		return plus(getNow(), months, Calendar.MONTH);
	}

	/**
	 * 取得指定日期以后某月的日期。如果要得到以前月份的日期，参数用负数。 注意，可能不是同一日子，例如2003-1-31加上一个月是2003-2-28
	 * 
	 * @param date
	 *            基准日期
	 * @param months
	 *            增加的月份数
	 * @return 增加以后的日期
	 */
	public static Date plusMonths(Date date, int months) {
		return plus(date, months, Calendar.MONTH);
	}

	/**
	 * 取得当前日期以后某年的日期。如果要得到以前年份的日期，参数用负数。
	 * 
	 * @param months
	 *            增加的年份数
	 * @return 增加以后的日期
	 */
	public static Date plusYears(int years) {
		return plus(getNow(), years, Calendar.YEAR);
	}

	/**
	 * 取得指定日期以后某年的日期。如果要得到以前年份的日期，参数用负数。
	 * 
	 * @param date
	 *            基准日期
	 * @param years
	 *            增加的年数
	 * @return 增加以后的日期
	 */
	public static Date plusYears(Date date, int years) {
		return plus(date, years, Calendar.YEAR);
	}

	/**
	 * 内部方法。为指定日期增加相应的天数或月数
	 * 
	 * @param date
	 *            基准日期
	 * @param amount
	 *            增加的数量
	 * @param field
	 *            增加的单位，年，月或者日
	 * @return 增加以后的日期
	 */
	private static Date plus(Date date, int amount, int field) {
		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);

		calendar.setTime(date);
		calendar.add(field, amount);

		return calendar.getTime();
	}

	/**
	 * 计算两个日期相差秒数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差秒数
	 */
	public static long differSeconds(Date one, Date two) {
		return (one.getTime() - two.getTime()) / (1000);
	}

	/**
	 * 计算两个日期相差分钟数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差分钟数
	 */
	public static long differMinutes(Date one, Date two) {
		return (one.getTime() - two.getTime()) / (60 * 1000);
	}

	/**
	 * 计算两个日期相差小时数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差小时数
	 */
	public static long differHours(Date one, Date two) {
		return (one.getTime() - two.getTime()) / (60 * 60 * 1000);
	}

	/**
	 * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差天数
	 */
	public static long differDays(Date one, Date two) {
		return (getStartOfDay(one).getTime() - getStartOfDay(two).getTime()) / (24 * 60 * 60 * 1000);
	}

	/**
	 * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差月份数
	 */
	public static int differMonths(Date one, Date two) {

		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);

		// 得到第一个日期的年分和月份数
		calendar.setTime(one);
		int yearOne = calendar.get(Calendar.YEAR);
		int monthOne = calendar.get(Calendar.MONDAY);

		// 得到第二个日期的年份和月份
		calendar.setTime(two);
		int yearTwo = calendar.get(Calendar.YEAR);
		int monthTwo = calendar.get(Calendar.MONDAY);

		return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
	}

	/**
	 * 计算两个日期相差年份数 如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差年份数
	 */
	public static int differYears(Date one, Date two) {

		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);

		// 得到第一个日期的年分和月份数
		calendar.setTime(one);
		int yearOne = calendar.get(Calendar.YEAR);

		// 得到第二个日期的年份和月份
		calendar.setTime(two);
		int yearTwo = calendar.get(Calendar.YEAR);

		return (yearOne - yearTwo);
	}

	/**
	 * 将一个字符串用给定的格式转换为日期类型。 <br>
	 * 注意：如果返回null，则表示解析失败
	 * 
	 * @param datestr
	 *            需要解析的日期字符串
	 * @param pattern
	 *            日期字符串的格式，默认为“yyyy-MM-dd”的形式
	 * @return 解析后的日期
	 */
	public static Date parse(String datestr, String pattern) {
		Date date = null;

		if (null == pattern || "".equals(pattern)) {
			pattern = DEFAULT_DATE_FORMAT;
		}

		try {
			SimpleDateFormat formater = new SimpleDateFormat();
			formater.applyPattern(pattern);
			date = formater.parse(datestr);
		} catch (ParseException e) {
			//
		}

		return date;
	}

	/**
	 * 将一个字符串用4种基本格式转换为日期类型。 <br>
	 * 注意：如果返回null，则表示解析失败
	 * 
	 * @param dateString
	 *            需要解析的日期字符串
	 * 
	 * @param dateString
	 * @return 解析后的日期
	 */
	public static Date parse(String dateString) {
		Date date = parse(dateString, "yyyy-MM-dd HH:mm:ss");
		if (date == null) {
			date = parse(dateString, "yyyy-MM-dd");
			if (date == null) {
				date = parse(dateString, "yyyy-MM");
				if (date == null) {
					date = parse(dateString, "yyyy");
				}
			}
		}
		return date;
	}

	/**
	 * 
	 * 功能描述：判断第一个日期是否大于第二个日期，大于返回true,否则返回false
	 *
	 * @param date1
	 * @param date2
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2015年11月19日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static boolean isGreaterThan(Date date1, Date date2) {
		return date1.getTime() > date2.getTime();
	}

	/**
	 * 
	 * 功能描述：判断时间是否已是过去时
	 *
	 * @param time
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2015年11月19日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static boolean isExpiredTime(Date time) {
		return isGreaterThan(getNow(), time);
	}

	/**
	 * 
	 * 功能描述：获取一天开始
	 *
	 * @param date
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date getStartOfDay(Date date) {
		DateTime dateTime = toDateTime(date);
		return toDate(dateTime.getStartOfDay());
	}

	/**
	 * 
	 * 功能描述：获取一天结束
	 *
	 * @param date
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date getEndOfDay(Date date) {
		DateTime dateTime = toDateTime(date);
		return toDate(dateTime.getEndOfDay());
	}

	/**
	 * 
	 * 功能描述：获取一月开始
	 *
	 * @param date
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date getStartOfMonth(Date date) {
		DateTime dateTime = toDateTime(date);
		return toDate(dateTime.getStartOfMonth());
	}

	/**
	 * 
	 * 功能描述：获取一月结束
	 *
	 * @param date
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date getEndOfMonth(Date date) {
		DateTime dateTime = toDateTime(date);
		return toDate(dateTime.getEndOfMonth());
	}

	/**
	 * 
	 * 功能描述：获取一周开始
	 *
	 * @param date
	 * @param weekStartIndex
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date getStartOfWeek(Date date, int weekStartIndex) {
		DateTime dateTime = toDateTime(date);
		Integer weekIndex = dateTime.getWeekDay();
		DateTime minusDays = dateTime.minusDays(weekIndex - weekStartIndex - 1);
		return toDate(minusDays.getStartOfDay());
	}

	/**
	 * 
	 * 功能描述：获取一周结束
	 *
	 * @param date
	 * @param weekStartIndex
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date getEndOfWeek(Date date, int weekStartIndex) {
		DateTime dateTime = toDateTime(date);
		Integer weekIndex = dateTime.getWeekDay();
		DateTime plusDays = dateTime.plusDays(7 - (weekIndex - weekStartIndex));
		return toDate(plusDays.getEndOfDay());
	}

	/**
	 * 
	 * 功能描述：获得一年开始
	 *
	 * @param date
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date getStartOfYear(Date date) {
		int year = getYear(date);
		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);
		calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0);
		return calendar.getTime();

	}

	/**
	 * 
	 * 功能描述：获得一年结束
	 *
	 * @param date
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date getEndOfYear(Date date) {
		Date plusYears = plusYears(date, 1);
		Date startOfYear = getStartOfYear(plusYears);
		return getEndOfDay(plusDays(startOfYear, -1));
	}

	/**
	 * 
	 * 功能描述：获得日期年份
	 *
	 * @param date
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static int getYear(Date date) {

		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);

	}

	/**
	 * 
	 * 功能描述：获得月份
	 *
	 * @param date
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static int getMonth(Date date) {

		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;

	}

	/**
	 * 
	 * 功能描述：获取几号
	 *
	 * @param date
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2017年3月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static int getDay(Date date) {

		Calendar calendar = Calendar.getInstance(DEFAULT_TIMEZONE);
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);

	}

	/**
	 * 得到当前年份
	 * 
	 * @return 当前年份
	 */
	public static int getYear() {
		return Calendar.getInstance(DEFAULT_TIMEZONE).get(Calendar.YEAR);
	}

	/**
	 * 得到当前月份
	 * 
	 * @return 当前月份
	 */
	public static int getMonth() {
		// 用get得到的月份数比实际的小1，需要加上
		return Calendar.getInstance(DEFAULT_TIMEZONE).get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当前日
	 * 
	 * @return 当前日
	 */
	public static int getDay() {
		return Calendar.getInstance(DEFAULT_TIMEZONE).get(Calendar.DATE);
	}

}

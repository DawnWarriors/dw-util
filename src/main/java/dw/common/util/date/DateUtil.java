package dw.common.util.date;

import dw.common.util.macro.MacroUtil;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil extends org.apache.commons.lang3.time.DateUtils
{
	private static final String curDate       = "${CURYEAR}-${CURMONTH}-${CURDAY}";
	private static final String curDateTime   = "${CURYEAR2}-${CURMONTH}-${CURDAY} ${CURHOUR}:${CURMINUTE}:${CURSECOND}";
	private static final String curDateTimeCS = "${CURYEAR2}-${CURMONTH}-${CURDAY} ${CURHOUR}:${CURMINUTE}:${CURSECOND}.${CURMILLISECOND}";
	private static final String curTime       = "${CURHOUR}:${CURMINUTE}:${CURSECOND}";

	/**
	 * 获取上周一
	 *
	 * @return
	 */
	public static Calendar getLastMondayOfWeek()
	{
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		{
			day_of_week = 7;
		}
		c.add(Calendar.DATE, -day_of_week - 6);
		return c;
	}

	/**
	 * 获取上周日
	 *
	 * @return
	 */
	public static Calendar getLastSundayOfWeek()
	{
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		{
			day_of_week = 7;
		}
		c.add(Calendar.DATE, -day_of_week);
		return c;
	}

	/**
	 * 获取本周周1
	 *
	 * @return
	 */
	public static Calendar getMondayOfThisWeek()
	{
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		{
			day_of_week = 7;
		}
		c.add(Calendar.DATE, -day_of_week + 1);
		return c;
	}

	/**
	 * 获取本周周日
	 *
	 * @return
	 */
	public static Calendar getSundayOfThisWeek()
	{
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		{
			day_of_week = 7;
		}
		c.add(Calendar.DATE, -day_of_week + 7);
		return c;
	}

	/**
	 * 获取当前日期
	 *
	 * @return
	 */
	public static String getCurDate()
	{
		return MacroUtil.macroReplace(curDate);
	}

	/**
	 * 获取当前日期时间
	 *
	 * @return
	 */
	public static String getCurDateTime()
	{
		return MacroUtil.macroReplace(curDateTime);
	}

	/**
	 * 获取当前世家包含毫秒
	 *
	 * @return
	 */
	public static String getCurDateTimeCS()
	{
		return MacroUtil.macroReplace(curDateTimeCS);
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static String getCurTime()
	{
		return MacroUtil.macroReplace(curTime);
	}

	/**
	 * 获取自定义格式的事件
	 *
	 * @param timeExpr
	 * @return
	 */
	public static String getCurCustTime(String timeExpr)
	{
		timeExpr = timeExpr.replaceAll("yyyy", "\\$\\{CURYEAR\\}");
		timeExpr = timeExpr.replaceAll("yy", "\\$\\{CURYEAR2\\}");
		timeExpr = timeExpr.replaceAll("mm", "\\$\\{CURMONTH\\}");
		timeExpr = timeExpr.replaceAll("dd", "\\$\\{CURDAY\\}");
		timeExpr = timeExpr.replaceAll("hh", "\\$\\{CURHOUR\\}");
		timeExpr = timeExpr.replaceAll("ii", "\\$\\{CURMINUTE\\}");
		timeExpr = timeExpr.replaceAll("ss", "\\$\\{CURSECOND\\}");
		return MacroUtil.macroReplace(timeExpr);
	}

	/**
	 * 根据指定格式格式化日期
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, String timeExpr)
	{
		timeExpr = timeExpr.replaceAll("yyyy", "\\$\\{CURYEAR\\}");
		timeExpr = timeExpr.replaceAll("yy", "\\$\\{CURYEAR2\\}");
		timeExpr = timeExpr.replaceAll("mm", "\\$\\{CURMONTH\\}");
		timeExpr = timeExpr.replaceAll("dd", "\\$\\{CURDAY\\}");
		timeExpr = timeExpr.replaceAll("hh", "\\$\\{CURHOUR\\}");
		timeExpr = timeExpr.replaceAll("ii", "\\$\\{CURMINUTE\\}");
		timeExpr = timeExpr.replaceAll("ss", "\\$\\{CURSECOND\\}");
		return MacroUtil.dateMacroReplace(date, timeExpr);
	}

	/**
	 * 格式化日期(yyyy-mm-dd)
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate_Y4MD(Date date)
	{
		return formatDate(date, "yyyy-mm-dd");
	}

	/**
	 * 格式化日期(yy-mm-dd)
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate_Y2MD(Date date)
	{
		return formatDate(date, "yy-mm-dd");
	}

	/**
	 * 格式化日期(yyyy-mm-dd hh:ii:ss)
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate_Y4MDHIS(Date date)
	{
		return formatDate(date, "yyyy-mm-dd hh:ii:ss");
	}

	/**
	 * 格式化日期(hh:ii:ss)
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate_HIS(Date date)
	{
		return formatDate(date, "hh:ii:ss");
	}

	/**
	 * 根据指定格式格式化当前日期
	 *
	 * @param timeExpr
	 * @return
	 */
	public static String formatDate(String timeExpr)
	{
		return formatDate(new Date(), timeExpr);
	}

	/**
	 * 解析string为date。
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parse(String date, String pattern) throws ParseException
	{
		FastDateFormat format = FastDateFormat.getInstance(pattern);// 优先从全局缓存中取，没有才会new
		return format.parse(date);
	}

	/**
	 * 解析string为date: yyyy-mm-dd
	 *
	 * @param date
	 * @return
	 */
	public static Date parse_Y4MD(String date) throws ParseException
	{
		FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd");
		return format.parse(date);
	}

	/**
	 * 解析string为date: yyyy-mm-dd hh:ii:ss
	 *
	 * @param date
	 * @return
	 */
	public static Date parse_Y4MDHIS(String date) throws ParseException
	{
		FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		return format.parse(date);
	}

	/**
	 * 解析string为date: hh:ii
	 *
	 * @param date
	 * @return
	 */
	public static Date parse_HI(String date) throws ParseException
	{
		FastDateFormat format = FastDateFormat.getInstance("mm:ss");
		return format.parse(date);
	}

	public static Date cloneDate(Date date)
	{
		return (Date) (date.clone());
	}

	/**
	 * @return 当前年
	 */
	public static int getYear()
	{
		return (new GregorianCalendar()).get(Calendar.YEAR);
	}

	/**
	 * @return 当前月
	 */
	public static int getMonth()
	{
		return (new GregorianCalendar()).get(Calendar.MONTH) + 1;
	}

	/**
	 * @return 当前日
	 */
	public static int getDay()
	{
		return (new GregorianCalendar()).get(Calendar.DATE);
	}

	/**
	 * 获取指定日期的年
	 *
	 * @param date
	 * @return
	 */
	public static int getYearOfDate(Date date)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取指定日期的月
	 *
	 * @param date
	 * @return
	 */
	public static int getMonthOfDate(Date date)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取指定日期的日
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfDate(Date date)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 获取指定日期的星期
	 *
	 * @param date
	 * @return
	 */
	public static int getWeekOfDate(Date date)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static Date toDate(long date)
	{
		return new Date(date);
	}

	/**
	 * 鎻愪緵
	 * java.util.Date date = (java.util.Date)RemoteInvoke.remoteInvoke("snsoft.util.DateUtil.today",null);
	 */
	@SuppressWarnings("rawtypes")
	public static Date today(java.util.Map envP, java.util.Map params)
	{
		return new Date();
	}

	@SuppressWarnings("rawtypes")
	public static long todayTime(java.util.Map envP, java.util.Map params)
	{
		return System.currentTimeMillis();
	}

	public static long getDateTime(Object date)
	{
		return date instanceof Date ? ((Date) date).getTime() : 0;
	}

	static public long deltaFromServer = Long.MIN_VALUE;

	static public int getDefaultHolidays(int year, int month)
	{
		GregorianCalendar cal = new GregorianCalendar(year, month - 1, 1);
		int x = 0;
		for (int d = 0 ; ; d++)
		{
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY)
				x |= (1 << d);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			if (cal.get(Calendar.MONTH) + 1 != month)
				break;
		}
		return x;
	}

	static public boolean isDefaultHolidays(int year, int month, int day)
	{
		GregorianCalendar cal = new GregorianCalendar(year, month - 1, day);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY;
	}

	static public Date incDate(Date date, int day)
	{
		if (day == 0 || date == null)
			return date;
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}

	static public int diffDate(Date date1, Date date2)
	{
		return (int) ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000));
	}

	static public long diffSeconds(Date date1, Date date2)
	{
		return (date1.getTime() - date2.getTime()) / 1000l;
	}

	static public final String calendarToString(final Calendar cal, int hmsFormat)
	{
		final int options = hmsFormat >> 4;
		hmsFormat &= 0xf;
		int year = cal.get(Calendar.YEAR);//((java.sql.Timestamp)value).getYear() + 1900;
		int month = cal.get(Calendar.MONTH) + 1;//((java.sql.Timestamp)value).getMonth() + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);//((java.sql.Timestamp)value).getDate();
		String yearText = Integer.toString(year);
		if ((options & 1) != 0 && yearText.length() > 2)
			yearText = yearText.substring(yearText.length() - 2);
		//if( (options&1)!=0 ) year
		String text = yearText + (month < 10 ? "-0" : "-") + month + (day < 10 ? "-0" : "-") + day;
		if (hmsFormat == 2)//|| date instanceof java.sql.Date )
			return text;
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		if (hmsFormat == 4)
			return text + " " + toStr2(hour) + ":" + toStr2(min);
		int sec = cal.get(Calendar.SECOND);
		//System.err.println("date="+date+",cal="+cal+",cal.getTime()="+cal.getTime()+",hour="+hour);//+","+date.getHours());
		if (hmsFormat == 1)
			return text + " " + toStr2(hour) + ":" + toStr2(min) + ":" + toStr2(sec);
		if (hmsFormat == 5)
			return text + " " + toStr2(hour) + ":" + toStr2(min) + ":" + toStr2(sec) + "." + cal.get(Calendar.MILLISECOND);
		if (hour == 0 && min == 0 && sec == 0)
			return text;
		return sec == 0 ? text + " " + toStr2(hour) + ":" + toStr2(min) : text + " " + toStr2(hour) + ":" + toStr2(min) + ":" + toStr2(sec);
	}

	final static private String toStr2(int x)
	{
		return x < 10 ? "0" + x : "" + x;
	}

	static public String dateToEnglishString(Date date)
	{
		/*
		if (date==null) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int m = calendar.get(Calendar.MONTH);
		return "JanFebMarAprMayJunJulAugSepOctNovDec".substring(m*3,m*3+3)+'-'+calendar.get(Calendar.DATE)+'-'+calendar.get(Calendar.YEAR);
		*/
		return dateToEnglishString(date, true, '-', '-');
	}

	static public String dateToEnglishString(Date date, boolean shortMonth, char deliMD, char deliDY)
	{
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int m = calendar.get(Calendar.MONTH);
		String month;
		if (shortMonth)
			month = "JanFebMarAprMayJunJulAugSepOctNovDec".substring(m * 3, m * 3 + 3);
		else
		{
			String emonth[] = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
			month = emonth[m];
		}
		return month + deliMD + calendar.get(Calendar.DATE) + deliDY + calendar.get(Calendar.YEAR);
	}

	static public String secondsToString(int seconds)
	{
		int h = seconds / (60 * 60);
		int m = seconds / 60 % 60;
		int s = seconds % 60;
		return toStr2(h) + ":" + toStr2(m) + (s == 0 ? "" : ":" + toStr2(s));
	}

	/**
	 * 判断两个日期区间是否重叠
	 *
	 * @param begin1
	 * @param end1
	 * @param includeWeek1
	 * @param begin2
	 * @param end2
	 * @param includeWeek2
	 * @return
	 */
	public static boolean isOverlap(Date begin1, Date end1, String includeWeek1, Date begin2, Date end2, String includeWeek2)
	{
		Date curDate = getCurDayDate();
		if (begin1.getTime() < curDate.getTime())
		{
			begin1 = curDate;
		}
		List<Long> days1 = getDays(begin1, end1, includeWeek1);
		List<Long> days2 = getDays(begin2, end2, includeWeek2);
		for (int i = 0 ; i < days1.size() ; i++)
		{
			Long day = days1.get(i);
			if (days2.contains(day))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取日期区间内所有的天数所对应的秒数集合
	 *
	 * @param begin
	 * @param end
	 * @param includeWeek
	 * @return
	 */
	private static List<Long> getDays(Date begin, Date end, String includeWeek)
	{
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(begin);
		beginCalendar.set(Calendar.HOUR_OF_DAY, 0);
		beginCalendar.set(Calendar.MINUTE, 0);
		beginCalendar.set(Calendar.SECOND, 0);
		beginCalendar.set(Calendar.MILLISECOND, 0);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		endCalendar.set(Calendar.HOUR_OF_DAY, 0);
		endCalendar.set(Calendar.MINUTE, 0);
		endCalendar.set(Calendar.SECOND, 0);
		endCalendar.set(Calendar.MILLISECOND, 0);
		List<Long> dayList = new ArrayList<>();
		for (long day = beginCalendar.getTimeInMillis() ; day <= endCalendar.getTimeInMillis() ; day += 86400000)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(day));
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			if (includeWeek.contains(week + ""))
			{
				dayList.add(day);
			}
		}
		return dayList;
	}

	public static Date getCurDayDate()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取指定时间的日期（去掉时分秒毫秒）
	 *
	 * @param date
	 * @return
	 */
	public static Date getDayDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当月第一天 00:00:00
	 *
	 * @return
	 */
	public static Date getFirstDayOfMonth()
	{
		//获取前月的第一天
		Calendar cal = Calendar.getInstance();//获取当前日期
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当月最后一天 23:59:59
	 *
	 * @return
	 */
	public static Date getLastDayOfMonth()
	{
		//获取前月的最后一天
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 获取本周第一天
	 */
	public static Date getWeekStartDate()
	{
		Calendar cal = Calendar.getInstance();
		int d = 0;
		if (cal.get(Calendar.DAY_OF_WEEK) == 1)
		{
			d = -6;
		} else
		{
			d = 2 - cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取本周最后一天
	 *
	 * @return
	 */
	public static Date getWeekEndDate()
	{
		Date weekStart = getWeekStartDate();
		return new Date(weekStart.getTime() + 7 * 24 * 60 * 60 * 1000 - 1);
	}

	/**
	 * 获取当天结束时间
	 *
	 * @return
	 */
	public static Date getCurDayEnd()
	{
		Date begin = getCurDayDate();
		return new Date(begin.getTime() + 24 * 60 * 60 * 1000 - 1);
	}

	/**
	 * 获取今天的日期,String 格式 yyyy-MM-dd
	 *
	 * @return
	 */
	public static String getTodayString()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static void main(String[] args)
	{
		System.out.println(getCurCustTime("yyyy-yy-mm-dd hh:ii:ss"));
	}
}

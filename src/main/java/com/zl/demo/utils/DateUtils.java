package com.zl.demo.utils;

import com.google.common.collect.Lists;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtils {

	private final static SimpleDateFormat NORM_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat NORM_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat NORM_MONTH_FORMAT = new SimpleDateFormat("yyyy-MM");
    private final static SimpleDateFormat NOEM_MONTH_FORMAT_CH = new SimpleDateFormat("MM月dd日");
    private final static SimpleDateFormat QUERY_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat EXPORT_PRE_FORMAT = new SimpleDateFormat("MM.dd");
	private final static SimpleDateFormat NORM_MONTH_FORMAT2 = new SimpleDateFormat("yyyy.MM.dd");
	private final static SimpleDateFormat GENERATE_FORMAT=new SimpleDateFormat("yyyyMM");
	private static Calendar calendar = null;
	
	/**
	 *
	 * 当前时间，格式 yyyy-MM-dd HH:mm:ss
	 *
	 * @return 当前时间的标准形式字符串
	 *
	 */
	public static String now() {
		return formatDateTime(new Date());
	}
	public static String queryFormat(Date date) {
		return QUERY_FORMAT.format(date);
	}

	public static String zero() {
		return formatDateTime(getZero());
	}

	public static String nowDate() {
		return formatDate(new Date());
	}


	public static String formatDateTime(Date date) {
		return NORM_DATETIME_FORMAT.format(date);
	}


	public static String formatYearM(Date date) {
		return GENERATE_FORMAT.format(date);
	}


	public static String exportPreFormat(Date date) {
		return EXPORT_PRE_FORMAT.format(date);
	}

	public static String formatDate(Date date) {
		return NORM_DATE_FORMAT.format(date);
	}

	public static String formatMonthTime(Date date) {
		return NORM_MONTH_FORMAT.format(date);
	}

	public static String formatMonthTimeCH(Date date){
		return NOEM_MONTH_FORMAT_CH.format(date);
	}

	public static String formatMonthTime2(Date date){
		return NORM_MONTH_FORMAT2.format(date);
	}

	/**
	 * yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;

		if (null != str && !("null".equals(str))) {
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}

	/**
	 * 
	 * @param str
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws ParseException 
	 */
	public static Date StrToDate1(String str) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if (null != str && !("null".equals(str))) {

					date = sdf.parse(str);
		}
		return date;
	}
	
	/**
	 *	获取第一秒
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date getFirstSecond(String str) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if (null != str && !("null".equals(str))) {

				date = sdf.parse(str+" 00:00:00");
		}
		return date;
	}
	
	/**
	 *	获取最后一秒
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date getLastSecond(String str) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if (null != str && !("null".equals(str))) {

				date = sdf.parse(str+" 23:59:59");
		}
		return date;
	}

	/**
	 * yyyy-MM
	 * @param str
	 * @return
	 */
	public static Date StrToDate2(String str) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;

		if (null != str && !("null".equals(str))) {
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}

	public static Date YearAndMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		
			try {
				date = sdf.parse(now());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return date;
	}
	
	/**
	 * 
	 * @return 获取每天的0点
	 */
	public static Date getZero() {
		long current = System.currentTimeMillis();// 当前时间毫秒数
		long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();// 今天零点零分零秒的毫秒数
		Date date = new Date();
		date = new Timestamp(zero);
		return date;
	}

	/**
	 * 
	 * @return 获取每天的24点
	 */
	public static Date getTwelve() {
		long current = System.currentTimeMillis();// 当前时间毫秒数
		long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();// 今天零点零分零秒的毫秒数
		long twelve = zero + 24 * 60 * 60 * 1000 - 1;// 今天23点59分59秒的毫秒数
		Date date = new Date();
		date = new Timestamp(twelve);
		return date;
	}

	/**
	 * 
	 * @return 获取每个月的第一天
	 */
	public static Date getFirstDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		c.set(Calendar.MINUTE, 0);
		// 将秒至0
		c.set(Calendar.SECOND, 0);
		// 将毫秒至0
		c.set(Calendar.MILLISECOND, 0);
		// 获取本月第一天的时间戳
		return new Date(c.getTimeInMillis());

	}

	/**
	 * 
	 * @return 获取给定年月的第一天
	 */
	public static Date getFirstDay(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.YEAR, ca.get(Calendar.YEAR));
		c.set(Calendar.MONTH, ca.get(Calendar.MONTH));
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		c.set(Calendar.MINUTE, 0);
		// 将秒至0
		c.set(Calendar.SECOND, 0);
		// 将毫秒至0
		c.set(Calendar.MILLISECOND, 0);
		// 获取本月第一天的时间戳
		return new Date(c.getTimeInMillis());

	}

	/**
	 * 
	 * @return 获取每个月的最后一天
	 */
	public static Date getLastDay() {
		// 获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		// 将小时至0
		ca.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		ca.set(Calendar.MINUTE, 0);
		// 将秒至0
		ca.set(Calendar.SECOND, 0);
		// 将毫秒至0
		ca.set(Calendar.MILLISECOND, 0);
		// 获取本月最后一天的时间戳
		return new Date(ca.getTimeInMillis());
	}

	/**
	 *
	 * @return 获取每个月的最后一天
	 */
	public static Date getLastDay2() {
		// 获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		// 将小时至0
		ca.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		ca.set(Calendar.MINUTE, 59);
		// 将秒至0
		ca.set(Calendar.SECOND, 59);
		// 将毫秒至0
		ca.set(Calendar.MILLISECOND, 59);
		// 获取本月最后一天的时间戳
		return new Date(ca.getTimeInMillis());
	}

	/**
	 * 
	 * @return 获取给定年月的最后一天
	 */
	public static Date getLastDay(Date date) {
		// 获取给定年月最后一天
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.YEAR, ca.get(Calendar.YEAR));
		c.set(Calendar.MONTH, ca.get(Calendar.MONTH));
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		// 将小时至0
		ca.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		ca.set(Calendar.MINUTE, 59);
		// 将秒至0
		ca.set(Calendar.SECOND, 59);
		// 将毫秒至0
		ca.set(Calendar.MILLISECOND, 0);
		// 获取本月最后一天的时间戳

		return new Date(ca.getTimeInMillis());
	}

	/**
	 * 判断两个日期是否为同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}

	/**
	 * 判断两个日期是否为同一月
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameMonth(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		return isSameMonth;
	}
	
	/**
	 * 得到指定日期的年份
	 * @param df
	 * @return
	 */
	public static String getYear(Date df) {
		calendar = Calendar.getInstance();
		calendar.setTime(df);
		return "" + calendar.get(Calendar.YEAR);
	}

	/**
	 * 得到指定日期的月份
	 * @param df
	 * @return
	 */
	public static String getMoon(Date df) {
		calendar = Calendar.getInstance();
		calendar.setTime(df);
		return getNum((calendar.get(Calendar.MONTH)+1));
	}
	
	/**
	 * 得到指定日期的日期
	 * @param df
	 * @return
	 */
	public static String getDay(Date df) {
		calendar = Calendar.getInstance();
		calendar.setTime(df);
		return getNum(calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 得到指定日期的年月日
	 * @param df
	 * @return
	 */
	public static String getDate(Date df) {
		calendar = Calendar.getInstance();
		calendar.setTime(df);
		return "" + calendar.get(Calendar.YEAR) + getNum((calendar.get(Calendar.MONTH)+1)) 
												+ getNum(calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 补零
	 * @param i
	 * @return
	 */
	public static String getNum(int i){
		return i > 9 ? "" + i : "0" + i ;
	}
	
	
	/**
     * @return 获取昨天开始时间
     */
    public static Date getYestodayBegin() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
        // 将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        // 将分钟至0
        c.set(Calendar.MINUTE, 0);
        // 将秒至0
        c.set(Calendar.SECOND, 0);
        // 将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return new Date(c.getTimeInMillis());

    }

    /**
     * @return 获取昨天结束时间
     */
    public static Date getYestodayEnd() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);

        // 将小时至0
        c.set(Calendar.HOUR_OF_DAY, 23);
        // 将分钟至0
        c.set(Calendar.MINUTE, 59);
        // 将秒至0
        c.set(Calendar.SECOND, 59);
        // 获取本月第一天的时间戳
        return new Date(c.getTimeInMillis());

    }
    
    /**
     * 上个月的第一天
     * @return
     */
    public static String getLastMonthFrist(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);   
        calendar1.set(Calendar.MINUTE, 0);  
        calendar1.set(Calendar.SECOND, 0);
        return sdf.format(calendar1.getTime());
    }
    
    /**
     * 上个月的最后一天
     * @return
     */
    public static String getLastMonthEnd(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 Calendar calendar2 = Calendar.getInstance();
         calendar2.set(Calendar.DAY_OF_MONTH, 0);
      
         calendar2.set(Calendar.HOUR_OF_DAY, 23);
        
         calendar2.set(Calendar.MINUTE, 59);
    
         calendar2.set(Calendar.SECOND, 59);
         return sdf.format(calendar2.getTime());
    }


    /**
            * 获取给定年份的第一天
	 * @param year
	 * @return*/

    public static Date getYearStart(int year){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.YEAR,year);
        ca.set(Calendar.MONTH,0);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
        // 将小时至0
        ca.set(Calendar.HOUR_OF_DAY, 0);
        // 将分钟至0
        ca.set(Calendar.MINUTE, 0);
        // 将秒至0
        ca.set(Calendar.SECOND, 0);
        // 获取本月最一天的时后间戳
        return new Date(ca.getTimeInMillis());
    }

	/**
	 * 获取给定日期所在年份的第一天
	 * @param date
	 * @return
	 */
	public  static Date getYearStart(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.YEAR,ca.get(Calendar.YEAR));
		ca.set(Calendar.MONTH,0);
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
		// 将小时至0
		ca.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		ca.set(Calendar.MINUTE, 0);
		// 将秒至0
		ca.set(Calendar.SECOND, 0);
		return ca.getTime();
	}

	/**
            * 获取给定年份的最后一天
	 * @param year
	 * @return*/

    public static Date getYearEnd(int year){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.YEAR,year);
        ca.set(Calendar.MONTH,11);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        // 将小时至23
        ca.set(Calendar.HOUR_OF_DAY, 23);
        // 将分钟至59
        ca.set(Calendar.MINUTE, 59);
        // 将秒至59
        ca.set(Calendar.SECOND, 59);
        // 获取本月最一天的时后间戳
        return new Date(ca.getTimeInMillis());
    }

	/**
	 * 获取给定日期所在年份的最后一天
	 * @param date
	 * @return
	 */
	public  static Date getYearEnd(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.YEAR,ca.get(Calendar.YEAR));
		ca.set(Calendar.MONTH,11);
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		// 将小时至23
		ca.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至59
		ca.set(Calendar.MINUTE, 59);
		// 将秒至59
		ca.set(Calendar.SECOND, 59);
		return ca.getTime();
	}

    /*

     */
    /**
        * 获取指定日期上一个月的第一天
	 * @param date*/


    public static String getBeforeFirstMonthDate(Date date) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return  format.format(calendar.getTime());
    }
    /**
        * 获取指定日期上一个月的最后一天
	 * @param date*/


    public static String getBeforeLastMonthDate(Date date){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return  sf.format(calendar.getTime());

    }

	public static Date getDays(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) +num);
		return calendar.getTime();
	}

	public static List<Date> findDates(String start, String end) {

		Date dBegin = StrToDate(start);
		Date dEnd = StrToDate(end);
		List<Date> lDate = Lists.newArrayList();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

	//将日期格式转换成字符串
	public static String changeTypeDate(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatDate = dateFormat.format(date);
		return formatDate;

	}


    public static void main(String[] args) {
		List<Date> dates = DateUtils.findDates("2020-04-01", "2020-04-30");
		dates.forEach(date -> {
			System.out.println(DateUtils.formatDate(date));
		});
	}
	
	
}

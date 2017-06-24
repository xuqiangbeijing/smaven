/*
 * IBM Confidential
 * 
 * #ID# IBM CRL E&U Research
 * 
 * (C) Copyright IBM Corp. 2010
 * 
 * The source code for this program is not published or otherwise divested of
 * its trade secrets.
 * 
 */
package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



/**
 * 日期字符串工具类 日期字符串必须为"yyyy-MM-dd hh:mm:ss"或"yyyy-MM-dd"格式
 * 如果不是规定的时间字符串格式，将返回当前日期
 * @author liuhuawei
 * @version 2010-7-15 下午02:05:29
 */
public class StringToDateUtil {
	/**
	 * 指示一个月中的某天
	 */
	public final static int DATE=Calendar.DATE;
	/**
	 * 指示月份
	 */
	public final static int MONTH=Calendar.MONTH;
	/**
	 * 指示年
	 */
	public final static int YEAR=Calendar.YEAR;
	/**
	 * 指示小时。
	 */
	public final static int HOUR=Calendar.HOUR;
	/**
	 * 指示一小时中的分钟。
	 */
	public final static int MINUTE=Calendar.MINUTE;
	/**
	 * 指示一分钟中的秒。
	 */
	public final static int SECOND=Calendar.SECOND;
	/**
	 * 指示方法中日期格式为“yyyy-MM-dd HH:mm:ss”
	 */
	public  final static int FORMAT_ALL=1;
	/**
	 * 指示方法中日期格式为“yyyy-MM-dd”
	 */
	public final static int FORMAT_SUB=2;
//	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//	private static SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 根据字符串获取java.util.Date对象
	 * 如果format为null或""；将用本类内部规定的格式将datestr转换Date，如果datestr格式和本类内部规定
	 * 的格式不一致将返回当前日期或抛出Exception
	 * 如果format不为null或""；将根据传入格式将datestr转换Date，如果datestr格式和传入format
	 * 的格式不一致将抛出Exception
	 * @param format 转化格式
	 * @param datestr 日期字符串
	 * @return
	 * @throws Exception
	 */
	public static Date stringToDate(String format,String datestr) throws Exception{
		if(null==format||"".equals(format)){
			return stringToDate(datestr);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format); 
		return sdf.parse(datestr);
	}
	/**
	 * 根据字符串获取java.util.Date对象
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @return 返回java.util.Date
	 * @throws Exception
	 */
	private static Date stringToDate(String str) throws Exception{
		Date date=new Date();
		if(str==null||str.equals("")){
			return date ;
		}
		String[] strss=str.split("\\.");
		if(strss.length>1){
			str=strss[0];
		}
		str=str.trim();
		String[] strs=str.split(" ");
		if(strs.length==2){
			String[] substrs=strs[1].split(":");
			if(substrs.length>3){
				return date;
			}
			if(substrs.length<3){
				for(int i=substrs.length; i<3; i++){
					str+=":00";
				}
			}
			substrs=strs[0].split("-");
			if(substrs.length>3||substrs.length<3){
				return date;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = df.parse(str);
		}else if(strs.length==1){
			String[] substrs=strs[0].split("-");
			if(substrs.length>3||substrs.length<3){
				return date;
			}
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			date = df1.parse(str);
		}
		return date;
	}
	
	private static String getDateString(String str,Date date){
		if(str==null || str.length()<=0)return "";
		String[] strs=str.split(" ");
		if(strs.length==2){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(date);
		}else{
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			return df1.format(date);
		}
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少天数
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.lang.String格式为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @throws Exception
	 */
	public static String addDateToString(String str,int num) throws Exception{
		Date date = addDateToDate(str,num);
		return getDateString(str,date);
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少天数
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.util.Date
	 * @throws Exception
	 */
	public static Date addDateToDate(String str,int num) throws Exception{
		Date date = stringToDate(str);
		date = addOrRollDate(Calendar.DATE,date,num);
		return date;
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少月份
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.util.Date
	 * @throws Exception
	 */
	public static Date addMonthToDate(String str,int num) throws Exception{
		Date date = stringToDate(str);
		date = addOrRollDate(Calendar.MONTH,date,num);
		return date;
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少月份
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.lang.String 格式为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @throws Exception
	 */
	public static String addMonthToString(String str,int num) throws Exception{
		Date date = addMonthToDate(str,num);
		return getDateString(str,date);
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少年份
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.util.Date
	 * @throws Exception
	 */
	public static Date addYearToDate(String str,int num)throws Exception{
		Date date = stringToDate(str);
		date = addOrRollDate(Calendar.YEAR,date,num);
		return date;
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少年份
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.lang.String 格式为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @throws Exception
	 */
	public static String addYearToString(String str,int num)throws Exception{
		Date date = addYearToDate(str,num);
		return getDateString(str,date);
	}
	
	
	/**
	 * 在当前传入日期字符串基础上增加或减少小时
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.util.Date
	 * @throws Exception
	 */
	public static Date addHourToDate(String str,int num)throws Exception{
		Date date = stringToDate(str);
		date = addOrRollDate(Calendar.HOUR,date,num);
		return date;
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少小时
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.lang.String 格式为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @throws Exception
	 */
	public static String addHourToString(String str,int num)throws Exception{
		String[] strs=str.split(" ");
		if(strs.length==1){
			str+=" 00:00";
		}
		Date date = addHourToDate(str,num);
		return getDateString(str,date);
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少分钟
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.util.Date
	 * @throws Exception
	 */
	public static Date addMinuteToDate(String str,int num)throws Exception{
		Date date = stringToDate(str);
		date = addOrRollDate(Calendar.MINUTE,date,num);
		return date;
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减分钟
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.lang.String 格式为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @throws Exception
	 */
	public static String addMinuteToString(String str,int num)throws Exception{
		String[] strs=str.split(" ");
		if(strs.length==1){
			str+=" 00:00";
		}
		Date date = addMinuteToDate(str,num);
		return getDateString(str,date);
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少秒
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.util.Date
	 * @throws Exception
	 */
	public static Date addSecondToDate(String str,int num)throws Exception{
		Date date = stringToDate(str);
		date = addOrRollDate(Calendar.SECOND,date,num);
		return date;
	}
	
	/**
	 * 在当前传入日期字符串基础上增加或减少秒
	 * @param str 日期字符串必须为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @param num 正数增加，负数减少
	 * @return 返回java.lang.String 格式为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @throws Exception
	 */
	public static String addSecondToString(String str,int num)throws Exception{
		String[] strs=str.split(" ");
		if(strs.length==1){
			str+=" 00:00:00";
		}
		Date date = addSecondToDate(str,num);
		return getDateString(str,date);
	}
	
	/**
	 * 根据类型对年月日时分秒进行加减
	 * @param type 类型参见Calendar类常量或本类常量 如StringToDateUtil.YEAR，对年进行加减
	 * @param date java.util.Date
	 * @param num 加减数量 正数则加，负数则减；
	 * @return
	 * @throws Exception
	 */
//	private static Date getDate(int type,Date date,int num) throws Exception{
//		Calendar cal=Calendar.getInstance();
//		cal.setTime(date);
//		cal.add(type,num);
//		date=cal.getTime();
//		return date;
//	}
//	
	public static Date addOrRollDate(int type,Date date,int num)throws Exception{
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(type,num);
		date=cal.getTime();
		return date;
	}
	
	/**
	 * 将Date格式化String
	 * @param formatType StringToDateUtil.FORMAT_ALL返回"yyyy-MM-dd HH:mm:ss"格式； StringToDateUtil.FORMAT_SUB或其他返回"yyyy-MM-dd"格式
	 * @param date 
	 * @return  返回java.lang.String 格式为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 */
	public static String  dateFormatToString(int formatType,Date date){
		if(null==date){
			return "";
		}
		if(formatType==FORMAT_ALL){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(date);
		}else{
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			return df1.format(date);
		}
	}
	/**
	 * 将Date格式化String
	 * 如果format为null或""；将用本类内部规定的格式返回字符串
	 * 如果format不为null或""；将根据传入格式Date转换成字符串
	 * 格式不对将抛出Exception
	 * @param format
	 * @param date 
	 * @return  返回java.lang.String 格式为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 */
	public static String  dateFormatToString(String format,Date date){
		if(null==date){
			return "";
		}
		if(null==format||"".equals(format)){
			return dateFormatToString(FORMAT_ALL,date);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format); 
		return sdf.format(date);
	}
	
	/**
	 * 将String格式化String
	 * @param formatType StringToDateUtil.FORMAT_ALL返回"yyyy-MM-dd HH:mm:ss"格式； StringToDateUtil.FORMAT_SUB或其他返回"yyyy-MM-dd"格式
	 * @param str 时间字符串；
	 * @return 返回java.lang.String 格式为"yyyy-MM-dd HH:mm:ss"或"yyyy-MM-dd"格式
	 * @throws Exception
	 */
	public static String dateFormatToString(int formatType,String str) throws Exception{
		if(str==null||str.equals("")){
			return str;
		}
		String[] strs=str.split("\\.");
		Date date=null;
		if(strs.length>1){
			date=stringToDate(strs[0]);
		}else{
			date=stringToDate(str);
		}
		return dateFormatToString(formatType,date);
	}
	/**
	 * 比较两个日期相差的天数 datestr2-datestr1 计算具体到时分秒
	 * 比如你输入的参数"2010-07-21 12:12:12"与"2010-07-20 21:12:12"的两个时间，相差0天
	 * @param datestr1
	 * @param datestr2
	 * @return 
	 * @throws Exception
	 */
	public static int getIntervalDaysByTime(String datestr1, String datestr2) throws Exception{
		Date dateOne = stringToDate(datestr1);
		Date dateTwo = stringToDate(datestr2);
		return getIntervalDaysByTime(dateOne, dateTwo);
	}
	/**
	 * 比较两个日期相差的天数 datestr2-datestr1 计算具体到天
	 * 比如你输入的参数"2010-07-21 12:12:12"与"2010-07-20 21:12:12"的两个时间，相差1天
	 * @param datestr1
	 * @param datestr2
	 * @return
	 * @throws Exception
	 */
	public static int getIntervalDaysByDate(String datestr1, String datestr2) throws Exception {
		Date dateOne = stringToDate(datestr1);
		Date dateTwo = stringToDate(datestr2);
		return getIntervalDaysByDate(dateOne, dateTwo);
	}
	
	public static int getIntervalDaysByMinute(String datestr1, String datestr2)throws Exception {
		Date dateOne = stringToDate(datestr1);
		Date dateTwo = stringToDate(datestr2);
		long intervalMilli = dateTwo.getTime() - dateOne.getTime();
		return (int) (intervalMilli / (60 * 1000));
	}
	
	public static int getIntervalDaysByTime(Date fDate, Date oDate) {
	       if (null == fDate || null == oDate) {
	           return -1;
	       }
	       long intervalMilli = oDate.getTime() - fDate.getTime();
	       return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}
	public static int getIntervalDaysByDate(Date fDate, Date oDate) {
	       Calendar aCalendar = Calendar.getInstance();
	       aCalendar.setTime(fDate);
	       int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
	       aCalendar.setTime(oDate);
	       int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
	       return day2 - day1;
	}
	
	/**
	 * 比较两个日期字符串的日期大小 字符串必须为本类规定的格式；
	 * @param datestr1 日期字符串1
	 * @param datestr2 日期字符串2
	 * @return datestr1>datestr2 return 1; datestr1<datestr2 return -1; datestr1==datestr2 return 0;
	 * @throws Exception
	 */
	public static int compareDateString(String datestr1, String datestr2) throws Exception{
		Date dateOne = stringToDate(datestr1);
		Date dateTwo = stringToDate(datestr2);
		return dateOne.compareTo(dateTwo);
	}
	
	/**
	 * 根据年和第几周取得周一和周日的日期
	 * @param year 年
	 * @param week 该年的第几周
	 * @return String[0]:周一 String[1]:周日
	 */
	public static String[] getFirstEndDayOfWeek(int year, int week){
	    String[] result = new String[2];
	    Calendar calendar = Calendar.getInstance();
	    calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DATE, 1);
        result[0] = StringToDateUtil.dateFormatToString("yyyy-MM-dd", calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        calendar.add(Calendar.DATE, 1);
        result[1] = StringToDateUtil.dateFormatToString("yyyy-MM-dd", calendar.getTime());
        return result;
	}
	
	/**
	 * 根据年和第几周取得该周的所有日期
	 * @param year
	 * @param week
	 * @return
	 */
	public static List getAllDayOfWeek(int year, int week){
        List allDay = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DATE, 1);
        for(int i = 0; i < 7; i++){
            allDay.add(StringToDateUtil.dateFormatToString("yyyy-MM-dd", calendar.getTime()));
//            System.out.println(StringToDateUtil.dateFormatToString("yyyy-MM-dd", calendar.getTime()) + ", ");
            calendar.add(Calendar.DATE, 1);
        }
        return allDay;
    }
	
	/**
	 * 根据年和第几月取得该月的所有日期
	 * @param year
	 * @param week
	 * @return
	 */
	public static List getAllDayOfMonth(int year , int month){
		List allDay = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(calendar.YEAR, year);
		int val = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		for (int i = 0; i < val; i++) {
			allDay.add(StringToDateUtil.dateFormatToString("yyyy-MM-dd", calendar.getTime()));
			calendar.add(Calendar.DATE, 1);
		}
		return allDay;
	}
	
	/**
	 * 比较两个日期段是否有交集精确到秒
	 * @param begin
	 * @param end
	 * @param begin2
	 * @param end2
	 * @return
	 * @throws Exception
	 */
	 public static boolean comparisonRQ(String begin, String end, String begin2,
	            String end2) throws Exception {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        Date dt1 = df.parse(begin);
	        Date dt2 = df.parse(end);
	        Date dt3 = df.parse(begin2);
	        Date dt4 = df.parse(end2);
	        if (dt1.getTime() < dt3.getTime() && dt3.getTime() < dt2.getTime()) {
	           return true;
	        }
	        if (dt1.getTime() < dt4.getTime() && dt3.getTime() < dt2.getTime()) {
	        	 return true;
	        }
	        return false;
	    }
	
	public static void main(String[] args) throws Exception{
		//boolean f=comparisonRQ("2012-02-03 15:30:30", "2012-02-03 15:40:30", "2012-02-03 15:40:29", "2012-02-03 15:55:30");
		int f=getIntervalDaysByMinute("2012-02-03 00:00:00", "2012-02-03 1:40:00");
		System.out.println(f);
	}
}

// end

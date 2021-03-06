package com.dingxin.common.utils;

import com.dingxin.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author jeeplus
 * @version 2014-4-15
 */
@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
		"yyyyMMdd","MM/dd","yyyy-MM-dd'T'HH:mm:ss'Z'"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if(date==null){
			return "";
		}
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString(),
					TimeZone.getTimeZone("GMT+08:00"),Locale.SIMPLIFIED_CHINESE);
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd",
					TimeZone.getTimeZone("GMT+08:00"),Locale.SIMPLIFIED_CHINESE);
		}
		return formatDate;
	}
	
	public static String formatDatePattern(Date date,String pattern) {
		if(date==null){
			return "";
		}
		if (pattern != null){
			return formatDate(date, pattern);
		}else{
			return DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		if(date==null){
			return "";
		}
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 * @throws ParseException 
	 */
	public static Date parseDate(Object str) throws ParseException {
		if (str == null){
			return null;
		}
		String dateStr = str.toString();
		if (dateStr.contains("T") && dateStr.contains("Z") ) {
			dateStr = dateStr.replace("Z", " UTC"); 
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z"); 
			Date d = format.parse(dateStr); 
			return d;
		}
		return parseDate(str.toString(), parsePatterns);
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 获取前X天的 Date
	 * @param days
	 * @return
	 */
	public static String getBeforeDay(int days){   
		Date today = new Date();
		Date yesterday = new Date(today.getTime() - days*86400000L);//86400000L，它的意思是说1天的时间=24小时 x 60分钟 x 60秒 x 1000毫秒 单位是L。
		String time = formatDate(yesterday, "yyyyMMdd");
		return time;
	}  
	
	/**
	 * 获取前X天的 Date
	 * @param days
	 * @return
	 */
	public static String getBeforeDay(Date date,int days){   
		Date yesterday = new Date(date.getTime() - days*86400000L);//86400000L，它的意思是说1天的时间=24小时 x 60分钟 x 60秒 x 1000毫秒 单位是L。
		String time = formatDate(yesterday, "yyyyMMdd");
		return time;
	} 
	
	public static Date getBeforeDate(int days){   
		try {
			return DateUtils.parseDate(getBeforeDay(days),"yyyyMMdd");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}  
	public static Date getBeforeDate(Date date,int days){   
		try {
			return DateUtils.parseDate(getBeforeDay(date,days),"yyyyMMdd");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}  
	
	/**
	 * 获取当月第一天
	 * @param date
	 * @return
	 */
	public static String getFirstDayOfMonth(Date date) {
		date.setDate(1);
		return formatDate(date, "yyyyMMdd");
	}

	public static String formatDateTimeStr(long mss) {
			String DateTimes = null;
			long hours = mss/ (60 * 60);
			long minutes = (mss % (60 * 60)) / 60;
			long seconds = mss % 60;
			if (hours > 0) {
				DateTimes = hours + "小时" + minutes + "分钟"+seconds + "秒";
			} else if (minutes > 0) {
				DateTimes = minutes + "分钟"+seconds + "秒";
			} else {
				DateTimes = seconds + "秒";
			}

			return DateTimes;
	}
	public static String formatDateTimeStrDay(long mss) {
		String DateTimes = null;
		long days = mss / (60 * 60 * 24);
		long hours = (mss % (60 * 60 * 24)) / (60 * 60);
		long minutes = (mss % (60 * 60)) / 60;
		long seconds = mss % 60;
		if (days > 0) {
			DateTimes = days + "天" + hours + "小时" + minutes + "分钟"+seconds + "秒";
		} else if (hours > 0) {
			DateTimes = hours + "小时" + minutes + "分钟"+seconds + "秒";
		} else if (minutes > 0) {
			DateTimes = minutes + "分钟"+seconds + "秒";
		} else {
			DateTimes = seconds + "秒";
		}

		return DateTimes;
	}

	/**
	 * 将localDateTime转化为时间戳
	 * @param localDateTime
	 * @return
	 */
	public static Long localDateTimeToLong(LocalDateTime localDateTime){
			return localDateTime == null ? CommonConstant.LONG_DEFAULT :localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	/**
	 * 时间戳转化为localDateTime
	 * @param time
	 * @return
	 */
	public static LocalDateTime longToLocalDateTime(@NotNull Long time){
			return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
	}

	public static String localDatetimeToString(LocalDateTime time){
	    return time == null ? null:DateTimeFormatter.ofPattern("yyyy年-MM月-dd日 HH:mm:ss").format(time);
    }

	/**
	 * 将秒转换为时间 示例：6000 => 1小时40分钟
	 * @param seconds
	 * @return
	 */
	public static String secondsToTime(Long seconds){
		String time = null;
		if (seconds==null || seconds <= 0)
			return null;
		long hours = (seconds % (60 * 60 * 24)) / (60 * 60);
		long minutes = (seconds % (60 * 60)) / 60;
		long mySeconds = seconds % 60;
		if (hours > 0) {
			time = hours + "小时" + minutes + "分钟"+mySeconds + "秒";
		} else if (minutes > 0) {
			time = minutes + "分钟"+mySeconds + "秒";
		} else {
			time = mySeconds + "秒";
		}
		return time;

	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//		Date date = new Date();
//		System.out.println(formatDate(date, "E"));
//		System.out.println(getDateTime());
		
		System.out.println(parseDate("2019-01-12T22:13:22.459Z"));
		
		String dateString = "2019-01-12T22:13:22.459Z"; 
		dateString = dateString.replace("Z", " UTC"); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z"); 
		Date d = format.parse(dateString); 
		System.out.println(d.getTime());


	}
}

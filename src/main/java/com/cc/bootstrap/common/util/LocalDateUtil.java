package com.cc.bootstrap.common.util;

import org.apache.commons.lang.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName: LocalDateUtil 
 * @Description: jdk8新日期用法工具
 * @author CC  
 * @date 2019年9月5日 下午2:22:34 
 * @version V1.0 
 */
public class LocalDateUtil {
	
	/**
	 * 日期格式1 yyyy-MM-dd
	 */
	public static final String FormatterPattern_YYYYMMDD = "yyyy-MM-dd";
	/**
	 * 日期格式2 yyyy-MM-dd hh:mm:ss
	 */
	public static final String FormatterPattern_YYYYMMDD_hhmmss = "yyyy-MM-dd HH:mm:ss";
	// UTC
	public static final String FormatterPattern_YYYYMMDD_hhmmss_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	
	/**
	 * @Title: localDateToDate 
	 * @Description: localDate转Date
	 * @param @param localDate
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author CC
	 * @date 2019年9月5日 下午2:22:59
	 * @version V1.0
	 */
	public static Date localDateToDate(LocalDate localDate) {
	    ZoneId zone = ZoneId.systemDefault();
	    Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
	    return Date.from(instant);
	}
	
	/**
	 * @Title: stringToLocalDate 
	 * @Description: string转LocalDate
	 * @param @param dateStr
	 * @param @param formatterPattern
	 * @param @return   
	 * @return LocalDate  
	 * @throws
	 * @author CC
	 * @date 2019年9月5日 下午2:24:24
	 * @version V1.0
	 */
	public static LocalDate stringToLocalDate(String dateStr, String formatterPattern) {
		DateTimeFormatter matter = DateTimeFormatter.ofPattern(formatterPattern);
	    return LocalDate.parse(dateStr, matter);
	}
	
	/**
	 * @Title: stringToDate 
	 * @Description: string转Date
	 * @param @param dateStr
	 * @param @param formatterPattern
	 * @param @return   
	 * @return LocalDate  
	 * @throws
	 * @author CC
	 * @date 2019年9月5日 下午2:30:30
	 * @version V1.0
	 */
	public static Date stringToDate(String dateStr, String formatterPattern) {
		LocalDate dateLocale = LocalDateUtil.stringToLocalDate(dateStr, formatterPattern);
		return LocalDateUtil.localDateToDate(dateLocale);
	}
	
	/**
	 * @Title: dateToString 
	 * @Description: date转String
	 * @param @param date
	 * @param @param formatterPattern
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author CC
	 * @date 2019年9月5日 下午4:16:38
	 * @version V1.0
	 */
	public static String dateToString(Date date) {
		LocalDate localDate = LocalDateUtil.dateToLocalDate(date);
		return localDate.toString();
	}
	
	/**
	 * @Title: dateToLocalDate 
	 * @Description: Date转LocalDate
	 * @param @param date
	 * @param @return   
	 * @return LocalDate  
	 * @throws
	 * @author CC
	 * @date 2019年9月5日 下午4:16:52
	 * @version V1.0
	 */
	public static LocalDate dateToLocalDate(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalDate();
	}
	
	/**
	 * @Title: localDateTimeToDate 
	 * @Description: localDateTime转Date
	 * @param @param localDateTime
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author CC
	 * @date 2019年9月5日 下午2:43:38
	 * @version V1.0
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		 
        return Date.from(zdt.toInstant());
	}
	
	/**
	 * @Title: localDateTimeToString 
	 * @Description: localDateTime转String
	 * @param @param localDateTime
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author CC
	 * @date 2019年10月22日 上午11:06:57
	 * @version V1.0
	 */
	public static String localDateTimeToString(LocalDateTime localDateTime) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern(FormatterPattern_YYYYMMDD_hhmmss);
        return df.format(localDateTime);
	}

	/**
	 * @Description String转localDateTime
	 * @param timeStr
	 * @author ChenChen
	 * @return java.time.LocalDateTime
	 * @date 2022/7/26 14:56
	 */
	public static LocalDateTime stringToLocalDateTime(String timeStr) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern(FormatterPattern_YYYYMMDD_hhmmss);
		LocalDateTime localDateTime = LocalDateTime.parse(timeStr, df);
		return localDateTime;
	}

	/**
	 * @Description 计算两个时间的期间长度（持续时间）
	 * @param startTime
	 * @param endTime
	 * @author ChenChen
	 * @return java.time.Duration
	 * @date 2022/7/26 14:58
	 */
	public static Duration calculatePeriod(String startTime, String endTime) {
		LocalDateTime startDateTime = LocalDateUtil.stringToLocalDateTime(startTime);
		LocalDateTime endDateTime = LocalDateUtil.stringToLocalDateTime(endTime);
		return Duration.between(startDateTime, endDateTime);
	}

	/**
	 * @Description Duration转为string
	 * @param duration
	 * @author ChenChen
	 * @return java.lang.String
	 * @date 2022/7/26 14:59
	 */
	public static String convertDurationToString(Duration duration) {
		// 总秒数
		long seconds = duration.getSeconds();

		// 格式化为：XX小时XX分钟XX秒
		long temp = seconds;//temp记录剩余秒数
		long hoursFormat = temp / 3600;
		temp = temp - hoursFormat * 3600;
		long minutesFormat = temp / 60;
		temp = temp - minutesFormat * 60;
		long secondsFormat = temp;

		String runPeriod = hoursFormat + "小时" + minutesFormat + "分钟" + secondsFormat + "秒";
		return runPeriod;
	}

	/**
	 * @Description 将UTC时间字符串转为普通时间字符串，例如：2022-07-08T08:47:37Z转为2022-07-08 08:47:37
	 * @param timeStr
	 * @author ChenChen
	 * @return java.lang.String
	 * @date 2022/7/26 15:06
	 */
	public static String convertUTCToGenerateStr(String timeStr) {
		DateTimeFormatter df_UTC = DateTimeFormatter.ofPattern(FormatterPattern_YYYYMMDD_hhmmss_UTC);
		DateTimeFormatter df = DateTimeFormatter.ofPattern(FormatterPattern_YYYYMMDD_hhmmss);

		if(StringUtils.isNotEmpty(timeStr) && StringUtils.contains(timeStr, 'T')) {
			LocalDateTime localDateTime = LocalDateTime.parse(timeStr, df_UTC);
			return df.format(localDateTime);
		}

		return timeStr;
	}
}

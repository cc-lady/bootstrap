package com.cc.bootstrap.common.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

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
	//（本地比世界UTC时间多8小时）
	public static String convertUTCToGenerateStr(String timeStr) {
		DateTimeFormatter df_UTC = DateTimeFormatter.ofPattern(FormatterPattern_YYYYMMDD_hhmmss_UTC);
		DateTimeFormatter df = DateTimeFormatter.ofPattern(FormatterPattern_YYYYMMDD_hhmmss);

		if(StringUtils.isNotEmpty(timeStr) && StringUtils.contains(timeStr, 'T')) {
			LocalDateTime localDateTime = LocalDateTime.parse(timeStr, df_UTC);
			// 增加8小时返回
			return df.format(localDateTime.plusHours(8));
		}

		return timeStr;
	}

	/**
	 * @Description 将UTC时间字符串转为普通时间字符串，例如：2022-07-08T08:47:37Z转为2022-07-08 08:47:37 第二钟写法
	 * @param timeUtc
	 * @author ChenChen
	 * @return java.lang.String
	 * @date 2022/8/4 14:51
	 */
	public static String convertUTCToGenerateStr2(String timeUtc) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern(FormatterPattern_YYYYMMDD_hhmmss);

		// 1.instant.atZone(zoneId)
//		String timeUtc = "2022-08-04T10:14:24Z";
		Instant instant = Instant.parse(timeUtc);

		ZonedDateTime zonedDateTimeUtc = instant.atZone(ZoneId.of("UTC"));
		String timeUtc_format = zonedDateTimeUtc.format(df);
		System.out.println(timeUtc_format);//2022-08-04 10:14:24

		ZoneId zoneId = ZoneId.of("Asia/Shanghai");
		ZonedDateTime zonedDateTime = instant.atZone(zoneId);
		System.out.println(zonedDateTime.format(df));//2022-08-04 18:14:24

		ZoneId zoneId_nine = ZoneId.of("Asia/Tokyo");
		ZonedDateTime zonedDateTime_nine = instant.atZone(zoneId_nine);
		System.out.println(zonedDateTime_nine.format(df));//2022-08-04 19:14:24

		return zonedDateTime.format(df);//2022-08-04 18:14:24
	}

	/**
	 * @Description 将UTC时间字符串转为普通时间字符串，例如：2022-07-08T08:47:37Z转为2022-07-08 08:47:37 第三钟写法
	 * @param timeUtc
	 * @author ChenChen
	 * @return java.lang.String
	 * @date 2022/8/4 14:57
	 */
	public static String convertUTCToGenerateStr3(String timeUtc) throws ParseException {
		// 2.DateTimeZone.convertUTCToLocal
		timeUtc = timeUtc.replace("T", " ");
		timeUtc = timeUtc.replace("Z", "");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatterPattern_YYYYMMDD_hhmmss);
		Date date = simpleDateFormat.parse(timeUtc);
		DateTimeZone gmt = DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		long millis = gmt.convertUTCToLocal(date.getTime());
		Date dateLocale = new Date(millis);
		System.out.println(dateLocale.toString());//Thu Aug 04 18:14:24 CST 2022

		long millis2= gmt.convertLocalToUTC(dateLocale.getTime(), true);
		Date dateLocale2 = new Date(millis2);
		System.out.println(dateLocale2.toString());//Thu Aug 04 10:14:24 CST 2022

		return simpleDateFormat.format(dateLocale);
	}

	public static String convertLocalToUtcTimeStr(String timeStr) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dt = LocalDateTime.parse(timeStr, dateTimeFormatter);

		ZoneId zone = ZoneId.of("Asia/Shanghai");
		ZonedDateTime zdt = dt.atZone(zone);

		Instant instant = zdt.toInstant();
		System.out.println("instant = " + instant.toString());

		return instant.toString();
	}

	public static void main(String[] args) throws ParseException {

		String timeUtc = "2022-08-04T10:14:24Z";
		String result1 = convertUTCToGenerateStr(timeUtc);
		String result2 = convertUTCToGenerateStr2(timeUtc);
		String result3 = convertUTCToGenerateStr3(timeUtc);

		System.out.println("result1 == " + result1);
		System.out.println("result2 == " + result2);
		System.out.println("result3 == " + result3);
//		result1 == 2022-08-04 18:14:24
//		result2 == 2022-08-04 18:14:24
//		result3 == 2022-08-04 18:14:24

		String localTimeStr = result1;
		String utcTimeStr = convertLocalToUtcTimeStr(localTimeStr);
		System.out.println("localTimeStr = " + localTimeStr + " convertLocalToUtcTimeStr then result = " + utcTimeStr);
//		localTimeStr = 2022-08-04 18:14:24 convertLocalToUtcTimeStr then result = 2022-08-04T10:14:24Z
	}
}

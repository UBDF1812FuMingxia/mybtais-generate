/**
 *
 */
package com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @file DateHelper.java
 * @author kevin
 * @mail kevin@emateglobal.com
 * @since 2016年8月3日
 */
public class DateHelper {

    /**
     * 年月日时分秒: yyyyMMddHHmmssSSS
     */
    public static final String DATE_FORMAT        = "yyyyMMddHHmmssSSS";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT_YMD    = "yyyy-MM-dd";

    /**
     * HH:mm:ss
     */
    public static final String DATE_FORMAT_HMS    = "HH:mm:ss";

    public static Date startOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date before(long time) {
        return new Date(System.currentTimeMillis() - time);
    }

    /**
     * 格式：yyyyMMddHHmmssSSS
     * @return
     */
    public static String currentTimeStamp() {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(new Date());
    }

    /**
     * 获取30天之前
     * @return
     */
    public static Date getMonthAgoDate() {
        Calendar calendar = Calendar.getInstance();
        // 获取 一天之前的时间
        //calendar.add(Calendar.DAY_OF_MONTH, -1);

        // 获取12h之前的时间
        //calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 12);

        // 获取30天之前的时间
        calendar.set(Calendar.DAY_OF_MONTH, -30);

        return calendar.getTime();
    }

    /**
     * 获取1天之前[负]/之后[正]的时间
     * @return
     */
    public static Date getDayAgoDate(int dateNum) {
        Calendar calendar = Calendar.getInstance();
        // 获取 dateNum天之前[正数即之后，负数即之前]的时间
        calendar.add(Calendar.DAY_OF_MONTH, dateNum);
        return calendar.getTime();
    }

    /**
     * 获取 当前时间 +- X分钟 的时间
     * @param minute
     * @return
     */
    public static Date getDayMinuteDate(int minute) {
        Calendar calendar = Calendar.getInstance();
        // 获取 dateNum天之前[正数即之后，负数即之前]的时间
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获取指定时间的 +- X分钟 的时间
     * @param paramDt
     * @param minute
     * @return
     */
    public static Date getSomeDayMinuteDate(Date paramDt, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDt);
        // 获取 dateNum天之前[正数即之后，负数即之前]的时间
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获取指定时间之前/之后n天
     * @param date 指定日期
     * @param dateNum 正数即之后，负数即之前
     * @return
     */
    public static Date getSomeDayAgoDate(Date date, int dateNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 获取 dateNum天之前/之后的时间
        calendar.add(Calendar.DAY_OF_MONTH, dateNum);
        return calendar.getTime();
    }

    /**
     * 获取某天00：00：00
     * @param date
     * @return
     */
    public static Date getZeroOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE), 00, 00, 00);
        return cal.getTime();
    }

    /**
     * 获取某天23:59:59[24:00:00]
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE), 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 设置字符串为指定格式的日期
     * @param pattern
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateFromString(String pattern, String dateStr)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }

    /**
     * 格式化日期
     * @param pattern
     * @param date
     * @return
     * @since 2018年2月1日
     */
    public static String getFormatStringFromDate(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 获取某天当月第一天
     * @author gaoss
     * @since  2019年5月9日
     */
    public static Date initDateByMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某天当月最后一天
     * @author gaoss
     * @since  2019年5月9日
     */
    public static Date endDateByMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static int getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonthOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getDayOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    /**
     *  获取 endDate - beginDate 时间差 int
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int differentDays(Date beginDate, Date endDate) {
        Long day = 0L;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            if (beginDate != null) {
                String begin = sdf.format(beginDate);
                beginDate = sdf.parse(begin);
            }
            if (endDate != null) {
                String end = sdf.format(endDate);
                endDate = sdf.parse(end);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day.intValue();
    }

    /**
     * 获取 endDate - beginDate 时间差 long
     * @param beginDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static long getInterval(Date beginDate, Date endDate) {
        long day = 0L;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            if (beginDate != null) {
                String begin = sdf.format(beginDate);
                beginDate = sdf.parse(begin);
            }
            if (endDate != null) {
                String end = sdf.format(endDate);
                endDate = sdf.parse(end);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

}

package com.chxlay.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * @author Alay
 * @date 2020-12-01 14:55
 * @project netty-chat
 */
public class DateUtil {

    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYYMM = "yyyyMM";
    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String YMD = "yyyyMMdd";
    public static final String YEAR_TO_S = "yyyy-MM-dd HH:mm:ss";
    private static final long ONE_DAY = 86400L;

    /**
     * localDate转Date
     *
     * @param localDate
     * @return Date
     */
    public static Date asDate(LocalDate localDate) {
        LocalDateTime localDateTime = localDate.atStartOfDay();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    /**
     * localDateTime转Date
     *
     * @param localDateTime
     * @return Date
     */
    public static Date asDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Date转LocalDate
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate asLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDate();
    }

    /**
     * Date转 LocalDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime asLTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }

    /**
     * 时间字符串转LocalDate
     *
     * @param dateStr
     * @param format
     * @return LocalDate
     */
    public static LocalDate str2LDate(String dateStr, String format) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 时间字符串转 Date
     *
     * @param dateStr
     * @param format
     * @return Date
     */
    @Deprecated
    public static Date str2Date(String dateStr, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间字符串转 LocalDateTime
     *
     * @param dateStr
     * @param format
     * @return LocalDateTime
     */
    public static LocalDateTime str2LTime(String dateStr, String format) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(format));
    }


    /**
     * 时间转字符串
     *
     * @param time
     * @param format
     * @return 时间字符串
     */
    public static String toString(LocalDateTime time, String format) {
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 时间转字符串
     *
     * @param time
     * @param format
     * @return 时间字符串
     */
    public static String toString(LocalDate time, String format) {
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 时间转字符串
     *
     * @param date
     * @param format
     * @return 时间字符串
     */
    @Deprecated
    public static String toString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 当天的起始时间
     *
     * @param date
     * @return
     */
    public static LocalDateTime dayStart(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIN);
    }

    /**
     * 获取当天结束时间
     *
     * @param date
     * @return
     */
    public static LocalDateTime dayEnd(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MAX);
    }


    /**
     * 日期是否是当天
     *
     * @param dateTime
     * @return
     */
    public static boolean isToday(LocalDateTime dateTime) {
        boolean isToday = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.Y_M_D);
        String nowStr = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        String targetTime = dateTime.format(formatter);
        if (nowStr.equals(targetTime)) {
            isToday = true;
        }
        return isToday;
    }

    /**
     * 是否是同一天
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean isSameDay(LocalDateTime time1, LocalDateTime time2) {
        if (null == time1 || null == time2) {
            throw new IllegalArgumentException("参数不正确");
        }
        boolean isSomeday = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.Y_M_D);
        String time1Str = time1.format(formatter);
        String time2Str = time2.format(formatter);
        if (Objects.equals(time1Str, time2Str)) {
            isSomeday = true;
        }
        return isSomeday;
    }

    /**
     * 是否是同一个月
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean isSameMonth(LocalDateTime time1, LocalDateTime time2) {
        if (null == time1 || null == time2) {
            throw new IllegalArgumentException("参数不正确");
        }
        boolean isSameMonth = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.YYYY_MM);
        String time1Str = time1.format(formatter);
        String time2Str = time2.format(formatter);
        if (Objects.equals(time1Str, time2Str)) {
            isSameMonth = true;
        }
        return isSameMonth;
    }

    /**
     * 月份的开始时间
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime monthStart(LocalDateTime dateTime) {
        dateTime = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN);
        dateTime = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        return dateTime;
    }

    /**
     * 月份的最后一天
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime monthEnd(LocalDateTime dateTime) {
        dateTime = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX);
        dateTime = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        return dateTime;
    }


    /**
     * 两个时间的相差月份数
     *
     * @param dateTime1 前时间
     * @param dateTime2 后时间
     * @return
     */
    public static int monthDiff(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        // 第一个时间的年月
        int year1 = dateTime1.getYear();
        int month1 = dateTime1.getMonthValue();

        // 第二个时间的年月
        int year2 = dateTime2.getYear();
        int month2 = dateTime2.getMonthValue();

        // 两个时间相差月份数
        int monthCount = (year1 - year2) * 12 + (month1 - month2);
        return monthCount;
    }

    /**
     * 获取秒时间戳
     *
     * @param time
     * @return
     */
    public static long stampOfS(LocalDateTime time) {
        return time.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 获取当前时间戳秒
     *
     * @return
     */
    public static long currentTimeSecond() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 获取毫秒时间戳
     *
     * @param time
     * @return
     */
    public static long stampOfMS(LocalDateTime time) {
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 过期时间
     *
     * @param minutes 多少分钟后过期
     * @return
     */
    public static long expireAt(int minutes) {
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(-minutes);
        long second = localDateTime.toEpochSecond(ZoneOffset.of("+8"));
        return second;
    }

    /**
     * 比较时间相差天数(不足一天为一天)
     *
     * @param start
     * @param end
     * @return
     */
    public static int compareTo(LocalDateTime start, LocalDateTime end) {
        int cmp = 0;
        if (null != start && null != end) {
            long stamp1 = stampOfS(start);
            long stamp2 = stampOfS(end);
            long delta = stamp2 - stamp1;
            BigDecimal divide = new BigDecimal(delta).divide(new BigDecimal(ONE_DAY), BigDecimal.ROUND_CEILING);
            cmp = divide.intValue();
        }
        return cmp;
    }

    /**
     * 计算两个时间秒数差
     *
     * @param start
     * @param end
     * @return
     */
    public static long subSecond(LocalDateTime start, LocalDateTime end) {
        if (null == end || null == start) {
            return 0;
        }
        long startSecond = start.toEpochSecond(ZoneOffset.of("+8"));
        long endSecond = end.toEpochSecond(ZoneOffset.of("+8"));
        return Math.abs(startSecond - endSecond);
    }

    /**
     * 计算年龄
     *
     * @param birthDay 生日
     * @return
     */
    public static Integer calcAge(LocalDate birthDay) {
        int years = birthDay.until(LocalDate.now()).getYears();
        return years;
    }

}
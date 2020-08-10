package org.donny.base.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * DateUtil
 *
 * @author 1792998761@qq.com
 * @date 2019/10/25
 */
public class DateUtil {

    /**
     * 默认日期格式
     */
    private static final String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认时区
     */
    private static final String TIMEZONE = "Asia/Shanghai";

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 根据format格式化日期输出字符串
     *
     * @param date   日期
     * @param format 日期要转的字符串格式
     * @return String
     */
    public static String formatDate(Date date, String format) {
        if (StringUtils.isNotBlank(format) && date != null) {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } else {
            return "";
        }
    }

    /**
     * 获取前一日日期 0时0分0秒
     *
     * @return Date
     */
    public static Date getBeforeDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取某一时刻前一天的日期
     *
     * @param date 某一时刻
     * @return Date
     */
    public static Date getBeforeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取当前时间字符串格式的日期
     *
     * @return String
     */
    public static String getDefaultDate() {
        DateFormat dateFormat = new SimpleDateFormat(TIMEFORMAT);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 根据format获取当前时间字符串格式的日期
     *
     * @return String
     */
    private static String getNowDate(String format) {
        if (StringUtils.isNotBlank(format)) {
            DateFormat dateFormat = new SimpleDateFormat(format);
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
            return dateFormat.format(calendar.getTime());
        } else {
            return "";
        }
    }

    /**
     * 根据format和 时区获取当前时间字符串格式的日期
     *
     * @return String
     */
    public static String getNowDate(String format, String timezone) {
        if (StringUtils.isNotBlank(format)) {
            Calendar calendar;
            if (StringUtils.isNotBlank(timezone)) {
                calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
                DateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.format(calendar.getTime());
            } else {
                return getNowDate(format);
            }
        } else {
            return "";
        }
    }

    /**
     * 根据时区和日期的格式输出hours间隔前的日期字符串
     *
     * @return String
     */
    public static String getDateBeforeHours(String format, int hours, String timezone) {
        if (StringUtils.isBlank(format)) {
            return "";
        }
        if (hours < 0) {
            return "";
        }
        Calendar calendar;
        if (StringUtils.isNotBlank(timezone)) {
            calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hours);
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(calendar.getTime());
        } else {
            calendar = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE));
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hours);
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(calendar.getTime());
        }
    }

    public static Date parseStrToDate(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }

        if (StringUtils.isEmpty(format)) {
            format = TIMEFORMAT;
        }
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 功能描述: <br>
     * 将绝对秒数时间转换成 format格式的时间
     *
     * @param minusTime minusTime
     * @return Date
     * @date 2020/2/4
     * @author 19039209
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date parseLongToDate(long minusTime) {
        if (minusTime < 0) {
            throw new IllegalArgumentException("无效的绝对秒数时间");
        }

        Date date = new Date();
        date.setTime(minusTime);
        return date;
    }

    /**
     * 功能描述: <br>
     * 获取前一天的日期 2020-01-01
     *
     * @param
     * @return
     * @date 2020/2/2
     * @author 19039209
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getOneDayBefore(Date date) {
        if (date == null) {
            throw new NullPointerException();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    public static int getSeconds(long milliSecond) {
        double k = Math.round(milliSecond * 1.0 / 1000L);
        int result = (int) Math.rint(k);
        String str = new BigDecimal(result + "").toString();
        return Integer.parseInt(str);
    }

    /**
     * 判断该日期是否是该月的最后一天
     *
     * @param date 需要判断的日期
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) == calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断是星期几
     *
     * @param weekday 1代表周日 2代表周一 ... 7代表周六
     * @return true false
     */
    public static boolean isWeekdayOnDate(int weekday) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekdayTmp = c.get(Calendar.DAY_OF_WEEK);
        return weekday == weekdayTmp;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getDayBeforeDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(TIMEFORMAT);
        return format.format(today);
    }

    /**
     * 时间格式转long
     *
     * @param dateStr
     * @return
     */
    public static long strToLong(String dateStr, String timeFormat) throws ParseException {
        SimpleDateFormat format;
        if (StringUtils.isEmpty(timeFormat)) {
            format = new SimpleDateFormat(TIMEFORMAT);
        } else {
            format = new SimpleDateFormat(timeFormat);
        }
        return format.parse(dateStr).getTime();
    }

    public static Date getDateByStr(String dateStr, String timeFormat) throws ParseException {
        SimpleDateFormat format;
        if (StringUtils.isEmpty(timeFormat)) {
            format = new SimpleDateFormat(TIMEFORMAT);
        } else {
            format = new SimpleDateFormat(timeFormat);
        }
        return format.parse(dateStr);
    }

    /**
     * 验证日期是否为指定格式
     *
     * @param sDate      字符串的日期
     * @param dateFormat 日期格式
     * @return true/false
     */
    public static boolean isLegalDate(String sDate, String dateFormat) {
        if ((sDate == null) || (sDate.length() == 0)) {
            return false;
        }

        DateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (ParseException e) {
            return false;
        }
    }
}
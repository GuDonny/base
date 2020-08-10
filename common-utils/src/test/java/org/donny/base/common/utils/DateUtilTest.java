package org.donny.base.common.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author 19057578
 * @date 2020/7/27 14:59
 */
public class DateUtilTest {

    @Test
    public void formatDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 4);
        String dd = DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd");
        assertTrue("2020-02-04".equals(dd));
    }

    @Test
    public void getBeforeDate() {
        assertNotNull(DateUtil.getBeforeDate());
    }

    @Test
    public void getBeforeDate1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 4);
        Date d = DateUtil.getBeforeDate(calendar.getTime());
        calendar.set(2020, 1, 3);
        assertTrue(d.getTime() == calendar.getTimeInMillis());
    }

    @Test
    public void getDefaultDate() {
        assertNotNull(DateUtil.getDefaultDate());
    }

    @Test
    public void getNowDate() {
        assertNotNull(DateUtil.getNowDate("yyyy-MM-dd", ""));
    }

    @Test
    public void parseStrToDate() {
        assertNotNull(DateUtil.parseStrToDate("2020-02-14", "yyyy-MM-dd"));
    }

    @Test
    public void parseLongToDate() {
        assertNotNull(DateUtil.parseLongToDate(1581341892756L));
    }

    @Test
    public void getOneDayBefore() {
        assertNotNull(DateUtil.getDayBeforeDate(2));
    }

    @Test
    public void getSeconds() {
        assertTrue(100 == DateUtil.getSeconds(100000L));
    }

    @Test
    public void isLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 29);
        assertTrue(DateUtil.isLastDayOfMonth(calendar.getTime()));
        calendar.set(2020, 1, 28);
        assertFalse(DateUtil.isLastDayOfMonth(calendar.getTime()));
    }

    @Test
    public void isWeekdayOnDate() {
        Calendar calendar = Calendar.getInstance();
        int e = calendar.get(Calendar.DAY_OF_WEEK);
        assertTrue(DateUtil.isWeekdayOnDate(e));
    }

    @Test
    public void getDayBeforeDate() {
        assertNotNull(DateUtil.getDayBeforeDate(1));
    }

    @Test
    public void strToLong() throws Exception {
        assertNotNull(DateUtil.strToLong("2020-02-14 10:10:10", ""));
    }

    @Test
    public void getDateByStr() throws Exception {
        assertNotNull(DateUtil.getDateByStr("2020-02-14", "yyyy-MM-dd"));
    }
}
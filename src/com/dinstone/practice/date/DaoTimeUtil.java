
package com.dinstone.practice.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DaoTimeUtil {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 取得当前日期所在周的最后一天
     * 
     * @param date
     * @return
     */
    public static String getLastDayOfWeek(String date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        try {
            c.setTime(formatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return formatter.format(c.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     * 
     * @param date
     * @return
     */
    public static String getFirstDayOfWeek(String date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        try {
            c.setTime(formatter.parse(date));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return formatter.format(c.getTime());
    }

    /**
     * 计算两个月前的时间
     * 
     * @param date
     * @return
     */
    public static String getTwoMonthBefor(String date) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            cal.add(Calendar.MONTH, -2);
            String mDateTime = formatter.format(cal.getTime());
            return mDateTime.substring(0, 10);
        } catch (ParseException e) {
            System.out.println("计算前两个月的时间错误!");
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 计算当前月的最大天数
     * 
     * @param date
     * @return
     */
    public static String getTheMonthMaxDay(String date) {
        Calendar cal = Calendar.getInstance();
        int day = 30;
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            System.out.println("计算前两个月的时间错误!");
            e.printStackTrace();
        }
        return day + "";
        // calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 当前天的第-num天或第num天
     * 
     * @param time
     * @param days
     * @return
     */
    public static String getTheDayByNum(String time, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time));
            cal.add(Calendar.DAY_OF_MONTH, days);
            String mDateTime = formatter.format(cal.getTime());
            return mDateTime.substring(0, 10);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }

    public static void main(String[] args) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String date = f.format(new Date());
        // String date = "2011-02-03";
        System.out.println(getTheMonthMaxDay(date) + "   当前月最大的天数");
        System.out.println(getTwoMonthBefor(date) + "     当前时间的前两个月时间");
        System.out.println(getFirstDayOfWeek(date) + "       当前周的第一天");
        System.out.println(getLastDayOfWeek(date) + "      当前周的最后一天");
        System.out.println(getTheDayByNum(date, -30));
    }
}
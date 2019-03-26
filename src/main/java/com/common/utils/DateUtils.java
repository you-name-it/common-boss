package com.common.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by laizhilong on 2017/4/25.
 */
public class DateUtils {

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        String dateString = formatter.format(dateDate);  
        return dateString;  
    }  
  
    public static Date strToDate(String strDate) {  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        ParsePosition pos = new ParsePosition(0);  
        Date strtodate = formatter.parse(strDate, pos);  
        return strtodate;  
    }
    
 // 将时间戳转为字符串  
    public static String getStrTime(Long cc_time) {  
      
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    String re_StrTime = sdf.format(cc_time);  
      
    return re_StrTime;  
      
    }


    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfThisWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        System.out.println(c.getTime().getTime());
        return sdf.format(c.getTime());
    }



    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return sdf.format(c.getTime());
    }


    public static void main(String[] args) {
        System.out.println("本周周一"+getMondayOfThisWeek());

        System.out.println("本周周末"+getSundayOfThisWeek());
    }

}

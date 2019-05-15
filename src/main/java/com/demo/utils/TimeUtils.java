package com.demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtils {


    /**
     * 获得最近一个月的日期字符串  2019-05-15
     * @return
     */
    public static List<String> getOneMonthDates() {
        Calendar begin = Calendar.getInstance();// 得到一个Calendar的实例
        begin.setTime(new Date()); // 设置时间为当前时间
        begin.add(Calendar.MONTH, -1);// 月份减1
        begin.add(Calendar.DATE, +1);// 日期加1
        Date result = begin.getTime();
        Calendar end = Calendar.getInstance();
        Long startTime = begin.getTimeInMillis();
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;// 一天的时间转化为ms
        List<String> dates = new ArrayList<>();
        Long time = startTime;
        int i = 0;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            dates.add(i, df.format(d));
            i++;
            time += oneDay;
        }
        return dates;
    }


}

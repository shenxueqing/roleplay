package com.ebupt.roleplay.server.north.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class TimeUtil {

	public static Date getDayStart() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return  cal.getTime();
	}
	public static Date getBeforeNDay(int n) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.add(Calendar.DAY_OF_YEAR, 0-n);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return  cal.getTime();
	}

	public static Date getDayEnd() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(Calendar.HOUR_OF_DAY, 23);
	    cal.set(Calendar.MINUTE, 59);
	    cal.set(Calendar.SECOND, 59);
	    cal.set(Calendar.MILLISECOND, 999);
	    return  cal.getTime();
	}
	
	public static Date getWeekStart() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
	    return  cal.getTime();
	}
	
	public static Date getWeekEnd() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    cal.set(Calendar.HOUR_OF_DAY,23);
	    cal.set(Calendar.MINUTE,59);
	    cal.set(Calendar.SECOND, 59);
	    cal.set(Calendar.MILLISECOND, 999);
	    return  cal.getTime();
	}

	public static String getDayInStringFormat() {
		Format formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date(System.currentTimeMillis()));
	}
	public static String getMonthAndDayInStringFormat() {
		Format formatter = new SimpleDateFormat("MMdd");
		return formatter.format(new Date(System.currentTimeMillis()));
	}
	
	public static String getFormatDate(Date date) {
		Format formatter = new SimpleDateFormat("yyyy年MM月dd日");
		return formatter.format(date);
	}
	public static String getLastDayInStringFormat(){
		Format formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return formatter.format(cal.getTime());
	}
	
	public static void main(String args[]){
		System.out.println(TimeUtil.getLastDayInStringFormat());
	//	System.out.println(TimeUtil.getYearInStringFormat());
		System.out.println(TimeUtil.getBeforeNDay(6));
	}

	public static String getYearInStringFormat() {
		Format formatter = new SimpleDateFormat("yyyy");
		return formatter.format(new Date(System.currentTimeMillis()));
	}
}

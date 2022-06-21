package com.general.template.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class DateUtil {
    public static DateFormat getParseableDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static DateFormat getSlashParseableDateFormat() {
        return new SimpleDateFormat("MM/dd/yyyy");
    }
    public static DateFormat getSlashParseableDateFormatForView() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    public static DateFormat getReadableDateForGraph() {
        return new SimpleDateFormat("dd MMM YY");
    }

    public static DateFormat getReadableDateFull() {
        return new SimpleDateFormat("dd MMMM YYYY");
    }

    public static String getReadableDate(Date date) {
        return getReadableDateFull().format(date);
    }

    public static String getReadableDateForSMS(Date date) {
        return getSlashParseableDateFormatForView().format(date);
    }


    public static SimpleDateFormat getReadableDateForView() {
        return new SimpleDateFormat("dd MMM YY h:mm a");
    }


    public static DateFormat getReadableDateTime() {
        return new SimpleDateFormat("MMM dd, yyyy h:m a");
    }

    public static Period getAge(Date date) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return Period.between(birthday, today);
    }

    public static Date getServerDateTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getDefault());
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Map<DateRangeType, Calendar> buildDateRange(String period){
        DateTimeUtils datetimeUtil = new DateTimeUtils(Calendar.getInstance());
        Calendar dateFrom, dateTo ;
        switch (period) {
            case "this_month":
                dateFrom = datetimeUtil.getThisMonthStartDate();
                dateTo = datetimeUtil.getThisMonthEndDate();
                break;
            case "last_month":
                dateFrom = datetimeUtil.getLastMonthStartDate();
                dateTo = datetimeUtil.getLastMonthEndDate();
                break;
            case "this_year":
                dateFrom = datetimeUtil.getThisYearStartDate();
                dateTo = datetimeUtil.getThisYearEndDate();
                break;
            case "last_year":
                dateFrom = datetimeUtil.getLastYearStartDate();
                dateTo = datetimeUtil.getLastYearEndDate();
                break;
            case "last_day":
                dateFrom = datetimeUtil.getLastDay(Calendar.getInstance());
                dateTo = datetimeUtil.getLastDay(Calendar.getInstance());
                break;
            case "all_time":
            default:
                dateFrom = datetimeUtil.getBeginningFromDate();
                dateTo = datetimeUtil.getThisYearEndDate();
                break;
        }
        Map<DateRangeType,Calendar> dateRangeMap = new HashMap();
        dateRangeMap.put(DateRangeType.DATE_FROM,dateFrom);
        dateRangeMap.put(DateRangeType.DATE_TO,dateTo);
        return dateRangeMap;
    }
//    public static String calculateAge(St){
//        LocalDate birthdate = new LocalDate(1970, 1, 20);
//        LocalDate now = new LocalDate();
//        Years age = Years.yearsBetween(birthdate, now);
//    }

    public enum DateRangeType{
        DATE_FROM("dateFrom"),
        DATE_TO("dateTo");

        private String value;


        DateRangeType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}
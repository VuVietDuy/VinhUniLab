package com.VinhUniLab.utils;

import com.VinhUniLab.model.DateTimeFormatDto;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.GregorianCalendar.DAY_OF_WEEK;


public class DateUtil {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private DateUtil() {
    }

    public static Date fromString(String dateString) throws ParseException {
        if (StringUtils.isEmpty(dateString)) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.parse(dateString);
    }

    public static Date getCurrentDateWithoutTime() {
        try {
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            Date today = new Date();
            return formatter.parse(formatter.format(today));
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Map<String, String> getDayOfWeek() {
        Calendar now = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        SimpleDateFormat formatDayOfWeek = new SimpleDateFormat("E");
        Map<String, String> data = new LinkedHashMap<>();
        int delta = -now.get(DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            data.put(formatDayOfWeek.format(now.getTime()), format.format(now.getTime()));
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        return data;
    }

    public static List<String> getMonthOfYear() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        List<String> months = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        months.add(sdf.format(cal.getTime()));
        for (int i = 0; i < 11; i++) {
            cal.add(Calendar.MONTH, 1);
            months.add(sdf.format(cal.getTime()));
        }
        return months;
    }

    public static List<String> getNumberOfYears(Integer numberOfYear) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        List<Integer> years = new ArrayList<>();
        years.add(cal.get(Calendar.YEAR));
        while (numberOfYear > 1) {
            cal.add(Calendar.YEAR, -1);
            years.add(cal.get(Calendar.YEAR));
            numberOfYear--;
        }
        return years.stream().sorted().map(Object::toString).collect(Collectors.toList());
    }

    public static Map<String, String> getDaysOfMonth(Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Map<String, String> data = new LinkedHashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        data.put(formatter.format(cal.getTime()), formatter.format(cal.getTime()));
        for (int i = 1; i < maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i + 1);
            data.put(formatter.format(cal.getTime()), formatter.format(cal.getTime()));
        }
        return data;
    }

    public static String nowDateTimeString() {
        ZonedDateTime now = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern(DATETIME_FORMAT).format(now);
    }

    public static ZonedDateTime fromDateTimeString(String dateTimeString) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern(DATETIME_FORMAT).withZone(ZoneId.systemDefault());
        return ZonedDateTime.parse(dateTimeString, parser);
    }


    public static ZonedDateTime toZonedDateTime(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return ZonedDateTime.ofInstant(formatter.parse(dateString).toInstant(),
                ZoneId.systemDefault());
    }

    public static DateTimeFormatDto checkAndGetDate(Object argument) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            return DateTimeFormatDto.builder()
                    .date(ZonedDateTime.ofInstant(formatter.parse(argument.toString()).toInstant(),
                            ZoneId.systemDefault()))
                    .isDateFormat(true)
                    .build();
        } catch (ParseException e) {
            return DateTimeFormatDto.builder()
                    .isDateFormat(false)
                    .build();
        }
    }

    public static int getDayOfMonth() {
        try {
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            return dayOfMonth;
        } catch (Exception ex) {
            return 0;
        }
    }
}

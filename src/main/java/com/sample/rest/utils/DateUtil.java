package com.sample.rest.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rishchenko on 13.09.2017.
 */
public class DateUtil {

    public static XMLGregorianCalendar getCurrentDataPlusOneYear() throws DatatypeConfigurationException {
        Date date = new Date();
        Date newDate;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 1);
        newDate = c.getTime();
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(newDate));
    }


    public static Date parseDate(String responseTime, String pattern, A2TimeZoneMapping timeZoneMapping) throws Exception {
        SimpleDateFormat spf = new SimpleDateFormat(pattern);
        spf.setTimeZone(TimeZone.getTimeZone(timeZoneMapping.getZone()));
        return spf.parse(responseTime);

    }

    public static Date parseDate(String time, String pattern) throws Exception {
        SimpleDateFormat spf = new SimpleDateFormat(pattern);
        return spf.parse(time);
    }


    public static String getCurrentDate(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static String incrementDatePlusOneYear(Date date, String pattern) throws DatatypeConfigurationException {
        Date newDate;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 1);
        newDate = c.getTime();
        return new SimpleDateFormat(pattern).format(newDate);
    }


    public static String incrementDatePlusOneYear(String date, String pattern) throws Exception {
        Date newDate;
        Calendar c = Calendar.getInstance();
        c.setTime(parseDate(date, pattern));
        c.add(Calendar.YEAR, 1);
        newDate = c.getTime();
        return new SimpleDateFormat(pattern).format(newDate);
    }


    public static Date incrementDatePlusOneHour(String date, String pattern) throws Exception {
        Date newDate;
        Calendar c = Calendar.getInstance();
        c.setTime(parseDate(date, pattern));
        c.add(Calendar.HOUR, 1);
        return newDate = c.getTime();

    }

    public static A2TimeZoneMapping geTimeZoneByCountry(String country) {
        A2TimeZoneMapping countryA2Code;
        if (country.equalsIgnoreCase("DE")) {
            countryA2Code = A2TimeZoneMapping.DE;
        } else if (country.equalsIgnoreCase("FR")) {
            countryA2Code = A2TimeZoneMapping.FR;
        } else if (country.equalsIgnoreCase("ES")) {
            countryA2Code = A2TimeZoneMapping.ES;
        } else if (country.equalsIgnoreCase("NL")) {
            countryA2Code = A2TimeZoneMapping.NL;
        } else if (country.equalsIgnoreCase("GB")) {
            countryA2Code = A2TimeZoneMapping.GB;
        } else if (country.equalsIgnoreCase("DK")) {
            countryA2Code = A2TimeZoneMapping.DK;
        } else {
            throw new RuntimeException(country + " is not supported to select time zone");
        }
        return countryA2Code;
    }


}




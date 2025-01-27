package ru.job4j.ood.srp.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XmlReportDateTimeParserFormatter implements DateTimeParser<Calendar>, DateTimeFormatter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public Calendar format(String s) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DATE_FORMAT.parse(s));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return calendar;
    }

    @Override
    public String parse(Calendar calendar) {
        return DATE_FORMAT.format(calendar.getTime());
    }
}

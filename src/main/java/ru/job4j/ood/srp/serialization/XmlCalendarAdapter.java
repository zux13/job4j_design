package ru.job4j.ood.srp.serialization;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import ru.job4j.ood.srp.formatter.XmlReportDateTimeParserFormatter;

import java.util.Calendar;

public class XmlCalendarAdapter extends XmlAdapter<String, Calendar> {

    private final XmlReportDateTimeParserFormatter parser;

    public XmlCalendarAdapter(XmlReportDateTimeParserFormatter parser) {
        this.parser = parser;
    }

    @Override
    public Calendar unmarshal(String v) throws Exception {
        return parser.format(v);
    }

    @Override
    public String marshal(Calendar v) throws Exception {
        return parser.parse(v);
    }
}

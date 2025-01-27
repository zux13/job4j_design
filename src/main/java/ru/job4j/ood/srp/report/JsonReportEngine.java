package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.serialization.JsonCalendarAdapter;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> parser;

    public JsonReportEngine(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(GregorianCalendar.class, new JsonCalendarAdapter(parser))
                .setPrettyPrinting()
                .create();
        return gson.toJson(store.findBy(filter));
    }
}

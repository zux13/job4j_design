package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ITDepartmentReport implements Report {

    private static final String SEPARATOR = ",";

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ITDepartmentReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(SEPARATOR)
                    .append(dateTimeParser.parse(employee.getHired())).append(SEPARATOR)
                    .append(dateTimeParser.parse(employee.getFired())).append(SEPARATOR)
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingReport implements Report {

    private static final Currency BASE_CURRENCY = Currency.RUB;

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter converter;
    private final Currency currency;

    public AccountingReport(Store store, DateTimeParser<Calendar> dateTimeParser, Currency currency,
                            CurrencyConverter converter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currency = currency;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(BASE_CURRENCY, employee.getSalary(), currency))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

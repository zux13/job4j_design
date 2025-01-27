package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountingReportTest {

    private DateTimeParser<Calendar> parser;
    private CurrencyConverter converter;

    @BeforeAll
    void initAll() {
        parser = new ReportDateTimeParser();
        converter = new InMemoryCurrencyConverter();
    }

    @Test
    public void whenRUBReport() {
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Oleg", now, now, 200);
        Employee worker2 = new Employee("Maria", now, now, 300);
        Store store = new MemoryStore();
        store.add(worker1);
        store.add(worker2);
        Report report = new AccountingReport(store, parser, Currency.RUB, converter);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(parser.parse(worker1.getHired())).append(" ")
                .append(parser.parse(worker1.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker1.getSalary(), Currency.RUB))
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(parser.parse(worker2.getHired())).append(" ")
                .append(parser.parse(worker2.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker2.getSalary(), Currency.RUB))
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenEURReport() {
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Oleg", now, now, 1455);
        Employee worker2 = new Employee("Maria", now, now, 4660);
        Store store = new MemoryStore();
        store.add(worker1);
        store.add(worker2);
        Report report = new AccountingReport(store, parser, Currency.EUR, converter);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(parser.parse(worker1.getHired())).append(" ")
                .append(parser.parse(worker1.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker1.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> employee.getName().equals("Oleg"))).isEqualTo(expected.toString());
    }

    @Test
    public void whenUSDReport() {
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Oleg", now, now, 111);
        Employee worker2 = new Employee("Maria", now, now, 777);
        Store store = new MemoryStore();
        store.add(worker1);
        store.add(worker2);
        Report report = new AccountingReport(store, parser, Currency.USD, converter);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(parser.parse(worker2.getHired())).append(" ")
                .append(parser.parse(worker2.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker2.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> employee.getSalary() == 777)).isEqualTo(expected.toString());
    }
}

package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ITDepartmentReportTest {

    @Test
    public void whenReportInSCVFormat() {
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        String separator = ",";
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        MemoryStore store = new MemoryStore();
        store.add(worker);
        Report report = new ITDepartmentReport(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(separator)
                .append(parser.parse(worker.getHired())).append(separator)
                .append(parser.parse(worker.getFired())).append(separator)
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportInSCVFormatWithPredicate() {
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        String separator = ",";
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Jora", now, now, 200);
        Employee worker3 = new Employee("Inna", now, now, 700);

        MemoryStore store = new MemoryStore();
        store.add(worker);
        store.add(worker2);
        store.add(worker3);

        Report report = new ITDepartmentReport(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(separator)
                .append(parser.parse(worker3.getHired())).append(separator)
                .append(parser.parse(worker3.getFired())).append(separator)
                .append(worker3.getSalary())
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> employee.getSalary() == 700)).isEqualTo(expected.toString());
    }

}

package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class HumanResourcesReportTest {

    @Test
    public void whenSalarySortedDesc() {
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Anatoly", now, now, 200);
        Employee worker3 = new Employee("Anna", now, now, 300);

        MemoryStore store = new MemoryStore();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);

        Report report = new HumanResourcesReport(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenSalarySortedDescWithPredicate() {
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Anatoly", now, now, 200);
        Employee worker3 = new Employee("Anna", now, now, 300);

        MemoryStore store = new MemoryStore();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);

        Report report = new HumanResourcesReport(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> employee.getSalary() < 300)).isEqualTo(expected.toString());
    }
}
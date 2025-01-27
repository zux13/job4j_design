package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.formatter.XmlReportDateTimeParserFormatter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.serialization.Employees;
import ru.job4j.ood.srp.serialization.XmlCalendarAdapter;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {

    private final Store store;
    private final XmlReportDateTimeParserFormatter parser;

    public XmlReportEngine(Store store, XmlReportDateTimeParserFormatter parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees();
        employees.setEmployees(store.findBy(filter));

        try (StringWriter writer = new StringWriter()) {
            marshallEmployees(employees, writer);
            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error generating XML", e);
        }
    }

    private void marshallEmployees(Employees employees, StringWriter writer) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = createMarshaller(context);
            marshaller.marshal(employees, writer);
        } catch (JAXBException e) {
            throw new RuntimeException("Error during marshalling", e);
        }
    }

    private Marshaller createMarshaller(JAXBContext context) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setAdapter(new XmlCalendarAdapter(parser));
        return marshaller;
    }
}

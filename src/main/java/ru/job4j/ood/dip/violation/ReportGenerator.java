package ru.job4j.ood.dip.violation;

class ExcelExporter {
    public void export(Data data) {
        /* Логика экспорта данных в Excel */
    }
}

class Data {
}

public class ReportGenerator {
    private ExcelExporter exporter; /* Нарушение DIP */

    public ReportGenerator() {
        this.exporter = new ExcelExporter();
    }

    public void generateReport(Data data) {
        exporter.export(data);
    }
}

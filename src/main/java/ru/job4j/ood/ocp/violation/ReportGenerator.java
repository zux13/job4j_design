package ru.job4j.ood.ocp.violation;

class ReportGenerator {

    /* Для добавления нового формата отчета потребуется изменять код. */

    public void generateReport(String format) {
        if ("PDF".equals(format)) {
            System.out.println("Generating PDF report...");
        } else if ("HTML".equals(format)) {
            System.out.println("Generating HTML report...");
        } else {
            throw new IllegalArgumentException("Unsupported format");
        }
    }
}
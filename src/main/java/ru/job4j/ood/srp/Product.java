package ru.job4j.ood.srp;

class Product {

    /* Класс выполняет вывод данных и реализует логику расчета скидки */

    public void displayProductDetails() {
        /* Логика получения данных продукта */
        System.out.println("Product Name: Example Product");
        System.out.println("Price: $100");
    }

    public void calculateDiscount() {
        /* Логика расчета скидки */
        System.out.println("Discount: $10");
    }
}

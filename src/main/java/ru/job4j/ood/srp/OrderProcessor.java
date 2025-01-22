package ru.job4j.ood.srp;

class OrderProcessor {

    /* В одном методе размещена логика получения данных из БД, обработки заказа и сохранения в БД */

    public void processOrder(int orderId) {
        /* Получение данных заказа из базы */
        System.out.println("Fetching order from database");

        /* Логика обработки заказа */
        System.out.println("Processing order: " + orderId);

        /* Сохранение изменений в базу */
        System.out.println("Saving order to database");
    }
}

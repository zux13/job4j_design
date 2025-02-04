package ru.job4j.ood.dip.violation;

class DatabaseRepository {
    public void save(Order order) {
        /* Логика сохранения заказа в базе данных */
    }
}

class Order {
}

public class OrderProcessor {
    private DatabaseRepository repository; /* Нарушение DIP */

    public OrderProcessor() {
        this.repository = new DatabaseRepository();
    }

    public void processOrder(Order order) {
        repository.save(order);
    }
}

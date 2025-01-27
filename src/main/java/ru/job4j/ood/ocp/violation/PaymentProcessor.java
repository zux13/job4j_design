package ru.job4j.ood.ocp.violation;

public class PaymentProcessor {

    /* В данном примере алгоритм обработки меняется в зависимости от метода.
    * Здесь можно использовать шаблон Стратегия с вызовом одного метода, который будет разниться от реализации.
    * Нарушение OCP заключается в необходимости изменять код при добавлении новых методов. */

    public void processPayment(String method, double amount) {
        if ("CREDIT_CARD".equals(method)) {
            System.out.println("Processing credit card payment of $" + amount);
        } else if ("PAYPAL".equals(method)) {
            System.out.println("Processing PayPal payment of $" + amount);
        } else if ("BANK_TRANSFER".equals(method)) {
            System.out.println("Processing bank transfer of $" + amount);
        } else {
            throw new IllegalArgumentException("Unknown payment method: " + method);
        }
    }
}


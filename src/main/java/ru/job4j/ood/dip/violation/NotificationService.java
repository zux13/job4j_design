package ru.job4j.ood.dip.violation;

class EmailNotifier {
    public void sendNotification(String message) {
        /* Логика отправки уведомления по email */
    }
}

public class NotificationService {
    private EmailNotifier notifier; /* Нарушение DIP */

    public NotificationService() {
        this.notifier = new EmailNotifier();
    }

    public void notifyUser(String message) {
        notifier.sendNotification(message);
    }
}

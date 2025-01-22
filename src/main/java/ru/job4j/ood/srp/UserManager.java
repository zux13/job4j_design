package ru.job4j.ood.srp;

class UserManager {

    /* Класс управляет созданием пользователей, отправляет письма, генерирует отчеты по пользователям */

    public void createUser(String name, String email) {
        /* Логика создания пользователя */
        System.out.println("User created: " + name);
    }

    public void sendWelcomeEmail(String email) {
        /* Логика отправки приветственного письма */
        System.out.println("Welcome email sent to: " + email);
    }

    public void generateUserReport() {
        /* Логика генерации отчета по пользователям */
        System.out.println("User report generated");
    }
}

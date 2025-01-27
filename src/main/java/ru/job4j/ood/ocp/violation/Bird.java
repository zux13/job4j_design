package ru.job4j.ood.ocp.violation;

/* Базовый класс для расширения Bird потребует изменений при добавлении новых действий — нарушение OCP.
* Кроме того, некоторые реализации птиц не умеют fly(), что нарушает LSP.
* Для исправления можно оставить один метод eat(), который является общим для всех живых птиц
* и добавить интерфейсы flyable(), swimable() и т.д. */

class Bird {
    public void fly() {
        System.out.println("Flying...");
    }

    public void eat() {
        System.out.println("Eating...");
    }
}
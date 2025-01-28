package ru.job4j.ood.lsp.violation;

public class Animals {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.eat("Peanut");
    }
}

class Animal {
    public void eat(String food) {
        if (food == null) {
            throw new IllegalArgumentException("Food cannot be null");
        }
        System.out.println("Eating " + food);
    }
}

class Dog extends Animal {
    @Override
    public void eat(String food) {

        /* Усиление предусловий — нарушение LSP */
        if ("Peanut".equals(food)) {
            throw new IllegalArgumentException("Every dog is allergic to peanuts!");
        }

        super.eat(food);
    }
}

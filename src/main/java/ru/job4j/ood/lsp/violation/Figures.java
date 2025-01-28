package ru.job4j.ood.lsp.violation;

class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int area() {
        return width * height;
    }
}

/* Square нарушает принцип подстановки Лисков, потому что его методы setWidth и setHeight влияют друг на друга,
* что приводит к неожиданному поведению при использовании объекта Square вместо Rectangle. */

class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}

public class Figures {
    public static void main(String[] args) {
        Rectangle rectangle = new Square();
        rectangle.setWidth(5);
        rectangle.setHeight(10);
        System.out.println(rectangle.area()); /* Ожидаем 50, но получаем 100 */
    }
}

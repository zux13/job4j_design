package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

public class Shop extends AbstractStore {
    @Override
    public boolean canAccept(Food food) {
        double percentLeft = calculatePercentLeft(food);
        return percentLeft >= 0 && percentLeft < 0.75;
    }

    @Override
    public void applyDiscount(Food food) {
        if (calculatePercentLeft(food) < 0.25) {
            food.setPrice(food.getPrice() * 0.8);
            food.setDiscount(20);
        }
    }
}

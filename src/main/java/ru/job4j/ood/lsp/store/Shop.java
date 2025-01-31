package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

public class Shop extends AbstractStore {
    @Override
    public boolean canAccept(Food food) {
        double percentLeft = calculatePercentLeft(food);
        return percentLeft >= TRASH_THRESHOLD && percentLeft < SHOP_ACCEPT_THRESHOLD;
    }

    @Override
    public void applyDiscount(Food food) {
        if (calculatePercentLeft(food) < SHOP_DISCOUNT_THRESHOLD) {
            food.setPrice(food.getPrice() * 0.8);
            food.setDiscount(20);
        }
    }
}

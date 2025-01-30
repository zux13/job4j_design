package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

public class Warehouse extends AbstractStore {
    @Override
    public boolean canAccept(Food food) {
        double percentLeft = calculatePercentLeft(food);
        return percentLeft >= 0.75;
    }
}

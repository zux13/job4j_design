package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> foods;

    public AbstractStore() {
        this.foods = new ArrayList<>();
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public void add(Food food) {
        if (canAccept(food)) {
            applyDiscount(food);
            foods.add(food);
        }
    }

    @Override
    public void remove(Food food) {
        foods.remove(food);
    }

    @Override
    public abstract boolean canAccept(Food food);

    @Override
    public void applyDiscount(Food food) {
        /* По умолчанию нет скидки */
    }

    protected double calculatePercentLeft(Food food) {
        long daysSinceCreate = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        long daysUntilExpiry = ChronoUnit.DAYS.between(LocalDate.now(), food.getExpiryDate());
        long totalDays = daysSinceCreate + daysUntilExpiry;
        return daysUntilExpiry / (double) totalDays;
    }
}

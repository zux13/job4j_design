package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.food.Food;
import ru.job4j.ood.lsp.store.Store;

import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(List<Food> foods) {
        for (Food food : foods) {
            distribute(food);
        }
    }

    private void distribute(Food food) {
        for (Store store : stores) {
            if (store.canAccept(food)) {
                store.add(food);
                break;
            }
        }
    }
}

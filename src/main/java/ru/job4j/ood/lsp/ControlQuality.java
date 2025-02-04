package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.food.Food;
import ru.job4j.ood.lsp.store.Store;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
            List<Food> storeFoods = store.getFoods();
            Iterator<Food> iterator = storeFoods.iterator();
            while (iterator.hasNext()) {
                Food food = iterator.next();
                foods.add(food);
                iterator.remove();
            }
        }
        control(foods);
    }
}

package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.food.Food;
import ru.job4j.ood.lsp.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
            ListIterator<Food> listIterator = store.getFoods().listIterator();
            while (listIterator.hasNext()) {
                foods.add(listIterator.next());
                listIterator.remove();
            }
        }
        control(foods);
    }
}

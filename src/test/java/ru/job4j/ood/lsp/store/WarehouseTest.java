package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    private Warehouse warehouse;

    @BeforeEach
    public void setUp() {
        warehouse = new Warehouse();
    }

    @Test
    public void testAddFoodWithin75Percent() {
        Food food = new Fish("Salmon",
                LocalDate.now().plusDays(60),
                LocalDate.now().minusDays(10),
                200,
                0,
                FishType.RED);
        warehouse.add(food);
        List<Food> foods = warehouse.getFoods();
        assertEquals(1, foods.size());
        assertTrue(foods.contains(food));
    }

    @Test
    public void testAddFoodBelow75Percent() {
        Food food = new Meat("Beef",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                200,
                0,
                MeatType.BEEF);
        warehouse.add(food);
        List<Food> foods = warehouse.getFoods();
        assertEquals(0, foods.size());
    }
}
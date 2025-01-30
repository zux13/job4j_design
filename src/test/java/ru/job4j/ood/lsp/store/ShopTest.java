package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {
    private Shop shop;

    @BeforeEach
    public void setUp() {
        shop = new Shop();
    }

    @Test
    public void testAddFoodBetween25And75Percent() {
        Food food = new Vegetable("Carrot",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.ROOT_VEGETABLES);
        shop.add(food);
        List<Food> foods = shop.getFoods();
        assertEquals(1, foods.size());
        assertTrue(foods.contains(food));
    }

    @Test
    public void testAddFoodBelow25PercentWithDiscount() {
        Food food = new Vegetable("Banana",
                LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.FRUITS);
        shop.add(food);
        List<Food> foods = shop.getFoods();
        assertEquals(1, foods.size());
        assertTrue(foods.contains(food));
        assertEquals(160, food.getPrice(), 0.01);
        assertEquals(20, food.getDiscount());
    }

    @Test
    public void testAddFoodAbove0Percent() {
        Food food = new Vegetable("Onion",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.ROOT_VEGETABLES);
        shop.add(food);
        List<Food> foods = shop.getFoods();
        assertEquals(0, foods.size());
    }
}
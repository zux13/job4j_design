package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrashTest {
    private Trash trash;

    @BeforeEach
    public void setUp() {
        trash = new Trash();
    }

    @Test
    public void testAddFoodBelow0Percent() {
        Food food = new Meat("Chicken",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(10),
                200,
                0,
                MeatType.CHICKEN);
        trash.add(food);
        List<Food> foods = trash.getFoods();
        assertEquals(1, foods.size());
        assertTrue(foods.contains(food));
    }

    @Test
    public void testAddFoodAbove0Percent() {
        Food food = new Meat("Pork",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                200,
                0,
                MeatType.PORK);
        trash.add(food);
        List<Food> foods = trash.getFoods();
        assertEquals(0, foods.size());
    }
}
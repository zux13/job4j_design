package ru.job4j.ood.lsp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.food.*;
import ru.job4j.ood.lsp.store.Shop;
import ru.job4j.ood.lsp.store.Store;
import ru.job4j.ood.lsp.store.Trash;
import ru.job4j.ood.lsp.store.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ControlQualityTest {
    private ControlQuality controlQuality;
    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;

    @BeforeEach
    public void setUp() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        List<Store> stores = new ArrayList<>();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        controlQuality = new ControlQuality(stores);
    }

    @Test
    public void testControlFoodWithin75Percent() {
        Food food = new Vegetable("Carrot",
                LocalDate.now().plusDays(60),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.ROOT_VEGETABLES);
        controlQuality.control(List.of(food));
        assertEquals(1, warehouse.getFoods().size());
        assertEquals(0, shop.getFoods().size());
        assertEquals(0, trash.getFoods().size());
    }

    @Test
    public void testControlFoodBetween25And75Percent() {
        Food food = new Fish("Salmon",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                200,
                0,
                FishType.RED);
        controlQuality.control(List.of(food));
        assertEquals(0, warehouse.getFoods().size());
        assertEquals(1, shop.getFoods().size());
        assertEquals(0, trash.getFoods().size());
    }

    @Test
    public void testControlFoodBelow25PercentWithDiscount() {
        Food food = new Vegetable("Banana",
                LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.FRUITS);
        controlQuality.control(List.of(food));
        assertEquals(0, warehouse.getFoods().size());
        assertEquals(1, shop.getFoods().size());
        assertEquals(0, trash.getFoods().size());
        assertEquals(160, food.getPrice(), 0.01);
        assertEquals(20, food.getDiscount());
    }

    @Test
    public void testControlFoodExpired() {
        Food food = new Meat("Beef",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(10),
                200,
                0,
                MeatType.BEEF);
        controlQuality.control(List.of(food));
        assertEquals(0, warehouse.getFoods().size());
        assertEquals(0, shop.getFoods().size());
        assertEquals(1, trash.getFoods().size());
    }

    @Test
    public void testControlMultipleFood() {
        Food beef = new Meat("Beef",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(10),
                200,
                0,
                MeatType.BEEF);
        Food banana = new Vegetable("Banana",
                LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.FRUITS);
        Food salmon = new Fish("Salmon",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                200,
                0,
                FishType.RED);
        Food carrot = new Vegetable("Carrot",
                LocalDate.now().plusDays(60),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.ROOT_VEGETABLES);
        controlQuality.control(List.of(beef, banana, salmon, carrot));
        assertEquals(1, warehouse.getFoods().size());
        assertEquals(2, shop.getFoods().size());
        assertEquals(1, trash.getFoods().size());
    }

    @Test
    public void testResortHandlesEmptyStores() {
        controlQuality.resort();

        assertEquals(0, warehouse.getFoods().size());
        assertEquals(0, shop.getFoods().size());
        assertEquals(0, trash.getFoods().size());
    }

    @Test
    public void testResortToWarehouse() {
        Food beef = new Meat("Beef",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(10),
                200,
                0,
                MeatType.BEEF);
        Food banana = new Vegetable("Banana",
                LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.FRUITS);
        Food salmon = new Fish("Salmon",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                200,
                0,
                FishType.RED);
        Food carrot = new Vegetable("Carrot",
                LocalDate.now().plusDays(60),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.ROOT_VEGETABLES);
        controlQuality.control(List.of(beef, banana, salmon, carrot));
        assertEquals(1, warehouse.getFoods().size());
        assertEquals(2, shop.getFoods().size());
        assertEquals(1, trash.getFoods().size());

        beef.setExpiryDate(LocalDate.now().plusDays(100));
        banana.setExpiryDate(LocalDate.now().plusDays(100));
        salmon.setExpiryDate(LocalDate.now().plusDays(100));
        carrot.setExpiryDate(LocalDate.now().plusDays(100));

        controlQuality.resort();

        assertEquals(4, warehouse.getFoods().size());
        assertEquals(0, shop.getFoods().size());
        assertEquals(0, trash.getFoods().size());
    }

    @Test
    public void testResortToShop() {
        Food beef = new Meat("Beef",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(10),
                200,
                0,
                MeatType.BEEF);
        Food banana = new Vegetable("Banana",
                LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.FRUITS);
        Food salmon = new Fish("Salmon",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                200,
                0,
                FishType.RED);
        Food carrot = new Vegetable("Carrot",
                LocalDate.now().plusDays(60),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.ROOT_VEGETABLES);
        controlQuality.control(List.of(beef, banana, salmon, carrot));
        assertEquals(1, warehouse.getFoods().size());
        assertEquals(2, shop.getFoods().size());
        assertEquals(1, trash.getFoods().size());

        beef.setExpiryDate(LocalDate.now().plusDays(10));
        banana.setExpiryDate(LocalDate.now().plusDays(10));
        salmon.setExpiryDate(LocalDate.now().plusDays(10));
        carrot.setExpiryDate(LocalDate.now().plusDays(10));

        controlQuality.resort();

        assertEquals(0, warehouse.getFoods().size());
        assertEquals(4, shop.getFoods().size());
        assertEquals(0, trash.getFoods().size());
    }

    @Test
    public void testResortToTrash() {
        Food beef = new Meat("Beef",
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(10),
                200,
                0,
                MeatType.BEEF);
        Food banana = new Vegetable("Banana",
                LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.FRUITS);
        Food salmon = new Fish("Salmon",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                200,
                0,
                FishType.RED);
        Food carrot = new Vegetable("Carrot",
                LocalDate.now().plusDays(60),
                LocalDate.now().minusDays(10),
                200,
                0,
                VegetableType.ROOT_VEGETABLES);
        controlQuality.control(List.of(beef, banana, salmon, carrot));
        assertEquals(1, warehouse.getFoods().size());
        assertEquals(2, shop.getFoods().size());
        assertEquals(1, trash.getFoods().size());

        beef.setExpiryDate(LocalDate.now().minusDays(1));
        banana.setExpiryDate(LocalDate.now().minusDays(1));
        salmon.setExpiryDate(LocalDate.now().minusDays(1));
        carrot.setExpiryDate(LocalDate.now().minusDays(1));

        controlQuality.resort();

        assertEquals(0, warehouse.getFoods().size());
        assertEquals(0, shop.getFoods().size());
        assertEquals(4, trash.getFoods().size());
    }

}
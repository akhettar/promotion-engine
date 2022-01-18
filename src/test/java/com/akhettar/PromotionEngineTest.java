package com.akhettar;

import com.akhettar.model.ActivePromotions;
import com.akhettar.model.Combo;
import com.akhettar.model.Item;
import com.akhettar.model.SKU;
import com.akhettar.pormotions.CombinedItemsForFixedPrice;
import com.akhettar.pormotions.NumberOfItemsForFixedPrice;
import com.akhettar.pormotions.Promotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PromotionEngineTest {

    private PromotionEngine underTest;
    private Map<String, Promotion> promotions;
    private SKU a;
    private SKU b;
    private SKU c;
    private SKU d;

    @BeforeEach
    public void setUp() {

        a = SKU.builder().ID("A").unitPrice(BigDecimal.valueOf(50)).build();
        b = SKU.builder().ID("B").unitPrice(BigDecimal.valueOf(30)).build();
        c = SKU.builder().ID("C").unitPrice(BigDecimal.valueOf(20)).build();
        d = SKU.builder().ID("D").unitPrice(BigDecimal.valueOf(15)).build();
        promotions = new HashMap<>();

        promotions.put("A", new NumberOfItemsForFixedPrice(a, 3, BigDecimal.valueOf(130)));
        promotions.put("B", new NumberOfItemsForFixedPrice(b, 2, BigDecimal.valueOf(45)));
        promotions.put("C", new CombinedItemsForFixedPrice(new Combo(SKU.builder().ID("C").build(),
                SKU.builder().ID("D").build()), BigDecimal.valueOf(30)));
        promotions.put("D", new CombinedItemsForFixedPrice(new Combo(SKU.builder().ID("D").build(),
                SKU.builder().ID("C").build()), BigDecimal.valueOf(30)));
        underTest = new PromotionEngine();
    }

    @Test
    void calculateTotalScenarioANoPromotion() {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(new Item(a, 1));
        shoppingCart.add(new Item(b, 1));
        shoppingCart.add(new Item(c, 1));

        // perform calculations
        BigDecimal got = underTest.calculateTotal(shoppingCart,
                ActivePromotions.builder().promotions(promotions).build());

        // assert the expected value
        assertEquals(BigDecimal.valueOf(100), got);
    }

    @Test
    void calculateTotalScenarioB() {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(new Item(a, 5));
        shoppingCart.add(new Item(b, 5));
        shoppingCart.add(new Item(c, 1));

        // perform calculations
        BigDecimal got = underTest.calculateTotal(shoppingCart,
                ActivePromotions.builder().promotions(promotions).build());

        // assert the expected value
        assertEquals(BigDecimal.valueOf(370), got);
    }

    @Test
    void calculateTotalScenarioC() {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(new Item(a, 3));
        shoppingCart.add(new Item(b, 5));
        shoppingCart.add(new Item(c, 1));
        shoppingCart.add(new Item(d, 1));

        // perform calculations
        BigDecimal got = underTest.calculateTotal(shoppingCart,
                ActivePromotions.builder().promotions(promotions).build());

        // assert the expected value
        assertEquals(BigDecimal.valueOf(280), got);

    }
}
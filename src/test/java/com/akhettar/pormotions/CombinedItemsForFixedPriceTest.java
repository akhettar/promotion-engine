package com.akhettar.pormotions;

import com.akhettar.model.Combo;
import com.akhettar.model.SKU;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CombinedItemsForFixedPriceTest {

    private CombinedItemsForFixedPrice underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CombinedItemsForFixedPrice(new Combo(SKU.builder()
                .ID("C")
                .unitPrice(BigDecimal.valueOf(30))
                .build(),
                SKU.builder()
                        .ID("D")
                        .unitPrice(BigDecimal.valueOf(20))
                        .build()), BigDecimal.valueOf(30));
    }

    @Test
    public void applyShouldReturnTheCorrectPromotedValueForOneUnitPromotion() {
        assertEquals(BigDecimal.valueOf(30), underTest.apply(1,1));
    }

    @Test
    public void applyShouldReturnTheCorrectPromotedValueForMoreThanOneUnitPromotionC() {
        assertEquals(BigDecimal.valueOf(90), underTest.apply(3,2));
    }

    @Test
    public void applyShouldReturnTheCorrectPromotedValueForMoreThanOneUnitPromotionD() {
        assertEquals(BigDecimal.valueOf(80), underTest.apply(2,3));
    }
}
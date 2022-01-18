package com.akhettar.pormotions;

import com.akhettar.model.SKU;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class NumberOfItemsForFixedPriceTest {

    private NumberOfItemsForFixedPrice underTest;

    @BeforeEach
    public void setUp() {
        SKU a = SKU.builder().ID("A").unitPrice(BigDecimal.valueOf(50)).build();
        underTest = new NumberOfItemsForFixedPrice(a, 3, BigDecimal.valueOf(130));
    }
    @Test
    void applyShouldReturnTheCorrectPromotionValue() {
        assertEquals(130, underTest.apply(3).doubleValue());
        assertEquals(260, underTest.apply(6).doubleValue());
        assertEquals(310, underTest.apply(7).doubleValue());
    }
}
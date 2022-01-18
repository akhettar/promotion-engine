package com.akhettar;


import model.Item;
import model.SKU;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart underTest;


    @BeforeEach
    public void setUp() {
        underTest = new ShoppingCart();
        underTest.add(new Item(SKU.builder().ID("A").build(), 5));
    }

    @Test
    void getItems() {
        assertTrue(underTest.getItems().contains(new Item(SKU.builder().ID("A").build(), 5)));
    }

    @Test
    void addItems() {
        underTest.add(new Item(SKU.builder().ID("B").build(), 6));
        underTest.add(new Item(SKU.builder().ID("C").build(), 6));
        assertTrue(underTest.getItems().stream()
                .filter(item -> item.getSku().getID().equals("B")).findFirst().isPresent());
        assertTrue(underTest.getItems().stream()
                .filter(item -> item.getSku().getID().equals("B")).findFirst().isPresent());
    }
}
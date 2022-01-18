package com.akhettar;

import com.akhettar.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * ShoppingCart holding list of items to be purchased
 */
public class ShoppingCart {
    private final List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    public void remote(final Item item) {
        this.items.remove(item);
    }
}


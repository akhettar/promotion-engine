package model;

import lombok.Data;

@Data
public class Item {
    private final SKU sku;
    private final int quantity;
}

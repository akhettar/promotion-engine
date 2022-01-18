package com.akhettar.pormotions;

import com.akhettar.model.SKU;

import java.math.BigDecimal;

/**
 * Number of Items for fixed price promotion
 */
public class NumberOfItemsForFixedPrice implements Promotion {
    private final SKU sku;
    private final int n;
    private final BigDecimal price;

    public NumberOfItemsForFixedPrice(final SKU sku, final int n, final BigDecimal price) {
        this.sku = sku;
        this.n = n;
        this.price = price;
    }

    @Override
    public BigDecimal apply(int... quantity) {
        int promoted = quantity[0] / n;
        int unitPriceApplied = quantity[0] % n;
        return price.multiply(BigDecimal.valueOf(promoted)).
                add(sku.getUnitPrice().multiply(BigDecimal.valueOf(unitPriceApplied)));
    }
}

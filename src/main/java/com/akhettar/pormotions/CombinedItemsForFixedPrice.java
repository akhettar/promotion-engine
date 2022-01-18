package com.akhettar.pormotions;


import com.akhettar.model.Combo;

import java.math.BigDecimal;

/**
 * Implementation Combined Item offers
 */
public class CombinedItemsForFixedPrice implements Promotion {

    private final Combo combo;
    private final BigDecimal price;

    public CombinedItemsForFixedPrice(final Combo combo, final BigDecimal price) {
        this.combo = combo;
        this.price = price;
    }

    public Combo getCombo() {
        return combo;
    }
    @Override
    public BigDecimal apply(final int... quantity) {
        int a = quantity[0];
        int b = quantity[1];
        if (a > b) {
            return price.multiply(BigDecimal.valueOf(b))
                    .add(combo.getA().getUnitPrice()
                            .multiply(BigDecimal.valueOf(a - b)));

        } else if (b > a){
            return price.multiply(BigDecimal.valueOf(a))
                    .add(combo.getB().getUnitPrice()
                            .multiply(BigDecimal.valueOf(b - a)));
        }
        return price.multiply(BigDecimal.valueOf(b));
    }
}

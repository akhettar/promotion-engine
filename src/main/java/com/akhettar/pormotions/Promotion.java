package com.akhettar.pormotions;


import java.math.BigDecimal;

public interface Promotion {

    BigDecimal apply(int... quantity);
}

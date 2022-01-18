package com.akhettar.model;


import java.util.Objects;

public class Combo {


    private final SKU a;
    private final SKU b;

    public Combo(SKU a, SKU b) {
        this.a = a;
        this.b = b;
    }

    public SKU getA() {
        return a;
    }

    public SKU getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combo combo = (Combo) o;
        return (a.getID().equals(combo.a.getID()) && b.getID().equals(combo.b.getID())) ||
                (a.getID().equals(combo.b.getID()) && b.getID().equals(combo.a.getID()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}

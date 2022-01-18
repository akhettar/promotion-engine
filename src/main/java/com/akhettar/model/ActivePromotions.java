package com.akhettar.model;

import com.akhettar.pormotions.Promotion;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder(toBuilder = true)
public class ActivePromotions {
    @Builder.Default
    private final Map<String, Promotion> promotions = new HashMap<>();
}

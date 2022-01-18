package model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class SKU {
    private final String ID;
    private final String description;
    private final BigDecimal unitPrice;
}

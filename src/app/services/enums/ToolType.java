package app.services.enums;

import java.math.BigDecimal;

//TODO: might be better suited as a class? In real life this is probably an entity
public enum ToolType {
    CHAINSAW(new BigDecimal("1.49")),
    LADDER(new BigDecimal("1.99")),
    JACKHAMMER(new BigDecimal("2.99"));
    private final BigDecimal dailyRentalValue;

    ToolType(BigDecimal dailyRentalValue) {
        this.dailyRentalValue = dailyRentalValue;
    }

    public BigDecimal getDailyRentalValue() {
        return dailyRentalValue;
    }
}

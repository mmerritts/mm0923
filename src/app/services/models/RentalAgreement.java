package app.services.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {
    private final CheckoutRequest checkoutRequest;

    public RentalAgreement(CheckoutRequest checkoutRequest) {
        this.checkoutRequest = checkoutRequest;
    }

    private BigDecimal getDailyRentalCharge() {
        return checkoutRequest.getTool().getToolType().getDailyRentalValue();
    }

    private LocalDate getDueDate() {
        return checkoutRequest.getCheckOutDate().plusDays(checkoutRequest.getRentalDayCount());
    }

    private int getChargeDays() {
        //TODO: determine how many non-charge days to remove from getRentalDayCount()
        return checkoutRequest.getRentalDayCount();
    }

    private BigDecimal getPreDiscountCharge() {
        return getDailyRentalCharge()
                .multiply(BigDecimal.valueOf(getChargeDays()))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getDiscountAmount() {
        return getPreDiscountCharge()
                .multiply(BigDecimal.valueOf(((long) checkoutRequest.getDiscountPercent()/100)))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getFinalCharge() {
        return getPreDiscountCharge().subtract(getDiscountAmount());
    }
}

package app.services;

import app.models.CheckoutRequest;
import app.views.RentalAgreementView;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreementService {
    private final HolidayService holidayService = new HolidayService();

    public RentalAgreementService() {
    }

    public RentalAgreementView processCheckoutRequest(CheckoutRequest checkoutRequest) {
        return new RentalAgreementView(
                checkoutRequest.getTool().getToolCode(),
                checkoutRequest.getTool().getToolType(),
                checkoutRequest.getTool().getBrand(),
                checkoutRequest.getRentalDayCount(),
                checkoutRequest.getCheckOutDate(),
                getDueDate(checkoutRequest),
                checkoutRequest.getTool().getToolRentalInfo().getDailyCharge(),
                getChargeDays(checkoutRequest),
                getPreDiscountCharge(checkoutRequest),
                checkoutRequest.getDiscountPercent(),
                getDiscountAmount(checkoutRequest),
                getFinalCharge(checkoutRequest));
    }

    private LocalDate getDueDate(CheckoutRequest checkoutRequest) {
        return checkoutRequest.getCheckOutDate().plusDays(checkoutRequest.getRentalDayCount());
    }

    private int getChargeDays(CheckoutRequest checkoutRequest) {
        int chargeDays = 0;
        for(int i = 1; i <= checkoutRequest.getRentalDayCount(); i++) {
            var day = checkoutRequest.getCheckOutDate().plusDays(i);

            // skip holidays
            if (!checkoutRequest.getTool().getToolRentalInfo().isHolidayCharge() && holidayService.isDateAHoliday(day)) {
                continue;
            }

            // skip weekday
            if (!checkoutRequest.getTool().getToolRentalInfo().isWeekdayCharge() && !holidayService.isDateAWeekEnd(day)) {
                continue;
            }

            // skip weekend
            if (!checkoutRequest.getTool().getToolRentalInfo().isWeekendCharge() && holidayService.isDateAWeekEnd(day)) {
                continue;
            }

            chargeDays++;
        }
        return chargeDays;
    }

    //TODO: remove getChargeDays() and replace with actual value so charge days
    // isn't calculated twice. Already called in processCheckoutRequest(...)
    private BigDecimal getPreDiscountCharge(CheckoutRequest checkoutRequest) {
        return checkoutRequest.getTool().getToolRentalInfo().getDailyCharge()
                .multiply(BigDecimal.valueOf(getChargeDays(checkoutRequest)))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getDiscountAmount(CheckoutRequest checkoutRequest) {
        double discountPercent = checkoutRequest.getDiscountPercent() * 0.01;
        return getPreDiscountCharge(checkoutRequest)
                .multiply(BigDecimal.valueOf(discountPercent))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getFinalCharge(CheckoutRequest checkoutRequest) {
        return getPreDiscountCharge(checkoutRequest).subtract(getDiscountAmount(checkoutRequest));
    }
}

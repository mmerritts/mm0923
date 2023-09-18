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
        var chargeDays = getChargeDays(checkoutRequest);

        return new RentalAgreementView(
                checkoutRequest.getTool().getToolCode(),
                checkoutRequest.getTool().getToolType(),
                checkoutRequest.getTool().getBrand(),
                checkoutRequest.getRentalDayCount(),
                checkoutRequest.getCheckOutDate(),
                getDueDate(checkoutRequest),
                checkoutRequest.getTool().getToolRentalInfo().getDailyCharge(),
                chargeDays,
                getPreDiscountCharge(checkoutRequest, chargeDays),
                checkoutRequest.getDiscountPercent(),
                getDiscountAmount(checkoutRequest, chargeDays),
                getFinalCharge(checkoutRequest, chargeDays));
    }

    private LocalDate getDueDate(CheckoutRequest checkoutRequest) {
        return checkoutRequest.getCheckOutDate().plusDays(checkoutRequest.getRentalDayCount());
    }

    private int getChargeDays(CheckoutRequest checkoutRequest) {
        int chargeDays = 0;
        for (int i = 1; i <= checkoutRequest.getRentalDayCount(); i++) {
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

    private BigDecimal getPreDiscountCharge(CheckoutRequest checkoutRequest, int chargeDays) {
        return checkoutRequest.getTool().getToolRentalInfo().getDailyCharge()
                .multiply(BigDecimal.valueOf(chargeDays))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getDiscountAmount(CheckoutRequest checkoutRequest, int chargeDays) {
        double discountPercent = checkoutRequest.getDiscountPercent() * 0.01;
        return getPreDiscountCharge(checkoutRequest, chargeDays)
                .multiply(BigDecimal.valueOf(discountPercent))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getFinalCharge(CheckoutRequest checkoutRequest, int chargeDays) {
        return getPreDiscountCharge(checkoutRequest, chargeDays).subtract(getDiscountAmount(checkoutRequest, chargeDays));
    }
}

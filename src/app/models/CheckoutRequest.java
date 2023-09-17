package app.models;

import app.models.tools.Tool;

import java.time.LocalDate;
import java.util.Objects;

public class CheckoutRequest {
    private final Tool tool;
    private final int rentalDayCount;
    private final int discountPercent;
    private final LocalDate checkOutDate;

    public CheckoutRequest(Tool tool, int rentalDayCount, int discountPercent, LocalDate checkOutDate) {
        validateCheckoutRequestArguments(tool, rentalDayCount, discountPercent, checkOutDate);
        this.tool = tool;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkOutDate = checkOutDate;
    }

    private static void validateCheckoutRequestArguments(Tool tool, int rentalDayCount, int discountPercent, LocalDate checkOutDate) {
        Objects.requireNonNull(tool);
        Objects.requireNonNull(checkOutDate);
        if (rentalDayCount <= 0) {
            throw new IllegalArgumentException("The number of rental days requested is invalid." +
                    " The days requested was " + rentalDayCount + ", but must be number greater than 0");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("The discount percent requested is invalid . " +
                    "The discount percent requested was " + discountPercent +
                    ", but must be number  within the range of 0 - 100");
        }
    }

    public Tool getTool() {
        return tool;
    }

    public int getRentalDayCount() {
        return rentalDayCount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
}

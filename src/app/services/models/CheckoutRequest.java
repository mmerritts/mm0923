package app.services.models;

import java.time.LocalDate;
import java.util.Objects;

public class CheckoutRequest {
    private final Tool tool;
    private final int rentalDayCount;
    private final int discountPercent;
    private final LocalDate checkOutDate;

    public CheckoutRequest(Tool tool, int rentalDayCount, int discountPercent, LocalDate checkOutDate) {
        Objects.requireNonNull(tool);
        Objects.requireNonNull(checkOutDate);
        this.tool = tool;
        //TODO: throw exception if rentalDayCount is not valid
        this.rentalDayCount = rentalDayCount;
        //TODO: throw exception if discountPrecent is not valid
        this.discountPercent = discountPercent;
        this.checkOutDate = checkOutDate;
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

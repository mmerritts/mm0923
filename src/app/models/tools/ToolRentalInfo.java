package app.models.tools;

import java.math.BigDecimal;
import java.util.Objects;

public class ToolRentalInfo {
    private final BigDecimal dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    public ToolRentalInfo(BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        validateDailyCharge(dailyCharge);
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    private static void validateDailyCharge(BigDecimal dailyCharge) {
        Objects.requireNonNull(dailyCharge);
        if (dailyCharge.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Daily Charge request was invalid" +
                    "The Daily Charge request was " + dailyCharge +
                    ". Daily Charge cannot be less than 0");
        }
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}

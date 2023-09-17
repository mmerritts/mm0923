package app.views;

import app.enums.Brand;
import app.enums.ToolType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreementView {
    private final String toolCode;
    private final ToolType toolType;
    private final Brand toolBrand;
    private final int rentalDays;
    private final LocalDate checkOutDate;
    private final LocalDate dueDate;
    private final BigDecimal dailyRentalCharge;
    private final int chargeDays;
    private final BigDecimal preDiscountCharge;
    private final int discountPercent;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;

    public RentalAgreementView(String toolCode,
                               ToolType toolType,
                               Brand toolBrand,
                               int rentalDays,
                               LocalDate checkOutDate,
                               LocalDate dueDate,
                               BigDecimal dailyRentalCharge,
                               int chargeDays,
                               BigDecimal preDiscountCharge,
                               int discountPercent,
                               BigDecimal discountAmount,
                               BigDecimal finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public void printView() {
        System.out.println("Tool Code: " + toolCode);
        System.out.println("Tool Type: " + toolType);
        System.out.println("Tool Brand: " + toolBrand);
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Checkout Date: " + checkOutDate);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Daily Rental Charge: $" + dailyRentalCharge);
        System.out.println("Charge Days: " + chargeDays);
        System.out.println("Pre-Discount Charge: $" + preDiscountCharge);
        System.out.println("Discount Percent: " + discountPercent);
        System.out.println("Discount Amount: $" + discountAmount);
        System.out.println("Final Charge: $" + finalCharge);
    }

    public String getToolCode() {
        return toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public Brand getToolBrand() {
        return toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }
}

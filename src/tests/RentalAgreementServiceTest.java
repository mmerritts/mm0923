package tests;

import app.enums.Brand;
import app.enums.ToolType;
import app.models.CheckoutRequest;
import app.services.RentalAgreementService;
import app.services.ToolCreationService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RentalAgreementServiceTest {
    private final RentalAgreementService rentalAgreementService = new RentalAgreementService();
    private final ToolCreationService toolCreationService = new ToolCreationService();

    @Test // Test1
    void testCreateRequestWithDiscountOver100Percent() {
        var testTool = toolCreationService.getToolByToolCode("JAKR");

        assertThrows(
                IllegalArgumentException.class,
                () -> new CheckoutRequest(
                        testTool,
                        5,
                        101,
                        LocalDate.of(2015, 9, 3)));
    }

    @Test // Test2
    void testCheckoutWernerLadderOverFourthOfJulyWeekendWith10Discount() {
        var testTool = toolCreationService.getToolByToolCode("LADW");
        var testCheckoutRequest = new CheckoutRequest(testTool, 3, 10, LocalDate.of(2020, 7, 2));
        var testRentalAgreement = rentalAgreementService.processCheckoutRequest(testCheckoutRequest);

        testRentalAgreement.printView();
        assertEquals("LADW", testRentalAgreement.getToolCode());
        assertEquals(ToolType.LADDER, testRentalAgreement.getToolType());
        assertEquals(Brand.WERNER, testRentalAgreement.getToolBrand());
        assertEquals(3, testRentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), testRentalAgreement.getCheckOutDate());
        assertEquals(LocalDate.of(2020, 7, 5), testRentalAgreement.getDueDate());
        assertEquals(BigDecimal.valueOf(1.99), testRentalAgreement.getDailyRentalCharge());
        assertEquals(2, testRentalAgreement.getChargeDays());
        assertEquals(BigDecimal.valueOf(3.98), testRentalAgreement.getPreDiscountCharge());
        assertEquals(10, testRentalAgreement.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(0.40).setScale(2), testRentalAgreement.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(3.58), testRentalAgreement.getFinalCharge());
    }

    @Test // Test3
    void testCheckoutStihlChainsawOverFourthOfJulyWeekendWith25Discount() {
        var testTool = toolCreationService.getToolByToolCode("CHNS");
        var testCheckoutRequest = new CheckoutRequest(testTool, 5, 25, LocalDate.of(2015, 7, 2));
        var testRentalAgreement = rentalAgreementService.processCheckoutRequest(testCheckoutRequest);

        testRentalAgreement.printView();
        assertEquals("CHNS", testRentalAgreement.getToolCode());
        assertEquals(ToolType.CHAINSAW, testRentalAgreement.getToolType());
        assertEquals(Brand.STIHL, testRentalAgreement.getToolBrand());
        assertEquals(5, testRentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), testRentalAgreement.getCheckOutDate());
        assertEquals(LocalDate.of(2015, 7, 7), testRentalAgreement.getDueDate());
        assertEquals(BigDecimal.valueOf(1.49), testRentalAgreement.getDailyRentalCharge());
        assertEquals(3, testRentalAgreement.getChargeDays());
        assertEquals(BigDecimal.valueOf(4.47), testRentalAgreement.getPreDiscountCharge());
        assertEquals(25, testRentalAgreement.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(1.12), testRentalAgreement.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(3.35), testRentalAgreement.getFinalCharge());
    }

    @Test // Test4
    void testCheckoutDeWaltJackhammerOverLaborDayWeekendNoDiscount(){
        var testTool = toolCreationService.getToolByToolCode("JAKD");
        var testCheckoutRequest = new CheckoutRequest(testTool, 6, 0, LocalDate.of(2015, 9, 3));
        var testRentalAgreement = rentalAgreementService.processCheckoutRequest(testCheckoutRequest);

        testRentalAgreement.printView();
        assertEquals("JAKD", testRentalAgreement.getToolCode());
        assertEquals(ToolType.JACKHAMMER, testRentalAgreement.getToolType());
        assertEquals(Brand.DEWALT, testRentalAgreement.getToolBrand());
        assertEquals(6, testRentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 3), testRentalAgreement.getCheckOutDate());
        assertEquals(LocalDate.of(2015, 9, 9), testRentalAgreement.getDueDate());
        assertEquals(BigDecimal.valueOf(2.99), testRentalAgreement.getDailyRentalCharge());
        assertEquals(3, testRentalAgreement.getChargeDays());
        assertEquals(BigDecimal.valueOf(8.97), testRentalAgreement.getPreDiscountCharge());
        assertEquals(0, testRentalAgreement.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(0).setScale(2), testRentalAgreement.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(8.97), testRentalAgreement.getFinalCharge());
    }

    @Test // Test5
    void testCheckoutRidgidJackhammerOverFourthJulyWeekendNoDiscount(){
        var testTool = toolCreationService.getToolByToolCode("JAKR");
        var testCheckoutRequest = new CheckoutRequest(testTool, 9, 0, LocalDate.of(2015, 7, 2));
        var testRentalAgreement = rentalAgreementService.processCheckoutRequest(testCheckoutRequest);

        testRentalAgreement.printView();
        assertEquals("JAKR", testRentalAgreement.getToolCode());
        assertEquals(ToolType.JACKHAMMER, testRentalAgreement.getToolType());
        assertEquals(Brand.RIDGID, testRentalAgreement.getToolBrand());
        assertEquals(9, testRentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), testRentalAgreement.getCheckOutDate());
        assertEquals(LocalDate.of(2015, 7, 11), testRentalAgreement.getDueDate());
        assertEquals(BigDecimal.valueOf(2.99), testRentalAgreement.getDailyRentalCharge());
        assertEquals(5, testRentalAgreement.getChargeDays());
        assertEquals(BigDecimal.valueOf(14.95), testRentalAgreement.getPreDiscountCharge());
        assertEquals(0, testRentalAgreement.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(0).setScale(2), testRentalAgreement.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(14.95), testRentalAgreement.getFinalCharge());
    }

    @Test // Test6
    void testCheckoutRidgidJackhammerOverFourthJulyWeekend50Discount(){
        var testTool = toolCreationService.getToolByToolCode("JAKR");
        var testCheckoutRequest = new CheckoutRequest(testTool, 4, 50, LocalDate.of(2020, 7, 2));
        var testRentalAgreement = rentalAgreementService.processCheckoutRequest(testCheckoutRequest);

        testRentalAgreement.printView();
        assertEquals("JAKR", testRentalAgreement.getToolCode());
        assertEquals(ToolType.JACKHAMMER, testRentalAgreement.getToolType());
        assertEquals(Brand.RIDGID, testRentalAgreement.getToolBrand());
        assertEquals(4, testRentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), testRentalAgreement.getCheckOutDate());
        assertEquals(LocalDate.of(2020, 7, 6), testRentalAgreement.getDueDate());
        assertEquals(BigDecimal.valueOf(2.99), testRentalAgreement.getDailyRentalCharge());
        assertEquals(1, testRentalAgreement.getChargeDays());
        assertEquals(BigDecimal.valueOf(2.99), testRentalAgreement.getPreDiscountCharge());
        assertEquals(50, testRentalAgreement.getDiscountPercent());
        assertEquals(BigDecimal.valueOf(1.5).setScale(2), testRentalAgreement.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(1.49), testRentalAgreement.getFinalCharge());
    }
}
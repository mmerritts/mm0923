package tests;

import app.models.CheckoutRequest;
import app.services.ToolCreationService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutRequestTest {
    private final ToolCreationService toolCreationService = new ToolCreationService();

    @Test
    void testCheckoutRequestNullTool() {
        assertThrows(NullPointerException.class, () -> new CheckoutRequest(null, 4, 20, LocalDate.now()));
    }

    @Test
    void testCheckoutNullRequestDate() {
        var testTool = toolCreationService.getToolByToolCode("JAKR");
        assertThrows(NullPointerException.class, () -> new CheckoutRequest(testTool, 4, 20, null));
    }

    @Test
    void testCheckoutRequestRentalDayCountIsZero() {
        var testTool = toolCreationService.getToolByToolCode("JAKR");
        assertThrows(IllegalArgumentException.class, () -> new CheckoutRequest(testTool, 0, 20, LocalDate.now()));
    }

    @Test
    void testCheckoutRequestDiscountPercentIsLessThanZero() {
        var testTool = toolCreationService.getToolByToolCode("JAKR");
        assertThrows(IllegalArgumentException.class, () -> new CheckoutRequest(testTool, 7, -1, LocalDate.now()));
    }

    @Test
    void testCheckoutRequestDiscountPercentIsAboveOneHundred() {
        var testTool = toolCreationService.getToolByToolCode("JAKR");
        assertThrows(IllegalArgumentException.class, () -> new CheckoutRequest(testTool, 5, 101, LocalDate.now()));
    }

    @Test
    void testCheckoutRequestIsCorrectWithValidInput() {
        var testTool = toolCreationService.getToolByToolCode("JAKR");
        var testCheckoutRequest = new CheckoutRequest(testTool, 7, 5, LocalDate.now());

        assertEquals(7, testCheckoutRequest.getRentalDayCount());
        assertEquals(5, testCheckoutRequest.getDiscountPercent());
        assertEquals(LocalDate.now(), testCheckoutRequest.getCheckOutDate());
    }
}
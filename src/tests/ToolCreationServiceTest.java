package tests;

import app.enums.Brand;
import app.enums.ToolType;
import app.services.ToolCreationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ToolCreationServiceTest {
    private final ToolCreationService toolCreationService = new ToolCreationService();

    @ParameterizedTest
    @ValueSource(strings = {"CHNR", "LADD", "JAKS", "JAKW"})
    void testInvalidCodes(String toolCode) {
        assertThrows(IllegalArgumentException.class, () -> toolCreationService.getToolByToolCode(toolCode));
    }

    @Test
    void testStihlChainsaw() {
        var testTool = toolCreationService.getToolByToolCode("CHNS");

        assertEquals(ToolType.CHAINSAW, testTool.getToolType());
        assertEquals(Brand.STIHL, testTool.getBrand());
        assertEquals(BigDecimal.valueOf(1.49), testTool.getToolRentalInfo().getDailyCharge());
        assertTrue(testTool.getToolRentalInfo().isWeekdayCharge());
        assertFalse(testTool.getToolRentalInfo().isWeekendCharge());
        assertTrue(testTool.getToolRentalInfo().isHolidayCharge());
    }

    @Test
    void testWernerLadder() {
        var testTool = toolCreationService.getToolByToolCode("LADW");

        assertEquals(ToolType.LADDER, testTool.getToolType());
        assertEquals(Brand.WERNER, testTool.getBrand());
        assertEquals(BigDecimal.valueOf(1.99), testTool.getToolRentalInfo().getDailyCharge());
        assertTrue(testTool.getToolRentalInfo().isWeekdayCharge());
        assertTrue(testTool.getToolRentalInfo().isWeekendCharge());
        assertFalse(testTool.getToolRentalInfo().isHolidayCharge());
    }

    @Test
    void testDeWaltJackHammer() {
        var testTool = toolCreationService.getToolByToolCode("JAKD");

        assertEquals(ToolType.JACKHAMMER, testTool.getToolType());
        assertEquals(Brand.DEWALT, testTool.getBrand());
        assertEquals(BigDecimal.valueOf(2.99), testTool.getToolRentalInfo().getDailyCharge());
        assertTrue(testTool.getToolRentalInfo().isWeekdayCharge());
        assertFalse(testTool.getToolRentalInfo().isWeekendCharge());
        assertFalse(testTool.getToolRentalInfo().isHolidayCharge());
    }

    @Test
    void testRidgidJackhammer() {
        var testTool = toolCreationService.getToolByToolCode("JAKR");

        assertEquals(ToolType.JACKHAMMER, testTool.getToolType());
        assertEquals(Brand.RIDGID, testTool.getBrand());
        assertEquals(BigDecimal.valueOf(2.99), testTool.getToolRentalInfo().getDailyCharge());
        assertTrue(testTool.getToolRentalInfo().isWeekdayCharge());
        assertFalse(testTool.getToolRentalInfo().isWeekendCharge());
        assertFalse(testTool.getToolRentalInfo().isHolidayCharge());
    }
}
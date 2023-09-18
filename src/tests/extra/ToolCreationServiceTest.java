package tests.extra;

import app.enums.Brand;
import app.enums.ToolType;
import app.services.ToolCreationService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToolCreationServiceTest {
    private final ToolCreationService toolCreationService = new ToolCreationService();

    @ParameterizedTest
    @ValueSource(strings = {"CHNR", "LADD", "JAKS", "JAKW"})
    void testInvalidCodes(String toolCode) {
        assertThrows(IllegalArgumentException.class, () -> toolCreationService.getToolByToolCode(toolCode));
    }

    @ParameterizedTest
    @MethodSource("generateExpectedBrandTypeAndDailyCharge")
    void testAllValidTools(String toolCode,
                           String expectedToolType,
                           String expectedToolBrand,
                           BigDecimal expectedDailyCharge,
                           boolean expectedWeekdayCharge,
                           boolean expectedWeekendCharge,
                           boolean expectedHolidayCharge) {
        var testTool = toolCreationService.getToolByToolCode(toolCode);

        assertEquals(expectedToolType, testTool.getToolType().toString());
        assertEquals(expectedToolBrand, testTool.getBrand().toString());
        assertEquals(expectedDailyCharge, testTool.getToolRentalInfo().getDailyCharge());
        assertEquals(expectedWeekdayCharge, testTool.getToolRentalInfo().isWeekdayCharge());
        assertEquals(expectedWeekendCharge, testTool.getToolRentalInfo().isWeekendCharge());
        assertEquals(expectedHolidayCharge, testTool.getToolRentalInfo().isHolidayCharge());
    }

    public static Stream<Arguments> generateExpectedBrandTypeAndDailyCharge() {
        return Stream.of(
                Arguments.of("CHNS", ToolType.CHAINSAW.toString(), Brand.STIHL.toString(), BigDecimal.valueOf(1.49), true, false, true),
                Arguments.of("LADW", ToolType.LADDER.toString(), Brand.WERNER.toString(), BigDecimal.valueOf(1.99), true, true, false),
                Arguments.of("JAKD", ToolType.JACKHAMMER.toString(), Brand.DEWALT.toString(), BigDecimal.valueOf(2.99), true, false, false),
                Arguments.of("JAKR", ToolType.JACKHAMMER.toString(), Brand.RIDGID.toString(), BigDecimal.valueOf(2.99), true, false, false)
        );
    }

}
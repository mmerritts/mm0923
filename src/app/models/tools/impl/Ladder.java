package app.models.tools.impl;

import app.enums.Brand;
import app.enums.ToolType;
import app.models.tools.Tool;
import app.models.tools.ToolRentalInfo;

import java.math.BigDecimal;

public class Ladder extends Tool {
    public Ladder(String toolCode, ToolType toolType, Brand brand) {
        super(toolCode, toolType, brand);
        ToolRentalInfo toolRentalInfo = new ToolRentalInfo(BigDecimal.valueOf(1.99), true, true, false);
        setToolRentalInfo(toolRentalInfo);
    }
}

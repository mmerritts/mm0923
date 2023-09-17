package app.models.tools;

import app.enums.Brand;
import app.enums.ToolType;

import java.util.Objects;

public abstract class Tool {
    private final String toolCode;
    private final ToolType toolType;
    private final Brand brand;

    private ToolRentalInfo toolRentalInfo;

    public Tool(String toolCode, ToolType toolType, Brand brand) {
        validateToolArguments(toolCode, toolType, brand);
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    private static void validateToolArguments(String toolCode, ToolType toolType, Brand brand) {
        Objects.requireNonNull(toolCode);
        Objects.requireNonNull(toolType);
        Objects.requireNonNull(brand);
    }

    public String getToolCode() {
        return toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public Brand getBrand() {
        return brand;
    }

    public ToolRentalInfo getToolRentalInfo() {
        return toolRentalInfo;
    }

    public void setToolRentalInfo(ToolRentalInfo toolRentalInfo) {
        Objects.requireNonNull(toolRentalInfo);
        this.toolRentalInfo = toolRentalInfo;
    }
}

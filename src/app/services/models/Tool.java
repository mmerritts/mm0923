package app.services.models;

import app.services.enums.Brand;
import app.services.enums.ToolType;

import java.util.Objects;

public class Tool {
    private final String toolCode;
    private final ToolType toolType;
    private final Brand brand;

    public Tool(String toolCode, ToolType toolType, Brand brand) {
        Objects.requireNonNull(toolCode);
        Objects.requireNonNull(toolType);
        Objects.requireNonNull(brand);
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
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
}

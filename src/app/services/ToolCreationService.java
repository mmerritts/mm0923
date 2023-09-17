package app.services;

import app.enums.Brand;
import app.enums.ToolType;
import app.models.tools.Tool;
import app.models.tools.impl.Chainsaw;
import app.models.tools.impl.Jackhammer;
import app.models.tools.impl.Ladder;

import java.util.HashMap;
import java.util.Map;

public class ToolCreationService {
    Map<String, Tool> mockToolDatabase;

    public ToolCreationService() {
        this.mockToolDatabase = populateMockDb();
    }

    private static Map<String, Tool> populateMockDb() {
        Map<String, Tool> toolFactory = new HashMap<>();
        var sthilChainSaw = new Chainsaw("CHNS", ToolType.CHAINSAW, Brand.STIHL);
        toolFactory.put("CHNS", sthilChainSaw);
        var wernerLadder = new Ladder("LADW", ToolType.LADDER, Brand.WERNER);
        toolFactory.put("LADW", wernerLadder);
        var deWaltJackhammer = new Jackhammer("JAKD", ToolType.JACKHAMMER, Brand.DEWALT);
        toolFactory.put("JAKD", deWaltJackhammer);
        var ridgidJackhammer = new Jackhammer("JAKR", ToolType.JACKHAMMER, Brand.RIDGID);
        toolFactory.put("JAKR", ridgidJackhammer);
        return toolFactory;
    }

    public Tool getToolByToolCode(String toolCode) {
        if (mockToolDatabase.containsKey(toolCode)) {
            return mockToolDatabase.get(toolCode);
        }

        throw new IllegalArgumentException("Tool Code could not be found.");
    }
}

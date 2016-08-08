/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUICommandField;
import gui.components.GUILabel;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import org.newdawn.slick.Color;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;

/**
 *
 * @author bhargav
 */
public class PresetSpec extends ComponentInputGUI {

    private int type;
    private String typeStr;
    private NewPreset parent;

    public PresetSpec(String n, int t, NewPreset parent) {
        super(n);
        type = t;
        this.parent = parent;
        switch (type) {
            case 0:
                typeStr = "creature";
                inputs.add(new GUIButton("eCostSetVisible", this, nextPlace(parent.getStartPos(), 3, -4), BUTTON_SIZE, "Energy Costs", Color.white));
                //ENERGY COSTS
                components.add(new GUILabel("eCostHuntLabel", nextPlace(parent.getStartPos(), 4, -4), BUTTON_SIZE.multiply(new Vec2(0.5)), "Hunting", Color.white));
                inputs.add(new GUICommandField("eCostHuntField",this,nextPlace(parent.getStartPos(), 4, -4).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("eCostForageLabel", nextPlace(parent.getStartPos(), 4, -3), BUTTON_SIZE.multiply(new Vec2(0.5)), "Foraging", Color.white));
                inputs.add(new GUICommandField("eCostForageField",this,nextPlace(parent.getStartPos(), 4, -3).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("eCostReproLabel", nextPlace(parent.getStartPos(), 4, -2), BUTTON_SIZE.multiply(new Vec2(0.5)), "Reproduction", Color.white)); 
                inputs.add(new GUICommandField("eCostReproField",this,nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("eCostDetectLabel", nextPlace(parent.getStartPos(), 4, -1), BUTTON_SIZE.multiply(new Vec2(0.5)), "Detection", Color.white));
                inputs.add(new GUICommandField("eCostDetectField",this,nextPlace(parent.getStartPos(), 4, -1).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("eCostMoveLabel", nextPlace(parent.getStartPos(), 4, 0), BUTTON_SIZE.multiply(new Vec2(0.5)), "Movement", Color.white)); 
                inputs.add(new GUICommandField("eCostMoveField",this,nextPlace(parent.getStartPos(), 4, 0).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                // </energyCosts>
                //THRESHOLDS
                components.add(new GUILabel("reproThreshLabel", nextPlace(parent.getStartPos(), 3, -4), BUTTON_SIZE.multiply(new Vec2(0.5)), "Reproduction Threshold", Color.white));
                inputs.add(new GUICommandField("reproThreshField",this,nextPlace(parent.getStartPos(), 3, -4).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("hunterThreshLabel", nextPlace(parent.getStartPos(), 3, -3), BUTTON_SIZE.multiply(new Vec2(0.5)), "Hunter Threshold", Color.white));
                inputs.add(new GUICommandField("hunterThreshField",this,nextPlace(parent.getStartPos(), 3, -3).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                //RIP THRESHOLDS
                //Other
                components.add(new GUILabel("reproBufferLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE.multiply(new Vec2(0.5)), "Reproduction Buffer", Color.white));
                inputs.add(new GUICommandField("reproBufferField",this,nextPlace(parent.getStartPos(), 3, -2).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("actionRadiusLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE.multiply(new Vec2(0.5)), "Action Radius", Color.white));
                inputs.add(new GUICommandField("actionRadiusField",this,nextPlace(parent.getStartPos(), 3, -2).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                /*
                 inputs for:
                 <Creature>
                 eCostHunt- COMPLETE
                 eCostForage- COMPLETE
                 eCostRepro- COMPLETE
                 reproThreshold- COMPLETE
                 reproBuffer- COMPLETE
                 hunterThreshold- COMPLETE
                 <NewBehavior>
                 eCostDetect- COMPLETE
                 eCostMove- COMPLETE
                 <Terrain>
                 actionRadius- COMPLETE
                 */
                for (int i = 0; i < 5; i++) {
                    components.add(new GUIPanel("top1." + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2,1)), getColor(0).multiply(0.8 - 0.1 * i)));
                    components.add(new GUIPanel("top2." + i, nextPlace(parent.getStartPos(), 4, -i), BUTTON_SIZE.multiply(new Vec2(2,1)), getColor(0).multiply(0.8 - 0.1 * i)));
                }
                break;
            case 1:
                typeStr = "terrain";
                components.add(new GUILabel("huntYieldLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE.multiply(new Vec2(0.5)), "Hunter Yield", Color.white));
                inputs.add(new GUICommandField("huntYieldField",this,nextPlace(parent.getStartPos(), 3, -1).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("numCreaturesLabel", nextPlace(parent.getStartPos(), 3, 0), BUTTON_SIZE.multiply(new Vec2(0.5)), "Number of Creatures", Color.white));
                inputs.add(new GUICommandField("numCreaturesField",this,nextPlace(parent.getStartPos(), 3, 0).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                for (int i = 0; i < 2; i++) {
                    components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2,1)), getColor(0).multiply(0.8 - 0.1 * i)));
                }
                /*
                 inputs for:
                 <Terrain>
                 huntYield
                 <SimGenerator>
                 numCreatures
                 */
                break;
            case 2:
                typeStr = "food";
                components.add(new GUILabel("nutrientsPFLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE.multiply(new Vec2(0.5)), "Nutrients Per Food", Color.white));
                inputs.add(new GUICommandField("nPFField",this,nextPlace(parent.getStartPos(), 3, -2).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("fRespawnAmountLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE.multiply(new Vec2(0.5)), "Food Respawn Amount", Color.white));
                inputs.add(new GUICommandField("fRespawnAmountField",this,nextPlace(parent.getStartPos(), 3, -1).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("fRespawnRateLabel", nextPlace(parent.getStartPos(), 3, 0), BUTTON_SIZE.multiply(new Vec2(0.5)), "Food Respawn Rate", Color.white));
                inputs.add(new GUICommandField("fRespawnRateField",this,nextPlace(parent.getStartPos(), 3, 0).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                for (int i = 0; i < 3; i++) {
                    components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2,1)), getColor(0).multiply(0.8 - 0.1 * i)));
                }
                /*
                 <Terrain>
                 nutrientsPerFood
                 fRespawnAmount
                 <TerrainGenerator>
                 foodSpawnRate
                 */
                break;
            case 3:
                typeStr = "advanced";
                components.add(new GUILabel("mutationFactorLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE.multiply(new Vec2(0.5)), "Mutation Factor", Color.white));
                inputs.add(new GUICommandField("mFactorField",this,nextPlace(parent.getStartPos(), 3, -2).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("lengthVarianceLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE.multiply(new Vec2(0.5)), "Gene Length Variance", Color.white));
                inputs.add(new GUICommandField("lenVarianceField",this,nextPlace(parent.getStartPos(), 3, -1).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                components.add(new GUILabel("wGenomeLabel", nextPlace(parent.getStartPos(), 3, 0), BUTTON_SIZE.multiply(new Vec2(0.5)), "Weighted Genome", Color.white));
                inputs.add(new GUICommandField("wGenomeField",this,nextPlace(parent.getStartPos(), 3, 0).add(BUTTON_SIZE.multiply(new Vec2(0.5,0))),(BUTTON_SIZE.x)*1.5, Color.black));
                for (int i = 0; i < 3; i++) {
                    components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2,1)), getColor(0).multiply(0.8 - 0.1 * i)));
                }
                /*
                 inputs for:
                 <Chromosome>
                 mutationFactor
                 lengthVariance
                 <StructureInterpreter>
                 weightedGenome
                 */
                break;
        }
    }

    public void start(){
        this.setVisible(true);
        typing(this, true);
    }
    
    @Override
    public void recieve(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }

}

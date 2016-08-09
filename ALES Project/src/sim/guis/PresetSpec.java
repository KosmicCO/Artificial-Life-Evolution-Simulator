/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import gui.GUIController;
import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUICommandField;
import gui.components.GUILabel;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import util.Color4;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;

/**
 *
 * @author bhargav
 */
public class PresetSpec extends ComponentInputGUI {

   
    private NewPreset parent;
    

    public PresetSpec(String n, NewPreset parent) {
        super(n);
        //type = t;
        this.parent = parent;
        /*switch (type) {
            case 0:
                typeStr = "creature";
                inputs.add(new GUIButton("eCostSetVisible", this, nextPlace(parent.getStartPos(), 3, -5), BUTTON_SIZE, "Energy Costs", Color.white));
                components.add(new GUIPanel("eCostPanel", nextPlace(parent.getStartPos(), 3, -5), BUTTON_SIZE, Color4.RED));
                //ENERGY COSTS
                eCostMenu = new EnergyCostMenu("eCostMenu", this);
                GUIController.add(eCostMenu);
                // </energyCosts>
                //THRESHOLDS
                components.add(new GUILabel("reproThreshLabel", nextPlace(parent.getStartPos(), 3, -4), BUTTON_SIZE, "Mate Threshold", Color.white));
                inputs.add(new GUICommandField("reproThreshField", this, nextPlace(parent.getStartPos(), 4, -4).add(BUTTON_SIZE), (BUTTON_SIZE.x), Color.black));
                components.add(new GUILabel("hunterThreshLabel", nextPlace(parent.getStartPos(), 3, -3), BUTTON_SIZE, "Hunt Threshold", Color.white));
                inputs.add(new GUICommandField("hunterThreshField", this, nextPlace(parent.getStartPos(), 4, -3).add(BUTTON_SIZE), (BUTTON_SIZE.x), Color.black));
                //RIP THRESHOLDS
                //Other
                components.add(new GUILabel("reproBufferLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Mating Buffer", Color.white));
                inputs.add(new GUICommandField("reproBufferField", this, nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                components.add(new GUILabel("actionRadiusLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Action Radius", Color.white));
                inputs.add(new GUICommandField("actionRadiusField", this, nextPlace(parent.getStartPos(), 4, -1).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                
//                 inputs for:
//                 <Creature>
//                 eCostHunt- COMPLETE
//                 eCostForage- COMPLETE
//                 eCostRepro- COMPLETE
//                 reproThreshold- COMPLETE
//                 reproBuffer- COMPLETE
//                 hunterThreshold- COMPLETE
//                 <NewBehavior>
//                 eCostDetect- COMPLETE
//                 eCostMove- COMPLETE
//                 <Terrain>
//                 actionRadius- COMPLETE
                 
                for (int i = 1; i < 6; i++) {
                    components.add(new GUIPanel("top1." + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
                }
                System.out.println("all creature components constructed according to plan");
                break;
            case 1:
                typeStr = "terrain";
                components.add(new GUILabel("huntYieldLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Hunter Yield", Color.white));
                inputs.add(new GUICommandField("huntYieldField", this, nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                components.add(new GUILabel("numCreaturesLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Number of Creatures", Color.white));
                inputs.add(new GUICommandField("numCreaturesField", this, nextPlace(parent.getStartPos(), 4, -1).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                for (int i = 1; i < 3; i++) {
                    components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
                }
                
                 inputs for:
                 <Terrain>
                 huntYield
                 <SimGenerator>
                 numCreatures
                
                System.out.println("all terrain components constructed according to plan");
                break;
            case 2:
                typeStr = "food";
                components.add(new GUILabel("nutrientsPFLabel", nextPlace(parent.getStartPos(), 3, -3), BUTTON_SIZE, "Nutrients Per Food", Color.white));
                inputs.add(new GUICommandField("nPFField", this, nextPlace(parent.getStartPos(), 4, -3).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                components.add(new GUILabel("fRespawnAmountLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Food Respawn Amount", Color.white));
                inputs.add(new GUICommandField("fRespawnAmountField", this, nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                components.add(new GUILabel("fRespawnRateLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Food Respawn Rate", Color.white));
                inputs.add(new GUICommandField("fRespawnRateField", this, nextPlace(parent.getStartPos(), 4, -1).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                for (int i = 1; i < 4; i++) {
                    components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
                }
                
//                 <Terrain>
//                 nutrientsPerFood
//                 fRespawnAmount
//                 <TerrainGenerator>
//                 foodSpawnRate
                 
                System.out.println("all food components constructed according to plan");
                break;
            case 3:
                typeStr = "advanced";
                components.add(new GUILabel("mutationFactorLabel", nextPlace(parent.getStartPos(), 3, -3), BUTTON_SIZE, "Mutation Factor", Color.white));
                inputs.add(new GUICommandField("mFactorField", this, nextPlace(parent.getStartPos(), 4, -3).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                components.add(new GUILabel("lengthVarianceLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Gene Length Variance", Color.white));
                inputs.add(new GUICommandField("lenVarianceField", this, nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                components.add(new GUILabel("wGenomeLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Weighted Genome", Color.white));
                inputs.add(new GUICommandField("wGenomeField", this, nextPlace(parent.getStartPos(), 4, -1).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
                for (int i = 1; i < 4; i++) {
                    components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
                }
                
//                 inputs for:
//                 <Chromosome>
//                 mutationFactor
//                 lengthVariance
//                 <StructureInterpreter>
//                 weightedGenome
                 
                System.out.println("all advanced settings components constructed according to plan");
                break;
        }*/
        inputs.add(new GUIButton("cancelBtn", this, nextPlace(parent.getStartPos(), 3, 0), BUTTON_SIZE, "Cancel", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 3, 0), BUTTON_SIZE, Color4.RED));
    }

    public void start() {
        this.setVisible(true);
        typing(this, true);
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("DRAWN");
    }

    @Override
    public void recieve(String string, Object o) {
        String inputStr;
        double newValDouble;
        int newValInt;
        switch (string) {
            case "cancelBtn":
                setVisible(false);
                typing(parent, true);
                break;
            case "eCostSetVisible":
                //eCostMenu.start();
                break;
            case "reproThreshField":
                inputStr = (String) o;
                newValDouble = parseDouble(inputStr);
                NewPreset.reproThreshold = newValDouble;
                ((GUICommandField) inputs.get(getFieldIndex("reproThreshField"))).setText(inputStr);
                break;
            case "hunterThreshField":
                inputStr = (String) o;
                newValDouble = parseDouble(inputStr);
                NewPreset.hunterThreshold = newValDouble;
                ((GUICommandField) inputs.get(getFieldIndex("hunterThreshField"))).setText(inputStr);
                break;
            case "reproBufferField":
                inputStr = (String) o;
                newValDouble = parseDouble(inputStr);
                NewPreset.reproBuffer = newValDouble;
                ((GUICommandField) inputs.get(getFieldIndex("reproBufferField"))).setText(inputStr);
                break;
            case "actionRadiusField":
                inputStr = (String) o;
                newValInt = parseInt(inputStr);
                NewPreset.actionRad = newValInt;
                ((GUICommandField) inputs.get(getFieldIndex("actionRadiusField"))).setText(inputStr);
                break;
            case "huntYieldField":
                inputStr = (String) o;
                newValDouble = parseDouble(inputStr);
                NewPreset.huntYield = newValDouble;
                ((GUICommandField) inputs.get(getFieldIndex("huntYieldField"))).setText(inputStr);
                break;
            case "numCreaturesField":
                inputStr = (String) o;
                newValInt = parseInt(inputStr);
                NewPreset.numCreatures = newValInt;
                ((GUICommandField) inputs.get(getFieldIndex("numCreaturesField"))).setText(inputStr);
                break;
            case "nPFField":
                inputStr = (String) o;
                newValInt = parseInt(inputStr);
                NewPreset.nutrientsPerFood = newValInt;
                ((GUICommandField) inputs.get(getFieldIndex("nPFField"))).setText(inputStr);
                break;
            case "fRespawnAmountField":
                inputStr = (String) o;
                newValInt = parseInt(inputStr);
                NewPreset.fRespawnAmount = newValInt;
                ((GUICommandField) inputs.get(getFieldIndex("fRespawnAmountField"))).setText(inputStr);
                break;
            case "fRespawnRateField":
                inputStr = (String) o;
                newValDouble = parseDouble(inputStr);
                NewPreset.foodSR = newValDouble;
                ((GUICommandField) inputs.get(getFieldIndex("fRespawnRateField"))).setText(inputStr);
                break;
            case "mFactorField":
                inputStr = (String) o;
                newValDouble = parseDouble(inputStr);
                NewPreset.mutantFactor = newValDouble;
                ((GUICommandField) inputs.get(getFieldIndex("mutantFactorField"))).setText(inputStr);
                break;
            case "lenVarianceField":
                inputStr = (String) o;
                newValInt = parseInt(inputStr);
                NewPreset.lenVariance = newValInt;
                ((GUICommandField) inputs.get(getFieldIndex("lenVarianceField"))).setText(inputStr);
                break;
            case "wGenomeField":
                inputStr = (String) o;
                List<Integer> wG = new ArrayList<>();
                String sub = "";
                for(int i = 0; i<inputStr.length(); i++){
                    //String j = inputStr.substring(i);
                    if(inputStr.charAt(i) == '-'||i==inputStr.length()-1){
                        int chr = parseInt(sub);
                        wG.add(chr);
                        sub = "";
                    }
                    sub+=inputStr.charAt(i);
                }
                NewPreset.wGenome = wG;
                ((GUICommandField) inputs.get(getFieldIndex("wGenomeField"))).setText(inputStr);
                break;
        }
    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }

    public int getFieldIndex(String str) {
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).getName().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    Vec2 getStartPos() {
        return parent.getStartPos();
    }

}

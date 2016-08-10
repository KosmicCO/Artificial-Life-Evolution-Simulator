/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis.pres;

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
import org.newdawn.slick.Color;
import sim.guis.NewPreset;
import util.Color4;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;

/**
 *
 * @author bhargav
 */
public class CreaturePresetSpec extends ComponentInputGUI {

    private EnergyCostMenu eCostMenu;
    private NewPreset parent;

    public CreaturePresetSpec(String n, NewPreset parent) {
        super(n);
        this.parent = parent;
                for (int i = 0; i < 5; i++) {
            components.add(new GUIPanel("top1." + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(1, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
        }
        inputs.add(new GUIButton("cancelBtn", this, nextPlace(parent.getStartPos(), 3, 1), BUTTON_SIZE, "Cancel", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 3, 1), BUTTON_SIZE, Color4.RED.multiply(0.8)));
        
        inputs.add(new GUIButton("eCostSetVisible", this, nextPlace(parent.getStartPos(), 3, -4), BUTTON_SIZE, "Energy Costs", Color.white));
        //ENERGY COSTS
        eCostMenu = new EnergyCostMenu("eCostMenu", this);
        GUIController.add(eCostMenu);
        // </energyCosts>
        //THRESHOLDS
        components.add(new GUILabel("reproThreshLabel", nextPlace(parent.getStartPos(), 3, -3), BUTTON_SIZE, "Mate Threshold", Color.white));
        inputs.add(new GUICommandField("reproThreshField", this, nextPlace(parent.getStartPos(), 4, -4).add(BUTTON_SIZE), (BUTTON_SIZE.x), Color.black));
        components.add(new GUILabel("hunterThreshLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Hunt Threshold", Color.white));
        inputs.add(new GUICommandField("hunterThreshField", this, nextPlace(parent.getStartPos(), 4, -3).add(BUTTON_SIZE), (BUTTON_SIZE.x), Color.black));
        //RIP THRESHOLDS
        //Other
        components.add(new GUILabel("reproBufferLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Mating Buffer", Color.white));
        inputs.add(new GUICommandField("reproBufferField", this, nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
        components.add(new GUILabel("actionRadiusLabel", nextPlace(parent.getStartPos(), 3, -0), BUTTON_SIZE, "Action Radius", Color.white));
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

        System.out.println("all creature components constructed according to plan");
    }

    public void start() {
        this.setVisible(true);
        typing(this, true);
    }

    @Override
    public void recieve(String string, Object o) {
        String inputStr;
        double newValDouble;
        int newValInt;
        switch (string) {
            case "cancelBtn":
                setVisible(false);
                parent.start();
                break;
            case "eCostSetVisible":
                eCostMenu.start();
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

        }
    }

    Vec2 getStartPos() {
        return parent.getStartPos();
    }

    public int getFieldIndex(String str) {
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).getName().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }

}

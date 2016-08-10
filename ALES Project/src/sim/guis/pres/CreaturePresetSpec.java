/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis.pres;

import gui.GUIController;
import static gui.GUIController.FONT;
import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUICommandField;
import gui.components.GUILabel;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIComponent;
import gui.types.GUIInputComponent;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.List;
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
        for (int i = 0; i < 4; i++) {
            components.add(new GUIPanel("top1." + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2 - (i == 3 ? 1 : 0), 1)), getColor(0).multiply(0.8 - 0.1 * i)));
        }
        inputs.add(new GUIButton("cancelBtn", this, nextPlace(parent.getStartPos(), 3, 1), BUTTON_SIZE, "Cancel", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 3, 1), BUTTON_SIZE, Color4.RED.multiply(0.8)));

        inputs.add(new GUIButton("eCostSetVisible", this, nextPlace(parent.getStartPos(), 3, -3), BUTTON_SIZE, "Energy Costs", Color.white));
        //ENERGY COSTS
        eCostMenu = new EnergyCostMenu("eCostMenu", this);
        GUIController.add(eCostMenu);
        // </energyCosts>
        //THRESHOLDS
        components.add(new GUILabel("reproThreshLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Mate Threshold", Color.white));

        inputs.add(new GUIButton("rtfb", this, nextPlace(parent.getStartPos(), 4, -2), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("rtf", this, nextPlace(parent.getStartPos(), 4, -2).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));
        //RIP THRESHOLDS
        //Other
        components.add(new GUILabel("reproBufferLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Mating Buffer", Color.white));

        inputs.add(new GUIButton("rbfb", this, nextPlace(parent.getStartPos(), 4, -1), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("rbf", this, nextPlace(parent.getStartPos(), 4, -1).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

        components.add(new GUILabel("actionRadiusLabel", nextPlace(parent.getStartPos(), 3, 0), BUTTON_SIZE, "Action Radius", Color.white));

        inputs.add(new GUIButton("arfb", this, nextPlace(parent.getStartPos(), 4, 0), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("arf", this, nextPlace(parent.getStartPos(), 4, 0).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

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

                ((GUICommandField) inputs.get(getFieldIndex("rtf"))).send();
                ((GUICommandField) inputs.get(getFieldIndex("htf"))).send();
                ((GUICommandField) inputs.get(getFieldIndex("rbf"))).send();
                ((GUICommandField) inputs.get(getFieldIndex("arf"))).send();
                
                setVisible(false);
                parent.start();
                break;

            case "eCostSetVisible":

                eCostMenu.start();
                break;

            case "rtf":

                inputStr = (String) o;

                try {

                    newValDouble = parseDouble(inputStr);
                    NewPreset.reproThreshold = newValDouble;
                    ((GUICommandField) inputs.get(getFieldIndex("rtf"))).setText(inputStr);
                } catch (Exception e) {

                    ((GUICommandField) inputs.get(getFieldIndex("rtf"))).setText("Error");
                }

                break;

            case "htf":

                inputStr = (String) o;

                try {

                    newValDouble = parseDouble(inputStr);
                    NewPreset.hunterThreshold = newValDouble;
                    ((GUICommandField) inputs.get(getFieldIndex("htf"))).setText(inputStr);
                } catch (Exception e) {

                    ((GUICommandField) inputs.get(getFieldIndex("htf"))).setText("Error");
                }

                break;

            case "rbf":

                inputStr = (String) o;

                try {

                    newValDouble = parseDouble(inputStr);
                    NewPreset.reproBuffer = newValDouble;
                    ((GUICommandField) inputs.get(getFieldIndex("rbf"))).setText(inputStr);
                } catch (Exception e) {

                    ((GUICommandField) inputs.get(getFieldIndex("rbf"))).setText("Error");
                }

                break;

            case "arf":

                inputStr = (String) o;

                try {

                    newValInt = parseInt(inputStr);
                    NewPreset.actionRad = newValInt;
                    ((GUICommandField) inputs.get(getFieldIndex("arf"))).setText(inputStr);
                } catch (Exception e) {

                    ((GUICommandField) inputs.get(getFieldIndex("arf"))).setText("Error");
                }

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
    public List<GUIComponent> mousePressed(Vec2 p) {

        List<GUIComponent> lgc = super.mousePressed(p);

        for (int i = 0; i < 3; i++) {

            if (lgc.contains(inputs.get(i * 2 + 2))) {

                lgc.remove(inputs.get(i * 2 + 2));
                lgc.add(inputs.get(i * 2 + 3));
            }
        }

        return lgc;
    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }

}

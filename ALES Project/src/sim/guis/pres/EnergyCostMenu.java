/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis.pres;

import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUICommandField;
import gui.components.GUILabel;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import java.io.Reader;
import java.io.StringReader;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import org.newdawn.slick.Color;
import sim.guis.NewPreset;
import static sim.guis.NewPreset.newPreset;
import util.Color4;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;

/**
 *
 * @author bhargav
 */
public class EnergyCostMenu extends ComponentInputGUI {

    private CreaturePresetSpec parent;

    public EnergyCostMenu(String n, CreaturePresetSpec parent) {
        super(n);
        this.parent = parent;
                for (int i = 0; i < 5; i++) {
            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 4, -i), BUTTON_SIZE.multiply(new Vec2(1, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
        }
        components.add(new GUILabel("eCostHuntLabel", nextPlace(parent.getStartPos(), 4, -4), BUTTON_SIZE, "Hunting", Color.white));
        inputs.add(new GUICommandField("eCostHuntField", this, nextPlace(parent.getStartPos(), 4, -4).add(BUTTON_SIZE.multiply(new Vec2(0.5, 0))), (BUTTON_SIZE.x) * 1.5, Color.black));
        components.add(new GUILabel("eCostForageLabel", nextPlace(parent.getStartPos(), 4, -3), BUTTON_SIZE, "Foraging", Color.white));
        inputs.add(new GUICommandField("eCostForageField", this, nextPlace(parent.getStartPos(), 4, -3).add(BUTTON_SIZE.multiply(new Vec2(0.5, 0))), (BUTTON_SIZE.x) * 1.5, Color.black));
        components.add(new GUILabel("eCostReproLabel", nextPlace(parent.getStartPos(), 4, -2), BUTTON_SIZE, "Reproduction", Color.white));
        inputs.add(new GUICommandField("eCostReproField", this, nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE.multiply(new Vec2(0.5, 0))), (BUTTON_SIZE.x) * 1.5, Color.black));
        components.add(new GUILabel("eCostDetectLabel", nextPlace(parent.getStartPos(), 4, -1), BUTTON_SIZE, "Detection", Color.white));
        inputs.add(new GUICommandField("eCostDetectField", this, nextPlace(parent.getStartPos(), 4, -1).add(BUTTON_SIZE.multiply(new Vec2(0.5, 0))), (BUTTON_SIZE.x) * 1.5, Color.black));
        components.add(new GUILabel("eCostMoveLabel", nextPlace(parent.getStartPos(), 4, 0), BUTTON_SIZE, "Movement", Color.white));
        inputs.add(new GUICommandField("eCostMoveField", this, nextPlace(parent.getStartPos(), 4, 0).add(BUTTON_SIZE.multiply(new Vec2(0.5, 0))), (BUTTON_SIZE.x) * 1.5, Color.black));

        inputs.add(new GUIButton("eCostCancel", this, nextPlace(parent.getStartPos(), 4, 1), BUTTON_SIZE, "Cancel", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 4, 1), BUTTON_SIZE, Color4.RED.multiply(0.8)));


    }

    public void start() {
        this.setVisible(true);
        typing(this, true);
    }

    @Override
    public void recieve(String string, Object o) {

        String input;
        int newCost;
        //Reader inReader = new StringReader(input);
        switch (string) {
            case "eCostCancel":
                System.out.println("Cancel clicked");
                setVisible(false);
                parent.start();
                break;
            case "eCostHuntField":
                input = (String) o;
                newCost = parseInt(input);
                NewPreset.eCostHunt = newCost;
                ((GUICommandField) inputs.get(parent.getFieldIndex("eCostHuntField"))).setText(input);
                break;
            case "eCostForageField":
                input = (String) o;
                newCost = parseInt(input);
                NewPreset.eCostForage = newCost;
                ((GUICommandField) inputs.get(parent.getFieldIndex("eCostForageField"))).setText(input);
                break;
            case "eCostReproField":
                input = (String) o;
                newCost = parseInt(input);
                NewPreset.eCostRepro = newCost;
                ((GUICommandField) inputs.get(parent.getFieldIndex("eCostReproField"))).setText(input);
                break;
            case "eCostDetectField":
                input = (String) o;
                newCost = parseInt(input);
                NewPreset.eCostDetect = newCost;
                ((GUICommandField) inputs.get(parent.getFieldIndex("eCostDetectField"))).setText(input);
                break;
            case "eCostMoveField":
                input = (String) o;
                newCost = parseInt(input);
                NewPreset.eCostMove = newCost;
                ((GUICommandField) inputs.get(parent.getFieldIndex("eCostMoveField"))).setText(input);
                break;
        }

    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }

}

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
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
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
public class FoodPresetSpec extends ComponentInputGUI {

    private NewPreset parent;

    public FoodPresetSpec(String n, NewPreset parent) {
        super(n);
        this.parent = parent;
        for (int i = -1; i < 4; i++) {
            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(1, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
        }
        inputs.add(new GUIButton("cancelBtn", this, nextPlace(parent.getStartPos(), 3, 2), BUTTON_SIZE, "Cancel", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 3, 2), BUTTON_SIZE, Color4.RED));
        components.add(new GUILabel("nutrientsPFLabel", nextPlace(parent.getStartPos(), 3, -3), BUTTON_SIZE, "Nutrients/Food", Color.white));
        inputs.add(new GUICommandField("nPFField", this, nextPlace(parent.getStartPos(), 4, -3).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
        components.add(new GUILabel("fRespawnAmountLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Food Respawn", Color.white));
        inputs.add(new GUICommandField("fRespawnAmountField", this, nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
        components.add(new GUILabel("fRespawnRateLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Respawn Rate", Color.white));
        inputs.add(new GUICommandField("fRespawnRateField", this, nextPlace(parent.getStartPos(), 4, -1).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));

//                 <Terrain>
//                 nutrientsPerFood
//                 fRespawnAmount
//                 <TerrainGenerator>
//                 foodSpawnRate
        System.out.println("all food components constructed according to plan");
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
                typing(parent, true);
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
        }
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

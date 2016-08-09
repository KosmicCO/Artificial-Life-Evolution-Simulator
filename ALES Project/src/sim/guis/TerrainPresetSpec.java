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
public class TerrainPresetSpec extends ComponentInputGUI {

    private NewPreset parent;

    public TerrainPresetSpec(String n, NewPreset parent) {
        super(n);
        this.parent = parent;
        for (int i = -1; i < 3; i++) {
            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(1, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
        }
        inputs.add(new GUIButton("cancelBtn", this, nextPlace(parent.getStartPos(), 3, 2), BUTTON_SIZE, "Cancel", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 3, 2), BUTTON_SIZE, Color4.RED));

        components.add(new GUILabel("huntYieldLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Hunt Yield", Color.white));
        inputs.add(new GUICommandField("huntYieldField", this, nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
        components.add(new GUILabel("numCreaturesLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Population Size", Color.white));
        inputs.add(new GUICommandField("numCreaturesField", this, nextPlace(parent.getStartPos(), 4, -1).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));

//                 inputs for:
//                 <Terrain>
//                 huntYield
//                 <SimGenerator>
//                 numCreatures
        System.out.println("all terrain components constructed according to plan");
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
            case "numCreaturesField":
                inputStr = (String) o;
                newValInt = parseInt(inputStr);
                NewPreset.numCreatures = newValInt;
                ((GUICommandField) inputs.get(getFieldIndex("numCreaturesField"))).setText(inputStr);
                break;
            case "huntYieldField":
                inputStr = (String) o;
                newValDouble = parseDouble(inputStr);
                NewPreset.huntYield = newValDouble;
                ((GUICommandField) inputs.get(getFieldIndex("huntYieldField"))).setText(inputStr);
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

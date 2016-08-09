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
public class AdvancedPresetSpec extends ComponentInputGUI {

    private NewPreset parent;

    public AdvancedPresetSpec(String n, NewPreset parent) {
        super(n);
        this.parent = parent;
        for (int i = -1; i < 4; i++) {
            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(1, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
        }
        inputs.add(new GUIButton("cancelBtn", this, nextPlace(parent.getStartPos(), 3, 2), BUTTON_SIZE, "Cancel", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 3, 2), BUTTON_SIZE, Color4.RED));
        components.add(new GUILabel("mutationFactorLabel", nextPlace(parent.getStartPos(), 3, -3), BUTTON_SIZE, "Mutation Factor", Color.white));
        inputs.add(new GUICommandField("mFactorField", this, nextPlace(parent.getStartPos(), 4, -3).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
        components.add(new GUILabel("lengthVarianceLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Size Variance", Color.white));
        inputs.add(new GUICommandField("lenVarianceField", this, nextPlace(parent.getStartPos(), 4, -2).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));
        components.add(new GUILabel("wGenomeLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Weighted Genome", Color.white));
        inputs.add(new GUICommandField("wGenomeField", this, nextPlace(parent.getStartPos(), 4, -1).add(BUTTON_SIZE), BUTTON_SIZE.x, Color.black));

//                 inputs for:
//                 <Chromosome>
//                 mutationFactor
//                 lengthVariance
//                 <StructureInterpreter>
//                 weightedGenome
        System.out.println("all advanced settings components constructed according to plan");
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
                for (int i = 0; i < inputStr.length(); i++) {
                    //String j = inputStr.substring(i);
                    if (inputStr.charAt(i) == '-' || i == inputStr.length() - 1) {
                        int chr = parseInt(sub);
                        wG.add(chr);
                        sub = "";
                    }
                    sub += inputStr.charAt(i);
                }
                NewPreset.wGenome = wG;
                ((GUICommandField) inputs.get(getFieldIndex("wGenomeField"))).setText(inputStr);
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

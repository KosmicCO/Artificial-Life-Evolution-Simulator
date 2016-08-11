/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis.pres;

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
public class FoodPresetSpec extends ComponentInputGUI {

    private NewPreset parent;

    public FoodPresetSpec(String n, NewPreset parent) {
        super(n);
        this.parent = parent;
        for (int i = 0; i < 3; i++) {
            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 3, -i), BUTTON_SIZE.multiply(new Vec2(2, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
        }
        
        inputs.add(new GUIButton("cancelBtn", this, nextPlace(parent.getStartPos(), 3, 1), BUTTON_SIZE, "Back", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 3, 1), BUTTON_SIZE, Color4.RED.multiply(0.8)));

        components.add(new GUILabel("nutrientsPFLabel", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, "Nutrients/Food", Color.white));

        inputs.add(new GUIButton("npffb", this, nextPlace(parent.getStartPos(), 4, -2), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("npff", this, nextPlace(parent.getStartPos(), 4, -2).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

        components.add(new GUILabel("fRespawnAmountLabel", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, "Food Respawn", Color.white));

        inputs.add(new GUIButton("frafb", this, nextPlace(parent.getStartPos(), 4, -1), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("fraf", this, nextPlace(parent.getStartPos(), 4, -1).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

        components.add(new GUILabel("fRespawnRateLabel", nextPlace(parent.getStartPos(), 3, 0), BUTTON_SIZE, "Respawn Rate", Color.white));

        inputs.add(new GUIButton("frrfb", this, nextPlace(parent.getStartPos(), 4, 0), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("frrf", this, nextPlace(parent.getStartPos(), 4, 0).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

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
                
                ((GUICommandField) inputs.get(getFieldIndex("npff"))).send();
                ((GUICommandField) inputs.get(getFieldIndex("fraf"))).send();
                ((GUICommandField) inputs.get(getFieldIndex("frrf"))).send();
                
                setVisible(false);
                parent.start();
                break;
                
            case "npff":
                
                inputStr = (String) o;
                
                try{
                
                newValInt = parseInt(inputStr);
                NewPreset.nutrientsPerFood = newValInt;
                ((GUICommandField) inputs.get(getFieldIndex("npff"))).setText(inputStr);
                }catch(Exception e){
                    
                    ((GUICommandField) inputs.get(getFieldIndex("npff"))).setText("Error");
                }
                
                break;
                
            case "fraf":
                
                inputStr = (String) o;
                
                try{
                
                newValInt = parseInt(inputStr);
                NewPreset.fRespawnAmount = newValInt;
                ((GUICommandField) inputs.get(getFieldIndex("fraf"))).setText(inputStr);
                }catch(Exception e){
                    
                    ((GUICommandField) inputs.get(getFieldIndex("fraf"))).setText("Error");
                }
                
                break;
                
            case "frrf":
                
                inputStr = (String) o;
                
                try{
                
                newValDouble = parseDouble(inputStr);
                NewPreset.foodSR = newValDouble;
                ((GUICommandField) inputs.get(getFieldIndex("frrf"))).setText(inputStr);
                }catch(Exception e){
                    
                    ((GUICommandField) inputs.get(getFieldIndex("frrf"))).setText("Error");
                }
                
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
    public List<GUIComponent> mousePressed(Vec2 p) {

        List<GUIComponent> lgc = super.mousePressed(p);

        for (int i = 0; i < 3; i++) {

            if (lgc.contains(inputs.get(i * 2 + 1))) {

                lgc.remove(inputs.get(i * 2 + 1));
                lgc.add(inputs.get(i * 2 + 2));
            }
        }

        return lgc;
    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }
}

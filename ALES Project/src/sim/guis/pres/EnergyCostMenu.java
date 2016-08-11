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
public class EnergyCostMenu extends ComponentInputGUI {

    private CreaturePresetSpec parent;

    public EnergyCostMenu(String n, CreaturePresetSpec parent) {
        super(n);
        this.parent = parent;
        for (int i = 0; i < 5; i++) {
            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 4, -i), BUTTON_SIZE.multiply(new Vec2(2, 1)), getColor(0).multiply(0.8 - 0.1 * i)));
        }
        components.add(new GUILabel("eCostHuntLabel", nextPlace(parent.getStartPos(), 4, -4), BUTTON_SIZE, "Hunting", Color.white));

        inputs.add(new GUIButton("echfb", this, nextPlace(parent.getStartPos(), 5, -4), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("echf", this, nextPlace(parent.getStartPos(), 5, -4).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

        components.add(new GUILabel("eCostForageLabel", nextPlace(parent.getStartPos(), 4, -3), BUTTON_SIZE, "Foraging", Color.white));

        inputs.add(new GUIButton("ecffb", this, nextPlace(parent.getStartPos(), 5, -3), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("ecff", this, nextPlace(parent.getStartPos(), 5, -3).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

        components.add(new GUILabel("eCostReproLabel", nextPlace(parent.getStartPos(), 4, -2), BUTTON_SIZE, "Reproduction", Color.white));

        inputs.add(new GUIButton("ecrfb", this, nextPlace(parent.getStartPos(), 5, -2), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("ecrf", this, nextPlace(parent.getStartPos(), 5, -2).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

        components.add(new GUILabel("eCostDetectLabel", nextPlace(parent.getStartPos(), 4, -1), BUTTON_SIZE, "Detection", Color.white));

        inputs.add(new GUIButton("ecdfb", this, nextPlace(parent.getStartPos(), 5, -1), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("ecdf", this, nextPlace(parent.getStartPos(), 5, -1).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

        components.add(new GUILabel("eCostMoveLabel", nextPlace(parent.getStartPos(), 4, 0), BUTTON_SIZE, "Movement", Color.white));

        inputs.add(new GUIButton("ecmfb", this, nextPlace(parent.getStartPos(), 5, 0), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("ecmf", this, nextPlace(parent.getStartPos(), 5, 0).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

        inputs.add(new GUIButton("eCostCancel", this, nextPlace(parent.getStartPos(), 4, 1), BUTTON_SIZE, "Back", Color.white));
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

                ((GUICommandField) inputs.get(parent.getFieldIndex("echf"))).send();
                ((GUICommandField) inputs.get(parent.getFieldIndex("ecff"))).send();
                ((GUICommandField) inputs.get(parent.getFieldIndex("ecrf"))).send();
                ((GUICommandField) inputs.get(parent.getFieldIndex("ecdf"))).send();
                ((GUICommandField) inputs.get(parent.getFieldIndex("ecmf"))).send();

                setVisible(false);
                parent.start();
                break;

            case "echf":

                input = (String) o;

                try {
                    
                    newCost = parseInt(input);
                    NewPreset.eCostHunt = newCost;
                    ((GUICommandField) inputs.get(parent.getFieldIndex("echf"))).setText(input);
                } catch (Exception e) {

                    ((GUICommandField) inputs.get(parent.getFieldIndex("echf"))).setText("Error");
                }
                break;
                
            case "ecff":
                
                input = (String) o;
                
                try{
                    
                newCost = parseInt(input);
                NewPreset.eCostForage = newCost;
                ((GUICommandField) inputs.get(parent.getFieldIndex("ecff"))).setText(input);
                }catch(Exception e){
                    
                    ((GUICommandField) inputs.get(parent.getFieldIndex("ecff"))).setText("Error");
                }
                break;
            case "ecrf":
                
                input = (String) o;
                
                try{
                
                newCost = parseInt(input);
                NewPreset.eCostRepro = newCost;
                ((GUICommandField) inputs.get(parent.getFieldIndex("ecrf"))).setText(input);
                }catch(Exception e){
                    
                    ((GUICommandField) inputs.get(parent.getFieldIndex("ecrf"))).setText("Error");
                }
                
                break;
                
            case "ecdf":
                
                input = (String) o;
                
                try{
                
                newCost = parseInt(input);
                NewPreset.eCostDetect = newCost;
                ((GUICommandField) inputs.get(parent.getFieldIndex("ecdf"))).setText(input);
                }catch(Exception e){
                    
                    ((GUICommandField) inputs.get(parent.getFieldIndex("ecdf"))).setText("Error");
                }
                
                break;
                
            case "ecmf":
                
                input = (String) o;
                
                try{
                
                newCost = parseInt(input);
                NewPreset.eCostMove = newCost;
                ((GUICommandField) inputs.get(parent.getFieldIndex("ecmf"))).setText(input);
                }catch(Exception e){
                    
                     ((GUICommandField) inputs.get(parent.getFieldIndex("ecmf"))).setText("Error");
                }
                
                break;
        }

    }

    @Override
    public List<GUIComponent> mousePressed(Vec2 p) {

        List<GUIComponent> lgc = super.mousePressed(p);

        for (int i = 0; i < 5; i++) {

            if (lgc.contains(inputs.get(i * 2))) {

                lgc.remove(inputs.get(i * 2));
                lgc.add(inputs.get(i * 2 + 1));
            }
        }

        return lgc;
    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }

}

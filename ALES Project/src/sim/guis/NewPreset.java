/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;

/**
 *
 * @author Kosmic
 */
public class NewPreset extends ComponentInputGUI {

    private Presets parent;
    List<PresetSpec> preSpecs;

    public NewPreset(String n, Presets parent) {

        super(n);
        this.parent = parent;

        inputs.add(new GUIButton("creature", this, nextPlace(parent.getStartPos(), 2, -4), BUTTON_SIZE, "Creature", Color.white));
        inputs.add(new GUIButton("terrain", this, nextPlace(parent.getStartPos(), 2, -3), BUTTON_SIZE, "Terrain", Color.white));
        inputs.add(new GUIButton("food", this, nextPlace(parent.getStartPos(), 2, -2), BUTTON_SIZE, "Food", Color.white));
        inputs.add(new GUIButton("advanced", this, nextPlace(parent.getStartPos(), 2, -1), BUTTON_SIZE, "Advanced", Color.white));
        
        
        for (int i = 0; i < 5; i++) {

            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 2, -i), BUTTON_SIZE, getColor(0).multiply(0.8 - 0.1 * i)));
        }

        inputs.add(new GUIButton("create", this, nextPlace(parent.getStartPos(), 2, 1), BUTTON_SIZE, "Create", Color.white));
        inputs.add(new GUIButton("cancel", this, nextPlace(parent.getStartPos(), 2, 2), BUTTON_SIZE, "Cancel", Color.white));

        components.add(new GUIPanel("middle", nextPlace(parent.getStartPos(), 2, 1), BUTTON_SIZE, getColor(1).multiply(0.8)));
        components.add(new GUIPanel("bottom", nextPlace(parent.getStartPos(), 2, 2), BUTTON_SIZE, getColor(1).multiply(0.7)));
        preSpecs = new ArrayList<>();
    }

    public void start() {

        this.setVisible(true);
        typing(this, true);
        for(int i = 0; i<4; i++){
            preSpecs.add(new PresetSpec("specMenu"+i,i,this));
        }
    }

    public Vec2 getStartPos(){
        return parent.getStartPos();
    }
    
    @Override
    public void recieve(String string, Object o) {
        switch(string){
            case "creature":
                preSpecs.get(0).start();
                break;
            case "terrain":
                preSpecs.get(1).start();
                break;
            case "food":
                preSpecs.get(2).start();
                break;
            case "advanced":
                preSpecs.get(3).start();
                break;
        }
        if (string.equals("cancel")) {
            setVisible(false);
            typing(parent, true);
        }
    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }

}

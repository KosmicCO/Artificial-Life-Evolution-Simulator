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
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;

/**
 *
 * @author Kosmic
 */
public class Presets extends ComponentInputGUI{
    
    private MainMenu parent;
    
    public Presets(String name, MainMenu parent) {
        
        super(name);
        
        this.parent = parent;
        
        inputs.add(new GUIButton("plenty", this, nextPlace(parent.getStartPos(), 1, -2), BUTTON_SIZE, "Plenty Preset", Color.white));
        inputs.add(new GUIButton("uneven", this, nextPlace(parent.getStartPos(), 1, -1), BUTTON_SIZE, "Uneven Preset", Color.white));
        inputs.add(new GUIButton("custom", this, nextPlace(parent.getStartPos(), 1, 0), BUTTON_SIZE, "Custom Preset", Color.white));
        
        inputs.add(new GUIButton("back", this, nextPlace(parent.getStartPos(), 1, 1), BUTTON_SIZE, "Back", Color.white));
        
        for (int i = 0; i < 3; i++) {
            
            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 1, -i), BUTTON_SIZE, getColor(0).multiply(0.7 - 0.1 * i)));
        }
        
        components.add(new GUIPanel("bottom", nextPlace(parent.getStartPos(), 1, 1), BUTTON_SIZE, getColor(1).multiply(0.7)));
    }
    
    public void start(boolean grabbed){
        
        this.setVisible(true);
        this.grabbed = grabbed;
        typing(this, true);
    }

    @Override
    public void recieve(String string, Object o) {

        switch(string){
            
            case "back":
                setVisible(false);
                typing(parent, true);
                break;
        }
    }
    
    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }
}

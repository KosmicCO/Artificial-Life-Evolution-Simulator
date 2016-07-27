/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import gui.components.GUIButton;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import org.newdawn.slick.Color;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class MainMenu extends ComponentInputGUI{
    
    private Vec2 start = new Vec2(0, 250);
    
    public MainMenu(String name) {
        
        super(name);
        
        inputs.add(new GUIButton("start", this, nextPlace(start, 0, -2), BUTTON_SIZE, "Start", Color.white));
        inputs.add(new GUIButton("presets", this, nextPlace(start, 0, -1), BUTTON_SIZE, "Presets", Color.white));
        inputs.add(new GUIButton("settings", this, nextPlace(start, 0, 0), BUTTON_SIZE, "Settings", Color.white));
        
        inputs.add(new GUIButton("quit", this, nextPlace(start, 0, 1), BUTTON_SIZE, "Quit", Color.white));
        
        for (int i = 0; i < 3; i++) {
            
            components.add(new GUIPanel("top" + i, nextPlace(start, 0, -i), BUTTON_SIZE, getColor(0).multiply(0.8 - 0.1 * i)));
        }
        
        components.add(new GUIPanel("bottom", nextPlace(start, 0, 1), BUTTON_SIZE, getColor(1).multiply(0.6)));
    }

    @Override
    public void recieve(String string, Object o) {

        
    }
    
    @Override
    public GUIInputComponent getDefaultComponent() {

        return inputs.get(0);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import gui.components.GUIButton;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import org.newdawn.slick.Color;
import static sim.GUIs.getColor;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class MainMenu extends ComponentInputGUI{
    
    private Vec2 start = new Vec2(350, 0);
    
    public MainMenu(String name) {
        
        super(name, (GUIInputComponent) null);
        
        inputs.add(new GUIButton("start", this, null, null, "Start", Color.black));
        inputs.add(new GUIButton("preset", this, null, null, "Preset", Color.black));
        inputs.add(new GUIButton("settings", this, null, null, "Settings", Color.black));
        
        for (int i = 0; i < 3; i++) {
            
            components.add(new GUIPanel("top" + i, null, null, getColor(0).multiply(1.0 - 0.2 * i)));
        }
    }

    @Override
    public void recieve(String string, Object o) {

        
    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return inputs.get(0);
    }
}

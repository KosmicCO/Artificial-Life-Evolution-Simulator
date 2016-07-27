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

/**
 *
 * @author Kosmic
 */
public class MainMenu extends ComponentInputGUI{
    
    
    public MainMenu(String name) {
        
        super(name, (GUIInputComponent) null);
        
        inputs.add(new GUIButton("start", this, null /*it goes*/, null, "Start Simulation", Color.black));
        
        for (int i = 0; i < 3; i++) {
            
            components.add(new GUIPanel("startP", null, null, getColor(0)));
        }
        
    }

    @Override
    public void recieve(String string, Object o) {

        
    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        //start button
        return null;
    }
}

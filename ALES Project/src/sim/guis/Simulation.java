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
import org.newdawn.slick.Color;
import util.Vec2;
import static utility.GUIs.getColor;

/**
 *
 * @author Kosmic
 */
public class Simulation extends ComponentInputGUI{
    
    public Simulation(String n) {
        
        super(n);
        
        inputs.add(new GUIButton("plane", this, new Vec2(0, 0), new Vec2(500), " ", Color.transparent));
        components.add(new GUIPanel("planeP", new Vec2(0, 0), new Vec2(500), getColor(2)));
    }
    
    public void start(){
        
        this.setVisible(true);
        typing(this, true);
    }

    @Override
    public void recieve(String string, Object o) {
        
        switch(string){
            
            case "plane":
                
                break;
        }
    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }
    
    
}

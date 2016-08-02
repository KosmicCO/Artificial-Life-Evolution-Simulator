/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import gui.GUIController;
import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import sim.Preset;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;

/**
 *
 * @author Kosmic
 */
public class Presets extends ComponentInputGUI {

    private MainMenu parent;
    private NewPreset newPre;
    
    private List<Preset> presets;
    private List<GUIButton> buttons;
    private List<GUIPanel> panels;
    private int panIndex;

    public Presets(String name, MainMenu parent) {

        super(name);

        this.parent = parent;
        presets = new ArrayList();
        buttons = new ArrayList();
        inputs.addAll(buttons);
        panels = new ArrayList();
        panIndex = 0;
        
        for (int i = 0; i < 6; i++) {
            
            buttons.add(new GUIButton("button" + i, this, nextPlace(parent.getStartPos(), 1, -i), BUTTON_SIZE, "-None-", Color.white));
            panels.add(new GUIPanel("panel" + i, nextPlace(parent.getStartPos(), 1, -i), BUTTON_SIZE, getColor(0).multiply(0.7 - 0.1 * i)));
        }

        inputs.add(new GUIButton("new", this, nextPlace(parent.getStartPos(), 1, 1), BUTTON_SIZE, "New Preset", Color.white));
        inputs.add(new GUIButton("back", this, nextPlace(parent.getStartPos(), 1, 2), BUTTON_SIZE, "Back", Color.white));

        components.add(new GUIPanel("middle", nextPlace(parent.getStartPos(), 1, 1), BUTTON_SIZE, getColor(1).multiply(0.7)));
        components.add(new GUIPanel("bottom", nextPlace(parent.getStartPos(), 1, 2), BUTTON_SIZE, getColor(1).multiply(0.6)));
        
        newPre = new NewPreset("presetCreate", this);
        GUIController.add(newPre);
    }

    public Vec2 getStartPos(){
        
        return parent.getStartPos();
    }
    
    public void start() {

        this.setVisible(true);
        typing(this, true);
    }

    @Override
    public void draw() {

        super.draw();
        panels.forEach(GUIPanel::draw);
        buttons.forEach(GUIButton::draw);
    }

    @Override
    public void recieve(String string, Object o) {

        if(string.equals("new")){
            
            newPre.start();
        }
        
        if (string.equals("back")) {
            
            setVisible(false);
            typing(parent, true);
        }

    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }
}

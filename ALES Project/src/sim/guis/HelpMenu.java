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
 * @author bhargav
 */
public class HelpMenu extends ComponentInputGUI {

    private MainMenu parent;

    public HelpMenu(String n, MainMenu parent) {
        super(n);
        this.parent = parent;
        inputs.add(new GUIButton("exit", this, new Vec2(0), BUTTON_SIZE, "exit", Color.white));
        components.add(new GUIPanel("exit", new Vec2(0), BUTTON_SIZE, getColor(1)));
    }

    @Override
    public void recieve(String string, Object o) {
        switch (string) {
            case "exit":
                this.setVisible(false);
                System.out.println("HIT IT");
                break;
        }
    }

    public void start() {

        this.setVisible(true);
        typing(this, true);
    
    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }

}

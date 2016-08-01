/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import graphics.Graphics2D;
import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import org.newdawn.slick.Color;
import util.Color4;
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
    private int selected;

    public Presets(String name, MainMenu parent) {

        super(name);

        selected = 0;

        this.parent = parent;

        inputs.add(new GUIButton("pre0", this, nextPlace(parent.getStartPos(), 1, -2), BUTTON_SIZE, "Plenty Preset", Color.white));
        inputs.add(new GUIButton("pre1", this, nextPlace(parent.getStartPos(), 1, -1), BUTTON_SIZE, "Uneven Preset", Color.white));
        inputs.add(new GUIButton("custom", this, nextPlace(parent.getStartPos(), 1, 0), BUTTON_SIZE, "Custom Preset", Color.white));

        inputs.add(new GUIButton("back", this, nextPlace(parent.getStartPos(), 1, 1), BUTTON_SIZE, "Back", Color.white));

        for (int i = 0; i < 3; i++) {

            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 1, -i), BUTTON_SIZE, getColor(0).multiply(0.7 - 0.1 * i)));
        }

        components.add(new GUIPanel("bottom", nextPlace(parent.getStartPos(), 1, 1), BUTTON_SIZE, getColor(1).multiply(0.7)));
    }

    public void start() {

        this.setVisible(true);
        typing(this, true);
    }

    @Override
    public void draw() {

        super.draw();
        Graphics2D.drawWideLine(nextPlace(parent.getStartPos(), 1, -3), nextPlace(parent.getStartPos(), 1, 1), Color4.YELLOW, 2);
        Graphics2D.drawWideLine(nextPlace(parent.getStartPos(), 2, selected - 3).subtract(new Vec2(5, 0)), nextPlace(parent.getStartPos(), 2, selected - 2).subtract(new Vec2(5, 0)), Color4.RED.multiply(0.6), 2);
    }

    @Override
    public void recieve(String string, Object o) {

        if (string.contains("pre")) {

            selected = Integer.parseInt(string.substring(3));
        } else if (string.equals("back")) {
            
            setVisible(false);
            typing(parent, true);
        }

    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }
}

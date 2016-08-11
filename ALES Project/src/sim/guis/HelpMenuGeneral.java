/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sim.guis;

import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUIListOutputField;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import org.newdawn.slick.Color;
import util.Color4;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.nextPlace;

/**
 *
 * @author bhargav
 */
public class HelpMenuGeneral  extends ComponentInputGUI{
    private HelpMenu parent;

    public HelpMenuGeneral(String n, HelpMenu parent) {
        super(n);
        this.parent = parent;
        GUIListOutputField prInfo = new GUIListOutputField("generalInfo", this, nextPlace(parent.getStartPos(), 1, -10), new Vec2(812, 512), Color.white);
        inputs.add(new GUIButton("cancel", this, nextPlace(parent.getStartPos(), 0, 2), BUTTON_SIZE, "Back", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 0, 2), BUTTON_SIZE, Color4.RED));
        prInfo.appendLine("Welcome to ALES (Artificial Life Evolution Simulator)! This is the general info page for some info about the program. "
                + "Throughout the simulation, you will see, in addition to the creatures four kinds of ground: "
                + "the ORANGE squares are food particles, from which cells can harness energy. The GREY blocks are walls, which cells cannot go through and must go around. "
                + "The BLACK blocks are pits, which your creatures can fall into if they have bad luck. The final type of ground is the general WHITE terrain which the creatures inhabit. "
                + "But you're probably wondering what you're seeing. You are viewing randomly generated creatures, but only initially. At some point in the simulation, the creatures "
                + "gain the facility to reproduce, and pass on their genes to the next generation, with mutations along the way. In a very simple sense, the program simulates evolution through multiple generations. "
                + "You can also create a preset in that respective menu! Go to the Preset Info help menu to learn about all of the preset parameters, and then you can create your own custom simulation with your personal settings. "
                + "Just enter in your preferences and press CREATE. Happy simulating!");
        components.add(prInfo);
        components.add(new GUIPanel("foodPanel", nextPlace(parent.getStartPos(), 0, -2), BUTTON_SIZE.multiply(new Vec2(0.75,1)), Color4.ORANGE));
        components.add(new GUIPanel("wallPanel", nextPlace(parent.getStartPos(), 0, -1), BUTTON_SIZE.multiply(new Vec2(0.75,1)), Color4.gray(0.5)));
        components.add(new GUIPanel("pitPanel", nextPlace(parent.getStartPos(), 0, 0), BUTTON_SIZE.multiply(new Vec2(0.75,1)), Color4.BLACK));
        components.add(new GUIButton("Food", this, nextPlace(parent.getStartPos(), 0, -2), BUTTON_SIZE.multiply(new Vec2(0.75,1)), "FOOD", Color.white));
        components.add(new GUIButton("Wall", this, nextPlace(parent.getStartPos(), 0, -1), BUTTON_SIZE.multiply(new Vec2(0.75,1)), "WALL", Color.white));
        components.add(new GUIButton("Pit", this, nextPlace(parent.getStartPos(), 0, 0), BUTTON_SIZE.multiply(new Vec2(0.75,1)), "PIT (black)", Color.white));
        
    }

    public void start() {
        this.setVisible(true);
        typing(this, true);
    }
    
    @Override
    public void recieve(String string, Object o) {
        switch (string) {
            case "cancel":
                this.setVisible(false);
                parent.setVisible(true);
                typing(parent, true);
        }
    }

    public int getFieldIndex(String str) {
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).getName().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }
}

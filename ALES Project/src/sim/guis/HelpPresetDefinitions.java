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
public class HelpPresetDefinitions extends ComponentInputGUI {

    private HelpMenu parent;

    public HelpPresetDefinitions(String n, HelpMenu parent) {
        super(n);
        this.parent = parent;
        GUIListOutputField prInfo = new GUIListOutputField("presetInfo", this, nextPlace(parent.getStartPos(), 1, -10), new Vec2(773, 512), Color.white);
        inputs.add(new GUIButton("cancel", this, nextPlace(parent.getStartPos(), 0, 2), BUTTON_SIZE, "Back", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 0, 2), BUTTON_SIZE, Color4.RED));
        prInfo.appendLine("ENERGY COSTS- The amount of energy lost every time a creature executes a particular action. REPRODUCTION THRESHOLD- The percentage of max energy a creature must have to be able to mate. "
                + "HUNTER THRESHOLD- The percentage of max energy a creature must have to be able to hunt other cells. "
                + "REPRODUCTION BUFFER- The percentage of max energy a creature must regain in order to be able to mate again. "
                + "ACTION RADIUS- The distance to which the creature will check for other cells or objects in its vicinity. "
                + "NUTRIENTS PER FOOD- The amount of nutrients gained per food particle. "
                + "HUNT YIELD- The percentage of cell energy gained by hunting and consuming a cell. "
                + "FOOD RESPAWN AMOUNT- How many food particles are regenerated at a time. "
                + "FOOD SPAWN RATE- The rate at which food particles are regenerated. "
                + "MUTANT FACTOR- The percentage of genes in the chromosome which are mutated despite the parental structure. "
                + "LENGTH VARIANCE- The maximum amount that a chromosome's length can change from generation to generation, affecting size and other traits. "
                + "WEIGHTED GENOME- A list of integers representing cells, with a greater integer at a certain point. A list of 12 integers, each separated by dashes (e.g. '12-18-4-16-22-56-20-34-26-12-33-16'). "
                + "NUMBER OF CREATURES- The number of creatures initially generated on the map. ");
        components.add(prInfo);
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

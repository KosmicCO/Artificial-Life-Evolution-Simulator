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
public class HelpMenuControls extends ComponentInputGUI{
    private HelpMenu parent;

    public HelpMenuControls(String n, HelpMenu parent) {
        super(n);
        this.parent = parent;
        GUIListOutputField prInfo = new GUIListOutputField("generalInfo", this, nextPlace(parent.getStartPos(), 1, -10), new Vec2(786, 512), Color.white);
        inputs.add(new GUIButton("cancel", this, nextPlace(parent.getStartPos(), 0, 2), BUTTON_SIZE, "Back", Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(), 0, 2), BUTTON_SIZE, Color4.RED));
        prInfo.appendLine("When you start the simulation, it will open up rather close up. Just use the SCROLL on your mouse to ZOOM OUT. You can SCROLL in to ZOOM back in as well. "
                + "And while you're at it, you can use the ARROW KEYS to move your zoomed in view of the map. To PAUSE the simulation, press the SPACEBAR. While you are paused, you "
                + "can also CYCLE THROUGH TICKS of the program by pressing the 'S' KEY.");
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

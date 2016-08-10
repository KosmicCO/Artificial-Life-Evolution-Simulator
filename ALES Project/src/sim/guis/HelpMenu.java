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
import org.newdawn.slick.Color;
import util.Color4;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.nextPlace;

/**
 *
 * @author bhargav
 */
public class HelpMenu extends ComponentInputGUI {

    private MainMenu parent;
    private HelpKey cellKey;
    private HelpPresetDefinitions presetKey;
    private HelpMenuGeneral helpGen;

    public HelpMenu(String n, MainMenu parent) {
        super(n);
        this.parent = parent;
        inputs.add(new GUIButton("exit", this, nextPlace(parent.getStartPos(), 0, 2), BUTTON_SIZE, "Back", Color.white));
        components.add(new GUIPanel("exitPanel", nextPlace(parent.getStartPos(), 0, 2), BUTTON_SIZE, Color4.RED));
        inputs.add(new GUIButton("key", this, nextPlace(parent.getStartPos(), 0, 1), BUTTON_SIZE, "Cell Key", Color.white));
        components.add(new GUIPanel("keyPanel", nextPlace(parent.getStartPos(), 0, 1), BUTTON_SIZE, Color4.BLUE));
        cellKey = new HelpKey("key", this);
        inputs.add(new GUIButton("presetKey", this, nextPlace(parent.getStartPos(), 0, 0), BUTTON_SIZE, "Preset Info", Color.white));
        components.add(new GUIPanel("prKeyPanel", nextPlace(parent.getStartPos(), 0, 0), BUTTON_SIZE, Color4.BLUE.multiply(.4)));
        presetKey = new HelpPresetDefinitions("prKey", this);
        inputs.add(new GUIButton("generalInfoButton", this, nextPlace(parent.getStartPos(),0,-1),BUTTON_SIZE, "General Info", Color.white));
        components.add(new GUIPanel("genInfoPanel", nextPlace(parent.getStartPos(), 0, -1), BUTTON_SIZE, Color4.BLUE.multiply(.8)));
        helpGen = new HelpMenuGeneral("helpGen", this);
        GUIController.add(cellKey);
        GUIController.add(presetKey);
        GUIController.add(helpGen);
        
    }

    @Override
    public void recieve(String string, Object o) {
        switch (string) {
            case "exit":
                this.setVisible(false);
                typing(parent, true);
                parent.setVisible(true);
                //System.out.println("HIT IT");
                break;
            case "key":
                this.setVisible(false);
                cellKey.start();
                break;
            case "presetKey":
                this.setVisible(false);
                presetKey.start();
                break;
            case "generalInfoButton":
                this.setVisible(false);
                helpGen.start();
        }
    }

    public Vec2 getStartPos() {
        return parent.getStartPos();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUILabel;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import org.newdawn.slick.Color;
import util.Color4;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.nextPlace;

/**
 *
 * @author bhargav
 */
public class HelpKey extends ComponentInputGUI {

    private HelpMenu parent;

    public HelpKey(String n, HelpMenu parent) {
        super(n);
        this.parent = parent;
        components.add(new GUIPanel("cell0", nextPlace(parent.getStartPos(), 2, 0), BUTTON_SIZE, Color4.BLUE.withG(1)));
        components.add(new GUILabel("cell0", nextPlace(parent.getStartPos(), 2, 0), "   Core Cell", Color.black));
        components.add(new GUIPanel("cell1", nextPlace(parent.getStartPos(), 2, -1), BUTTON_SIZE, Color4.BLACK));
        components.add(new GUILabel("cell1", nextPlace(parent.getStartPos(), 2, -1), "   Dead Cell", Color.white));
        components.add(new GUIPanel("cell2", nextPlace(parent.getStartPos(), 2, -2), BUTTON_SIZE, Color4.YELLOW));
        components.add(new GUILabel("cell2", nextPlace(parent.getStartPos(), 2, -2), "  Forager Cell", Color.black));
        components.add(new GUIPanel("cell3", nextPlace(parent.getStartPos(), 2, -3), BUTTON_SIZE, Color4.PURPLE));
        components.add(new GUILabel("cell3", nextPlace(parent.getStartPos(), 2, -3), "  Hunter Cell", Color.white));
        components.add(new GUIPanel("cell4", nextPlace(parent.getStartPos(), 3, 0), BUTTON_SIZE, Color4.BLUE.multiply(0.4)));
        components.add(new GUILabel("cell4", nextPlace(parent.getStartPos(),3, 0), "   'Up' Cell", Color.white));
        components.add(new GUIPanel("cell5", nextPlace(parent.getStartPos(), 3, -1), BUTTON_SIZE, Color4.BLUE.multiply(0.6)));
        components.add(new GUILabel("cell5", nextPlace(parent.getStartPos(), 3, -1), " 'Right' Cell", Color.white));
        components.add(new GUIPanel("cell6", nextPlace(parent.getStartPos(), 3, -2), BUTTON_SIZE, Color4.BLUE.multiply(0.8)));
        components.add(new GUILabel("cell6", nextPlace(parent.getStartPos(), 3, -2), "  'Down' Cell", Color.white));
        components.add(new GUIPanel("cell7", nextPlace(parent.getStartPos(), 3, -3), BUTTON_SIZE, Color4.BLUE));
        components.add(new GUILabel("cell7", nextPlace(parent.getStartPos(), 3, -3), "  'Left' Cell", Color.white));
        components.add(new GUIPanel("cell8", nextPlace(parent.getStartPos(), 4, 0), BUTTON_SIZE, Color4.RED));
        components.add(new GUILabel("cell8", nextPlace(parent.getStartPos(), 4, 0), " Mating Cell", Color.white));
        components.add(new GUIPanel("cell9", nextPlace(parent.getStartPos(), 4, -1), BUTTON_SIZE, Color4.GREEN));
        components.add(new GUILabel("cell9", nextPlace(parent.getStartPos(), 4, -1), " Storage Cell", Color.white));
        components.add(new GUIPanel("cell10", nextPlace(parent.getStartPos(),4, -2), BUTTON_SIZE, Color4.RED.withR(.4)));
        components.add(new GUILabel("cell10", nextPlace(parent.getStartPos(), 4, -2), "Structure Cell", Color.white));
        components.add(new GUIPanel("cell11", nextPlace(parent.getStartPos(), 4, -3), BUTTON_SIZE, Color4.GREEN.multiply(0.2)));
        components.add(new GUILabel("cell11", nextPlace(parent.getStartPos(), 4, -3), " Detector Cell", Color.white));
        inputs.add(new GUIButton("cancel", this, nextPlace(parent.getStartPos(),0,2),BUTTON_SIZE,"Back",Color.white));
        components.add(new GUIPanel("cancelPanel", nextPlace(parent.getStartPos(),0,2),BUTTON_SIZE,Color4.RED));
    }

    public void start() {

        this.setVisible(true);
        typing(this, true);

    }

    @Override
    public void recieve(String string, Object o) {
       switch(string){
           case "cancel":
               this.setVisible(false);
               parent.setVisible(true);
               typing(parent,true);
       }
    }

    @Override
    public GUIInputComponent getDefaultComponent() {
        return null;
    }

}

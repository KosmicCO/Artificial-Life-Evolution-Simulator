/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import graphics.Graphics2D;
import gui.GUIController;
import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUILabel;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import static map.Terrain.currentT;
import org.newdawn.slick.Color;
import static sim.SimGenerator.generate;
import util.Color4;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class MainMenu extends ComponentInputGUI {

    private Vec2 start = new Vec2(-512, -156);
    private boolean init = false;

    private boolean selected;
    private GUIPanel hide;

    private Presets pre;
    private Simulation sim;
    private HelpMenu help;

    private boolean loading;
    private int progress;
    private GUILabel load;

    public MainMenu(String name) {

        super(name);

        inputs.add(new GUIButton("start", this, nextPlace(start, 0, -2), BUTTON_SIZE, "Start", Color.white));
        inputs.add(new GUIButton("presets", this, nextPlace(start, 0, -1), BUTTON_SIZE, "Presets", Color.white));
        inputs.add(new GUIButton("help", this, nextPlace(start, 0, 0), BUTTON_SIZE, "Help", Color.white));

        inputs.add(new GUIButton("quit", this, nextPlace(start, 0, 1), BUTTON_SIZE, "Quit", Color.white));

        for (int i = 0; i < 3; i++) {

            components.add(new GUIPanel("top" + i, nextPlace(start, 0, -i), BUTTON_SIZE, getColor(0).multiply(0.8 - 0.1 * i)));
        }

        components.add(new GUIPanel("bottom", nextPlace(start, 0, 1), BUTTON_SIZE, getColor(1).multiply(0.6)));

        selected = false;
        hide = new GUIPanel("hide", nextPlace(start, 0, 1), BUTTON_SIZE.multiply(new Vec2(1, 4)), Color4.BLACK.withA(0.6));
        load = new GUILabel("loading", nextPlace(start, 0, -2), BUTTON_SIZE, "Loading", Color.white);

        pre = new Presets("presets", this);
        help = new HelpMenu("help", this);
        GUIController.add(pre);
    }

    public void progress(int p) {

        progress = p;
    }

    public Vec2 getStartPos() {

        return start;
    }

    public void start() {

        this.setVisible(true);
        typing(this, true);
        selected = true;
    }

    @Override
    public void recieve(String string, Object o) {

        switch (string) {

            case "start":

                if (currentT == null) {

                    generate();
                }

                if (!init) {

                    sim = new Simulation("simulation", this);
                    GUIController.add(sim);
                }

                ((GUIButton) inputs.get(0)).setLabel("Continue");

                this.setVisible(false);
                typing(this, false);
                sim.start();
                break;

            case "presets":

                pre.start();
                selected = false;
                break;

            case "help":

                System.out.println("clicked");
                this.setVisible(false);
                help.start();
                System.out.println("TRIGGERED");
                break;

            case "quit":

                System.exit(0);
        }
    }

    @Override
    public void draw() {

        super.draw();

        if (!selected) {

            hide.draw();
        }

        if (loading) {
            
            Graphics2D.drawRect(nextPlace(start, 0, -2), BUTTON_SIZE.multiply(new Vec2(100 / progress, 1)), Color4.BLUE);
            load.draw();
        }
    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import creature.Creature;
import static creature.Creature.SIDE_LENGTH;
import creature.cells.Cell;
import engine.Input;
import graphics.Graphics2D;
import gui.components.GUIButton;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import static map.Terrain.currentT;
import org.newdawn.slick.Color;
import util.Vec2;
import static utility.GUIs.getColor;
import static map.Terrain.ORIGIN;
import static sim.Start.setRunning;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.nextPlace;
import static gui.TypingManager.typing;
import java.util.List;

/**
 *
 * @author Kosmic
 */
public class Simulation extends ComponentInputGUI {

    private static int zoom = 2;
    
    private Vec2 start = new Vec2(0, -150);
    private MainMenu parent;
    
    private static Vec2 offsetToDraw = new Vec2(2, 250 - SIDE_LENGTH * 8);
    private Creature toDraw;

    public Simulation(String n, MainMenu parent) {

        super(n);

        inputs.add(new GUIButton("back", this, nextPlace(start, 0, 1), BUTTON_SIZE, "Main Menu", Color.white));
        components.add(new GUIPanel("bottom", nextPlace(start, 0, 1), BUTTON_SIZE, getColor(1).multiply(0.6)));

        inputs.add(new GUIButton("plane", this, new Vec2(-500, -250), new Vec2(500), " ", Color.transparent));
        components.add(new GUIPanel("planeP", new Vec2(-500, -250), new Vec2(500), getColor(2)));

        this.parent = parent;
    }

    public static int getZoom() {

        return zoom;
    }

    public static void setZoom(int zoom) {

        Simulation.zoom = zoom;
    }

    public void start() {

        this.setVisible(true);
        typing(this, true);
        setRunning(true);
    }

    @Override
    public void recieve(String string, Object o) {

        switch (string) {

            case "back":
                setRunning(false);
                this.setVisible(false);
                parent.start();

            case "plane":

                Cell c = currentT.cellAtAbsPos((int) (Input.getMouse().x - ORIGIN.x) / 2, (int) (Input.getMouse().y - ORIGIN.y) / 2);
                if (c != null) {

                    toDraw = c.getCreature();
                }
                break;
        }
    }

    public void setInspect(Creature c) {

        toDraw = c;
    }

    @Override
    public void update() {

        super.update();

        if (!currentT.isAlive(toDraw)) {

            toDraw = null;
        }
    }

    @Override
    public void draw() {

        super.draw();
        currentT.draw();

        if (toDraw != null) {

            Graphics2D.fillRect(offsetToDraw, new Vec2(SIDE_LENGTH * 8), getColor(2));
            currentT.drawSection(offsetToDraw, new Vec2(toDraw.getPosX(), toDraw.getPosY()), SIDE_LENGTH, 8);
            Vec2 tdPos = new Vec2(toDraw.getPosX(), toDraw.getPosY());
            List<Creature> draws = currentT.creaturesInSec(tdPos, tdPos.add(new Vec2(SIDE_LENGTH)));

            for (Creature c : draws) {

                Vec2 p = new Vec2(c.getPosX(), c.getPosY());
                c.drawCut(tdPos, tdPos.add(new Vec2(SIDE_LENGTH)), offsetToDraw.add(p.subtract(tdPos).multiply(8)), 8);
            }
        }
    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }
}

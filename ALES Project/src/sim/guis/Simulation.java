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
import gui.components.GUILabel;
import gui.types.GUIComponent;
import java.util.ArrayList;
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
    private List<GUILabel> stats;
    private List<GUIButton> goToParents;
    private List<GUIPanel> gtpPanels;

    public Simulation(String n, MainMenu parent) {

        super(n);

        goToParents = new ArrayList();
        gtpPanels = new ArrayList();

        for (int i = 0; i < 2; i++) {

            goToParents.add(new GUIButton("parent" + (i + 1), this, new Vec2(i * (SIDE_LENGTH * 8) / 2 + offsetToDraw.x, 50), new Vec2((SIDE_LENGTH * 8) / 2, 32), "Parent " + (i + 1), Color.white));
            gtpPanels.add(new GUIPanel("pp" + (i + 1), new Vec2(i * (SIDE_LENGTH * 8) / 2 + offsetToDraw.x, 50), new Vec2((SIDE_LENGTH * 8) / 2, 32), getColor(0).multiply(0.8 - 0.1 * i)));
        }

        inputs.add(new GUIButton("back", this, nextPlace(start, 0, 1), BUTTON_SIZE, "Main Menu", Color.white));
        components.add(new GUIPanel("bottom", nextPlace(start, 0, 1), BUTTON_SIZE, getColor(1).multiply(0.6)));

        inputs.add(new GUIButton("plane", this, new Vec2(-500, -250), new Vec2(500), " ", Color.transparent));
        components.add(new GUIPanel("planeP", new Vec2(-500, -250), new Vec2(500), getColor(2)));

        stats = new ArrayList();

        for (int i = 0; i < 8; i++) {

            stats.add(new GUILabel("stat" + i, new Vec2(SIDE_LENGTH * 8 + offsetToDraw.x * 2 + 4, 225 - 25 * i), "Stat " + i, Color.white));
        }

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

            case "parent1":

                //toDraw = the parent 1
                System.out.println("p1");
                break;

            case "parent2":

                //toDraw = the parent 2
                System.out.println("p2");
                break;

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
        } else {

            stats.get(0).setLabel("Creature Mode:      " + toDraw.getModeName());
            stats.get(1).setLabel("Energy Loss/Tick:   " + toDraw.getEnergyPerTick());
            stats.get(2).setLabel("Energy Stored:      " + toDraw.getEnergy());
            stats.get(3).setLabel("% of Max Energy:    " + ((int) (((double) toDraw.getEnergy() / toDraw.getMaxStore()) * 100)) + "%");

            if (((double) toDraw.getEnergy() / toDraw.getMaxStore()) * 100 <= 20) {

                stats.get(2).setColor(Color.red);
                stats.get(3).setColor(Color.red);
            } else {

                stats.get(2).setColor(Color.white);
                stats.get(3).setColor(Color.white);
            }

            stats.get(4).setLabel("Max Energy Storage: " + toDraw.getMaxStore());
            stats.get(5).setLabel("Children Spawned:   " + toDraw.getChildrenSpawned());
            stats.get(6).setLabel("Cells Eaten:        " + toDraw.getCellsEaten());
            stats.get(7).setLabel("Food Eaten:         " + toDraw.getFoodParticlesConsumed());
        }
    }

    @Override
    public List<GUIComponent> mousePressed(Vec2 p) {

        List<GUIComponent> pressed = new ArrayList();
        pressed = super.mousePressed(p);

        if (toDraw != null) {

            for (GUIButton gip : goToParents) {

                if (gip.containsClick(p)) {

                    pressed.add(gip);
                }
            }
        }

        return pressed;
    }

    @Override
    public void draw() {

        super.draw();
        currentT.draw();

        if (toDraw != null) {

            stats.forEach(GUILabel::draw);

            gtpPanels.forEach(GUIPanel::draw);
            goToParents.forEach(GUIButton::draw);

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

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
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.nextPlace;
import gui.components.GUILabel;
import gui.types.GUIComponent;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;
import util.Color4;
import static sim.Start.setRunning;
import static gui.TypingManager.typing;
import sim.SimGenerator;
import static sim.Start.isPaused;
import static sim.Start.setPaused;

/**
 *
 * @author Kosmic
 */
public class Simulation extends ComponentInputGUI {

    private static int zoom = 2;

    private Vec2 start = new Vec2(0, -150);
    private boolean init = false;
    private MainMenu parent;

    private static Vec2 offsetToDraw = new Vec2(2, 250 - SIDE_LENGTH * 8);
    private Creature toDraw;
    private List<GUILabel> stats;
    private List<GUIButton> goToParents;
    private GUIPanel gtpPanel;
    private GUILabel noParents;

    private static Vec2 moveSize = new Vec2(24, 16);
    private List<GUIButton> moveButtons;
    private List<GUIPanel> movePanels;

    public Simulation(String n, MainMenu parent) {

        super(n);

        goToParents = new ArrayList();
        moveButtons = new ArrayList();
        movePanels = new ArrayList();

        for (int i = 0; i < 2; i++) {

            goToParents.add(new GUIButton("parent" + (i + 1), this, new Vec2(i * (SIDE_LENGTH * 4) + offsetToDraw.x, 50), new Vec2((SIDE_LENGTH * 4), 32), "Parent " + (i + 1), Color.white));
        }

        moveButtons.add(new GUIButton("move0", this, offsetToDraw.add(new Vec2(SIDE_LENGTH * 4, SIDE_LENGTH * 8 - moveSize.y)).subtract(moveSize.withY(0).divide(2)), moveSize, " ", Color.transparent));
        movePanels.add(new GUIPanel("mp0", offsetToDraw.add(new Vec2(SIDE_LENGTH * 4, SIDE_LENGTH * 8 - moveSize.y)).subtract(moveSize.withY(0).divide(2)), moveSize, Color4.BLACK.withA(0.6)));

        moveButtons.add(new GUIButton("move1", this, offsetToDraw.add(new Vec2(SIDE_LENGTH * 8 - moveSize.x, SIDE_LENGTH * 4)).subtract(moveSize.withY(moveSize.x / 2).withX(0)), moveSize.normal().multiply(new Vec2(-1, 1)), " ", Color.transparent));
        movePanels.add(new GUIPanel("mp1", offsetToDraw.add(new Vec2(SIDE_LENGTH * 8 - moveSize.y, SIDE_LENGTH * 4)).subtract(moveSize.withY(moveSize.x / 2).withX(0)), moveSize.normal().multiply(new Vec2(-1, 1)), Color4.BLACK.withA(0.6)));

        moveButtons.add(new GUIButton("move2", this, offsetToDraw.add(new Vec2(SIDE_LENGTH * 4, 0)).subtract(moveSize.withY(0).divide(2)), moveSize, " ", Color.transparent));
        movePanels.add(new GUIPanel("mp2", offsetToDraw.add(new Vec2(SIDE_LENGTH * 4, 0)).subtract(moveSize.withY(0).divide(2)), moveSize, Color4.BLACK.withA(0.6)));

        moveButtons.add(new GUIButton("mov3", this, offsetToDraw.add(new Vec2(0, SIDE_LENGTH * 4)).subtract(moveSize.withY(moveSize.x / 2).withX(0)), moveSize.normal().multiply(new Vec2(-1, 1)), " ", Color.transparent));
        movePanels.add(new GUIPanel("mp3", offsetToDraw.add(new Vec2(0, SIDE_LENGTH * 4)).subtract(moveSize.withY(moveSize.x / 2).withX(0)), moveSize.normal().multiply(new Vec2(-1, 1)), Color4.BLACK.withA(0.6)));

        gtpPanel = new GUIPanel("pp", new Vec2(offsetToDraw.x, 50), new Vec2(SIDE_LENGTH * 8, 32), getColor(0).multiply(0.8));
        noParents = new GUILabel("np", new Vec2(offsetToDraw.x, 50), new Vec2(SIDE_LENGTH * 8, 32), "No Parents", Color.white);

        inputs.add(new GUIButton("back", this, nextPlace(start, 0, 1), BUTTON_SIZE, "Main Menu", Color.white));
        components.add(new GUIPanel("bottom", nextPlace(start, 0, 1), BUTTON_SIZE, getColor(1).multiply(0.6)));

        inputs.add(new GUIButton("plane", this, new Vec2(-500, -250), new Vec2(500), " ", Color.transparent));
        components.add(new GUIPanel("planeP", new Vec2(-500, -250), new Vec2(500), getColor(2)));
        
        inputs.add(new GUIButton("regenerate", this, nextPlace(start, 0, 0), BUTTON_SIZE, "New Map", Color.white));
        components.add(new GUIPanel("regenMap", nextPlace(start, 0, 0), BUTTON_SIZE, getColor(0)));

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
        toDraw = null;

        if (!init) {

            Input.whenKey(Keyboard.KEY_SPACE, true).onEvent(() -> {

                setPaused(!isPaused());
            });

            Input.whenKey(Keyboard.KEY_S, true).onEvent(() -> {

                if (isPaused()) {
                    
                    currentT.update();
                }
            });

            init = true;
        }
    }

    @Override
    public void recieve(String string, Object o) {

        if (string.contains("move")) {

            int dir = Integer.parseInt(string.substring(4));
            toDraw.setCurDir(dir);
        }

        switch (string) {

            case "parent1":

                toDraw = toDraw.getParent1();
                break;

            case "parent2":

                toDraw = toDraw.getParent2();
                break;

            case "back":

                setRunning(false);
                this.setVisible(false);
                parent.start();

            case "regenerate":
                SimGenerator.generate();
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

            if (toDraw.getParent1() != null) {

                for (GUIButton gip : goToParents) {

                    if (gip.containsClick(p)) {

                        pressed.add(gip);
                    }
                }
            }

            for (GUIButton gip : moveButtons) {

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
            gtpPanel.draw();

            if (toDraw.getParent1() != null) {

                goToParents.forEach(GUIButton::draw);
            } else {

                noParents.draw();
            }

            Graphics2D.fillRect(offsetToDraw, new Vec2(SIDE_LENGTH * 8), getColor(2));
            currentT.drawSection(offsetToDraw, new Vec2(toDraw.getPosX(), toDraw.getPosY()), SIDE_LENGTH, 8);
            Vec2 tdPos = new Vec2(toDraw.getPosX(), toDraw.getPosY());
            List<Creature> draws = currentT.creaturesInSec(tdPos, tdPos.add(new Vec2(SIDE_LENGTH)));

            for (Creature c : draws) {

                Vec2 p = new Vec2(c.getPosX(), c.getPosY());
                c.drawCut(tdPos, tdPos.add(new Vec2(SIDE_LENGTH)), offsetToDraw.add(p.subtract(tdPos).multiply(8)), 8);
            }

            movePanels.forEach(GUIPanel::draw);
        }
    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }
}

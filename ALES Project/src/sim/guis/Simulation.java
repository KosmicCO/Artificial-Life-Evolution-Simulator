/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import creature.Creature;
import creature.cells.Cell;
import engine.Input;
import gui.components.GUIButton;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import static map.Terrain.currentT;
import org.newdawn.slick.Color;
import util.Vec2;
import static utility.GUIs.getColor;
import static gui.TypingManager.typing;
import static map.Terrain.ORIGIN;
import static sim.Start.setRunning;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.nextPlace;

/**
 *
 * @author Kosmic
 */
public class Simulation extends ComponentInputGUI{
    
    private static int zoom = 2;
    private Vec2 start = new Vec2(0, -150);
    private MainMenu parent;
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
    
    public void start(){
        
        this.setVisible(true);
        typing(this, true);
        setRunning(true);
    }

    @Override
    public void recieve(String string, Object o) {
        
        switch(string){
            
            case "back":
                setRunning(false);
                this.setVisible(false);
                parent.start();
            
            case "plane":
                
                Cell c = currentT.cellAtAbsPos((int) (Input.getMouse().x - ORIGIN.x) / 2, (int) (Input.getMouse().y - ORIGIN.y) / 2);
                if(c != null){
                    
                    toDraw = c.getCreature();
                }
                break;
        }
    }
    
    public void setInspect(Creature c){
        
        toDraw = c;
    }
    
    @Override
    public void update(){
        
        super.update();
        
        if(!currentT.isAlive(toDraw)){
            
            toDraw = null;
        }
    }

    @Override
    public void draw() {
        
        super.draw();
        currentT.draw();
        
        if(toDraw != null){
            
            toDraw.drawNoPos(Vec2.ZERO, 8);
        }
    }
    
    

    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }
}
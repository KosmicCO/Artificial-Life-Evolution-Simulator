/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import sim.guis.MainMenu;
import engine.Core;
import engine.Input;
import graphics.Window2D;
import gui.GUIController;
import gui.TypingManager;
import static map.Terrain.currentT;
import org.lwjgl.input.Keyboard;
import static sim.TestGenMap.makeTestMap;
import static sim.SimGenerator.generate;
import util.Color4;

/**
 *
 * @author Kosmic
 */
public class Start {

    private static long updates;
    private static final int UPDATES_PER_TICK = 20;
    private static boolean running;
    private static boolean paused;

    public static boolean isRunning() {
        
        return running;
    }

    public static void setRunning(boolean running) {
        
        Start.running = running;
    }

    public static boolean isPaused() {
        
        return paused;
    }

    public static void setPaused(boolean paused) {
        
        Start.paused = paused;
    }
    
    public static void main(String[] args) {

        Core.screenWidth = 1000;
        Core.screenHeight = 500;
        Core.title = "ALES by Cruz & Bhargav";
        Core.is3D = false;

        Core.init();
        
        Window2D.background = Color4.BLACK;

        //testing graphics start
        generate(); //makeTestMap();

        //testing graphics end
        MainMenu main = new MainMenu("mainMenu");

        TypingManager tm = new TypingManager(main);
        GUIController.add(main);
        main.start();

        Core.renderLayer(100).onEvent(GUIController::draw);

        Core.update.onEvent(() -> {

            GUIController.update();

            if (running && !paused) {
                
                if (updates % UPDATES_PER_TICK == 0) {
                   
                    currentT.update();
                }

                updates++;
            }
        });

        Core.run();
    }
}

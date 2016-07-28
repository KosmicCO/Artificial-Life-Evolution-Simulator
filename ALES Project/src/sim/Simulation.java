/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import sim.guis.MainMenu;
import engine.Core;
import graphics.Window2D;
import gui.GUIController;
import gui.TypingManager;
import gui.types.ComponentInputGUI;
import util.Color4;

/**
 *
 * @author Kosmic
 */
public class Simulation {
    
    private static long updates;
    private static final int UPDATES_PER_TICK = 5;
    
    public static void main(String[] args) {
        
        Core.screenHeight = 500;
        Core.screenWidth = 1000;
        Core.title = "ALES by CEB";
        Core.is3D = true;
        
        Core.init();
        
        Window2D.background = Color4.BLACK;
        
        MainMenu main = new MainMenu("mainMenu");
        
        TypingManager tm = new TypingManager(main);
        GUIController.add(main);
        main.start();
        
        Core.render.onEvent(GUIController::draw);
        
        Core.update.onEvent(() -> {
        
            GUIController.update();
            
            if(updates % UPDATES_PER_TICK == 0){
                
                //creature simulation;
            }
            
            updates++;
        });
        
        Core.run();
    }
}

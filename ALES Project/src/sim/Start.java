/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import sim.guis.MainMenu;
import engine.Core;
import graphics.Window2D;
import graphics.Window3D;
import gui.GUIController;
import gui.TypingManager;
import util.Color4;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class Start {
    
    private static long updates;
    private static final int UPDATES_PER_TICK = 5;
    
    public static void main(String[] args) {
        
        Core.screenWidth = 1000;
        Core.screenHeight = 500;
        Core.title = "ALES by Cruz & Bhargav";
        Core.is3D = false;
        
        Core.init();
        
        Window2D.background = Color4.WHITE;
        
        MainMenu main = new MainMenu("mainMenu");
        
        TypingManager tm = new TypingManager(main);
        GUIController.add(main);
        main.start();
        
        Core.renderLayer(100).onEvent(GUIController::draw);
        
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

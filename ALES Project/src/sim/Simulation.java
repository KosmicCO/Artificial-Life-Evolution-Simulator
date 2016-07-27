/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import sim.guis.MainMenu;
import engine.Core;
import graphics.Window2D;
import gui.GUI;
import gui.GUIController;
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
        
        GUI main = new MainMenu("mainMenu");
        
        GUIController.add(main);
        
        main.setVisible(true);
        
        Core.render.onEvent(GUIController::draw);
        
        Core.update.onEvent(() -> {
        
            if(updates % UPDATES_PER_TICK == 0){
                
                //creature simulation;
            }
            
            updates++;
        });
        
        Core.run();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature;

import static map.Terrain.currentT;

/**
 *
 * @author Kosmic
 */
public class NewBehavior {
    
    public static int energyPerDetect = -4;
    public static int energyPerMove = -8;
    
    private static void move(int dir, Creature cre) {
        
        if (cre.getUsedCells()[dir] > 0) {
            
            cre.addEnergy(energyPerMove / cre.getUsedCells()[dir]);
            currentT.move(dir, cre);
        }
    }
    
    private static boolean detect(Creature cre) {
        
        if (cre.getUsedCells()[4] > 0) {
            
            cre.addEnergy(energyPerDetect / cre.getUsedCells()[4]);
            
            if (cre.detectMode()) {
                
                return true;
            }
        }
        return false;
    }
    
    public static void step(Creature cre) {
        
        int x = cre.getPosX();
        int y = cre.getPosY();
        
        move(cre.getCurDir(), cre);
        
        if (x == cre.getPosX() && y == cre.getPosY()) {
            
            cre.setCurDir((int) (Math.random() * 4));
        }
        
        if (cre.detectMode()) {
            
            cre.doModeAction();
        }
    }
}

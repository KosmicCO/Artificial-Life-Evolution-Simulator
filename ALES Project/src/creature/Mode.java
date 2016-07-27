/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature;

/**
 *
 * @author Kosmic
 */
public enum Mode {
    
    REPRODUCE(Cell.ReproductionCell.class),
    HUNT(Cell.HunterCell.class),
    FORAGE(Cell.ForagerCell.class);
    
    private final Class<? extends Cell> preload;
    
    private Mode(Class<? extends Cell> preload){
        
        this.preload = preload;
    }

    public Class<? extends Cell> getPreload() {
        
        return preload;
    }
}

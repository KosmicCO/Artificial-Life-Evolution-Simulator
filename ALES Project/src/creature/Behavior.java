/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package creature;

import java.util.List;

/**
 *
 * @author bhargav
 */
public class Behavior {
    
    private List<Integer> mainBehavior;
    private List<List<Integer>> subBehaviors;
    private Creature creature;
    
    private int commIndex, index;
    
    public Behavior(List<Integer> main, List<List<Integer>> sub){
        
        mainBehavior = main;
        subBehaviors = sub;
        commIndex = index = 0;
    }

    public void setCreature(Creature creature) {
        
        this.creature = creature;
    }
    
    private void doComm(int comm, int redir){
        
        switch(comm){
            
            case 0:
                //move up
                break;
            case 1:
                //move right
                break;
            case 2:
                //move down
                break;
            case 3:
                //move left
                break;
            case 4:
                //mode sense
                break;
            case 5:
                //mode action
                break;
            case 6:
                //sense wall
                break;
            case 7:
                //sense pit
                break;
            case 8:
                
                commIndex = redir % subBehaviors.size() + 1;
                index = 0;
                break;
        }
    }
    
    public void step(){
        
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature;

import java.util.List;
import static map.Terrain.currentT;

/**
 *
 * @author bhargav
 */
public class Behavior {

    private List<Integer> mainBehavior;
    private List<List<Integer>> subBehaviors;
    private Creature creature;

    private int commIndex, index;

    public Behavior(List<Integer> main, List<List<Integer>> sub) {

        mainBehavior = main;
        subBehaviors = sub;
        commIndex = index = 0;
    }

    public void setCreature(Creature creature) {

        this.creature = creature;
    }

    private void doComm(int comm, int redir) {

        switch (comm) {

            case 0:
                
                currentT.move(0, creature);
                break;
            case 1:
                
                currentT.move(1, creature);
                break;
            case 2:
                
                currentT.move(2, creature);
                break;
            case 3:
                
                currentT.move(3, creature);
                break;
            case 4:
                
                if (creature.detectMode()) {
                    
                    redirect(redir);
                }
                break;
            case 5:
                
                creature.doModeAction();
                break;
            case 6:
                
                if (currentT.detect(creature, 3)) {
                    
                    redirect(redir);
                }
                break;
            case 7:
                
                if (currentT.detect(creature, 4)) {
                    
                    redirect(redir);
                }
                break;
            case 8:
                
                redirect(redir);
                break;
        }
    }

    private void redirect(int redir) {

        commIndex = redir % subBehaviors.size() + 1;
        index = 0;
    }

    public void step() {

    }
}

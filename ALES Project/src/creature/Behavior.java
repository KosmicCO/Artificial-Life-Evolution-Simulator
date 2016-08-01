/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature;

import static creature.genetics.BehaviorInterpreter.GOTO_FUNC;
import static creature.genetics.BehaviorInterpreter.MODE_SENSE;
import static creature.genetics.BehaviorInterpreter.SENSE_PIT;
import static creature.genetics.BehaviorInterpreter.SENSE_WALL;
import java.util.List;
import static map.Terrain.currentT;

/**
 *
 * @author bhargav
 */
public class Behavior {

    private final List<Integer> mainBehavior;
    private final List<List<Integer>> subBehaviors;
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

    public void reset() {

        commIndex = 0;
        index = 0;
    }

    public void step() {

        List<Integer> bhv = (commIndex == 0 ? mainBehavior : subBehaviors.get(commIndex - 1));

        if (bhv.size() > index) {

            int comm = bhv.get(index);

            if (comm == GOTO_FUNC || comm == MODE_SENSE || comm == SENSE_WALL || comm == SENSE_PIT) {

                if (bhv.size() > index + 1) {

                    doComm(comm, bhv.get(index + 1));
                    index += 2;
                } else {

                    reset();
                }
            } else {

                doComm(comm, 0);
                index++;
            }
        }else{
            
            reset();
        }
    }

    @Override
    public String toString() {
        
        String s = mainBehavior.toString();
        
        for (List<Integer> bhv : subBehaviors) {
            
            s += "\n- " + bhv.toString();
        }
        
        return s;
    }
    
    
}

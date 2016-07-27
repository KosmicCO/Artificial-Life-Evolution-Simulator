/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package map;

import creature.Creature;
import java.util.List;

/**
 *
 * @author bhargav
 */
public class Terrain {
    private final int[][] environment;
    public final static int FOOD = 1;
    public final static int OBSTACLE = 2;
    public final static int PIT = -1;
    private int width;
    private int height;
    private List<Creature> population;
            
    public Terrain(int[][] generated){
        environment = generated;
        height = generated.length;
        width = generated[0].length;
        //population also to be instantiated by generator
    }
}


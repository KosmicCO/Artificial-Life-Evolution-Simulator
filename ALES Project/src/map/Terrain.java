/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package map;

import creature.cells.Cell;
import creature.Creature;
import java.util.ArrayList;
import java.util.List;
import util.Vec2;

/**
 *
 * @author bhargav
 */
public class Terrain {
    
    public static Terrain currentT;
    
    private final int[][] environment;
    public final static int FOOD = 1;
    public final static int OBSTACLE = 2;
    public final static int PIT = -1;
    
    private int width;
    private int height;
    private List<Creature> population;
            
    public Terrain(int[][] generated, List<Creature> pop){
        environment = generated;
        height = generated.length;
        width = generated[0].length;
        population = pop;
    }
    
    public Cell cellAtAbsPos(int x, int y){
        List<Creature> creaturesAtPos = new ArrayList<Creature>();
        for (Creature c : population){
            int xDiff = x-c.getPosX();
            int yDiff = y-c.getPosY();
            if (xDiff<Creature.SIDE_LENGTH&&xDiff>=0){
                if(yDiff<Creature.SIDE_LENGTH&&yDiff>=0){
                    creaturesAtPos.add(c);
                }
            }
        }
        for(Creature c : creaturesAtPos){
            Cell found = c.cellAtRelPos(x-c.getPosX(), y-c.getPosY());
            if (found != null){
                return found;
            }
        }
        return null;
    }
    
    public void draw(){
        
        for(Creature cre : population){
            
            cre.draw(new Vec2(-500, -250));
        }
        
        //for the terrain too
    }
}
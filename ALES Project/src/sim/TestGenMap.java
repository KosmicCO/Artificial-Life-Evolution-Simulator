/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import creature.Creature;
import static creature.Creature.SIDE_LENGTH;
import creature.cells.Cell;
import creature.cells.StructureCell;
import java.util.ArrayList;
import java.util.List;
import map.Terrain;

/**
 *
 * @author Kosmic
 */
public class TestGenMap {
    
    public static void makeTestMap(){
        
        Cell[][] ca = new Cell[SIDE_LENGTH][SIDE_LENGTH];
        
        for (int i = 0; i < SIDE_LENGTH; i++) {
            
            for (int j = 0; j < SIDE_LENGTH; j++) {
                
                ca[i][j] = Math.random() < 0.1 ? null : new StructureCell(i, j);
            }
        }
        
        List<Creature> cl = new ArrayList();
        
        cl.add(new Creature(ca, 0, null, 1, 1));
        cl.add(new Creature(ca, 0, null, 80, 30));
        cl.add(new Creature(ca, 0, null, 50, 50));
        
        for (Cell[] row : ca) {
            
            for(Cell c : row){
                
                System.out.print(c != null ? "X" : " ");
            }
            
            System.out.println();
        }
        
        Terrain t = new Terrain(new int[250][250], cl);
        
        Terrain.currentT = t;
    }
}

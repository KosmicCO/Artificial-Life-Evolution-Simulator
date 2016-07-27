/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapTest;

import creature.cells.Cell;
import creature.Creature;
import creature.cells.StructureCell;
import java.util.ArrayList;
import java.util.List;
import map.Terrain;

/**
 *
 * @author bhargav
 */
public class TerrainTest {
    public static void main(String[] args) {
        int[][] env = new int[20][20];
        Cell[][] cellArray = new Cell[Creature.SIDE_LENGTH][Creature.SIDE_LENGTH];
        for (int row = 0; row<cellArray.length; row++){
            for(int c = 0; c<cellArray[0].length; c++){
                cellArray[row][c] = new StructureCell(row,c);
            }
        }
        List<Creature> pop = new ArrayList<Creature>();
        Creature test1 = new Creature(cellArray, 0, null, 0, 0);
        pop.add(test1);
        Terrain map = new Terrain(env,pop);
        Cell c = map.cellAtAbsPos(1, 0);
        System.out.println(c);
        System.out.println(test1);
    }
}

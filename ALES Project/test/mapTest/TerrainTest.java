/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapTest;

import creature.Cell;
import creature.Creature;
import map.Terrain;

/**
 *
 * @author bhargav
 */
public class TerrainTest {
    public static void main(String[] args) {
        int[][] env = new int[20][20];
        Cell[][] cellArray = new Cell[Creature.SIDE_LENGTH][Creature.SIDE_LENGTH];
        Terrain map = new Terrain(env);
        Creature test1 = new Creature(cellArray, 0, null, 3, 3);
        Creature test2 = new Creature(cellArray, 0, null, 2, 2);
        Cell c = map.cellAtAbsPos(4, 4);
        System.out.println(c);
    }
}

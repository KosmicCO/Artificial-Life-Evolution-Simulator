/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature.genetics;

import creature.Creature;
import creature.cells.*;
import java.util.ArrayList;
import java.util.List;
import util.Vec2;
import utility.Conversions;
import static utility.Mapping.ADX4;
import static utility.Mapping.ADY4;

/**
 *
 * @author bhargav
 */
public class StructureInterpreter {

    private static int up = 0;
    private static int right = 1;
    private static int down = 2;
    private static int left = 3;

    public static final int CELL_TYPES = 11;
    private static List<Integer> weightedGenome = new ArrayList<>();
    private static List<Integer> geneRefList = new ArrayList<>();

    public static List<Integer> getWeightedGenome() {
        List<Integer> w = new ArrayList<>();
        for (int count = 0; count < weightedGenome.size(); count++) {
            int i = weightedGenome.get(count);
            w.add(i);
        }
        return w;
    }

    public static List<Integer> getRefList() {
        List<Integer> g = new ArrayList<>();
        for (int count = 0; count < geneRefList.size(); count++) {
            int i = geneRefList.get(count);
            g.add(i);
        }
        return g;
    }

    public static void setWeightedGenome(List<Integer> w) {
        weightedGenome = w;
        geneReferenceGen();
    }

    private static Cell newCell(int i, int x, int y) {
        switch (i) {
            case 0:
                return new CoreCell(x, y);
            case 1:
                return new DeadCell(x, y);
            case 2:
                return new ForagerCell(x, y);
            case 3:
                return new HunterCell(x, y);
            case 4:
                return new MotorCell(up, x, y);
            case 5:
                return new MotorCell(right, x, y);
            case 6:
                return new MotorCell(down, x, y);
            case 7:
                return new MotorCell(left, x, y);
            case 8:
                return new ReproductionCell(x, y);
            case 9:
                return new StorageCell(x, y);
            case 10:
                return new StructureCell(x, y);
            case 11:
                return new DetectorCell(x, y);
            default:
                return null;
        }
    }

    public static Cell[][] interpret(Chromosome c) {
        List<Vec2> nodes = new ArrayList();
        nodes.add(new Vec2(Creature.SIDE_LENGTH / 2));
        int[][] cellMap = new int[Creature.SIDE_LENGTH][Creature.SIDE_LENGTH];
        for (int[] i : cellMap) {
            for (int j = 0; j < i.length; j++) {
                i[j] = -1;
            }
        }
        cellMap[Creature.SIDE_LENGTH / 2][Creature.SIDE_LENGTH / 2] = -2;
        for (int i = 0; i < c.geneCount(); i++) {
            int gene = geneToCode(Conversions.byteToInt(c.getSegment(i)));
            if (nodes.size() <= i) {
                break;
            }
            if (gene != CELL_TYPES) {

                for (int j = 0; j < 4; j++) {

                    Vec2 adj = nodes.get(i).add(new Vec2(ADX4[j], ADY4[j]));
                    boolean inBounds = adj.x < Creature.SIDE_LENGTH && adj.y < Creature.SIDE_LENGTH && adj.x > 0 && adj.y > 0;
                    if (inBounds && cellMap[(int) adj.x][(int) adj.y] == -1) {
                        nodes.add(adj);
                        cellMap[(int) adj.x][(int) adj.y] = -2;
                    }

                }
            }

            cellMap[(int) nodes.get(i).x][(int) nodes.get(i).y] = gene;
        }
        Cell[][] creatureStructure = new Cell[Creature.SIDE_LENGTH][Creature.SIDE_LENGTH];
        for (int row = 0; row < Creature.SIDE_LENGTH; row++) {
            for (int cell = 0; cell < Creature.SIDE_LENGTH; cell++) {
                creatureStructure[row][cell] = newCell(cellMap[row][cell], row, cell);
            }
        }
        return creatureStructure;
    }

    private static void geneReferenceGen() {
        for (int j = 0; j < weightedGenome.size(); j++) {
            for (int k = 0; k < weightedGenome.get(j); k++) {
                int ind = (int) (Math.random() * geneRefList.size());
                geneRefList.add(ind, j);
            }
        }
    }

    private static int geneToCode(int ind) {
        int j = geneRefList.get(ind);
        return j;
    }

}

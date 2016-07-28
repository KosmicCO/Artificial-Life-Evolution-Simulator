/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature.genetics;

import creature.cells.*;

/**
 *
 * @author bhargav
 */
public class GeneInterpreter {

    private static int left = 0;
    private static int right = 1;
    private static int down = 2;
    private static int up = 3;

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
                return new MotorCell(left, x, y);
            case 5:
                return new MotorCell(right, x, y);
            case 6:
                return new MotorCell(down, x, y);
            case 7:
                return new MotorCell(up, x, y);
            case 8:
                return new ReproductionCell(x, y);
            case 9:
                return new StorageCell(x, y);
            case 10:
                return new StructureCell(x, y);
        }
        return null;
    }

}

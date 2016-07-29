/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature.cells;

/**
 *
 * @author bhargav
 */
public class HunterCell extends Cell {

    public HunterCell(int x, int y) {

        super(3, 2, 1, x, y);
    }

    @Override
    public int getCellType() {

        return 3;
    }
}

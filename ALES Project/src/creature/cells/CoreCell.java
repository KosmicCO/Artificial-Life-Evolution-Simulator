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

public class CoreCell extends Cell {

    /**
     * Constructs a core cell with the given position
     * 
     * @param x The x-coordinate of the initial position of the cell
     * @param y The y-coordinate of the initial position of the cell
     */
    public CoreCell(int x, int y) {

        super(1, 2000, 4, x, y);
    }

    @Override
    public int getCellType() {
        return 0;
    }
}

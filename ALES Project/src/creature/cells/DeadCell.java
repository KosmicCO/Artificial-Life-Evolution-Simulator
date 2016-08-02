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
public class DeadCell extends Cell {

    /**
     *  Constructs a dead cell with the given position
     *
     * @param x The x-coordinate of the initial position of the cell
     * @param y The y-coordinate of the initial position of the cell
     */
    public DeadCell(int x, int y) {

        super(6, 0, 0, x, y);
    }

    @Override
    public int getCellType() {

        return 1;
    }
}

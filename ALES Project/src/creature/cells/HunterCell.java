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

    /**
     * Constructs a hunter cell at the given position. The cell will function to hunt other cells.
     * 
     * @param x The x-coordinate of the initial position of the cell
     * @param y The y-coordinate of the initial position of the cell
     */
    public HunterCell(int x, int y) {

        super(3, 500, 1, x, y);
    }

    @Override
    public int getCellType() {

        return 3;
    }
}

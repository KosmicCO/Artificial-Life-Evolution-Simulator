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
public class ForagerCell extends Cell {

    /**
     * Constructs a forager cell at the given position, which will consume food around the cell.
     * 
     * @param x The x-coordinate of the initial position of the cell
     * @param y The y-coordinate of the initial position of the cell
     */
    public ForagerCell(int x, int y) {

        super(2, 100, 1, x, y);
    }

    @Override
    public int getCellType() {

        return 2;
    }
}

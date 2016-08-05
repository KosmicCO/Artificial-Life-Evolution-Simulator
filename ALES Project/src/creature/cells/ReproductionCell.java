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
public class ReproductionCell extends Cell {

    /**
     * Constructs a reproduction cell at the given position. This cell will reproduce with other cells to create child creatures and facilitate evolution.
     * 
     * @param x The x-coordinate of the initial position of the cell
     * @param y The y-coordinate of the initial position of the cell
     */
    public ReproductionCell(int x, int y) {

        super(1, 500, 3, x, y);
    }

    @Override
    public int getCellType() {

        return 8;
    }

}

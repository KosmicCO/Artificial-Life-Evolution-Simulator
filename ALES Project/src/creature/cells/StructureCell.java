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
public class StructureCell extends Cell {

    /**
     * Constructs a structure cell at the given position. This cell is solely a structural component of the creature.
     * 
     * @param x The x-coordinate of the initial position of the cell
     * @param y The y-coordinate of the initial position of the cell
     */
    public StructureCell(int x, int y) {

        super(4, 5, 1, x, y);
    }

    @Override
    public int getCellType() {

        return 10;
    }
}

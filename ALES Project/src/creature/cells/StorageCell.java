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
public class StorageCell extends Cell {

    /**
     * Constructs a storage cell at the given position.
     * 
     * @param x The x-coordinate of the initial position of the cell
     * @param y The y-coordinate of the initial position of the cell
     */
    public StorageCell(int x, int y) {

        super(4, 25, 3, x, y);
    }

    @Override
    public int getCellType() {

        return 9;
    }
}

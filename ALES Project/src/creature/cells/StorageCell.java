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

    public StorageCell(int x, int y) {

        super(4, 8, 3, x, y);
    }

    @Override
    public int getCellType() {

        return 9;
    }
}

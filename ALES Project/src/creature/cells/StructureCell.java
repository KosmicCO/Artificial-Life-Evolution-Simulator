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

    public StructureCell(int x, int y) {

        super(4, 2, 1, x, y);
    }

    @Override
    public int getCellType() {

        return 10;
    }
}

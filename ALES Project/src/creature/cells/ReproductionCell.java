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

    public ReproductionCell(int x, int y) {

        super(1, 2, 3, x, y);
    }

    @Override
    public int getCellType() {

        return 8;
    }

}

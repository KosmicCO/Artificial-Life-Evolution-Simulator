/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature.cells;

/**
 *
 * @author Kosmic
 */
public class DetectorCell extends Cell{

    public DetectorCell(int x, int y) {
        
        super(2, 2, 2, x, y);
    }

    @Override
    public int getCellType() {

        return 11;
    }
    
}

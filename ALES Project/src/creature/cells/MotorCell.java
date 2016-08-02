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
public class MotorCell extends Cell {

    private final int direction;

    
    /**
     * Constructs a motor cell at the given position. This cell exists to propel the cell in different directions.
     * 
     * @param direction The direction in which the motor cell will move
     * @param x The x-coordinate of the initial position of the cell
     * @param y The y-coordinate of the initial position of the cell
     */
    public MotorCell(int direction, int x, int y) {

        super(3, 2, 1, x, y);
        this.direction = direction;
    }

    @Override
    public int getCellType() {

        return 4 + direction;
    }
}

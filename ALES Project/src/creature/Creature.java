/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature;

import creature.genetics.Chromosome;
import java.util.ArrayList;
import java.util.List;
import static utility.Mapping.ADX4;
import static utility.Mapping.ADY4;

/**
 *
 * @author Kosmic
 */
public class Creature {

    public static final int SIDE_LENGTH = 10;
    
    private final List<Cell> cells;
    private final Cell[][] cellMap;
    private final List<Chromosome> genes;

    private int posX;
    private int posY;
    private List<Cell> modeCells;
    private List<Cell.MotorCell> motorCells;
    private Mode mode;
    private int maxStore;
    private int energy;

    public Creature(Cell[][] cellMap, int energy, List<Chromosome> genes, int x, int y) {

        this.cellMap = cellMap;
        cells = new ArrayList();
        this.genes = genes;
        maxStore = 0;
        posX = x;
        posY = y;

        for (Cell[] cellRow : cellMap) {

            for (Cell cell : cellRow) {

                if (cell != null) {

                    maxStore += cell.getMaxStore();
                    cells.add(cell);
                }
            }
        }

        this.energy = energy;

        if (maxStore < energy) {

            this.energy = maxStore;
        }
    }

    private List<Cell> findType(Class<? extends Cell> type) {

        List<Cell> found = new ArrayList();

        for (Cell cell : cells) {

            if (cell.getClass() == type) {

                found.add(cell);
            }
        }

        return found;
    }

    public void update() {

    }

    private void changeMode(Mode mode) {

        this.mode = mode;
        modeCells = findType(mode.getPreload());
    }

    private void checkFromMode() {

        switch (mode) {

            case REPRODUCE:

                for (Cell rc : modeCells) {

                    Cell.ReproductionCell r = (Cell.ReproductionCell) rc;
                    int x = r.getX();
                    int y = r.getY();

                    for (int i = 0; i < 4; i++) {

                        int rx = x + ADX4[i];
                        int ry = y + ADY4[i];

                        if (rx >= 0 && rx < cellMap.length && ry >= 0 && ry < cellMap[0].length) {

                            Cell c = cellMap[rx][ry];

                            if (c != null) {

                                if (c instanceof Cell.ReproductionCell) {

                                    //reproduce methode with itself
                                } else {

                                    //ask map, no boarder check needed
                                }
                            }
                        } else {

                            //if in the map boarder
                            //if reproductive cell
                            //if similar enough*
                            //reproduce with other organism
                        }
                    }
                }

                break;

            case HUNT:

                for (Cell hc : modeCells) {

                    Cell.HunterCell h = (Cell.HunterCell) hc;

                    int x = h.getX();
                    int y = h.getY();

                    for (int i = 0; i < 4; i++) {

                        int hx = x + ADX4[i];
                        int hy = y + ADY4[i];

                        if (/*inside map*/true) {

                            Cell c = cellMap[hx][hy];

                            if (!cells.contains(c)) {

                                //ask map to do damage to sell and return food value
                            }
                        }
                    }
                }

                break;

            case FORAGE:
                
                //check for food
                
                break;
        }
    }
    
    public Cell cellAtRelPos(int x, int y){
        return cellMap[x][y];
    }
    
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

}

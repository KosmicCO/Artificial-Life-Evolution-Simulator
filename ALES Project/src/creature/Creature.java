/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature;

import creature.cells.Cell;
import creature.cells.ForagerCell;
import creature.cells.HunterCell;
import creature.cells.MotorCell;
import creature.cells.ReproductionCell;
import creature.genetics.Chromosome;
import creature.genetics.StructureInterpreter;
import graphics.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import static sim.guis.Simulation.getZoom;
import util.Color4;
import util.Vec2;
import static utility.Mapping.ADX4;
import static utility.Mapping.ADY4;

/**
 *
 * @author Kosmic
 */
public class Creature {

    public static final int REPRODUCE = 0;
    public static final int HUNT = 1;
    public static final int FORAGE = 2;

    public static final int SIDE_LENGTH = 21;

    private final List<Cell> cells;
    private final Cell[][] cellMap;
    private final List<Chromosome> genes;

    private int posX;
    private int posY;
    private List<Cell> modeCells;
    private List<MotorCell> motorCells;
    private int mode;
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

                    cell.setCreature(this);
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

    private List<Cell> findType(int type) {

        List<Cell> found = new ArrayList();

        for (Cell cell : cells) {

            boolean isType;

            switch (type) {

                case REPRODUCE:
                    isType = cell instanceof ReproductionCell;
                    break;
                case HUNT:
                    isType = cell instanceof HunterCell;
                    break;
                case FORAGE:
                    isType = cell instanceof ForagerCell;
                    break;
                default:
                    isType = false;
            }

            if (isType) {

                found.add(cell);
            }
        }

        return found;
    }

    public void update() {

    }

    private void changeMode(int mode) {

        this.mode = mode;
        modeCells = findType(mode);
    }

    private void checkFromMode() {

        switch (mode) {

            case REPRODUCE:

                for (Cell rc : modeCells) {

                    ReproductionCell r = (ReproductionCell) rc;
                    int x = r.getX();
                    int y = r.getY();

                    for (int i = 0; i < 4; i++) {

                        int rx = x + ADX4[i];
                        int ry = y + ADY4[i];

                        if (rx >= 0 && rx < cellMap.length && ry >= 0 && ry < cellMap[0].length) {

                            Cell c = cellMap[rx][ry];

                            if (c != null) {

                                if (c instanceof ReproductionCell) {

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

                    HunterCell h = (HunterCell) hc;

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

                for (Cell fc : modeCells) {

                    HunterCell f = (HunterCell) fc;

                    int x = f.getX();
                    int y = f.getY();

                    for (int i = 0; i < 5; i++) {

                        int hx;
                        int hy;

                        if (i == 5) {

                            hx = x;
                            hy = y;
                        } else {

                            hx = x + ADX4[i];
                            hy = y + ADY4[i];
                        }

                        if (/*inside map && map at pos has food*/true) {

                            //collect food
                        }
                    }
                }

                break;
        }
    }

    public Cell cellAtRelPos(int x, int y) {
        return cellMap[x][y];
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Cell[][] getCellMap() {
        return cellMap;
    }

    public List<Chromosome> getGenes() {
        return genes;
    }

    @Override
    public String toString() {
        String s = "";
        for (int row = 0; row < cellMap.length; row++) {
            s += ("ROW " + row + ":\n");
            for (int i = 0; i < cellMap[row].length; i++) {
                s += (cellMap[row][i]) + "\n";
            }
            s += ("\n");
        }
        return s;
    }

    public void draw(Vec2 ScreenAbs) {

        for (Cell c : cells) {

            Graphics2D.fillRect(ScreenAbs.add(new Vec2((c.getX() + posX) * getZoom(), (c.getY() + posY) * getZoom())), new Vec2(getZoom()), Color4.BLUE);
        }
    }
    
    public Creature reproduce(Creature other, int x, int y){
        List<Chromosome> childGene = new ArrayList<>();
        int shortSize = this.genes.size();
        int slack = other.genes.size()-shortSize;
        Creature larger = other;
        if(shortSize>other.genes.size()){
            shortSize = other.genes.size();
            slack = this.genes.size()-shortSize;
            larger = this;
        }
        for(int i = 0; i < shortSize; i++){
            Chromosome m = this.genes.get(i);
            Chromosome f = other.genes.get(i);
            Chromosome cr = new Chromosome(m.reproduce(f));
            childGene.add(cr);
        }
        for(int i = 0; i<slack; i++){
            Chromosome cr = larger.genes.get(shortSize+i);
            childGene.add(cr);
        }
        Cell[][] childCellMap = StructureInterpreter.StructureGen(childGene.get(0));
        Creature child = new Creature(childCellMap, 0, childGene, x, y);
        return child;
    }
}

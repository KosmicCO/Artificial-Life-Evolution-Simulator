/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature;

import creature.cells.Cell;
import creature.genetics.BehaviorInterpreter;
import creature.genetics.Chromosome;
import creature.genetics.StructureInterpreter;
import graphics.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import static map.Terrain.currentT;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class Creature {

    public static final int SIDE_LENGTH = 21;
    public static int energyCostPerHunt = 4;
    public static int energyCostPerForage = 2;
    public static int energyCostPerRepro = 10;
    //USER VARIABLES ABOVE
            
    public static final int HUNT = 0;
    public static final int REPRODUCE = 1;
    public static final int FORAGE = 2;

    private final List<Cell> cells;
    private final Cell[][] cellMap;
    private final List<Behavior> behaviors;
    private final List<Chromosome> genes;

    private int posX;
    private int posY;
    
    private List<Cell> modeCells;
    private int[] usedCells; //up right down left detector
    private int mode;
    private boolean modeToggle;
    
    private int maxStore;
    private int energy;
    private int energyPerTick;

    public Creature(Cell[][] cellMap, List<Behavior> behaviors, int energy, List<Chromosome> genes, int x, int y) {

        this.cellMap = cellMap;
        cells = new ArrayList();
        this.behaviors = behaviors;
        modeCells = new ArrayList();
        

        for (Behavior b : behaviors) {

            b.setCreature(this);
        }

        this.genes = genes;
        maxStore = 0;
        energyPerTick = 0;
        posX = x;
        posY = y;
        usedCells = new int[5];
        modeToggle = false;

        for (Cell[] cellRow : cellMap) {

            for (Cell cell : cellRow) {

                if (cell != null) {

                    cell.setCreature(this);
                    maxStore += cell.getMaxStore();
                    energyPerTick += cell.getEnergy();
                    cells.add(cell);

                    if ((cell.getCellType() >= 4 && cell.getCellType() <= 7)) {

                        usedCells[cell.getCellType() - 4]++;

                    } else if (cell.getCellType() == 11) {

                        usedCells[4]++;
                    }
                }
            }
        }
        
        changeMode(FORAGE);
        this.energy = energy;

        if (maxStore < energy) {

            this.energy = maxStore;
        }
    }
    
    public void deleteCell(Cell ce) {

        if (ce.getCellType() >= 4 && ce.getCellType() < 8) {

            usedCells[ce.getCellType() - 4]--;
        }

        if (ce.getCellType() == 11) {

            usedCells[4]--;
        }

        modeCells.remove(ce);
        cells.remove(ce);

        cellMap[ce.getX()][ce.getY()] = null;
        maxStore -= ce.getMaxStore();
        energyPerTick -= ce.getEnergy();
        checkVitality();
    }
    
    public void toggleMode(){
        
        modeToggle = !modeToggle;
    }

    public boolean isModeToggle() {
        
        return modeToggle;
    }

    public void setModeToggle(boolean modeToggle) {
        
        this.modeToggle = modeToggle;
    }
    
    public void addEnergy(int en){
        
        energy += en;
    }

    public void checkEnergy() {

        if (maxStore < energy) {

            energy = maxStore;
        }

        if (energy <= 0) {
            currentT.kill(this);
        }
    }

    public void checkVitality() {

        boolean hasCore = false;

        for (Cell c : cells) {

            hasCore |= c.getCellType() == 0;

            if (hasCore) {

                break;
            }
        }

        if (!hasCore) {

            currentT.kill(this);
        }
    }

    public int[] getUsedCells() {
        return usedCells;
    }

    public void update() {

        if(modeToggle){
            
            doModeAction();
        }
        
        behaviors.get(mode).step();
        energy -= energyPerTick;
        checkEnergy();
    }

    private List<Cell> findType(int type) {

        List<Cell> found = new ArrayList();

        for (Cell c : cells) {

            if (c.getCellType() == type) {

                found.add(c);
            }
        }

        return found;
    }

    public List<Cell> getCells() {
        return cells;
    }
    
    

    private void changeMode(int mode) {

        behaviors.get(this.mode).reset();
        modeCells = findType(mode == FORAGE ? 2 : (mode == HUNT ? 3 : 8));
        modeToggle = false;
        this.mode = mode;
    }

    public boolean detectMode() {

        return currentT.detect(this, mode);
    }

    public Cell cellAtRelPos(int x, int y) {
        return cellMap[x][y];
    }

    public int getPosX() {
        return posX;
    }

    public List<Cell> getModeCells() {
        return modeCells;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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

    public void draw(Vec2 ScreenAbs, int zoom) {

        for (Cell c : cells) {

            Graphics2D.fillRect(ScreenAbs.add(new Vec2((c.getX() + posX) * zoom, (c.getY() + posY) * zoom)), new Vec2(zoom), Cell.cellColor(c.getCellType()));
        }
    }

    public void doModeAction() {
        
        switch (mode) {
            
            case HUNT:
                
                this.addEnergy(currentT.hunt(modeCells) - energyCostPerHunt);
                break;
                
            case FORAGE:
                this.addEnergy(currentT.forage(modeCells) - energyCostPerForage);
                break;
                
            case REPRODUCE:
                
                currentT.reproduce(this);
                energy -= energyCostPerRepro;
                break;
        }
    }

    /**
     * Generates a child creature given two creatures by combining chromosomes.
     * Mutations are handled at the chromosomal level.
     *
     * @param other The creature's partner, the genetic material of which will
     * be used to produce the child creature
     * @return A child creature generated by combining the chromosomes from each
     * creature, the chromosomes of which are combined with the chromosomal
     * reproduce method
     */
    public Creature reproduce(Creature other) {
        List<Chromosome> childGene = new ArrayList<>();
        int shortSize = this.genes.size();
        int slack = other.genes.size() - shortSize;
        Creature larger = other;
        if (shortSize > other.genes.size()) {
            shortSize = other.genes.size();
            slack = this.genes.size() - shortSize;
            larger = this;
        }
        for (int i = 0; i < shortSize; i++) {
            Chromosome m = this.genes.get(i);
            Chromosome f = other.genes.get(i);
            Chromosome cr = m.reproduce(f);
            childGene.add(cr);
        }
        for (int i = 0; i < slack; i++) {
            Chromosome cr = larger.genes.get(shortSize + i);
            childGene.add(cr);
        }
        Cell[][] childCellMap = StructureInterpreter.interpret(childGene.get(0));

        List<Behavior> bhvs = new ArrayList();

        for (int i = 1; i < 4; i++) {

            bhvs.add(BehaviorInterpreter.interpret(childGene.get(i)));
        }
        Creature child = new Creature(childCellMap, bhvs, 0, childGene, 0, 0);
        return child;
    }
}

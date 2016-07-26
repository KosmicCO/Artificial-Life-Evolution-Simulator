/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature;

/**
 *
 * @author Kosmic
 */
public abstract class Cell {

    //private Color color;
    protected final Creature creature;
    protected int posX;
    protected int posY;
    
    private int hp;
    private final int maxHP;
    private final int maxStore;
    private final int energy;

    public Cell(int maxHP, int maxStore, int energy, Creature creature, int x, int y) {

        this.maxHP = hp = maxHP;
        this.maxStore = maxStore;
        this.energy = energy;
        this.creature = creature;
        //this.color = color;
    }

    //color getter

    public Creature getCreature() {
        
        return creature;
    }
    
    public int getMaxStore() {
        
        return maxStore;
    }
    
    public int getHp() {

        return hp;
    }

    public void setHp(int hp) {

        this.hp = hp;
    }

    public int getMaxHP() {

        return maxHP;
    }

    public int getEnergy() {

        return energy;
    }
    
    public boolean damage(){
        
        hp--;
        return hp <= 0;
    }

    public int getX() {
        
        return posX;
    }

    public void setX(int posX) {
        
        this.posX = posX;
    }

    public int getY() {
        
        return posY;
    }

    public void setY(int posY) {
        
        this.posY = posY;
    }

    public class DeadCell extends Cell {

        public DeadCell(Creature creature, int x, int y) {

            super(6, 0, 0, creature, x, y);
        }
    }
    
    public class StructureCell extends Cell{

        public StructureCell(Creature creature, int x, int y) {
            
            super(4, 2, 1, creature, x, y);
        }
    }
    
    public class CoreCell extends Cell{

        public CoreCell(Creature creature, int x, int y) {
            
            super(1, 6, 4, creature, x, y);
        }
    }
    
//    public class HealerCell extends Cell{
//
//        public HealerCell(Creature creature, int x, int y) {
//            
//            super(3, 3, 3, creature, x, y);
//        }
//
//        @Override
//        public void doAction() {
//
//            //creature.heal cells near (x, y)
//        }
//    }
    
    public class ReproductionCell extends Cell{

        public ReproductionCell(int maxHP, int maxStore, int energy, Creature creature, int x, int y) {
            
            super(1, 2, 3, creature, x, y);
        }

//        @Override
//        public void doAction() {
//
//            //creature.use energy -USER VAR-
//            //make a baby
//            //make creature check for 2 reproductive cells that are near each other
//        }
    }
    
    public class HunterCell extends Cell{

        public HunterCell(Creature creature, int x, int y) {
            
            super(3, 2, 1, creature, x, y);
        }

//        @Override
//        public void doAction() {
//
//            //creature.use energy -USER VAR-
//            //ask map to return int of energy gained (creature asks map to do damage)
//        }
    }
    
    public class ForagerCell extends Cell{

        public ForagerCell(Creature creature, int x, int y) {
            
            super(2, 4, 1, creature, x, y);
        }

//        @Override
//        public void doAction() {
//
//            //creature.use energy -USER VAR-
//            //ask map to return int of energy gained (creature asks map to remove food)
//        }
    }
    
    public class MotorCell extends Cell{

        private final int direction;
        
        public MotorCell(int direction, Creature creature, int x, int y) {
            
            super(3, 2, 1, creature, x, y);
            this.direction = direction;
        }

//        @Override
//        public void doAction() {
//
//            //divide blocks by 5, 1 E per extra 5 blocks for motor cells facing the same way
//        }
    }
    
    public class StorageCell extends Cell {

        public StorageCell(Creature creature, int x, int y) {
            
            super(4, 8, 3, creature, x, y);
        }
    }
}

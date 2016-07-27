/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature.cells;

import creature.Creature;

/**
 *
 * @author Kosmic
 */
public abstract class Cell {

    //private Color color;
    protected Creature creature;
    protected int posX;
    protected int posY;
    
    private int hp;
    private final int maxHP;
    private final int maxStore;
    private final int energy;

    public Cell(int maxHP, int maxStore, int energy, int x, int y) {

        this.maxHP = hp = maxHP;
        this.maxStore = maxStore;
        this.energy = energy;
        this.creature = null;
        //this.color = color;
    }

    public void setCreature(Creature c){
        creature = c;
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
}
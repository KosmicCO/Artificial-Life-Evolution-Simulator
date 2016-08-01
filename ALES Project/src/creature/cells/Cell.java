/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature.cells;

import creature.Creature;
import util.Color4;

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
        this.posX = x;
        this.posY = y;
        //this.color = color;
    }

    public void setCreature(Creature c) {
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

    public boolean damage() {

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

    public static Color4 cellColor(int type) {

        //switch goes here
        switch (type) {
            case 0:
                return Color4.BLUE;
            case 1:
                return Color4.GREEN;
            case 2:
                return Color4.YELLOW;
            case 3:
                return Color4.PURPLE;
            case 4:
                return Color4.ORANGE;
            case 5:
                return Color4.ORANGE;
            case 6:
                return Color4.ORANGE;
            case 7:
                return Color4.ORANGE;
            case 8:
                return Color4.RED;
            case 9:
                return Color4.BLACK;
            case 10:
                return Color4.RED.withR(.144);
            default:
                return Color4.WHITE;
        }

    }

    public abstract int getCellType();

    @Override
    public String toString() {
        int absX = this.getX() + this.creature.getPosX();
        int absY = this.getY() + this.creature.getPosY();
        return "Cell Relative Position: \n X-Coordinate: " + this.getX() + "\n Y-Coordinate: " + this.getY()
                + "\nCell Absolute Position: \n X-Coordinate: " + absX + "\n Y-Coordinate: " + absY;
    }
}

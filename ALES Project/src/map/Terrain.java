/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import creature.cells.Cell;
import creature.Creature;
import graphics.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import static sim.guis.Simulation.getZoom;
import util.Color4;
import util.Vec2;
import utility.Mapping;

/**
 *
 * @author bhargav
 */
public class Terrain {

    public static int nutrientsPerFood = 1;
    public static double hunterYield = 0.60;

    public static Terrain currentT;

    private final static Vec2 ORIGIN = new Vec2(-500, -250);

    public final static int FOOD = 1;
    public final static int WALL = 2;
    public final static int PIT = 3;

    private final int[][] environment;
    private int width;
    private int height;
    private List<Creature> population;

    public Terrain(int[][] generated, List<Creature> pop) {
        environment = generated;
        height = generated.length;
        width = generated[0].length;
        population = pop;
    }

    /**
     * Adds a creature to the terrain-managed list of creatures, or population.
     * Creatures can only be visualized if they are added to the population.
     *
     * @param c the creature to be added to the population
     */
    public void addCreature(Creature c) {
        population.add(c);
    }

    public void moveCreature(int direction, Creature cr) {
        //directions: 0 is up, 1 is right, 2 is down, 3 is left
        boolean blocked = false;
        if (direction == 0) {
            for (int ind = 0; ind < cr.getCellMap().length; ind++) {
                for (Cell c : cr.getCellMap()[ind]) {
                    if (environment[ind][c.getY() + cr.getPosY() + 1] == 2) {
                        blocked = true;
                    }
                    Cell found = cellAtAbsPos(c.getX(), c.getY() + 1);
                    if (found != null && !found.getCreature().equals(cr)) {
                        blocked = true;
                    }
                }
            }
            if (!blocked) {

                int newY = cr.getPosY() + 1;
                cr.setPosY(newY);

            }
        }
        if (direction == 1) {
            for (int ind = 0; ind < cr.getCellMap().length; ind++) {
                for (Cell c : cr.getCellMap()[ind]) {
                    if (environment[ind][c.getX() + cr.getPosX() + 1] == 2) {
                        blocked = true;
                    }
                    Cell found = cellAtAbsPos(c.getX() + 1, c.getY());
                    if (found != null && !found.getCreature().equals(cr)) {
                        blocked = true;
                    }
                }
            }
            if (!blocked) {
                int newX = cr.getPosX() + 1;
                cr.setPosX(newX);
            }
        }
        if (direction == 2) {
            for (int ind = 0; ind < cr.getCellMap().length; ind++) {
                for (Cell c : cr.getCellMap()[ind]) {
                    if (environment[ind][c.getY() + cr.getPosY() - 1] == 2) {
                        blocked = true;
                    }
                    Cell found = cellAtAbsPos(c.getX(), c.getY() - 1);
                    if (found != null && !found.getCreature().equals(cr)) {
                        blocked = true;
                    }
                }
            }
            if (!blocked) {
                int newY = cr.getPosY() - 1;
                cr.setPosY(newY);
            }
        }
        if (direction == 3) {
            for (int ind = 0; ind < cr.getCellMap().length; ind++) {
                for (Cell c : cr.getCellMap()[ind]) {
                    if (environment[ind][c.getX() + cr.getPosX() - 1] == 2) {
                        blocked = true;
                    }
                    Cell found = cellAtAbsPos(c.getX() - 1, c.getY());
                    if (found != null && !found.getCreature().equals(cr)) {
                        blocked = true;
                    }
                }
            }
            if (!blocked) {
                int newX = cr.getPosX() - 1;
                cr.setPosX(newX);
            }
        }

    }

    public void update() {

        for (Creature cre : population) {

            cre.update();
        }
    }

    /**
     * Determines if a creature can be moved in a given direction, and if so,
     * updates the creature's absolute position accordingly.
     *
     * @param direction the direction in which Creature cr needs to move
     * @param cr the creature to move
     */
    public void move(int direction, Creature cr) {
        boolean blocked = false;
        int deltaX = Mapping.ADX4[direction];
        int deltaY = Mapping.ADY4[direction];
        for (int ind = 0; ind < cr.getCellMap().length; ind++) {
            for (Cell c : cr.getCellMap()[ind]) {
                if (c != null) {

                    int newAbsX = c.getX() + cr.getPosX() + deltaX;
                    int newAbsY = c.getY() + cr.getPosY() + deltaY;

                    if (newAbsX >= width || newAbsY >= height || newAbsX < 0 || newAbsY < 0) {
                        blocked = true;
                    } else if (environment[newAbsX][newAbsY] == 2) {
                        blocked = true;
                    }
                    Cell found = cellAtAbsPos(c.getX() + cr.getPosX() + deltaX, c.getY() + cr.getPosY() + deltaY);
                    if (found != null && !found.getCreature().equals(cr)) {
                        blocked = true;
                    }
                }
            }
        }
        if (!blocked) {
            int newX = cr.getPosX() + deltaX;
            cr.setPosX(newX);
            int newY = cr.getPosY() + deltaY;
            cr.setPosY(newY);
        }
    }

    /**
     *
     * @param cr the creature that is detecting objects within its vicinity
     * @param type type of object being detected, where 0 is any cell from
     * another creature, 1 is any reproductive cell, 2 is any food particle, 3
     * is any walls, 4 is any pit
     * @return if an object of the specified type is found within the creature's
     * bounds
     */
    public boolean detect(Creature cr, int type) {
        boolean found = false;
        for (int i = cr.getPosX(); i < cr.getPosX() + Creature.SIDE_LENGTH; i++) {
            for (int j = cr.getPosY(); j < cr.getPosY() + Creature.SIDE_LENGTH; j++) {
                Cell atLoc = cellAtAbsPos(i, j);
                if (type == 0) {
                    if (atLoc != null && !(atLoc.getCreature().equals(cr))) {
                        found = true;
                    }
                } else if (type == 1) {
                    if (atLoc != null && atLoc.getCellType() == 8) {
                        found = true;
                    }
                } else if (i >= 0 && j >= 0 && i < width && j < height && environment[i][j] == type - 1) {
                    found = true;
                }
            }
        }
        return found;
    }

    /**
     * Adds a newly generated creature to the map in a randomly assigned
     * position. If all of the 20 attempts to find a position for the creature
     * fail to locate an empty slot, then Creature will be added to the map
     * off-screen
     *
     * @param child The given reproduced creature to be added to the map
     */
    public void spawn(Creature child) {
        //boolean inMap = false;
        int finalX = -Creature.SIDE_LENGTH;
        int finalY = -Creature.SIDE_LENGTH;
        for (int h = 0; h < 20; h++) {
            boolean empty = true;
            int x = (int) (Math.random() * width - Creature.SIDE_LENGTH);
            int y = (int) (Math.random() * height - Creature.SIDE_LENGTH);
            for (Cell[] row : child.getCellMap()) {
                for (Cell c : row) {
                    if (c != null) {
                        int newCellX = x + c.getX();
                        int newCellY = y + c.getY();
                        Cell atLoc = cellAtAbsPos(newCellX, newCellY);
                        if (atLoc != null) {
                            empty = false;
                        }
                        if (newCellX < 0 || newCellY < 0 || newCellX >= width || newCellY >= height || environment[newCellX][newCellY] != 0) {
                            empty = false;
                        }
                    }
                }
            }
            if (empty) {
                finalX = x;
                finalY = y;
                break;
            }
        }
        child.setPosX(finalX);
        child.setPosY(finalY);
        addCreature(child);
    }

    /**
     * Returns the amount of nutrients gained by consuming the food directly
     * adjacent to the creature's forager cells.
     *
     * @param cr The creature gaining nutrients.
     * @return The amount of nutrients gained from consuming the food around the
     * creature.
     */
    public int forage(Creature cr) {
        int nutrientsGained = 0;
        List<Cell> foragers = cr.getModeCells();
        for (Cell ce : foragers) {
            int x = ce.getX() + cr.getPosX();
            int y = ce.getY() + cr.getPosY();
            if (environment[x][y] == 1) {
                nutrientsGained += nutrientsPerFood;
                environment[x][y] = 0;
            }
            for (int k = 0; k < 4; k++) {
                int newX = x + Mapping.ADX4[k];
                int newY = y + Mapping.ADY4[k];
                boolean inBounds = newX < width && newY < height && newX >= 0 && newY >= 0;
                if (inBounds && environment[newX][newY] == 1) {
                    nutrientsGained += nutrientsPerFood;
                    environment[newX][newY] = 0;
                }
            }
        }
        return nutrientsGained;
    }

    /**
     * Returns the amount of nutrients gained by consuming the food directly
     * adjacent to the creature's forager cells.
     *
     * @param forageCells The forage cells in the creature gaining nutrients.
     * @return The amount of nutrients gained from consuming the food around the
     * creature.
     */
    public int forage(List<Cell> forageCells) {
        int nutrientsGained = 0;
        for (Cell ce : forageCells) {
            Creature cr = ce.getCreature();
            int x = ce.getX() + cr.getPosX();
            int y = ce.getY() + cr.getPosY();
            if (environment[x][y] == 1) {
                nutrientsGained += nutrientsPerFood;
                environment[x][y] = 0;
            }
            for (int k = 0; k < 4; k++) {
                int newX = x + Mapping.ADX4[k];
                int newY = y + Mapping.ADY4[k];
                boolean inBounds = newX < width && newY < height && newX >= 0 && newY >= 0;
                if (inBounds && environment[newX][newY] == 1) {
                    nutrientsGained += nutrientsPerFood;
                    environment[newX][newY] = 0;
                }
            }
        }
        return nutrientsGained;
    }

    public int hunt(List<Cell> hunters) {
        int nutrientsGained = 0;
        for (Cell ce : hunters) {
            Creature cr = ce.getCreature();
            int x = ce.getX() + cr.getPosX();
            int y = ce.getY() + cr.getPosY();
            for (int k = 0; k < 4; k++) {
                int newX = x + Mapping.ADX4[k];
                int newY = y + Mapping.ADY4[k];
                Cell prey = cellAtAbsPos(newX,newY);
                boolean inBounds = newX < width && newY < height && newX >= 0 && newY >= 0;
                if (inBounds && prey != null) {
                    nutrientsGained += (int)(hunterYield * prey.getMaxStore());
                    Creature victim = prey.getCreature();
                    victim.deleteCell(prey);
                }
            }
        }
        return nutrientsGained;
    }

    public void reproduce(Creature cr){
        List<Cell> reproductionCells = cr.getModeCells();
        for(Cell ce : reproductionCells){
            int x = ce.getX() + cr.getPosX();
            int y = ce.getY() + cr.getPosY();
            for (int k = 0; k < 4; k++) {
                int newX = x + Mapping.ADX4[k];
                int newY = y + Mapping.ADY4[k];
                Cell partner = cellAtAbsPos(newX,newY);
                boolean inBounds = newX < width && newY < height && newX >= 0 && newY >= 0;
                if(inBounds&&partner.getCellType()==8){
                    Creature p = partner.getCreature();
                    Creature child = cr.reproduce(p);
                    spawn(child);
                }
            }
        }
    }
    
    public boolean overPit(Creature cr){
        boolean toFall = true;
        for(int i = 0; i < cr.getCellMap().length; i++){
            for(int j = 0; j < cr.getCellMap()[i].length; j++){
                int x = i + cr.getPosX();
                int y = j + cr.getPosY();
                if(environment[x][y]!=3){
                    toFall = false;
                }
            }
        }
        return toFall;
    }
    
    public void kill(Creature cr){
        population.remove(cr);
    }
    
    /**
     * Returns the cell at a given position, designated by x and y coordinate
     * parameters
     *
     * @param x The x-coordinate of the position of inquiry
     * @param y The y-coordinate of the position of inquiry
     * @return The cell found at the given coordinates, or null if no cell is
     * found.
     */
    public Cell cellAtAbsPos(int x, int y) {
        List<Creature> creaturesAtPos = new ArrayList<>();
        for (Creature c : population) {
            int xDiff = x - c.getPosX();
            int yDiff = y - c.getPosY();
            if (xDiff < Creature.SIDE_LENGTH && xDiff >= 0) {
                if (yDiff < Creature.SIDE_LENGTH && yDiff >= 0) {
                    creaturesAtPos.add(c);
                }
            }
        }
        for (Creature c : creaturesAtPos) {
            Cell found = c.cellAtRelPos(x - c.getPosX(), y - c.getPosY());
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public static void setNutrientsPerFood(int nutrientsPerFood) {
        Terrain.nutrientsPerFood = nutrientsPerFood;
    }

    private static Color4 getTerColor(int type) {

        switch (type) {

            case PIT:
                return Color4.BLACK;

            case FOOD:
                return Color4.ORANGE;

            case WALL:
                return Color4.gray(0.5);
        }

        return null;
    }

    public void draw() {

        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                if (environment[i][j] != 0) {

                    Graphics2D.fillRect(ORIGIN.add(new Vec2(getZoom() * i, getZoom() * j)), new Vec2(getZoom()), getTerColor(environment[i][j]));
                }
            }
        }

        for (Creature cre : population) {

            cre.draw(ORIGIN, getZoom());
        }
    }
    
    public void kill(Creature cr){
        
    }
}

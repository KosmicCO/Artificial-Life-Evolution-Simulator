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

/**
 *
 * @author bhargav
 */
public class Terrain {

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

    public void addCreature(Creature c) {
        population.add(c);
    }

    public void move(int direction, Creature cr) {
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

            int newX = cr.getPosX() + 1;
            cr.setPosX(newX);

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

            int newY = cr.getPosY() - 1;
            cr.setPosY(newY);

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

            int newX = cr.getPosX() - 1;
            cr.setPosX(newX);

        }

    }

    public Cell cellAtAbsPos(int x, int y) {
        List<Creature> creaturesAtPos = new ArrayList<Creature>();
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

            cre.draw(ORIGIN);
        }
    }
}

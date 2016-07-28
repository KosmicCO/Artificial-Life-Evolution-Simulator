/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticsTest;

import creature.cells.Cell;
import creature.genetics.Chromosome;
import creature.genetics.GeneInterpreter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhargav
 */
public class InterpreterTest {

    public static void main(String[] args) {

        List<Boolean> g = new ArrayList<>();
        for (int i = 0; i < 387; i++) {
            double random = Math.random();
            if (random < 0.5) {
                g.add(true);
            } else {
                g.add(false);
            }
        }
        Chromosome m = new Chromosome(g);
        List<Boolean> h = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            double random = Math.random();
            if (random < 0.5) {
                h.add(true);
            } else {
                h.add(false);
            }
        }
        Chromosome f = new Chromosome(h);
        System.out.println(m);
        Cell[][] map = GeneInterpreter.StructureGen(m);
        for (Cell[] c : map) {
            for (Cell leppard : c) {
                System.out.print(leppard != null ? leppard.getCellType() + " " : "_ ");
            }
            System.out.println();
        }
        System.out.println(f);
        Cell[][] mapa = GeneInterpreter.StructureGen(f);
        
        for (Cell[] c : mapa) {
            for (Cell leppard : c) {
                System.out.print(leppard != null ? leppard.getCellType() + " " : "_ ");
            }
            System.out.println();
        }
        
    }
}

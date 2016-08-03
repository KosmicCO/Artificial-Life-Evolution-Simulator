/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticsTest;

import creature.Creature;
import creature.cells.Cell;
import creature.genetics.Chromosome;
import creature.genetics.StructureInterpreter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhargav
 */
public class ReproductionTest {

    public static void main(String[] args) {
        List<Chromosome> moGene = new ArrayList<>();
        System.out.println("Mother Gene:");
        for (int count = 0; count < 10; count++) {
            List<Boolean> g = new ArrayList<>();
            int len = (int) (Math.random() * 256);
            for (int i = 0; i < len; i++) {
                double random = Math.random();
                if (random < 0.5) {
                    g.add(true);
                } else {
                    g.add(false);
                }
            }
            Chromosome m = new Chromosome(g);
            System.out.println(m);
            moGene.add(m);
        }
        System.out.println("");
        List<Chromosome> faGene = new ArrayList<>();
        System.out.println("Father Gene:");
        for (int count = 0; count < 16; count++) {
            List<Boolean> g = new ArrayList<>();
            int len = (int) (Math.random() * 256);
            for (int i = 0; i < len; i++) {
                double random = Math.random();
                if (random < 0.5) {
                    g.add(true);
                } else {
                    g.add(false);
                }
            }
            Chromosome f = new Chromosome(g);
            System.out.println(f);
            faGene.add(f);
        }
        List<Integer> w = new ArrayList<>();
        int sum = 5;
        w.add(5);
        for (int i = 0; i < 11; i++) {
            int r = (int) ((Math.random() * 5 - 2) + 20);
            w.add(r);
            sum += r;
        }
        w.add(256 - sum);

//     
//        for (int i = 0; i < 10; i++) {
//            
//            w.add(i < 2 ? 0 : 16);
//        
//        }
//        
//        w.add(128);
        System.out.println(w);
        StructureInterpreter.setWeightedGenome(w);
        List<Integer> gRef = StructureInterpreter.getRefList();
        System.out.println(gRef);
        Cell[][] map = StructureInterpreter.interpret(moGene.get(0));
        for (Cell[] c : map) {
            for (int i = 0; i < c.length; i++) {
                if (c[i] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print("\u001B[" + (29 + c[i].getCellType()) + "m█\u001B[30m");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        Creature mother = new Creature(map, 5, moGene, 0, 0);
        Cell[][] mapa = StructureInterpreter.interpret(faGene.get(0));
        for (Cell[] c : mapa) {
            for (int i = 0; i < c.length; i++) {
                if (c[i] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print("\u001B[" + (29 + c[i].getCellType()) + "m█\u001B[30m");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        Creature father = new Creature(mapa, 5, faGene, 0, 0);
        Creature child = mother.reproduce(father);
        for (Cell[] c : child.getCellMap()) {
            for (int i = 0; i < c.length; i++) {
                if (c[i] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print("\u001B[" + (29 + c[i].getCellType()) + "m█\u001B[30m");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("CHILD GENE: ");
        for(int i = 0; i<child.getGenes().size(); i++){
            System.out.println(child.getGenes().get(i));
        }
    }
}

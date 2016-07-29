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
        for (int i = 0; i < 444; i++) {
            double random = Math.random();
            if (random < 0.5) {
                g.add(true);
            } else {
                g.add(false);
            }
        }
        Chromosome m = new Chromosome(g);
        List<Boolean> h = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            double random = Math.random();
            if (random < 0.5) {
                h.add(true);
            } else {
                h.add(false);
            }
        }
        
        List<Integer> w = new ArrayList<>();
        int sum = 5;
        w.add(5);
        for(int i = 0; i<10; i++){
            int r = (int)(Math.random()*33);
            w.add(r);
            sum+=r;
        }
        w.add(256-sum);
        
//     
//        for (int i = 0; i < 10; i++) {
//            
//            w.add(i < 2 ? 0 : 16);
//        
//        }
//        
//        w.add(128);
        
        System.out.println(w);
        GeneInterpreter.setWeightedGenome(w);
        List<Integer> gRef = GeneInterpreter.getRefList();
        System.out.println(gRef);
        Chromosome f = new Chromosome(h);
        System.out.println(m);
        Cell[][] map = GeneInterpreter.StructureGen(m);
        for (Cell[] c : map) {
            for(int i = 0; i<c.length; i++){
                if(c[i] == null){
                    System.out.print("_ ");
                }
                else{
                    System.out.print(c[i].getCellType() + " ");
                }
            }
            System.out.println("");
        }
        
    }
}

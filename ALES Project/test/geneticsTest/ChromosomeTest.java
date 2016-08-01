/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticsTest;

import creature.genetics.Chromosome;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhargav
 */
public class ChromosomeTest {

    public static void main(String[] args) {
        int counter = 0;
        int aveLen = 0;
        while(counter<10000){
        List<Boolean> g = new ArrayList<>();
        for (int i = 0; i < 43; i++) {
            double random = Math.random();
                if (random < 0.5) {
                    g.add(true);
                } else {
                    g.add(false);
                }
        }
        Chromosome m = new Chromosome(g);
        List<Boolean> h = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            double random = Math.random();
                if (random < 0.5) {
                    h.add(true);
                } else {
                    h.add(false);
                }
        }
        Chromosome f = new Chromosome(h);
        System.out.println("MOTHER: "+m);
        System.out.println("FATHER: "+f);
        Chromosome child = m.reproduce(f);
        aveLen+=child.getLength();
        System.out.println("CHILD:  "+child);
        counter++;
        }
        aveLen /= 10000;
        System.out.println("Average Child Length = " + aveLen);
    }

}

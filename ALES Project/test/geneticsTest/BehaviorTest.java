/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticsTest;

import creature.Behavior;
import creature.genetics.BehaviorInterpreter;
import creature.genetics.Chromosome;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kosmic
 */
public class BehaviorTest {

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
        System.out.println(m.toString() + "\n");

        List<Integer> w = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int r = (int) (Math.random() * 60);
            w.add(r);
            sum += r;
        }
        w.add(256 - sum);
        System.out.println(w + "\n");
        BehaviorInterpreter.setWeightedGenome(w);

        Behavior b = BehaviorInterpreter.interpret(m);
        System.out.println(b.toString());
    }
}

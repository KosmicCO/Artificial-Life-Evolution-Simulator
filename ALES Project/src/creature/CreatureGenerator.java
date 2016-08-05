/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature;

import creature.genetics.Chromosome;
import creature.genetics.StructureInterpreter;
import static creature.genetics.StructureInterpreter.interpret;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhargav
 */
public class CreatureGenerator {

    public static Creature generateCreature(List<Integer> wGen) {
        //System.out.println(o + "\n");
        StructureInterpreter.setWeightedGenome(wGen);
        List<Chromosome> g = new ArrayList<>();
        List<Boolean> chr = new ArrayList<>();
        for (int j = 0; j < 444; j++) {
            double random = Math.random();
            if (random < 0.5) {
                chr.add(true);
            } else {
                chr.add(false);
            }
        }
        g.add(new Chromosome(chr));
        Creature cr = new Creature(interpret(g.get(0)), 100000, g, 0, 0);
        return cr;
    }

}

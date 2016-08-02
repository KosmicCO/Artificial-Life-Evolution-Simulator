/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import creature.Behavior;
import creature.Creature;
import static creature.Creature.SIDE_LENGTH;
import creature.genetics.BehaviorInterpreter;
import creature.genetics.Chromosome;
import creature.genetics.StructureInterpreter;
import java.util.ArrayList;
import java.util.List;
import map.Terrain;
import static creature.genetics.StructureInterpreter.interpret;
import map.TerrainGenerator;

/**
 *
 * @author Kosmic
 */
public class TestGenMap {

    public static void makeTestMap() {

        List<List<Boolean>> g = new ArrayList();
        List<Creature> lca = new ArrayList();
        int deNum = 100;
        List<Integer> w = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int r = (int) (Math.random() * 60);
            w.add(r);
            sum += r;
        }
        w.add(256 - sum);
        System.out.println(w + "\n");

        List<Integer> o = new ArrayList<>();
        int bum = 0;
        for (int i = 0; i < 12; i++) {
            int r = (int) (Math.random() * 25);
            o.add(r);
            bum += r;
        }
        o.add(256 - bum);
        
        System.out.println(o + "\n");

        StructureInterpreter.setWeightedGenome(o);
        BehaviorInterpreter.setWeightedGenome(w);
        for (int i = 0; i < deNum; i++) {

            g.add(new ArrayList());
            for (int j = 0; j < 444; j++) {
                double random = Math.random();
                if (random < 0.5) {
                    g.get(i).add(true);
                } else {
                    g.get(i).add(false);
                }
            }

            List<Behavior> beh = new ArrayList();

            for (int j = 0; j < 3; j++) {

                List<Boolean> b = new ArrayList();

                for (int k = 0; k < 444; k++) {
                    double random = Math.random();
                    b.add(random < 0.5);
                }

                beh.add(BehaviorInterpreter.interpret(new Chromosome(b)));
            }

            for (int j = 0; j < 444; j++) {

                System.out.print(g.get(i).get(j) ? "1" : "0");
            }

            System.out.println();

            lca.add(new Creature(interpret(new Chromosome(g.get(i))), beh, 0, null, 0, 0));
        }

        Terrain t = TerrainGenerator.generate(250, 250);

        for (Creature c : lca) {
            
            t.spawn(c);
        }
        
        Terrain.currentT = t;
    }
}

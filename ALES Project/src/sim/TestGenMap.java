/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import creature.Creature;
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

            lca.add(new Creature(interpret(new Chromosome(g.get(i))), /*beh,*/ 100000, null, 0, 0));
        }

        Terrain t = TerrainGenerator.generate(250, Math.random());

        for (Creature c : lca) {
            
            t.spawn(c);
        }
        
        Terrain.currentT = t;
    }
}

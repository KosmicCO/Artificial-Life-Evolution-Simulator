/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import creature.Creature;
import static creature.Creature.SIDE_LENGTH;
import creature.genetics.Chromosome;
import creature.genetics.StructureInterpreter;
import java.util.ArrayList;
import java.util.List;
import map.Terrain;
import static creature.genetics.StructureInterpreter.interpret;

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

        int sum = 5;
        w.add(5);
        for (int i = 0; i < 11; i++) {
            int r = (int) (Math.random() * 33);
            w.add(r);
            sum += r;
        }
        w.add(256 - sum);

        StructureInterpreter.setWeightedGenome(w);

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

            for (int j = 0; j < 444; j++) {

                System.out.print(g.get(i).get(j) ? "1" : "0");
            }
            
            System.out.println();

            lca.add(new Creature(interpret(new Chromosome(g.get(i))), 0, null, (int) (Math.random() * (250 - SIDE_LENGTH)), (int) (Math.random() * (250 - SIDE_LENGTH))));
        }

        int[][] ter = new int[250][250];

        for (int i = 0; i < 250; i++) {

            for (int j = 0; j < 250; j++) {

                if (Math.random() < 0.05) {
                    ter[i][j] = 0;//(int) (Math.random() * 3 + 1);
                }
            }
        }

        Terrain t = new Terrain(ter, lca);

        Terrain.currentT = t;
    }
}

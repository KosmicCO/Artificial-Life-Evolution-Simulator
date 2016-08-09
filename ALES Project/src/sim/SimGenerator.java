/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import creature.Creature;
import creature.CreatureGenerator;
import creature.genetics.StructureInterpreter;
import gui.GUIController;
import java.util.ArrayList;
import java.util.List;
import map.Terrain;
import map.TerrainGenerator;
import sim.guis.MainMenu;

/**
 *
 * @author bhargav
 */
public class SimGenerator {

    public static int creatureAmount = 100;

    public static void generate() {
        
        MainMenu mame = (MainMenu) GUIController.getGUI("mainMenu");
        
        List<Integer> wGen = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int r = (int) (Math.random() * 25);
            wGen.add(r);
            sum += r;
        }
        wGen.add(256 - sum);

        mame.progress(20);
        
        StructureInterpreter.setWeightedGenome(wGen);
        List<Creature> pop = new ArrayList<>();
        for (int i = 0; i < creatureAmount; i++) {
            Creature cr = CreatureGenerator.generateCreature(wGen);
            pop.add(cr);
        }
        
        mame.progress(40);
        Terrain terr = TerrainGenerator.generate(256, Math.random());
        for(Creature c : pop){
            terr.spawn(c);
        }
        
        mame.progress(70);
        for (int i = 0; i < pop.size(); i++) {
            
            terr.spawn(pop.get(i));
        }
        
        mame.progress(100);
        Terrain.currentT = terr;
    }

}

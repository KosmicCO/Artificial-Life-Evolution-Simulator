/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package creature.genetics;

import creature.Behavior;
import java.util.ArrayList;
import java.util.List;
import utility.Conversions;

/**
 *
 * @author bhargav
 */
public class BehaviorInterpreter {

    public final static int MOVE_UP = 0;
    public final static int MOVE_RIGHT = 1;
    public final static int MOVE_DOWN = 2;
    public final static int MOVE_LEFT = 3;
    public final static int MOVE_RAND = 4;
    public final static int MODE_SENSE = 5;
    public final static int MODE_ACTION = 6;
    public final static int GOTO_FUNC = 7;
    public final static int NEW_FUNC = 8;
    
    public final static int COMM_NUM = 9;
    
    private static List<Integer> weightedGenome = new ArrayList();
    private static List<Integer> geneRefList = new ArrayList();
    
    public static List<Integer> getWeightedGenome() {
        List<Integer> w = new ArrayList<>();
        for (int count = 0; count < weightedGenome.size(); count++) {
            int i = weightedGenome.get(count);
            w.add(i);
        }
        return w;
    }

    public static List<Integer> getRefList() {
        List<Integer> g = new ArrayList<>();
        for (int count = 0; count < geneRefList.size(); count++) {
            int i = geneRefList.get(count);
            g.add(i);
        }
        return g;
    }

    public static void setWeightedGenome(List<Integer> w) {
        weightedGenome = w;
        geneReferenceGen();
    }
    
    private static int geneToComm(int ind) {
        int j = geneRefList.get(ind);
        return j;
    }
    
    private static void geneReferenceGen() {
        for (int j = 0; j < weightedGenome.size(); j++) {
            for (int k = 0; k < weightedGenome.get(j); k++) {
                int ind = (int) (Math.random() * geneRefList.size());
                geneRefList.add(ind, j);
            }
        }
    }
    
    public static Behavior interpret(Chromosome c){
        
        List<List<Integer>> subs = new ArrayList();
        subs.add(new ArrayList());
        int index = 0;
        
        for (int i = 0; i < c.geneCount(); i++) {
            
            int gene = geneToComm(Conversions.byteToInt(c.getSegment(i)));
            
            if(gene == NEW_FUNC){
                
                subs.add(new ArrayList());
                index++;
            }else{
                
                subs.get(index).add(gene);
                if (gene == GOTO_FUNC || gene == MODE_SENSE){
                    
                    subs.get(index).add(Conversions.byteToInt(c.getSegment(i)));
                }
            }
        }
        
        return new Behavior(subs.get(0), subs.subList(1, subs.size()));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creature.genetics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @date 7/26/2016
 */
public class Chromosome{
    
    private List<Boolean> gene = new ArrayList();
    
    /**
     * constructor
     */
    public Chromosome(List<Boolean> gene){
        this.gene = gene;
    }
    
    /**
     * getGene method - Creates a boolean array with a length of 8
     * and goes through List<Boolean> gene and adds its boolean values
     * into the boolean array
     * 
     * Things to add:
     * Make a for loop, divide the largest by 8 & round down
     */
    public boolean[] getGene(int index){
        boolean[] returningGene = new boolean[8];
        for(int i = 0; i < gene.size(); i++){
            boolean j = gene.get(i + index);
//        for(int j = 0; j < gene.size() / 8; j++){
//            
//        }
            returningGene[i] = j;
        }
        return returningGene;
    }
    
    /**
     * reproduce method - Reproduces a new gene and has the possibility
     * of reproducing a mutated gene if the inputted double is more than
     * the randomly generated double
     * Mutated genes change boolean values from true to false, and vice versa
     * 
     * Things to add:
     * For the parameters - 2 boolean lists, double change
     * Compare the two boolean lists, get the length of the longer list
     * Make a for loop and divide the list by 8 in the loop
     */
    public static List<Boolean> reproduce(List<Boolean> a1, List<Boolean> a2, double change){
        Boolean[] returningGene = new Boolean[8];
        
        double r = Math.random();
        for(int i = 0; i < a1.size(); i++){
            if (change > r){
                returningGene[i] = !returningGene[i];
            }
        }
        return Arrays.asList(returningGene);
    }
    
}

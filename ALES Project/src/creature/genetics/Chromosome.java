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
public class Chromosome {

    public static double MUTATION_FACTOR = 0.05;
    public static int VARIANCE = 3;
    private List<Boolean> gene;

    public Chromosome(List<Boolean> g) {
        gene = g;
    }

    public boolean[] getSegment(int ind) {
        boolean[] seg = new boolean[8];
        for (int i = 0; i < 8; i++) {
            seg[i] = gene.get(ind * 8 + i);
        }
        return seg;
    }

    public int getLength() {
        return gene.size();
    }
    
    public int geneCount(){
        return gene.size()/8;
    }

    public boolean get(int i) {
        return gene.get(i);
    }

    public List<Boolean> reproduce(Chromosome other) {
        List<Boolean> child = new ArrayList<>();
        int shortSize = this.getLength();
        Chromosome larger = other;
        if (shortSize > other.getLength()) {
            shortSize = other.getLength();
            larger = this;
        }
        int iterations = shortSize / 8;
        for (int i = 0; i < iterations; i++) {
            int rand = (int) (Math.random() * 2);
            boolean[] seg;
            if (rand == 0) {
                seg = this.getSegment(i);
            } else {
                seg = other.getSegment(i);
            }
            for (int j = 0; j < seg.length; j++) {
                child.add(seg[j]);
            }
        }
        int leftover = shortSize % 8;
        for (int i = 0; i < leftover; i++) {
            int rand = (int) (Math.random() * 2);
            if (rand == 0) {
                child.add(this.get(shortSize - leftover + i));
            } else {
                child.add(other.get(shortSize - leftover + i));
            }

        }
        int slack = larger.getLength() - shortSize;
        if (slack > 0) {
            for (int i = 0; i < slack; i++) {
                child.add(larger.get(larger.getLength() - slack + i));
            }
        }

        for (int m = 0; m < child.size(); m++) {
            double c = Math.random();
            if (c <= MUTATION_FACTOR) {
                boolean switched = child.remove(m);
                boolean ins = !switched;
                child.add(m, ins);
            }
        }

        int rand = (int) (Math.random() * ((2 * VARIANCE)+(larger.getLength()-shortSize)));
        rand -= ((larger.getLength()-shortSize)+VARIANCE);
        if (rand >= 0 || child.size()<VARIANCE) {
            for (int i = 0; i < rand; i++) {
                double random = Math.random();
                if (random < 0.5) {
                    child.add(true);
                } else {
                    child.add(false);
                }
            }
        } else {
            for (int i = 0; i > rand; i--) {
                child.remove(child.size()-1);
            }
        }
        return child;
    }

    @Override
    public String toString() {
        String r = "";
        for (int i = 0; i < this.getLength(); i++) {
            if (this.get(i)) {
                r += "0";
            } else {
                r += "1";
            }
        }
        r += "\n Length: " + this.getLength();
        return r;
    }
}

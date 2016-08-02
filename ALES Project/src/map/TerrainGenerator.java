/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import java.util.Random;
import util.Noise;

/**
 *
 * @author Kosmic
 */
public class TerrainGenerator {
    
    public final static double FOOD_SPAWN_RATE = 0.005;
    
    public static Terrain generate(int size, double seed){
        
        int[][] map = new int[size][size];
        Noise n = new Noise(1.0);
        n.seed = seed;
        
        for (int i = 0; i < size; i++) {
            
            for (int j = 0; j < size; j++) {
                
                double h = n.multi(i, j, 100, 0.01);
                
                if(Math.abs(h) > 0.8){
                    
                    map[i][j] = h > 0 ? 2 : 3;
                }else{
                    
                    if((Math.abs((new Random()).nextDouble() * h)) < FOOD_SPAWN_RATE){
                        
                        map[i][j] = 1;
                    }
                }
            }
        }
        
        return new Terrain(map, new ArrayList());
    }
}

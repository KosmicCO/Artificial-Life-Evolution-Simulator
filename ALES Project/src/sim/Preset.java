/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import creature.Creature;
import creature.NewBehavior;
import creature.genetics.Chromosome;
import creature.genetics.StructureInterpreter;
import java.util.List;
import map.Terrain;
import map.TerrainGenerator;

/**
 *
 * @author Kosmic
 */
public class Preset {

    //Creature
    private int sideLen;
    private int eCostHunt;
    private int eCostForage;
    private int eCostRepro;
    private double reproThreshold;
    private double hunterThreshold;

    //NewBehavior
    private int eCostDetect;
    private int eCostMove;

    //Terrain
    private int nutrientsPerFood;
    private double huntYield;

    //TerrainGenerator
    private double foodSR;
    
    //Chromosome
    private double mutantFactor;
    private int lenVariance;
    
    //StructureInterpreter
    private List<Integer> wGenome;

    public Preset(int sLen, int costHunt, int costForage, int costRepro, double repThresh, double huntThresh, int costDetect, int costMove, int nutrientsPF, int hYield, int fSR, double mFactor, int lenV, List<Integer> wGen) {
        sideLen = sLen;
        eCostHunt = costHunt;
        eCostForage = costForage;
        eCostRepro = costRepro;
        reproThreshold = repThresh;
        hunterThreshold = huntThresh;
        eCostDetect = costDetect;
        eCostMove = costMove;
        nutrientsPerFood = nutrientsPF;
        huntYield = hYield;
        foodSR = fSR;
        mutantFactor = mFactor;
        lenVariance = lenV;
        wGenome = wGen;
    }

    public void set() {
        Creature.sideLength = sideLen;
        Creature.energyCostPerHunt = eCostHunt;
        Creature.energyCostPerForage = eCostForage;
        Creature.energyCostPerRepro = eCostRepro;
        Creature.reproductionThreshold = reproThreshold;
        Creature.huntThreshold = hunterThreshold;
        NewBehavior.energyPerDetect = eCostDetect;
        NewBehavior.energyPerMove = eCostMove;
        Terrain.nutrientsPerFood = nutrientsPerFood;
        Terrain.hunterYield = huntYield;
        TerrainGenerator.foodSpawnRate = foodSR;
        Chromosome.mutationFactor = mutantFactor;
        Chromosome.variance = lenVariance;
        StructureInterpreter.setWeightedGenome(wGenome);
    }
 

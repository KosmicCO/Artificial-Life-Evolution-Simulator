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
    private int eCostHunt;
    private int eCostForage;
    private int eCostRepro;
    private double reproThreshold;
    private double reproBuffer;
    private double hunterThreshold;

    //NewBehavior
    private int eCostDetect;
    private int eCostMove;

    //Terrain
    private int actionRad;
    private int nutrientsPerFood;
    private double huntYield;
    private int fRespawnAmount;

    //TerrainGenerator
    private double foodSR;
    
    //Chromosome
    private double mutantFactor;
    private int lenVariance;
    
    //StructureInterpreter
    private List<Integer> wGenome;
    
    //SimGenerator
    private int numCreatures;

    public Preset(int costHunt, int costForage, int costRepro, double repThresh, double repBuffer, double huntThresh, int costDetect, int costMove, int actRad, int nutrientsPF, double hYield, int fRSAmount, double fSR, double mFactor, int lenV, List<Integer> wGen, int numCre) {
        eCostHunt = costHunt;
        eCostForage = costForage;
        eCostRepro = costRepro;
        reproThreshold = repThresh;
        reproBuffer = repBuffer;
        hunterThreshold = huntThresh;
        eCostDetect = costDetect;
        eCostMove = costMove;
        actionRad = actRad;
        nutrientsPerFood = nutrientsPF;
        huntYield = hYield;
        fRespawnAmount = fRSAmount;
        foodSR = fSR;
        mutantFactor = mFactor;
        lenVariance = lenV;
        wGenome = wGen;
        numCreatures = numCre;
    }
    
    public Preset(){
        eCostHunt = Creature.energyCostPerHunt;
        eCostForage = Creature.energyCostPerForage;
        eCostRepro = Creature.energyCostPerRepro;
        reproThreshold = Creature.reproductionThreshold;
        reproBuffer = Creature.reproductionBuffer;
        hunterThreshold = Creature.huntThreshold;
        eCostDetect = NewBehavior.energyPerDetect;
        eCostMove = NewBehavior.energyPerMove;
        actionRad = Terrain.actionRadius;
        this.nutrientsPerFood = Terrain.nutrientsPerFood;
        huntYield = Terrain.hunterYield;
        fRespawnAmount = Terrain.foodSpawnAmount;
        foodSR = TerrainGenerator.foodSpawnRate;
        mutantFactor = Chromosome.mutationFactor;
        lenVariance = Chromosome.variance;
        wGenome = StructureInterpreter.getWeightedGenome();
        numCreatures = SimGenerator.creatureAmount;
    }

    public void set() {
        Creature.energyCostPerHunt = eCostHunt;
        Creature.energyCostPerForage = eCostForage;
        Creature.energyCostPerRepro = eCostRepro;
        Creature.reproductionThreshold = reproThreshold;
        Creature.reproductionBuffer = reproBuffer;
        Creature.huntThreshold = hunterThreshold;
        NewBehavior.energyPerDetect = eCostDetect;
        NewBehavior.energyPerMove = eCostMove;
        Terrain.actionRadius = actionRad;
        Terrain.nutrientsPerFood = this.nutrientsPerFood;
        Terrain.hunterYield = huntYield;
        Terrain.foodSpawnAmount = fRespawnAmount;
        TerrainGenerator.foodSpawnRate = foodSR;
        Chromosome.mutationFactor = mutantFactor;
        Chromosome.variance = lenVariance;
        StructureInterpreter.setWeightedGenome(wGenome);
        SimGenerator.creatureAmount = numCreatures;
    }
}
 

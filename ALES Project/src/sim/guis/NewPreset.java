/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import creature.Creature;
import creature.NewBehavior;
import creature.genetics.Chromosome;
import creature.genetics.StructureInterpreter;
import gui.GUIController;
import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIInputComponent;
import java.util.ArrayList;
import java.util.List;
import map.Terrain;
import map.TerrainGenerator;
import org.newdawn.slick.Color;
import sim.Preset;
import sim.SimGenerator;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;

/**
 *
 * @author Kosmic
 */
public class NewPreset extends ComponentInputGUI {

    //Creature
    public static int eCostHunt;
    public static int eCostForage;
    public static int eCostRepro;
    public static double reproThreshold;
    public static double reproBuffer;
    public static double hunterThreshold;

    //NewBehavior
    public static int eCostDetect;
    public static int eCostMove;

    //Terrain
    public static int actionRad;
    public static int nutrientsPerFood;
    public static double huntYield;
    public static int fRespawnAmount;

    //TerrainGenerator
    public static double foodSR;

    //Chromosome
    public static double mutantFactor;
    public static int lenVariance;

    //StructureInterpreter
    public static List<Integer> wGenome;

    //SimGenerator
    public static int numCreatures;

    private Presets parent;
    //List<PresetSpec> preSpecs;
    public static Preset newPreset;

    private CreaturePresetSpec crPreSpec;
    private TerrainPresetSpec terrPreSpec;
    private FoodPresetSpec fPreSpec;
    private AdvancedPresetSpec advPreSpec;

    public NewPreset(String n, Presets parent) {

        super(n);
        this.parent = parent;

        newPreset = new Preset();

        //SETTING ALL OF THESE CLASS VARIABLES
        eCostHunt = Creature.energyCostPerHunt;
        eCostForage = Creature.energyCostPerForage;
        eCostRepro = Creature.energyCostPerRepro;
        reproThreshold = Creature.reproductionThreshold;
        reproBuffer = Creature.reproductionBuffer;
        hunterThreshold = Creature.huntThreshold;
        eCostDetect = NewBehavior.energyPerDetect;
        eCostMove = NewBehavior.energyPerMove;
        actionRad = Terrain.actionRadius;
        nutrientsPerFood = Terrain.nutrientsPerFood;
        huntYield = Terrain.hunterYield;
        fRespawnAmount = Terrain.foodSpawnAmount;
        foodSR = TerrainGenerator.foodSpawnRate;
        mutantFactor = Chromosome.mutationFactor;
        lenVariance = Chromosome.variance;
        wGenome = StructureInterpreter.getWeightedGenome();
        numCreatures = SimGenerator.creatureAmount;

        inputs.add(new GUIButton("creature", this, nextPlace(parent.getStartPos(), 2, -4), BUTTON_SIZE, "Creature", Color.white));
        inputs.add(new GUIButton("terrain", this, nextPlace(parent.getStartPos(), 2, -3), BUTTON_SIZE, "Terrain", Color.white));
        inputs.add(new GUIButton("food", this, nextPlace(parent.getStartPos(), 2, -2), BUTTON_SIZE, "Food", Color.white));
        inputs.add(new GUIButton("advanced", this, nextPlace(parent.getStartPos(), 2, -1), BUTTON_SIZE, "Advanced", Color.white));

        for (int i = 0; i < 5; i++) {

            components.add(new GUIPanel("top" + i, nextPlace(parent.getStartPos(), 2, -i), BUTTON_SIZE, getColor(0).multiply(0.8 - 0.1 * i)));
        }

        inputs.add(new GUIButton("create", this, nextPlace(parent.getStartPos(), 2, 1), BUTTON_SIZE, "Create", Color.white));
        inputs.add(new GUIButton("cancel", this, nextPlace(parent.getStartPos(), 2, 2), BUTTON_SIZE, "Cancel", Color.white));

        components.add(new GUIPanel("middle", nextPlace(parent.getStartPos(), 2, 1), BUTTON_SIZE, getColor(1).multiply(0.8)));
        components.add(new GUIPanel("bottom", nextPlace(parent.getStartPos(), 2, 2), BUTTON_SIZE, getColor(1).multiply(0.7)));
        //preSpecs = new ArrayList<>();
        crPreSpec = new CreaturePresetSpec("specMenu1", this);
        GUIController.add(crPreSpec);
        terrPreSpec = new TerrainPresetSpec("specMenu2", this);
        GUIController.add(terrPreSpec);
        fPreSpec = new FoodPresetSpec("specMenu3", this);
        GUIController.add(fPreSpec);
        advPreSpec = new AdvancedPresetSpec("specMenu4", this);
        GUIController.add(advPreSpec);
    }

    public void start() {

        this.setVisible(true);
        typing(this, true);

    }

    public Vec2 getStartPos() {
        return parent.getStartPos();
    }

    @Override
    public void recieve(String string, Object o) {
        switch (string) {
            case "creature":
                crPreSpec.start();
                System.out.println("Creature");
                break;
            case "terrain":

                terrPreSpec.start();
                System.out.println("Terrain");
                break;
            case "food":

                fPreSpec.start();
                System.out.println("Food");
                break;
            case "advanced":

                advPreSpec.start();
                System.out.println("Advanced Settings");
                break;
            case "cancel":
                System.out.println("Cancel clicked");
                setVisible(false);
                typing(parent, true);
                break;
            case "create":
                newPreset = new Preset(eCostHunt, eCostForage, eCostRepro, reproThreshold, reproBuffer, hunterThreshold, eCostDetect, eCostMove, actionRad, nutrientsPerFood, huntYield, fRespawnAmount, foodSR, mutantFactor, lenVariance, wGenome, numCreatures);
                newPreset.set();
                System.out.println("New Preset Created");
                break;
        }
        if (string.equals("cancel")) {
            setVisible(false);
            parent.start();
        }
    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return null;
    }

}

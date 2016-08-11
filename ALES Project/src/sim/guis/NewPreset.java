/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.guis;

import sim.guis.pres.TerrainPresetSpec;
import sim.guis.pres.AdvancedPresetSpec;
import sim.guis.pres.CreaturePresetSpec;
import sim.guis.pres.FoodPresetSpec;
import creature.Creature;
import creature.NewBehavior;
import creature.genetics.Chromosome;
import creature.genetics.StructureInterpreter;
import gui.GUIController;
import static gui.GUIController.FONT;
import static gui.TypingManager.typing;
import gui.components.GUIButton;
import gui.components.GUICommandField;
import gui.components.GUIPanel;
import gui.types.ComponentInputGUI;
import gui.types.GUIComponent;
import gui.types.GUIInputComponent;
import java.util.List;
import map.Terrain;
import map.TerrainGenerator;
import org.newdawn.slick.Color;
import sim.Preset;
import sim.SimGenerator;
import util.Color4;
import util.Vec2;
import static utility.GUIs.BUTTON_SIZE;
import static utility.GUIs.getColor;
import static utility.GUIs.nextPlace;

/**
 *
 * @author Kosmic
 */
public class NewPreset extends ComponentInputGUI {

    private String preName;
    private GUIPanel hide;
    private boolean selected;

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
        preName = "Unnamed";
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

        Preset def = new Preset("Default", eCostHunt, eCostForage, eCostRepro, reproThreshold, reproBuffer, hunterThreshold, eCostDetect, eCostMove, actionRad, nutrientsPerFood, huntYield, fRespawnAmount, foodSR, mutantFactor, lenVariance, wGenome, numCreatures);
        parent.addPreset(def);

        inputs.add(new GUIButton("creature", this, nextPlace(parent.getStartPos(), 2, -4), BUTTON_SIZE, "Creature", Color.white));
        inputs.add(new GUIButton("terrain", this, nextPlace(parent.getStartPos(), 2, -3), BUTTON_SIZE, "Terrain", Color.white));
        inputs.add(new GUIButton("food", this, nextPlace(parent.getStartPos(), 2, -2), BUTTON_SIZE, "Food", Color.white));
        inputs.add(new GUIButton("advanced", this, nextPlace(parent.getStartPos(), 2, -1), BUTTON_SIZE, "Advanced", Color.white));
        inputs.add(new GUIButton("nameButton", this, nextPlace(parent.getStartPos(), 2, 0), BUTTON_SIZE, "", Color.white));
        inputs.add(new GUICommandField("name", this, nextPlace(parent.getStartPos(), 2, 0).add(new Vec2(6, (BUTTON_SIZE.y - FONT.getHeight()) / 2.0 + FONT.getHeight())), BUTTON_SIZE.x - 12, Color.white, Color4.WHITE));

        hide = new GUIPanel("hide", nextPlace(parent.getStartPos(), 2, 2), BUTTON_SIZE.multiply(new Vec2(1, 7)), Color4.BLACK.withA(0.6));
        selected = true;

        ((GUICommandField) inputs.get(5)).setText("Name");

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
        selected = true;
    }

    public Vec2 getStartPos() {
        return parent.getStartPos();
    }

    @Override
    public void recieve(String string, Object o) {
        switch (string) {
            case "name":
                preName = (String) o;
                ((GUICommandField) inputs.get(5)).setText(preName);
                break;
            case "creature":
                selected = false;
                crPreSpec.start();
                System.out.println("Creature");
                break;
            case "terrain":
                selected = false;
                terrPreSpec.start();
                System.out.println("Terrain");
                break;
            case "food":
                selected = false;
                fPreSpec.start();
                System.out.println("Food");
                break;
            case "advanced":
                selected = false;
                advPreSpec.start();
                System.out.println("Advanced Settings");
                break;

            case "create":
                ((GUICommandField) inputs.get(5)).send();
                newPreset = new Preset(preName, eCostHunt, eCostForage, eCostRepro, reproThreshold, reproBuffer, hunterThreshold, eCostDetect, eCostMove, actionRad, nutrientsPerFood, huntYield, fRespawnAmount, foodSR, mutantFactor, lenVariance, wGenome, numCreatures);
                newPreset.set();
                parent.addPreset(newPreset);
                
            case "cancel":
                setVisible(false);
                parent.start();
                break;
        }
        if (string.equals("cancel")) {
            setVisible(false);
            parent.start();
        }
    }

    @Override
    public List<GUIComponent> mousePressed(Vec2 p) {

        List<GUIComponent> lgc = super.mousePressed(p);

        if (lgc.contains(inputs.get(4))) {

            lgc.remove(inputs.get(4));
            lgc.add(inputs.get(5));
        }

        return lgc;
    }

    @Override
    public void draw() {

        super.draw();

        if (!selected) {

            hide.draw();
        }
    }

    @Override
    public GUIInputComponent getDefaultComponent() {

        return inputs.get(4);
    }

}

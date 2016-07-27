/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import gui.GUI;
import java.util.HashMap;
import java.util.Map;
import util.Color4;
import util.Vec2;

/**
 *
 * @author Kosmic
 */
public class GUIs {

    private static final Map<String, GUI> GUIS = new HashMap();
    private static final Color4[] COLORS = {Color4.BLUE.withG(1.0), Color4.RED, Color4.WHITE};
    
    public static final Vec2 BUTTON_SIZE = new Vec2(200, 50);

    static {

        GUIS.put("mainMenu", new MainMenu("mainMenu"));
    }

    public static Color4 getColor(int rank) {

        if (rank >= 0 && rank < 3) {
            
            return COLORS[rank];
        }
        
        throw new IllegalArgumentException("rank must be an integer from 0 to 2");
    }
    
    public static Vec2 nextPlace(Vec2 start, int right, int down){
        
        return start.add(new Vec2(BUTTON_SIZE.x * right, -BUTTON_SIZE.y * down));
    }
}

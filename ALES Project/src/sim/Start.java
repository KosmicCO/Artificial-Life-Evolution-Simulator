/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim;

import sim.guis.MainMenu;
import engine.Core;
import graphics.Window2D;
import gui.GUIController;
import gui.TypingManager;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import static map.Terrain.currentT;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.PNGDecoder;
import static sim.TestGenMap.makeTestMap;
import util.Color4;

/**
 *
 * @author Kosmic
 */
public class Start {

    private static long updates;
    private static final int UPDATES_PER_TICK = 20;
    private static boolean running;
    private static boolean paused;

    public static boolean isRunning() {
        
        return running;
    }

    public static void setRunning(boolean running) {
        
        Start.running = running;
    }

    public static boolean isPaused() {
        
        return paused;
    }

    public static void setPaused(boolean paused) {
        
        Start.paused = paused;
    }
    
    public static void main(String[] args) {

        Core.screenWidth = 1000;
        Core.screenHeight = 500;
        Core.title = "ALES Project by Cruz & Bhargav";
        Core.is3D = false;

        Core.init();
        
        ByteBuffer[] icon_array = new ByteBuffer[2];
        
        try {
            
                icon_array[0] = ByteBuffer.allocateDirect(1);
                icon_array[0] = loadIcon("icons/ALES-Icon-16.png");
                icon_array[1] = ByteBuffer.allocateDirect(1);
                icon_array[1] = loadIcon("icons/ALES-Icon-32.png");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        
        Display.setIcon(icon_array);
        
        Window2D.background = Color4.BLACK;

        //testing graphics start
        makeTestMap();

        //testing graphics end
        MainMenu main = new MainMenu("mainMenu");

        TypingManager tm = new TypingManager(main);
        GUIController.add(main);
        main.start();

        Core.renderLayer(100).onEvent(GUIController::draw);

        Core.update.onEvent(() -> {

            GUIController.update();

            if (running && !paused) {
                
                if (updates % UPDATES_PER_TICK == 0) {
                   
                    currentT.update();
                }

                updates++;
            }
        });

        Core.run();
    }
    
    private static ByteBuffer loadIcon(String path) throws IOException {
        
        InputStream inputStream = new FileInputStream(path);
        try {
            PNGDecoder decoder = new PNGDecoder(inputStream);
            ByteBuffer bytebuf = ByteBuffer.allocateDirect(decoder.getWidth()*decoder.getHeight()*4);
            decoder.decode(bytebuf, decoder.getWidth()*4, PNGDecoder.RGBA);
            bytebuf.flip();
            return bytebuf;
        } finally {
            inputStream.close();
        }
    }
}

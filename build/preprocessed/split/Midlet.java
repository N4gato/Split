/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package split;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author mac1
 */
public class Midlet extends MIDlet {

    // Allocate a GameCanvas, set it to the current display, and start the game thread
   public void startApp() {
      GameMain game = new GameMain(); // GameMain extends GameCanvas for the game UI
      Display.getDisplay(this).setCurrent(game);
      new Thread(game).start();       // GameMain implements Runnable to run the game thread
   }
 
   public void pauseApp() { }
 
   public void destroyApp(boolean unconditional) { }
}

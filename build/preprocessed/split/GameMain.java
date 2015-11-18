/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package split;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author mac1
 */
class GameMain extends GameCanvas implements Runnable {
 
      // Avatar - Pacman with animation
      private Sprite goku;
      private Sprite gokuHandsAttack;
      private Sprite gokuLegsAttack;
      private Sprite gokuSp0;
      private Sprite gokuSp1;
      private Sprite gokuSp2;
      private Sprite gokuSayen;
      private Sprite teleport;
      private Sprite gokuUpDown;
      
      private String gokuF = "goku.png";
      private int gokuXCenter,  gokuYCenter; // (x,y) of the center of the Pacman
      
      private String gokuHandsAttackF = "gokuHandsAttack.png";
      private int gokuHandsAttackXCenter,  gokuHandsAttackYCenter; // (x,y) of the center of the Pacman
      
      private String gokuLegsAttackF = "gokuLegsAttack.png";
      private int gokuLegsAttackXCenter,  gokuLegsAttackYCenter; // (x,y) of the center of the Pacman
      
      private String gokuSp0F = "gokusp0.png";
      private int gokuSp0XCenter,  gokuSp0YCenter; // (x,y) of the center of the Pacman
      
      private String gokuSp1F = "gokusp1.png";
      private int gokuSp1XCenter,  gokuSp1YCenter; // (x,y) of the center of the Pacman
      
      private String gokuSp2F = "gokusp2.png";
      private int gokuSp2XCenter,  gokuSp2YCenter; // (x,y) of the center of the Pacman
      
      private String gokuSayenF = "sayenMode.png";
      private int gokuSayenXCenter,  gokuSayenYCenter; // (x,y) of the center of the Pacman
      
      private String teleportF = "teleport.png";
      private int teleportXCenter,  teleportYCenter; // (x,y) of the center of the Pacman
      
      private String gokuUpDownF = "gokuUpDown.png";
      private int gokuUpDownXCenter,  gokuUpDownYCenter; // (x,y) of the center of the Pacman
      
      private int Speed = 50;                // speed of move, in pixels
 
      // Avatar - Ghost with animation
      //private Sprite ghost;
      //private String ghostImageFilename = "/images/GhostFrames.png";
      //private int ghostXCenter,  ghostYCenter;
 
      private static final int FRAME_WIDTH = 40;
      private static final int FRAME_HEIGHT = 47;
      private static final int FRAME_RADIUS = FRAME_WIDTH / 2 + 1;      
       
      // Collision Detection
      //private boolean hasCollided;  // flag indicating collision
 
      private static final int UPDATE_INTERVAL = 100; // milliseconds
      private static final int INFO_AREA_HEIGHT = 20;  // height of the info display area
 
      // Constructor
      public GameMain() {
         super(true);
      }
 
      // Run the game loop in its own thread
      public void run() {
         int canvasWidth = getWidth();
         int canvasHeight = getHeight();
 
         // Construct the sprites
         try {
            Image gokuImg = Image.createImage(gokuF);
            goku = new Sprite(gokuImg, FRAME_WIDTH, FRAME_HEIGHT);
            goku.setRefPixelPosition(FRAME_RADIUS, FRAME_RADIUS);  // set rotation center

            Image gokuHandsAttackImg = Image.createImage(gokuHandsAttackF);
            gokuHandsAttack = new Sprite(gokuHandsAttackImg, FRAME_WIDTH, FRAME_HEIGHT);
            gokuHandsAttack.setRefPixelPosition(FRAME_RADIUS, FRAME_RADIUS);  // set rotation center
            
            Image gokuLegsAttackImg = Image.createImage(gokuLegsAttackF);
            gokuLegsAttack = new Sprite(gokuLegsAttackImg, FRAME_WIDTH, FRAME_HEIGHT);
            gokuLegsAttack.setRefPixelPosition(FRAME_RADIUS, FRAME_RADIUS);  // set rotation center
            
            Image gokuSp0Img = Image.createImage(gokuSp0F);
            gokuSp0 = new Sprite(gokuSp0Img, 80, FRAME_HEIGHT);
            gokuSp0.setRefPixelPosition(40, FRAME_RADIUS);  // set rotation center
            
            Image gokuSp1Img = Image.createImage(gokuSp1F);
            gokuSp1 = new Sprite(gokuSp1Img, FRAME_WIDTH, FRAME_HEIGHT);
            gokuSp1.setRefPixelPosition(FRAME_RADIUS, FRAME_RADIUS);  // set rotation center
            
            Image gokuSp2Img = Image.createImage(gokuSp2F);
            gokuSp2 = new Sprite(gokuSp2Img, 40, 63);
            gokuSp2.setRefPixelPosition(FRAME_RADIUS, 32);  // set rotation center
            
            Image gokuSayenImg = Image.createImage(gokuSayenF);
            gokuSayen = new Sprite(gokuSayenImg, 70, 56);
            gokuSayen.setRefPixelPosition(35, 28);  // set rotation center
            
            Image teleportImg = Image.createImage(teleportF);
            teleport = new Sprite(teleportImg,  FRAME_WIDTH, FRAME_HEIGHT);
           // teleport.setRefPixelPosition(FRAME_RADIUS, FRAME_RADIUS);  // set rotation center
            
            Image gokuUpDownImg = Image.createImage(gokuUpDownF);
            gokuUpDown = new Sprite(gokuUpDownImg, FRAME_WIDTH, FRAME_HEIGHT);
            gokuUpDown.setRefPixelPosition(FRAME_RADIUS, FRAME_RADIUS);  // set rotation center
            
         } catch (IOException e) {
            e.printStackTrace();
         }
 
         // Position goku at the center
         gokuXCenter = canvasWidth / 2;
         gokuYCenter = canvasHeight / 2;
         // Position ghost at the corner
         //ghostXCenter = canvasWidth / 4;
         //ghostYCenter = canvasHeight / 4;
         // Pacman's bounds
         int gokuXMin = FRAME_RADIUS;
         int gokuXMax = canvasWidth - FRAME_RADIUS;
         int gokuYMin = FRAME_RADIUS + INFO_AREA_HEIGHT;
         int gokuYMax = canvasHeight - FRAME_RADIUS;
 
         // Retrieve the off-screen graphics buffer for graphics drawing
         Graphics g = getGraphics();
         g.setColor(0x007fcf);
         g.fillRect(0, 0, canvasWidth, canvasHeight);
 
            // Draw the sprites
        goku.setPosition(gokuXCenter , gokuYCenter);
        goku.paint(g);
        goku.setFrame(0);
            
 
         // Game loop
         while (true) {
            // Check key state for user input
            int keyState = getKeyStates();
            if ((keyState & RIGHT_PRESSED) != 0) {
            teleportXCenter = gokuXCenter;
                teleportYCenter = gokuYCenter;
                
                teleport.setPosition(teleportXCenter, teleportYCenter);
                teleport.paint(g);
                teleport.nextFrame();
                flushGraphics();
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {}
                gokuXCenter += Speed;
                
                goku.setPosition(gokuXCenter, gokuYCenter);
                goku.paint(g);
                goku.setFrame(0);
                
                flushGraphics();
                try {
                    Thread.sleep(50);
                } catch (Exception ex) {}
               if (gokuXCenter > gokuXMax) {
                  gokuXCenter = gokuXMax;
               }
               goku.setTransform(Sprite.TRANS_NONE);
            } else if ((keyState & UP_PRESSED) != 0) {
               gokuYCenter -=3;
                
               goku.setTransform(Sprite.TRANS_NONE);
             } else if ((keyState & LEFT_PRESSED) != 0) {
               teleportXCenter = gokuXCenter;
                teleportYCenter = gokuYCenter;
                
                teleport.setPosition(teleportXCenter, teleportYCenter);
                teleport.paint(g);
                teleport.nextFrame();
                flushGraphics();
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {}
                gokuXCenter -= Speed;
                
                goku.setPosition(gokuXCenter, gokuYCenter);
                goku.paint(g);
                goku.setFrame(0);
                
                flushGraphics();
                try {
                    Thread.sleep(50);
                } catch (Exception ex) {}
               if (gokuXCenter < gokuXMin) {
                  gokuXCenter = gokuXMin;
               }
               goku.setTransform(Sprite.TRANS_MIRROR);
               goku.setTransform(Sprite.TRANS_MIRROR);
            } else if ((keyState & DOWN_PRESSED) != 0) {
               gokuYCenter += Speed;
               if (gokuYCenter > gokuYMax) {
                  gokuYCenter = gokuYMax;
               }
               //goku.setTransform(Sprite.TRANS_ROT90); // clockwise
            }else if((keyState & GAME_A_PRESSED) != 0){
                g.setColor(0x007fcf);
                g.fillRect(0, 0, canvasWidth, canvasHeight);
            
            
                gokuSp0XCenter = gokuXCenter;
                gokuSp0YCenter = gokuYCenter;
                
                
                gokuSp0.setPosition(gokuSp0XCenter, gokuSp0YCenter);
                gokuSp0.paint(g);
                gokuSp0.nextFrame();
                flushGraphics();
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {}
            }else if((keyState & GAME_B_PRESSED) != 0){
            
            }else if((keyState & GAME_C_PRESSED) != 0){
            
            }else if((keyState & GAME_D_PRESSED) != 0){
                   g.setColor(0x007fcf);
                g.fillRect(0, 0, canvasWidth, canvasHeight);
            
            
                gokuSayenXCenter = gokuXCenter;
                gokuSayenYCenter = gokuYCenter;
                
                
                gokuSayen.setPosition(gokuSayenXCenter, gokuSayenYCenter);
                gokuSayen.paint(g);
                gokuSayen.setFrame(0);
                flushGraphics();
                try {
                    Thread.sleep(150);
                } catch (Exception ex) {}
                 g.setColor(0x007fcf);
                g.fillRect(0, 0, canvasWidth, canvasHeight);
            
                gokuSayen.paint(g);
                gokuSayen.setFrame(1);
                flushGraphics();
                try {
                    Thread.sleep(150);
                } catch (Exception ex) {}
                 g.setColor(0x007fcf);
                g.fillRect(0, 0, canvasWidth, canvasHeight);
            
                gokuSayen.paint(g);
                gokuSayen.setFrame(2);
                flushGraphics();
                try {
                    Thread.sleep(100);
                } catch (Exception ex) {}
            }
 
            g.setColor(0x007fcf);
            g.fillRect(0, 0, canvasWidth, canvasHeight);
 
            // Draw the sprites
            goku.setPosition(gokuXCenter , gokuYCenter);
            goku.paint(g);
            goku.setFrame(0);
             
            //ghost.setPosition(ghostXCenter - FRAME_RADIUS, ghostYCenter - FRAME_RADIUS);
            //ghost.paint(g);
            //ghost.nextFrame();
 
            // Collision detection
            //hasCollided = goku.collidesWith(ghost, true);
 
            // Display info
            g.setColor(0xffffff);
            g.fillRect(0, 0, canvasWidth, INFO_AREA_HEIGHT);
            g.setColor(0x000000);
            g.drawString("(" + gokuXCenter + "," + gokuYCenter + ")", 4, 0, Graphics.TOP | Graphics.LEFT);
            //if (hasCollided) {
              // g.setColor(0xff0000);
               //g.drawString("Collided", canvasWidth / 2, 0, Graphics.TOP | Graphics.LEFT);
            //}
 
            // flush the off-screen buffer to the display
            flushGraphics();
 
            // Provide delay to achieve the targeted refresh rate,
            // also yield for other threads to perform their tasks.
            try {
               Thread.sleep(UPDATE_INTERVAL);
            } catch (InterruptedException e) {
            }
         }
      }
   }



           
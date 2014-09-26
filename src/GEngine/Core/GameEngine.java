/*
 * Copyright (c) 2014, Vladimir Moskalyuk 
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package GEngine.Core;

import GEngine.Game.BasicGame;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glClear;

public class GameEngine {

  public static final int DEBUG = 0;  
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  public static final String TITLE = "Engine";
  
  public static int TOTAL_GAME_UPDATES = 0;
  public static float TOTAL_GAME_TIME_MS =0;
  
  private double FRAME_RATE = 5000;
  
  private long startTime;
  private long passedTime;
  
  private boolean isRunning;
  
  private BasicGame game;
  
   private static GameEngine instance;
    
   public static GameEngine getInstance(BasicGame _game) {
       if (instance == null) {
            instance = new GameEngine(_game);
       }
       return instance;
   }
  private GameEngine(BasicGame _game)
  {
      System.out.println("Init GEngine");
      Window.createWindow(WIDTH, HEIGHT, TITLE, false);
      isRunning = false;  
      game = _game;
  }
  
  public void start()
  {
      if(!isRunning)
        run();
  }
  public void stop()
  {
      if(isRunning)
          isRunning = false;
  }
 
  private void run()
  {   
      isRunning = true;
      if(DEBUG != 1)
      {
          if(isRunning)
          {
            double start = Timer.getTime()/(double)Timer.MILI_SECOND;
            boolean res = game.loadContent();
            double end = Timer.getTime() /(double)Timer.MILI_SECOND;
            if(res)
                System.out.println("All loaded in: "+ (end - start)+"ms");
            else
                System.out.println("Oops something goes worng((");
          }
      }
      else
        if(isRunning)
            game.loadContent();   
      
      double frameTime = 1.0/FRAME_RATE;
      long lastTime = Timer.getTime();
      double unprocessedTime = 0;
      int render;
     
      //Timer initialization
      Timer.getDelta();
      Timer.setLastFPS(Timer.getSysTime());
      
      while(isRunning)
      {
          render = 0;
          startTime = Timer.getTime();
          passedTime = startTime - lastTime;
          lastTime = startTime;
          unprocessedTime +=passedTime/(double)Timer.SECOND;
          
          if(unprocessedTime > frameTime)
          {
              render = 1;
              if(Window.isCloseRequested())
                stop();
              
              unprocessedTime -= frameTime;

              update (Timer.getDelta());
              TOTAL_GAME_UPDATES++;
          }
          if(render!= 0)
            render();
          else
          {
              try {
                  Thread.sleep(1);
              }
              catch (InterruptedException ex) {
                  Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          
          TOTAL_GAME_TIME_MS +=unprocessedTime;
      }
      cleanUp();
  }
  
  private void update(int delta)
  {
      Timer.updateFPS();
      
      game.update(delta);
    
     //game.update(delta);
     this.inputUpdate();
         
  }
  private void inputUpdate()
  {
      Input.update();
      
  }
  private void render()
  {  
      glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);       
      game.render();     
      Window.render(); 
  }

  public void setFRAME_RATE(double FRAME_RATE)
  {
      this.FRAME_RATE = FRAME_RATE;
  }
  
  private void cleanUp()
  {

      game.cleanUp();
      Window.dispose();
      
      System.out.println("All Clear");
  }
}

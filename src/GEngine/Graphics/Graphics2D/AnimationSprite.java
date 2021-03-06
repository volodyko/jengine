/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GEngine.Graphics.Graphics2D;

import GEngine.Core.Timer;
import org.lwjgl.util.Rectangle;

/**
 *
 * @author 111
 */
class AnimationSprite
{
    public static int VERTICAL = 0;
    public static int HORIZONTAL = 1;
    
    
    private final int orientation;
    private final int framesCount;
   
    private final int frameWidth;
    private final int frameHeight;
    
    private int originalX;
    private int currentX;
    private int currentY;
    private int currentFrame;
    
    private int frequency;
    private int timer;
    
    private boolean isPlaying = false;

    public AnimationSprite( int frameCount, int x, int y,
                       int frameWidth, int frameHeight,int orientation)
    {
        this.framesCount = frameCount-1;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.orientation = orientation;
        currentX = x;
        currentY = y;
        currentFrame = 0;
        timer =0;
        frequency =Math.round(Timer.getAvarageFPS() / framesCount); 
        originalX = currentX;
    }
    
    public void start()
    {
        if(!isPlaying)
            isPlaying = true;

    }
    public void stop()
    {
        if(isPlaying) 
        {
            isPlaying = false;
                currentFrame = -1;
        }
            
    }
    public void pause()
    {
        if(isPlaying)
            isPlaying = false;
    }
    
    
    public void iterateFrame(int delta)
    {
        //frequency = Math.round(Timer.getAvarageFPS() / framesCount)*10;
        timer += Timer.getSysTime()/ Timer.MILI_SECOND;
       
        if(timer > frequency)
        {
            if(isPlaying)
            {
                    if(currentFrame!= framesCount)
                    {
                        currentFrame++;
                    }
                    else
                    {
                        currentFrame = 0;
                    }            
                    this.calculateFrame();  
            }
            timer = 0;
         }
    }
    
    public Rectangle draw()
    {
        return new Rectangle(originalX+currentX, currentY, frameWidth, frameHeight);
    }
    
    private void calculateFrame()
    {
        if(orientation == HORIZONTAL)
          currentX = currentFrame*frameWidth;
        if(orientation == VERTICAL)
            currentY =currentFrame*frameHeight;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }

    public boolean isIsPlaying()
    {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying)
    {
        this.isPlaying = isPlaying;
    }
    
    
    public int getCurrentFrame()
    {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame)
    {
        this.currentFrame = currentFrame;
    }

    public int getFrameWidth()
    {
        return frameWidth;
    }

    public int getFrameHeight()
    {
        return frameHeight;
    }

    public int getCurrentX()
    {
        return currentX;
    }

    public int getCurrentY()
    {
        return currentY;
    }
    
    
}

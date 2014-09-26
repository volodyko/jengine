/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GEngine.Graphics.Graphics2D;

/**
 *
 * @author 111
 */
public class Sprite
{
    
    private final Texture2D spriteSheet;
   
    private final int frameWidth;
    private final int frameHeight;
    
    private int currentX;
    private int currentY;
    
    public Sprite(Texture2D spriteSheet)
    {
        this.spriteSheet = spriteSheet;
        this.frameWidth = 0;
        this.frameHeight = 0;
        currentX = 0;
        currentY = 0;    
    }
    
    
    public void draw(SpriteBatch batch, float x, float y)
    {
       
         batch.drawRegion(spriteSheet, currentX, currentY, frameWidth, frameHeight, 
                    x, y, frameWidth,frameHeight);
    }
    
    public void dispose()
    {
        spriteSheet.dispose();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GEngine.Graphics.Graphics2D;

import static GEngine.Core.GameEngine.DEBUG;
import GEngine.Core.Window;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author 111
 */
public class Camera 
{
    float posX, posY, rot, zoom;
    private float width;
    private float height;

    /** 
     * Creates camera object with default params
     */
    public Camera()
    {
        this(0f,0f,1f,0f);
    }
    
    public Camera(float panX, float panY)
    {
        this(panX,panY,1f,0f);
    }

    public Camera(float panX, float panY, float zoom)
    {
        this(panX,panY,0f,zoom);
    }
    
    /** 
     * Creates camera object
     * @param panX up-left X coordinate of camera viewport
     * @param panY up-left Y coordiante of camera viewport
     * @param rot angle of camera rotation 
     * @param zoom zoom 
     */
    public Camera(float panX, float panY, float rot, float zoom)
    {
        this.posX = panX;
        this.posY = panY;
        this.rot = rot;
        this.zoom = zoom;
    }

    /** Return x coordinate relative to current viewport
     *
     * @param x world coordinate
     * @return normilized x
     */
    public float normalizeX(float x)
    {
        return x /zoom - this.posX;
    }
    /** Return y coordinate relative to current viewport
     *
     * @param y world coordinate
     * @return normilized y
    */
    public float normalizeY(float y)
    {
        return y/zoom - this.posY;
    }
    
    /**
     * Returns the current camera viewport width
     * @return float value 
     */
    public float getWidth( )
    {
        return normalizeX(Window.getWidth());
    }
    /**
     * Returns the current camera viewport height
     * @return float value 
     */
    public float getHeight( )
    {
        return normalizeY(Window.getHeight());
    }
   
    /**Draw current view matrix
     * Call before SpriteBatch.begin() 
     * @param batch current SpriteBatch
     * @see SpriteBatch#begin()
     */
    public void drawCAMView(SpriteBatch batch)
    {
        if(batch.isDrawing())
        {
            if(DEBUG != 0)
                System.out.println("Must be called before batch.begin() +"
                        + "or after batch.end()");
            return;
        }
        //get the instance of the view matrix for our batch
        Matrix4f view = batch.getViewMatrix();

        //reset the matrix to identity, i.e. "no camera transform"
        view.setIdentity();

        //scale the view
        if (zoom != 1f) {
             //we want to rotate by a center origin point, so first we translate
           // view.translate(new Vector2f(getWidth()/2,getHeight()/2));
                view.scale(new Vector3f(width, height, 1f));
           // view.translate(new Vector2f(-getWidth()/2, -getHeight()/2));
        }

        //pan the camera by translating the view matrix
        view.translate(new Vector2f(posX, posY));

        //after translation, we can rotate...
        if (rot!=0f) {
            //we want to rotate by a center origin point, so first we translate
            view.translate(new Vector2f(getWidth()/2,getHeight()/2));

            //then we rotate
            view.rotate(rot, new Vector3f(0, 0, 1));

            //then we translate back
            view.translate(new Vector2f(-getWidth()/2,-getHeight()/2));
        }

        //apply other transformations here...

        //update the new view matrix
        batch.updateUniforms();
    }
    
    /** Draw viewport with no view matrix transormations 
     * Call before SpriteBatch.begin
     * @param batch current SpriteBatch
     * @see SpriteBatch#begin()
     */
    public void drawHUDView(SpriteBatch batch)
    {
        if(batch.isDrawing())
        {
            if(DEBUG != 0)
                System.out.println("Must be called before batch.begin() +"
                        + "or after batch.end()");
            return;
        }
        //draw the text with identity matrix, i.e. no camera transformation
	batch.getViewMatrix().setIdentity();
	batch.updateUniforms();
    }

    public void zoom(float zoom)
    {
        this.zoom = zoom;
    }
    
    public void rotate(float angle)
    {
        rot = angle;
    }
    
    public void translate(float x, float y)
    {
        this.posX =x; 
        this.posY = y;
    }
    
    public float getX()
    {
        return posX;
    }

    public float getY()
    {
        return posY;
    }

    public float getAngle()
    {
        return rot;
    }

    public float getZoom()
    {
        return zoom;
    } 
}

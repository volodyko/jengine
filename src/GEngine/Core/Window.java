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

import java.awt.Canvas;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glViewport;

/**
 *
 * @author Vladimir Moskalyuk 
 */
public class Window {
    
    private static DisplayMode displayMode;
    
    protected static void createWindow(int width, int height, String title, boolean fullScreen)
    {
        System.out.println("Creating window");
        Display.setTitle(title);
        try {
           Display.setFullscreen(fullScreen); 
           DisplayMode d[] = Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            System.out.println(i+ " Height "+d[i].getHeight()+" Width "+d[i].getWidth());
            if (d[i].getWidth() == width
                && d[i].getHeight() == height
                && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
            Display.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

        initGL(displayMode.getWidth(), displayMode.getHeight());
       
    }
    
    protected static void initGL(int width, int height)
    {
        glClearColor(0.5f, 0.5f, 1.0f, 1.0f);
        // Enable blending
        glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        // Enable Texture Mapping
        glEnable(GL_TEXTURE_2D);
        
        // Enable Smooth Shading ?????
        glShadeModel(GL_SMOOTH);                            
        
        //Not needed in 2D
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
        
        //2D Scene
        glViewport(0,0,width,height);
        glLoadIdentity();// Reset The Current Viewport
        glMatrixMode(GL_MODELVIEW);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        
        
    }
    
    public static void setParent(Canvas canvas)
    {
        try {
            Display.setParent(canvas);
        }
        catch (LWJGLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void resize(int width, int height)  {
        glViewport(0, 0, width, height);
    }
    
    public static void clearColor(float r, float g, float b, float a)
    {
        glClearColor(r, g, b, a);
    }
           
    protected static void render()
    {    
        Display.update();
    }
    public static boolean isCloseRequested()
    {
        return Display.isCloseRequested();
    }
    
    public static int getWidth()
    {
        return Display.getDisplayMode().getWidth();
    }
    
    public static int getHeight()
    {
        return Display.getDisplayMode().getHeight();
    }
    
    public static String getTitle()
    {
        return Display.getTitle();
    }
    
    public static void setTitle(String title)
    {
        Display.setTitle(title);
    }
    
    protected static void dispose()
    {
        Display.destroy();
    }
}

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

import static GEngine.Core.GameEngine.DEBUG;
import org.lwjgl.Sys;

/**
 *
 * @author Vladimir Moskalyuk 
 */
public class Timer {
    private static long lastFrame;
    private static long lastFPS;
    private static int fps;
    private static int actualFPS;
    private static int avarageFPS;
    public static long SECOND = 1000000000L;
    public static long MILI_SECOND = 1000000L;
  
    public static long  getSysTime()
    {
        return (Sys.getTime() * 1000) /Sys.getTimerResolution();
    }
    public static long getTime()
    {
        return System.nanoTime();
    }
   
    
    public static int getDelta()
    {
        long time = getSysTime();
        int delta = (int) (time - lastFrame);
        Timer.lastFrame = time;
        return delta;
    }
    public static int getAvarageFPS()
    {
        return (avarageFPS + actualFPS)/2;
    }
    public static void updateFPS()
    {   
        if (Timer.getSysTime() - Timer.lastFPS > 1000) {
	     avarageFPS = actualFPS;
            //DEBUG: FPS out
            if(DEBUG == 1)
                System.out.println("FPS: "+fps);
            actualFPS = fps;
            
            Timer.fps = 0;
            
            Timer.lastFPS += 1000;
	}
        
	Timer.fps++;
    }
    
    public static void setLastFrame(long _lastFrame)
    {
        Timer.lastFrame = _lastFrame;
    }
    public static void setLastFPS(long _lastFPS)
    {
        Timer.lastFPS = _lastFPS;
    }
    public static int getFps() {
        return actualFPS;
    }
}

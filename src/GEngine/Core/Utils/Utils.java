/*
 * Copyright (c) 2014, 111
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
package GEngine.Core.Utils;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 111
 */
public class Utils {
	private static final Random RND = new Random();
    
    
    public static float rnd() {
    	return rndFloat();
    }
    
    public static int rndInt() {
    	return RND.nextInt();
    }
    
    public static int rnd(int high) {
    	return RND.nextInt(high);
    }
    
    public static float rndFloat() {
    	return RND.nextFloat();
    }
    
    public static int rnd(int low, int high) {
        if (low==high)
            return low;
        return RND.nextInt(high - low) + low;
    }
    
    public static float rnd(float low, float high) {
        if (low==high)
            return low;
        return low + (RND.nextFloat() * (high - low));
    }
    
    public static void log(String msg) {
    	getLogger().log(Level.INFO, msg);
    }
    
    public static void warn(String msg) {
    	getLogger().log(Level.WARNING, msg);
    }
    
    public static void error(String msg) {
    	error(msg, null);
    }
    
    public static Logger getLogger() {
    	return Logger.getLogger(Utils.class.getName());
    }
    
    public static void error(String msg, Throwable t) {
    	if (t!=null) getLogger().log(Level.SEVERE, msg, t);
    	else getLogger().log(Level.SEVERE, msg);
    }
    


}
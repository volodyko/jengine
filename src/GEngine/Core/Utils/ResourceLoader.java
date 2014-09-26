/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GEngine.Core.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 111
 */
public class ResourceLoader {
    
    private static ResourceLocator resourceLocator = new DefaultResourceLocator();
    
    	/**
	 * Loads the given input stream into a source code string.
	 * @param in the input stream
	 * @return the resulting source code String
	 * @author Nitram
	 */
    public static String readFile(InputStream in)  {
            try {
                final StringBuffer sBuffer = new StringBuffer();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                    final char[] buffer = new char[1024];
                    
                    int cnt;
                    while ((cnt = br.read(buffer, 0, buffer.length)) > -1) {
                        sBuffer.append(buffer, 0, cnt);
                    }
                }
                in.close();
                return sBuffer.toString();
            } catch (IOException ex) {
                Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
	}
    
    public static URL getResource(String str) {
    	URL u = getResourceLocator().getResource(str);
    	return u;
    }
    
    public static InputStream getResourceAsStream(String str) {
    	InputStream in = getResourceLocator().getResourceAsStream(str);
    	return in;
    }
    
    public static void setResourceLocator(ResourceLocator r) {
    	resourceLocator = r;
    }
    
    public static ResourceLocator getResourceLocator() {
    	return resourceLocator;
    }

    public static final class DefaultResourceLocator implements ResourceLocator {

    	public static final File ROOT = new File(".");
    	
        private static File createFile(String ref) {
            File file = new File(ROOT, ref);
            if (!file.exists()) {
                file = new File(ref);
            }
            
            return file;
        }
        
            @Override
	    public InputStream getResourceAsStream(String ref) {
	        InputStream in = ResourceLoader.class.getClassLoader().getResourceAsStream(ref);
	        if (in==null) { // try file system
	            try { in =  new FileInputStream(createFile(ref)); }
	            catch (FileNotFoundException e) {}
	        }
	        return in;
	    }

            @Override
	    public URL getResource(String ref) {
	        URL url = ResourceLoader.class.getClassLoader().getResource(ref);
	        if (url==null) {
	            try { 
	                File f = createFile(ref);
	                if (f.exists())
	                    return f.toURI().toURL();
	            } catch (MalformedURLException e) {}
	        }
	        return url;
	    }
    }
    
    public static interface ResourceLocator {
    	public URL getResource(String str);
    	public InputStream getResourceAsStream(String str);
    }
}

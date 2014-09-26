/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GEngine.Core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 111
 */
public class Configuration
{
    private final Properties XMLProperties = new Properties();
    private HashMap<String, String> engineProperties;

    public Configuration()
    {
        engineProperties = new HashMap<>();
    }
    
    public void load(String fileName)
    {
        try {
            XMLProperties.loadFromXML(new FileInputStream(fileName));
           
        }
        catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void save(String fileName)
    {   
    }
    
    public String getProperty()
    {
        return null;
    }
}


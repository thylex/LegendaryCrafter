/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henrik
 */
public class CrafterSettings {
    private Properties props = null;

    public CrafterSettings() {
        reset();
    }

    public void save() {
        try {
            OutputStream outstream = new FileOutputStream(new File(props.getProperty("ConfFile")));
            System.out.println(props.toString());
            props.storeToXML(outstream, "LegendaryCrafter Properties");
            outstream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrafterSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrafterSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void load() {
        try {
            InputStream instream = new FileInputStream(new File(props.getProperty("ConfFile")));
            System.out.println(props.toString());
            props.loadFromXML(instream);
            instream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrafterSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrafterSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void reset() {
        String baseDir = System.getProperty("user.home") + "\\Documents\\LegendaryCrafter";
        props = new Properties();
        props.setProperty("ConfFile", (baseDir + "\\config.xml"));
        props.setProperty("AppHome", baseDir);
        //props.setProperty("OsName", System.getProperty("os.name"));
        //props.setProperty("HomeFolder",System.getProperty("user.home"));
    }
    
    public String getProp(String PropKey) {
        return props.getProperty(PropKey, null);
    }
    
    public void setProp(String PropKey, String PropVal) {
        props.setProperty(PropKey, PropVal);
        save();
    }
}

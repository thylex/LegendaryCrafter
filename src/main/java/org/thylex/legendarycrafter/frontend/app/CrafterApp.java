/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.frontend.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.thylex.legendarycrafter.backend.CrafterSettings;
import org.thylex.legendarycrafter.backend.db.InventoryDB;
import org.thylex.legendarycrafter.backend.db.StaticDB;
import org.thylex.legendarycrafter.backend.db.entity.inv.Resource;
import org.thylex.legendarycrafter.frontend.gui.CrafterGUI;

/**
 *
 * @author Henrik
 */
public class CrafterApp {
    private CrafterSettings settings = null;
    private StaticDB staticDB = null;
    private InventoryDB invDB = null;
    private CrafterGUI gui = null;
    
    public CrafterApp() {
        String os = System.getProperty("os.name");
        if (! os.startsWith("Windows")) {
            System.out.println("Unsupported OS, exiting");
        }
        Initialize();
        gui = new CrafterGUI(this);
        
    }
    
    public Resource getInvByName(String resName) {
        return invDB.getResourceByName(resName);
    }
    
    public void updateInv(Resource res) {
        invDB.merge(res);
    }
    
    public List<Resource> getAllInv() {
        return invDB.getAllResources();
    }
    
    public void importFromGH() {
        invDB.importFromGH();
        gui.refresh();
    }
    
    private void Initialize() {
        settings = new CrafterSettings();
        
        // Check for new environment
        try {
            File dummy = new File(settings.getProp("ConfFile"));
            if (dummy.exists()) {
                settings.load();
            } else {
                new File(dummy.getParent()).mkdirs();
            }   
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        invDB = new InventoryDB(this);
        
        staticDB = new StaticDB(this, "bla");
    }
    
    public void close() {
        settings.save();
        invDB.close();
        staticDB.close();
        gui.setVisible(false);
        gui.dispose();
    }

    public CrafterSettings getSettings() {
        return this.settings;
    }
}

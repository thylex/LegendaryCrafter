/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.frontend.models;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import org.thylex.legendarycrafter.backend.db.entity.stat.Profession;
import org.thylex.legendarycrafter.backend.db.entity.stat.Schematic;
import org.thylex.legendarycrafter.backend.db.entity.stat.SkillGroup;
import org.thylex.legendarycrafter.frontend.app.CrafterApp;

/**
 *
 * @author Henrik
 */
public class SchematicListModel extends AbstractListModel {
    private Object[] schData = null;
    private CrafterApp app = null;
    
    public SchematicListModel(CrafterApp app, String profession) {
        this.app = app;
        if (profession != null) {
            Profession p = app.getStaticDB().getProfessionByName(profession);
//            System.out.println("Getting schematics for " + p.getProfName());
            ArrayList values = new ArrayList();
            for (SkillGroup sg : p.getSkillGroups()) {
//                System.out.println("Gettings schematics for group " + sg.getSkillGroupName() + sg.getSkillGroup());
                for (Schematic s : app.getStaticDB().getSchematicByGroupName(sg.getSkillGroup())) {
//                    System.out.println("Adding schematic: " + s.getSchematicName());
//                    values.add(s.getSchematicName());
                    values.add(new Item(s, s.getSchematicName()));
                }
            }
            schData = values.toArray();
        }
//        System.out.println("Number of schematics in list: " + getSize());
    }

    public int getIndexOf(Object o) {
        int i = 0;
        int retVal = 0;
        for (Object obj : schData) {
            if (obj.toString() == o.toString()) {
                retVal = i;
            }
            i++;
        }
        return retVal;
    }
    
    @Override
    public int getSize() {
        return schData.length;
    }

    @Override
    public Object getElementAt(int index) {
        return schData[index];
    }
    
}

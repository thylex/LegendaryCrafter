/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.frontend.models;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Comparator.comparing;
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

    public SchematicListModel(CrafterApp app, Profession profession) {
        this.app = app;
        if (profession != null) {
            schData = getSchematicsForProfession(profession).toArray();
        }
    }

    public SchematicListModel(CrafterApp app, String profession) {
        this.app = app;
        if (profession != null) {
            Profession p = app.getStaticDB().getProfessionByName(profession);
            schData = getSchematicsForProfession(p).toArray();
        }
    }

    private ArrayList<Item> getSchematicsForProfession(Profession prof) {
        ArrayList<Item> values = new ArrayList();
        if (prof != null) {
            for (SkillGroup sg : prof.getSkillGroups()) {
                for (Schematic s : app.getStaticDB().getSchematicByGroupName(sg.getSkillGroup())) {
                    values.add(new Item(s, s.getSchematicName()));
                }
            }
            Collections.sort(values, comparing(Item::getDescription));
        }
        return values;
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

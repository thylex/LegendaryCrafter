/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.frontend.models;

import java.util.ArrayList;
import org.thylex.legendarycrafter.backend.db.entity.stat.Profession;
import org.thylex.legendarycrafter.backend.db.entity.stat.Schematic;
import org.thylex.legendarycrafter.backend.db.entity.stat.SkillGroup;
import org.thylex.legendarycrafter.frontend.app.CrafterApp;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Henrik
 */
public class SchematicTableModel extends AbstractTableModel {

    private final static String[] colNames = {"Schematic"};
    private Object[][] schData = null;
    private CrafterApp app = null;

    public SchematicTableModel(CrafterApp app, Profession profession) {
        this.app = app;
        if (profession != null) {
            getSchematicsForProfession(profession);
        }
    }

    public SchematicTableModel(CrafterApp app, String profession) {
        this.app = app;
        if (profession != null) {
            Profession p = app.getStaticDB().getProfessionByName(profession);
            getSchematicsForProfession(p);
        }
    }

    private void getSchematicsForProfession(Profession prof) {
        ArrayList<ArrayList> rows = new ArrayList();
        if (prof != null) {
            for (SkillGroup sg : prof.getSkillGroups()) {
                for (Schematic s : app.getStaticDB().getSchematicByGroupName(sg.getSkillGroup())) {
                    ArrayList<Item> col = new ArrayList<>();
                    col.add(new Item(s, s.getSchematicName()));
                    rows.add(col);
                }
                System.out.println("Schematic count: " + rows.size());
                System.out.println("First schematic is: " + rows.get(0).toString());
            }
            //Collections.sort(cols, comparing(Item::getDescription));
            int rowSize = rows.size();
            int colSize = 1;
            schData = new Object[rowSize][colSize];
            int currRow = 0;
            for (ArrayList row : rows) {
                schData[currRow++] = row.toArray();
            }
        }

    }

    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return String.class;
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
    public int getRowCount() {
        return schData.length;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return schData[rowIndex][columnIndex];
    }

}

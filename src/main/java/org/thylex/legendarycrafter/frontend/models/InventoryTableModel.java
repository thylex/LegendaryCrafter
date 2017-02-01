/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.frontend.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.thylex.legendarycrafter.backend.db.entity.inv.Resource;

/**
 *
 * @author Henrik
 */
public class InventoryTableModel extends AbstractTableModel {

    public static final String[] columnNames = {"Name", "Type",
        "ER", "CR", "CD", "DR", "FL",
        "HR", "MA", "PE", "OQ", "SR", "UT",
        "Amount"};
    private Object[][] invData = null;

//    public InventoryTableModel(List<Resource> list) {
//        super(new Vector(list), new Vector(Arrays.asList(columnNames)));
//    }
    
    public InventoryTableModel(List<Resource> list) {
        int row = 0;
        int col = 0;
        int listRows = list.size();
        invData = new Object[listRows][];
        for (Resource res : list) {
            col = 0;
            Object[] line = new Object[14];
            line[col++] = res.getName();
            line[col++] = res.getResourceTypeName();
            line[col++] = res.getER();
            line[col++] = res.getCR();
            line[col++] = res.getCD();
            line[col++] = res.getDR();
            line[col++] = res.getFL();
            line[col++] = res.getHR();
            line[col++] = res.getMA();
            line[col++] = res.getPE();
            line[col++] = res.getOQ();
            line[col++] = res.getSR();
            line[col++] = res.getUT();
            line[col++] = res.getAmount();
            System.out.println("Adding to model: " + res.getName());
            invData[row++] = line;
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public void setValueAt(Object newValue, int rowIndex, int ColIndex) {
        invData[rowIndex][ColIndex] = (Integer)newValue;
        fireTableCellUpdated(rowIndex, ColIndex);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col == 13) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getRowCount() {
        return invData.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return invData[rowIndex][columnIndex];
    }

}

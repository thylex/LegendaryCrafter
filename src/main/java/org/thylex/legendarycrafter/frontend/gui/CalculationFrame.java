/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.frontend.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.thylex.legendarycrafter.backend.db.entity.inv.Resource;
import org.thylex.legendarycrafter.backend.db.entity.stat.ObjectType;
import org.thylex.legendarycrafter.backend.db.entity.stat.ResourceGroup;
import org.thylex.legendarycrafter.backend.db.entity.stat.ResourceType;
import org.thylex.legendarycrafter.backend.db.entity.stat.Schematic;
import org.thylex.legendarycrafter.backend.db.entity.stat.SchematicIngredients;
import org.thylex.legendarycrafter.frontend.app.CrafterApp;
import org.thylex.legendarycrafter.frontend.models.Item;

/**
 *
 * @author Henrik
 */
public class CalculationFrame extends javax.swing.JFrame implements java.awt.event.ActionListener {

    private CrafterApp app;
    private Schematic schem;
    private ArrayList<Type0Panel> type0List = new ArrayList<>();

    /**
     * Creates new form CalculationGUI
     */
    public CalculationFrame() {
        initComponents();
    }

    public CalculationFrame(CrafterApp app, Schematic schematic) {
        initComponents();
        this.app = app;
        this.schem = schematic;

        prepareLayout();

    }

    private void prepareLayout() {
        this.setTitle(schem.getSchematicName());
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int nrIngreds = schem.getIngredients().size();
        int row = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (SchematicIngredients si : schem.getIngredients()) {
            System.out.println("Adding: " + si.getIngredientName());
            gbc.gridy = row;
            if (si.getIngredientType() == 0) {
                Type0Panel panel = new Type0Panel();
                panel.setBorder(new TitledBorder(si.getIngredientName().replace("_", " ")));
                ResourceGroup rg = app.getStaticDB().getRescourceGroup(si.getIngredientObject());
                panel.setRequiredText(rg.getGroupName());
                panel.setAmountLabel(si.getIngredientQuantity().toString() + " x");
                // TODO: Set combobocmodel of materials
                List<ResourceGroup> rgList = app.getStaticDB().getResourceGroupByCategory(si.getIngredientObject());
                ArrayList<ResourceType> rtList = new ArrayList<>();
                for (ResourceGroup resGrp : rgList) {
                    rtList.addAll(app.getStaticDB().getResourceTypeByGroup(resGrp.getResourceGroup()));
                }
                panel.setMaterialBoxActionListener(this);
                panel.setMaterialBoxModel(setupMatBoxModel(rtList, si));
                this.getContentPane().add(panel, gbc);
                type0List.add(panel);
            }
            if (si.getIngredientType() == 3) {
                Type3Panel panel = new Type3Panel();
                panel.setBorder(new TitledBorder("sub component"));
                panel.setRequiredText(si.getIngredientName().replace("_", " "));
                this.getContentPane().add(panel, gbc);
            }
            row++;
        }
        
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridheight = nrIngreds;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(setupGeneralInfoPanel(), gbc);
        
        this.pack();
        this.validate();
        this.setVisible(true);
    }
    
    private JPanel setupGeneralInfoPanel() {
        ObjectType ot = app.getStaticDB().getObjectType(schem.getObjectType());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new TitledBorder("General info"));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel(ot.getTypeName()), gbc);
        
        gbc.gridy = 1;
        panel.add(new JLabel("Complexity: " + schem.getComplexity().toString()), gbc);
        
        gbc.gridy = 2;
        panel.add(new JLabel("XP: " + schem.getXpAmount().toString()), gbc);
        
        return panel;
    }
    
    private ComboBoxModel setupMatBoxModel(List<ResourceType> rtList, SchematicIngredients si) {
        ArrayList<Resource> resList = new ArrayList<>();
        DefaultComboBoxModel<Item> model = new DefaultComboBoxModel<>();
        
        for (ResourceType rt : rtList) {
            System.out.println("Getting resource of type: " + rt.getResourceTypeName());
            resList.addAll(app.getInvDB().getResourceByType(rt.getResourceType()));
        }
        System.out.println("Total resources found: " + resList.size());
        for (Resource res : resList) {
            ArrayList itemList = new ArrayList();
            itemList.add(si);
            itemList.add(res);
            Item item = new Item(itemList, res.getName());
            model.addElement(item);
        }
        return model;
    }
    
    private void materialBoxSelection(SchematicIngredients si, Resource res) {
        System.out.println("Material selected for: " + si.getIngredientName());
        System.out.println("Material chosen: " + res.getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass() == JComboBox.class) {
            JComboBox box = (JComboBox) e.getSource();
            Item sel = (Item) box.getSelectedItem();
            List val = (List) sel.getValue();
            SchematicIngredients si = (SchematicIngredients) val.get(0);
            Resource res = (Resource) val.get(1);
            materialBoxSelection(si, res);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CalculationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalculationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalculationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalculationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculationFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

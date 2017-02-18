/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter.frontend.gui;

import org.thylex.legendarycrafter.frontend.models.Item;
import org.thylex.legendarycrafter.frontend.models.SchematicListModel;
import org.thylex.legendarycrafter.frontend.models.InventoryTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import org.thylex.legendarycrafter.backend.db.entity.inv.Resource;
import org.thylex.legendarycrafter.backend.db.entity.stat.*;
import org.thylex.legendarycrafter.frontend.app.CrafterApp;

/**
 *
 * @author Henrik
 */
public class CrafterFrame extends javax.swing.JFrame implements TableModelListener, ListSelectionListener {

    private CrafterApp app = null;
    private JTabbedPane tabPane = null;
    private JPanel invPanel = null;
    private JTable invTable = null;
    private JPanel schPanel = null;
    private JList schList = null;
    private String activeProf;
    private JButton calcButton = null;

    /**
     * Creates new form CrafterGUI
     */
    public CrafterFrame(CrafterApp app) {
        this.app = app;
        initComponents();
        setTitle("Legendary Crafter");

        // Get properties
        activeProf = app.getSettings().getProp("SelectedProfession");

        // Create base Tabbed Pane
        tabPane = new JTabbedPane(JTabbedPane.TOP);

        // Create base Panel for Inventory display
        invPanel = new JPanel();
        invPanel.setLayout(new BorderLayout());

        // Top controls for inventory tab
        invPanel.add(new JLabel("Inventory tab"), BorderLayout.PAGE_START);

        // Inventory Table display
        invTable = new JTable(new InventoryTableModel(app.getInvDB().getAllResources()));
        invTable.setFillsViewportHeight(true);
        invTable.getModel().addTableModelListener(this);
        setInvColumnWidth();
        invPanel.add(new JScrollPane(invTable), BorderLayout.CENTER);

        // Create base Panel for Schematics view
        schPanel = setupSchematicsPanel(new JPanel());
        schPanel.setPreferredSize(this.getContentPane().getMaximumSize());

        // Add Panels to Tabs
        tabPane.addTab("Schematics", schPanel);
        tabPane.addTab("Inventory", invPanel);

        // Add Tabbed Pane to base Frame
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.getContentPane().add(tabPane);

        validate();
        this.setVisible(true);
    }

    private JPanel setupSchematicsPanel(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;

        //First row
        grid.gridy = 0;
        grid.gridx = 0;
        grid.anchor = GridBagConstraints.NORTHWEST;
        JComboBox profBox = new JComboBox();
        for (Profession prof : app.getStaticDB().getAllProfessions()) {
            profBox.addItem(new Item(prof, prof.getProfName()));
            //System.out.println("Skill group count: " + prof.getSkillGroups().size());
        }
        if (activeProf != null) {
            profBox.getModel().setSelectedItem(activeProf);
        }
        profBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                profBoxActionPerformed(e);
            }
        });
        JLabel profLabel = new JLabel("Select profession");
        profLabel.setLabelFor(profBox);

        panel.add(profLabel, grid);
        grid.gridx = 1;
        grid.fill = GridBagConstraints.NONE;
        panel.add(profBox, grid);

        //Second row
        grid.gridwidth = 2;
        grid.anchor = GridBagConstraints.WEST;
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridy = 1;
        grid.gridx = 0;
        schList = new JList(new SchematicListModel(app, activeProf));
        schList.setLayoutOrientation(JList.VERTICAL);
        schList.setVisibleRowCount(20);
        schList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        schList.addListSelectionListener(this);
        panel.add(new JScrollPane(schList), grid);

        //Third row
        grid.gridwidth = 1;
        grid.fill = GridBagConstraints.NONE;
        grid.anchor = GridBagConstraints.SOUTHWEST;
        grid.gridy = 2;
        grid.gridx = 0;
        calcButton = new JButton("Calculate");
        calcButton.setName("calcButton");
        calcButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcButtonAction(e);
            }
        });
        calcButton.setEnabled(false);
        panel.add(calcButton, grid);
        grid.gridx = 1;
        grid.anchor = GridBagConstraints.SOUTHEAST;
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonAction(e);
            }
        });
        panel.add(exitButton, grid);
        return panel;
    }

    private void calcButtonAction(java.awt.event.ActionEvent e) {
        Item sel = (Item) schList.getSelectedValue();
        Schematic s = (Schematic) sel.getValue();
        app.newCalculationWindow(s);
    }

    private void exitButtonAction(java.awt.event.ActionEvent e) {
        app.close();
    }

    @Override
    public void valueChanged(javax.swing.event.ListSelectionEvent e) {
        JList list = (JList) e.getSource();
        if (e.getValueIsAdjusting() != true) {
            if (list.getSelectedValuesList().size() > 0) {
                calcButton.setEnabled(true);
                Item sel = (Item) list.getSelectedValue();
                Schematic s = (Schematic) sel.getValue();
                System.out.println("Schematic selected: " + s.getSchematicID());
                List<SchematicIngredients> ingredList = s.getIngredients();
                System.out.println("Number of ingredients: " + ingredList.size());
                for (SchematicIngredients si : ingredList) {
                    System.out.println("Ingredient: " + si.getIngredientName());
                    System.out.println("Type: " + si.getIngredientType());
                    System.out.println("Object: " + si.getIngredientObject());
                    System.out.println("Quantity: " + si.getIngredientQuantity());
                    System.out.println("Contribution: " + si.getIngredientContribution());
                }
            } else if (list.getSelectedValuesList().isEmpty()) {
                calcButton.setEnabled(false);
            }
        }
    }

    private void profBoxActionPerformed(java.awt.event.ActionEvent e) {
        JComboBox box = (JComboBox) e.getSource();
        activeProf = box.getSelectedItem().toString();
        app.getSettings().setProp("SelectedProfession", activeProf);
        refresh();
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int col = e.getColumn();
        int row = e.getFirstRow();
        InventoryTableModel model = (InventoryTableModel) e.getSource();
        String name = (String) model.getValueAt(row, 0);
        Integer newAmount = (Integer) invTable.getValueAt(row, col);

        Resource res = app.getInvDB().getResourceByName(name);
        if (res != null) {
            res.setAmount(newAmount);
            app.getInvDB().merge(res);
            System.out.println("Amount of " + name + " changed to " + newAmount.toString());
        } else {
            System.out.println("ERROR: Unable to find resource by name: " + name);
        }
    }

    private void setInvColumnWidth() {
        TableColumnModel cModel = invTable.getColumnModel();
        for (int i = 0; i < cModel.getColumnCount(); i++) {
            switch (i) {
                case 0:
                    cModel.getColumn(i).setPreferredWidth(100);
                    break;
                case 1:
                    cModel.getColumn(i).setPreferredWidth(200);
                    break;
                case 13:
                    cModel.getColumn(i).setPreferredWidth(100);
                    break;
                default:
                    cModel.getColumn(i).setPreferredWidth(50);
                    break;
            }
        }
    }

    public void refresh() {
        invTable.setModel(new InventoryTableModel(app.getInvDB().getAllResources()));
        invTable.getModel().addTableModelListener(this);
        setInvColumnWidth();
        schList.setModel(new SchematicListModel(app, activeProf));
        validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");

        jMenuItem1.setText("Import");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 758, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        app.importFromGH();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        app.close();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(CrafterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrafterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrafterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrafterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new CrafterGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables

}

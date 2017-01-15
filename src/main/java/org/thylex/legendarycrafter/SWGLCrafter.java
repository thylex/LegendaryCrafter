/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.legendarycrafter;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.thylex.legendarycrafter.frontend.app.CrafterApp;

/**
 *
 * @author Henrik
 */
public class SWGLCrafter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        
        CrafterApp app = new CrafterApp();
    }
    
}

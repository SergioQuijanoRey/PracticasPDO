/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import deepspace.ShieldToUI;
import deepspace.SpaceStationToUI;
import java.awt.Component;
import java.util.ArrayList;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class ShieldPanel extends javax.swing.JPanel {

    /**
     * Creates new form ShieldPanel
     */
    public ShieldPanel() {
        initComponents();
    }
    
    public void setShield(SpaceStationToUI station) {
        System.out.println("Setting shield panel...");
        
        jpShieldCards.removeAll();
        ArrayList<ShieldToUI> shieldBoosters = station.getShieldBoosters();
        ShieldView shieldView;
        for ( ShieldToUI s : shieldBoosters ) {
            shieldView = new ShieldView();
            shieldView.setShield(s);
            jpShieldCards.add(shieldView);
        }
        
        repaint();
        revalidate();
    }
    
    ArrayList<Integer> getSelectedShieldBoosters() {
        ArrayList<Integer> selectedShieldBoosters = new ArrayList<>();
        int i = 0;
        for ( Component c : jpShieldCards.getComponents() ) {
            if ( ((WeaponView) c).isSelected() )
                selectedShieldBoosters.add(i);
            i++;
        }
        
        return selectedShieldBoosters;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPanel = new javax.swing.JScrollPane();
        jpShieldCards = new javax.swing.JPanel();

        jScrollPanel.setViewportView(jpShieldCards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JPanel jpShieldCards;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import deepspace.SpaceStationToUI;
import deepspace.WeaponToUI;
import java.awt.Component;
import java.util.ArrayList;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class WeaponPanel extends javax.swing.JPanel {

    /**
     * Creates new form WeaponPanel
     */
    public WeaponPanel() {
        initComponents();
    }
    
    public void setWeapon(SpaceStationToUI station) {
        System.out.println("Setting weapon panel...");
        
        jpWeaponCards.removeAll();
        ArrayList<WeaponToUI> weapons = station.getWeapons();
        System.out.println("Size of weapons selected: "+Integer.toString(weapons.size()));
        WeaponView weaponView;
        for ( WeaponToUI w : weapons ) {
            weaponView = new WeaponView();
            weaponView.setWeapon(w);
            jpWeaponCards.add(weaponView);
            System.out.println("Adding weapon to flow layout. Info: "+Integer.toString(w.hashCode()));
        }
        
        repaint();
        revalidate();
    }
    
    ArrayList<Integer> getSelectedWeapons() {
        ArrayList<Integer> selectedWeapons = new ArrayList<>();
        int i = 0;
        for ( Component c : jpWeaponCards.getComponents() ) {
            if ( ((WeaponView) c).isSelected() )
                selectedWeapons.add(i);
            i++;
        }
        
        return selectedWeapons;
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
        jpWeaponCards = new javax.swing.JPanel();

        jScrollPanel.setViewportView(jpWeaponCards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JPanel jpWeaponCards;
    // End of variables declaration//GEN-END:variables
}

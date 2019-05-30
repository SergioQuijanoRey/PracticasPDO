/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import deepspace.HangarToUI;
import deepspace.ShieldToUI;
import deepspace.SpaceStationToUI;
import deepspace.WeaponToUI;
import java.awt.Component;
import java.util.ArrayList;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class HangarPanel extends javax.swing.JPanel {

    /**
     * Auxiliar array to distinguish between WeaponView and ShieldView in
     * jpHangarCards' panel components.
     *  - isWeaponView[i] is true if jpHangarCards[i] is of type WeaponView
     *  - isWeaponView[i] is false if jpHangarCards[i] is of type WeaponView
     */
    ArrayList<Boolean> isWeaponView;
    
    
    /**
     * Creates new form HangarPanel
     */
    public HangarPanel() {
        initComponents();
        
        isWeaponView = new ArrayList<>();
    }
    
    public void setHangar(SpaceStationToUI station) {
        HangarToUI hangar = station.getHangar();
        System.out.println("Setting hangar panel...");
        jpHangarCards.removeAll();
        isWeaponView.clear();
        
        if ( hangar != null ) {
            if ( hangar.getMaxElements() == 1 )
                jlHangarPlaces.setText(Integer.toString(hangar.getMaxElements()) + " lugar");
            else
                jlHangarPlaces.setText(Integer.toString(hangar.getMaxElements()) + " lugares");

            WeaponView weaponView;
            ShieldView shieldView;
            
            ArrayList<WeaponToUI> hangar_weapons = hangar.getWeapons();
            ArrayList<ShieldToUI> hangar_shieldBoosters = hangar.getShieldBoosters();
            
            for ( WeaponToUI w: hangar_weapons ) {
                weaponView = new WeaponView();
                weaponView.setWeapon(w);
                jpHangarCards.add(weaponView);
                isWeaponView.add(true);
                
            }
            for ( ShieldToUI s : hangar_shieldBoosters ) {
                shieldView = new ShieldView();
                shieldView.setShield(s);
                jpHangarCards.add(shieldView);
                isWeaponView.add(false);
            }
            
            int value = (int) ( (hangar_weapons.size()+hangar_shieldBoosters.size()) * 100 / hangar.getMaxElements() );
            jpbHangarAmount.setValue(value);
            System.out.println("Setting value to " + Integer.toString(value));
        } else {
            jlHangarPlaces.setText("0 lugares");
            jpbHangarAmount.setValue(0);
        }
        
        repaint();
        revalidate();
    }

    ArrayList<Integer> getSelectedWeapons() {
        ArrayList<Integer> selectedWeaponsInHangar = new ArrayList<>();
        int i = 0;
        for ( Component c: jpHangarCards.getComponents() ) {
            if ( isWeaponView.get(i) )
                if ( ((WeaponView) c).isSelected() )
                    selectedWeaponsInHangar.add(i);
            i++;
        }
        
        return selectedWeaponsInHangar;
    }
    
    ArrayList<Integer> getSelectedShieldBoosters() {
        ArrayList<Integer> selectedShieldBoostersInHangar = new ArrayList<>();
        int i = 0;
        for ( Component c: jpHangarCards.getComponents() ) {
            if ( !isWeaponView.get(i) )
                if ( ((ShieldView) c).isSelected() )
                    selectedShieldBoostersInHangar.add(i);
            i++;
        }
        
        return selectedShieldBoostersInHangar;
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
        jpHangarCards = new javax.swing.JPanel();
        jpbHangarAmount = new javax.swing.JProgressBar();
        jlHangarPlaces = new javax.swing.JLabel();

        jScrollPanel.setViewportView(jpHangarCards);

        jlHangarPlaces.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlHangarPlaces.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlHangarPlaces.setText("  lugares");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlHangarPlaces, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpbHangarAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpbHangarAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlHangarPlaces))
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JLabel jlHangarPlaces;
    private javax.swing.JPanel jpHangarCards;
    private javax.swing.JProgressBar jpbHangarAmount;
    // End of variables declaration//GEN-END:variables
}

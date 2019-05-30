/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import View.View;
import Controller.Controller;
import deepspace.GameState;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class MainWindow extends javax.swing.JFrame implements View {
    /**
     * Unique instance of MainWindow.
     * Make singleton class.
     */
    private static MainWindow instance = null;
    
    /**
     * App name.
     */
    private String appName;
    
    /**
     * Version.
     */
    private String appVersion;
    
    
    /**
     * View: CurrentStationView.
     */
    private CurrentStationView currentStationView;
    
    /**
     * View: CurrentEnemyView.
     */
    private CurrentEnemyView currentEnemyView;
    
    
    /**
     * Get instance.
     * @return unique instance of MainWindow
     */
    public static MainWindow getInstance() {
        if ( instance == null )
            instance = new MainWindow();
        
        return instance;
    }
    
    /**
     * Creates new form MainWindow
     */
    private MainWindow() {
        initComponents();
        
        appVersion = "v1.0";
        appName = "Deepspace " + appVersion;
        setLocationRelativeTo(null);
        setTitle(appName);
        currentStationView = new CurrentStationView();
        currentEnemyView = new CurrentEnemyView();
        jpCurrentStation.add(currentStationView);
        jpCurrentEnemy.add(currentEnemyView);
        jlVersion.setText(appVersion);
        
        setResizable(false);
        
        // finish app on closing
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Controller.getInstance().finish(0);
            }
        });
    }
    
    @Override
    public void updateView() {
        GameState state = Controller.getInstance().getState();
        System.out.println("[STATE] - "+state.toString());
        currentStationView.setStation(Controller.getInstance().getUIversion().getCurrentStation());
        currentEnemyView.setEnemy(Controller.getInstance().getUIversion().getCurrentEnemy());
        
        // enable or disable buttons
        jbCombat.setEnabled(state != GameState.AFTERCOMBAT);
        jbDiscard.setEnabled(state != GameState.BEFORECOMBAT);
        jbDiscardComplete.setEnabled(state != GameState.BEFORECOMBAT);
        jbMount.setEnabled(state != GameState.BEFORECOMBAT);
        jbNextTurn.setEnabled(state != GameState.BEFORECOMBAT);
        
        // enable or disable enemy panel
        jpCurrentEnemy.setVisible((state != GameState.BEFORECOMBAT) && (state != GameState.INIT));
        
        if ( state == GameState.INIT )
            jbNextTurn.setEnabled(false);

        //TitledBorder border = new TitledBorder("");
        //jpCurrentStation.setBorder(border);
        
        //setButtons();
        repaint();
        revalidate();
    }
    
    // NOTE: this method needs revision
    @Deprecated
    public void setButtons() {
        // buttons for Discard, Discard Complete, Mount
        jbDiscard.setEnabled(false);
        if ( Controller.getInstance().getUIversion().getCurrentStation().getHangar() == null ) {
            jbDiscardComplete.setEnabled(false);
            jbMount.setEnabled(false);
            
            if ( !Controller.getInstance().getUIversion().getCurrentStation().getWeapons().isEmpty() )
                jbDiscard.setEnabled(true);
            else if ( !Controller.getInstance().getUIversion().getCurrentStation().getShieldBoosters().isEmpty() )
                jbDiscard.setEnabled(true);
        } else {
            if ( !Controller.getInstance().getUIversion().getCurrentStation().getHangar().getWeapons().isEmpty()
                    || !Controller.getInstance().getUIversion().getCurrentStation().getHangar().getShieldBoosters().isEmpty() ) {
                jbDiscardComplete.setEnabled(true);
                jbMount.setEnabled(true);
                jbDiscard.setEnabled(true);
            } else {
                jbDiscardComplete.setEnabled(false);
                jbMount.setEnabled(false);
                jbDiscard.setEnabled(false);
            }
        }
        
        // buttons for Combat, Next Turn
        // WIP
    }
    
    @Override
    public void showView() {
        setVisible(true);
    }
    
    @Override
    public String getAppName() {
        return appName;
    }
    
    @Override
    public String getAppVersion() {
        return appVersion;
    }
    
    @Override
    public ArrayList<String> readNamePlayers() {
        NamesCaptureView namesCapture = new NamesCaptureView(this);
        return namesCapture.getNames();
    }
    
    @Override
    public boolean confirmExitMessage() {
        return JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que deseas salir?", getAppName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }    
    
    @Override
    public void nextTurnNotAllowedMessage() {
        JOptionPane.showMessageDialog(this, "No puedes avanzar de turno, no has cumplido tu castigo", "Deepspace", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void lostCombatMessage() {
        JOptionPane.showMessageDialog(this, "Has PERDIDO el combate. Cumple tu castigo.", "Deepspace", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void escapeMessage() {
        JOptionPane.showMessageDialog(this, "Has logrado escapar. Eres una gallina espacial.", "Deepspace", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void wonCombatMessage() {
        JOptionPane.showMessageDialog(this, "Has GANADO el combate. Disfruta de tu botín.", "Deepspace", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void wonGameMessage() {
        JOptionPane.showMessageDialog(this, "¡HAS GANADO LA PARTIDA!", "Deepspace", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void conversionMessage() {
        JOptionPane.showMessageDialog(this, "Has GANADO el combate. Además, te has CONVERTIDO. ¡Disfruta de tu botín!", "Deepspace", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void noCombatMessage() {
        JOptionPane.showMessageDialog(this, "No puedes combatir en este momento", "Deepspace", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbInfo = new javax.swing.JButton();
        jbNextTurn = new javax.swing.JToggleButton();
        jlDeepspace = new javax.swing.JLabel();
        jlVersion = new javax.swing.JLabel();
        jbCombat = new javax.swing.JButton();
        jpCurrentStation = new javax.swing.JPanel();
        jlAuthor = new javax.swing.JLabel();
        jpCurrentEnemy = new javax.swing.JPanel();
        jbExit = new javax.swing.JButton();
        jbMount = new javax.swing.JButton();
        jbDiscard = new javax.swing.JButton();
        jbDiscardComplete = new javax.swing.JButton();
        jlAuthor1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbInfo.setText("Información");
        jbInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInfoActionPerformed(evt);
            }
        });

        jbNextTurn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jbNextTurn.setText("Siguiente turno →");
        jbNextTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNextTurnActionPerformed(evt);
            }
        });

        jlDeepspace.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jlDeepspace.setText("Deepspace");

        jlVersion.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlVersion.setText("version");

        jbCombat.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jbCombat.setText("Combatir »");
        jbCombat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCombatActionPerformed(evt);
            }
        });

        jpCurrentStation.setBorder(javax.swing.BorderFactory.createTitledBorder("Estación actual"));

        jlAuthor.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlAuthor.setText("Miguel Ángel Fernández Gutiérrez");

        jpCurrentEnemy.setBorder(javax.swing.BorderFactory.createTitledBorder("Enemigo actual"));

        jbExit.setText("Salir del juego");
        jbExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExitActionPerformed(evt);
            }
        });

        jbMount.setText("Equipar");
        jbMount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMountActionPerformed(evt);
            }
        });

        jbDiscard.setText("Descartar");
        jbDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDiscardActionPerformed(evt);
            }
        });

        jbDiscardComplete.setText("Descartar Hangar completo");
        jbDiscardComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDiscardCompleteActionPerformed(evt);
            }
        });

        jlAuthor1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlAuthor1.setText("Sergio Quijano Rey");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbMount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbDiscard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbDiscardComplete))
                    .addComponent(jpCurrentStation, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbExit, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpCurrentEnemy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbCombat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbNextTurn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlDeepspace)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlVersion))
                    .addComponent(jlAuthor)
                    .addComponent(jlAuthor1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlDeepspace)
                            .addComponent(jlVersion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlAuthor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlAuthor1)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jpCurrentEnemy, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225)
                        .addComponent(jbCombat, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbNextTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpCurrentStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbDiscard)
                    .addComponent(jbMount)
                    .addComponent(jbDiscardComplete)
                    .addComponent(jbInfo)
                    .addComponent(jbExit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbNextTurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNextTurnActionPerformed
        Controller.getInstance().nextTurn();
        updateView();
    }//GEN-LAST:event_jbNextTurnActionPerformed

    private void jbCombatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCombatActionPerformed
        System.out.println("Combatiendo...");
        Controller.getInstance().combat();
        updateView();
    }//GEN-LAST:event_jbCombatActionPerformed

    private void jbExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExitActionPerformed
        Controller.getInstance().finish(0);
    }//GEN-LAST:event_jbExitActionPerformed

    private void jbInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInfoActionPerformed
        JOptionPane.showMessageDialog(this, "Autores:\nMiguel Ángel Fernández Gutiérrez\nSergio Quijano Rey\n\nAsignatura: PDOO\n2º Doble Grado en Ingeniería Informática y Matemáticas\nUniversidad de Granada", "Deepspace", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jbInfoActionPerformed

    private void jbMountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMountActionPerformed
        Controller.getInstance().mount(currentStationView.getSelectedWeaponsInHangar(), currentStationView.getSelectedShieldBoostersInHangar());
        updateView();
    }//GEN-LAST:event_jbMountActionPerformed

    private void jbDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDiscardActionPerformed
        Controller.getInstance().discard(Controller.HANGAR, currentStationView.getSelectedWeaponsInHangar(), currentStationView.getSelectedShieldBoostersInHangar());
        Controller.getInstance().discard(Controller.WEAPON + Controller.SHIELD, currentStationView.getSelectedWeapons(), currentStationView.getSelectedShieldBoosters());
        updateView();
    }//GEN-LAST:event_jbDiscardActionPerformed

    private void jbDiscardCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDiscardCompleteActionPerformed
        Controller.getInstance().discardHangar();
        updateView();
        // WIP - getAppName aquí
        JOptionPane.showMessageDialog(this, "Hangar completo descartado", "Deepspace", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jbDiscardCompleteActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCombat;
    private javax.swing.JButton jbDiscard;
    private javax.swing.JButton jbDiscardComplete;
    private javax.swing.JButton jbExit;
    private javax.swing.JButton jbInfo;
    private javax.swing.JButton jbMount;
    private javax.swing.JToggleButton jbNextTurn;
    private javax.swing.JLabel jlAuthor;
    private javax.swing.JLabel jlAuthor1;
    private javax.swing.JLabel jlDeepspace;
    private javax.swing.JLabel jlVersion;
    private javax.swing.JPanel jpCurrentEnemy;
    private javax.swing.JPanel jpCurrentStation;
    // End of variables declaration//GEN-END:variables
}

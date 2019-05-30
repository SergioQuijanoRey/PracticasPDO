/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.ArrayList;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public interface View {
    public void updateView();
    public void showView();
    public String getAppName();
    public String getAppVersion();
    // Inputs
    public ArrayList<String> readNamePlayers();
    // Outputs
    public boolean confirmExitMessage();
    public void nextTurnNotAllowedMessage();
    public void lostCombatMessage();
    public void escapeMessage();
    public void wonCombatMessage();
    public void wonGameMessage();
    public void conversionMessage();
    public void noCombatMessage();
    
}

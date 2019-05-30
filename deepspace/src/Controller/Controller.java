/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.View;
import deepspace.CombatResult;
import deepspace.GameState;
import deepspace.GameUniverse;
import deepspace.GameUniverseToUI;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class Controller {
    private static Controller instance = new Controller();
    
    public static final int WEAPON = 0x1;
    public static final int SHIELD = 0x2;
    public static final int HANGAR = 0x4;
    private GameUniverse model;
    private View view;
    
    private Controller() { }
    
    /**
     * Singleton behaviour.
     */
    public static Controller getInstance() {
        return instance;
    }
    
    public void setModelView(GameUniverse aGame, View aView) {
        model = aGame;
        view = aView;
    }
    
    public void start() {
        model.init(view.readNamePlayers());
        view.updateView();
        view.showView();
    }
    
    public void finish(int i) {
        if ( view.confirmExitMessage() )
            System.exit(i);
    }
    
    public boolean nextTurn() {
        boolean nextTurnAllowed = model.nextTurn();
        if ( !nextTurnAllowed )
            view.nextTurnNotAllowedMessage();
        return nextTurnAllowed;
    }
    
    public void combat() {
        CombatResult result = model.combat();
        switch ( result ) {
            case ENEMYWINS:
                view.lostCombatMessage();
                break;
            case STATIONESCAPES:
                view.escapeMessage();
                break;
            case STATIONWINS:
                view.wonCombatMessage();
                if ( model.haveAWinner() ) {
                    view.wonGameMessage();
                    System.exit(0);
                }
                break;
            case STATIONWINSANDCONVERTS:
                view.conversionMessage();
                if ( model.haveAWinner() ) {
                    view.wonGameMessage();
                    System.exit(0);
                }
                break;
            case NOCOMBAT:
                view.noCombatMessage();
                break;
        }
    }
    
    public GameState getState() {
        return model.getState();
    }
    
    public GameUniverseToUI getUIversion() {
        return model.getUIversion();
    }
    
    private void invertArray(ArrayList<Integer> array) {
        int i, n;
        
        n = array.size();
        
        for ( i = 0; i < n/2; i++ )
            Collections.swap(array, i, n-i-1);
    }
    
    public void mount(ArrayList<Integer> weapons, ArrayList<Integer> shields) {
        invertArray(weapons);
        invertArray(shields);
        
        for ( int i : weapons )
            model.mountWeapon(i);
        
        for ( int i : shields )
            model.mountShieldBooster(i);
    }
    
    public void discard(int places, ArrayList<Integer> weapons, ArrayList<Integer> shields) {
        invertArray(weapons);
        invertArray(shields);
        
        if ( ( places & WEAPON ) == WEAPON )
            for ( int i : weapons )
                model.discardWeapon(i);
        if ( ( places & SHIELD ) == SHIELD )
            for ( int i : shields )
                model.discardShieldBooster(i);
        if ( ( places & HANGAR ) == HANGAR ) {
            for ( int i : weapons )
                model.discardWeaponInHangar(i);
            for ( int i : shields )
                model.discardShieldBoosterInHangar(i);
        }
    }
    
    public void discardHangar() {
        model.discardHangar();
    }
}

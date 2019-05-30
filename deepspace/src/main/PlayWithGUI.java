/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import View.GUI.MainWindow;
import Controller.Controller;
import deepspace.GameUniverse;
import View.View;

/**
 * Main GUI for Deepspace.
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class PlayWithGUI {
    /**
     * Main for Deepspace GUI.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        View view;
        GameUniverse model = new GameUniverse();
        view = MainWindow.getInstance();
        Controller controller = Controller.getInstance();
        controller.setModelView(model, view);
        controller.start();
    }
}

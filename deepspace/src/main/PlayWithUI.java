/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import View.DeepSpaceView;
import View.UI.TextMainView;
import Controller.ControllerText;
import deepspace.GameUniverse;

/**
 *
 * @author Profe
 */
public class PlayWithUI {
    public static void main(String[] args) {
        DeepSpaceView ui;
        GameUniverse game = new GameUniverse();
        ui = TextMainView.getInstance();
        ControllerText controller = ControllerText.getInstance();
        controller.setModelView(game,ui);
        controller.start();   
    }
  
}

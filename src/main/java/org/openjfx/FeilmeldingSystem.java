/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import javafx.scene.control.Alert;

/**
 *
 * @author Magnus
 */
public class FeilmeldingSystem {
    
     public static void visFeilmelding(String msg){
   Alert alert = new Alert(Alert.AlertType.ERROR);
   alert.setTitle("Feil");
   alert.setContentText(msg);

   alert.showAndWait();
    }
    
}

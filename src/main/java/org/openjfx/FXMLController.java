package org.openjfx;

import Model.Domene.Artist;
import Model.Domene.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FXMLController {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        Person p=new Artist("Jon", "Rafoss", "","Sanger");
        label.setText(p.toString());
    }
    
    public void initialize() {
        // TODO
    }    
}

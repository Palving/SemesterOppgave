package org.openjfx;

import Model.Domene.Artist;
import Model.Domene.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class FXMLController {
    
    @FXML
    private Label label;
    
    @FXML
    private TableView tabell;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        Artist p=new Artist("Jon", "Rafoss", "","Sanger");
        
        label.setText(p.toString());
    }
    
    public void initialize() {
        // TODO
    }    
}

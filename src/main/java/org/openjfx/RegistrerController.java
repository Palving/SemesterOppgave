package org.openjfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class RegistrerController implements Initializable {

 
 @FXML
 RadioButton artist;
 
 @FXML
 AnchorPane ap;
 
 @FXML
 private void artistReg(ActionEvent event){
     String[] attributes={"Fornavn","Etternavn","Tlf","Type artist"};
    TextField[] input=new TextField[4];
    int teller=0;
    for (TextField t : input){
        t=new TextField();
       t.setPromptText(attributes[teller]);
        input[teller]=t;
        t.setId(""+teller);
        
        try{
            t.setLayoutY(input[teller-1].getLayoutY()+40);
            t.setLayoutX(50);
        }
        catch(Exception e){
            t.setLayoutY(130);
            t.setLayoutX(50);
        }
        ap.getChildren().add(t);
        teller++;
        
    }
     
     
 }
 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

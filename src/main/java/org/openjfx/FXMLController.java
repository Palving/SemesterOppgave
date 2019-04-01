package org.openjfx;

import Model.Domene.Artist;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLController {
    
    @FXML
    private Label label;
    
    @FXML
    private TableView tabell;
    
    @FXML
    private AnchorPane ap;
    
    @FXML
    private void registrer(ActionEvent event) throws IOException{
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registrer.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Registrer");
    stage.setScene(new Scene(root1));  
    stage.show();
     
    }
   
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

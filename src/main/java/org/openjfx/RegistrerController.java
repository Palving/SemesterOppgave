package org.openjfx;

import Model.Domene.Artist;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


public class RegistrerController implements Initializable {
    
private String valgt;
@FXML
private TextField[] input;


 @FXML
private ToggleGroup radioGrp;
 
 @FXML
 private RadioButton artistRadio, lokaleRadio, kontaktPersonRadio, arrangRadio, billettRadio; 
 
 @FXML
 private AnchorPane ap;
 
 String[] att;
 
 @FXML
 private void formaterTextFields(){
     
   
     
      String[] artistAttributes={"Fornavn","Etternavn","Tlf","Type artist"};
      String[] lokaleAttributes={"Test","Test"};
     if (valgt.equals("Artist")){
         att=artistAttributes;
     }
     else if(valgt.equals("Lokale")){
     att=lokaleAttributes;
 }
     
     input=new TextField[att.length];
   
     int teller=0;
    for (TextField t : input){
        t=new TextField();
       t.setPromptText(att[teller]);
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
    
    Button btn=new Button("Bekreft");
     btn.setLayoutY(input[teller-1].getLayoutY()+40);
    btn.setLayoutX(50);
      btn.addEventHandler(ActionEvent.ACTION, ev->registrer());
      ap.getChildren().add(btn);
 }
 
 
 @FXML
 private void registrer(){
      
     ArrayList<String> data=new ArrayList<>();
     for (TextField f : input){
         data.add(f.getText());
     }
     if (valgt.equals("Artist")){
         Artist artist=new Artist(data);

     }
          //System.out.print("registrert"+artist.toString());
  
 }

 private void initRadioGroup(){
    radioGrp=new ToggleGroup();
    artistRadio.setToggleGroup(radioGrp);
    artistRadio.setUserData("Artist");
    
    lokaleRadio.setToggleGroup(radioGrp);
    lokaleRadio.setUserData("Lokale");

    kontaktPersonRadio.setToggleGroup(radioGrp);
    arrangRadio.setToggleGroup(radioGrp);
    billettRadio.setToggleGroup(radioGrp);
 }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    initRadioGroup();
    
    radioGrp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> ov,
          Toggle old_toggle, Toggle new_toggle) {
        if (radioGrp.getSelectedToggle() != null) {
          valgt=radioGrp.getSelectedToggle().getUserData().toString();
          System.out.println(valgt);
          
          formaterTextFields();
          //System.out.println(Registrer.registrer(new Artist("Jon","Rafoss","123","Sanger")));
          
        }
      }
      });
            }
}
     
     
    
          
    


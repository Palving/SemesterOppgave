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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


public class RegistrerController implements Initializable {
    
private String valgt;
@FXML
private TextField[] input=null;

@FXML
private Label utskriftRegistrert;

 @FXML
private ToggleGroup radioGrp;
 
 @FXML
 private RadioButton artistRadio, lokaleRadio, kontaktPersonRadio, arrangRadio, billettRadio; 
 
 @FXML
 private AnchorPane ap;
 
 @FXML
 private Button btn;
 
 String[] att;
 

 private void refreshTextFields(){
     if (input==null){
         return;
     }
     for (TextField f : input){
         ap.getChildren().remove(f);
     }
     ap.getChildren().remove(btn);
     
 }
 
 private void formaterTextFields(){
     
   refreshTextFields();
     
      String[] artistAttributes={"Fornavn","Etternavn","Tlf","Type artist"};
      String[] lokaleAttributes={"Lokalenavn","Antall plasser"};
      String[] arrangAttributes={"Arrangementnavn","Program", "Sted", "Dato","Type arrangement"};
      String[] kontaktPersonAttributes={"Fornavn","Etternavn","Tlf","Firma","Info","Nettsted"};
      String[] billettAttributes={"Lokalenavn","Antall plasser"};
      
      switch(valgt){
          case "Artist":
               att=artistAttributes;
          case "Lokale":
               att=lokaleAttributes;
          case "Arrangement":
              att=arrangAttributes;
          case "KontaktPerson":
              att=kontaktPersonAttributes;
          case "Billett":
          
      }
    
     input=new TextField[att.length];
   
     int teller=0;
     
     if (valgt.equals("Arrangement")){
         teller=2;
         // legg til ddl med alle artister og kontaktpersoner
     }
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
    
     btn=new Button("Bekreft");
   
     btn.setLayoutY(input[teller-1].getLayoutY()+40);
    btn.setLayoutX(50);
      btn.addEventHandler(ActionEvent.ACTION, ev->registrer());
      ap.getChildren().add(btn);
 }
 
 
 @FXML
 private void registrer(){
      
     ArrayList<String> data=new ArrayList<>();
     for (TextField f : input){
         System.out.print(f.getId());
         data.add(f.getText());
     }
     if (valgt.equals("Artist")){
         Artist artist=new Artist(data);
         System.out.print("Artist registert"+artist.toString());
          utskriftRegistrert.setText(utskriftRegistrert.getText()+"\n"+artist.toString());

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
    kontaktPersonRadio.setUserData("KontaktPerson");
    
    arrangRadio.setToggleGroup(radioGrp);
    arrangRadio.setUserData("Arrangement");
    
    billettRadio.setToggleGroup(radioGrp);
    billettRadio.setUserData("Billett");
 }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // Setter radio-knapper i gruppe og gir userdata
    initRadioGroup();
    
    radioGrp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> ov,
          Toggle old_toggle, Toggle new_toggle) {
        if (radioGrp.getSelectedToggle() != null) {
            // Valget i radio-gruppen
          valgt=radioGrp.getSelectedToggle().getUserData().toString();
          System.out.println(valgt);
          
          // Lager textfields basert på valgt
          formaterTextFields();
          //System.out.println(Registrer.registrer(new Artist("Jon","Rafoss","123","Sanger")));
          
        }
      }
      });
            }
}
     
     
    
          
    


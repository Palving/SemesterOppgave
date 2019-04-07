package org.openjfx;

import Model.Domene.*;
import Model.Registrering.Register;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
 private AnchorPane anchorPane;
 
 @FXML
 private Button btn;
 
 String[] attributter;
 

 private void refreshTextFields(){
     if (input==null){
         return;
     }
     for (TextField f : input){
         anchorPane.getChildren().remove(f);
     }
     anchorPane.getChildren().remove(btn);
     
 }
 
 private void formaterDropdown(){
     ObservableList<Artist> obsArtister=FXCollections.observableArrayList(register.getArtister());
      ObservableList<KontaktPerson> obsKontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
      
      
     ComboBox ddlArtister=new ComboBox(obsArtister);
     ComboBox ddlKontaktPerson=new ComboBox(obsKontaktPerson);
     
     anchorPane.getChildren().add(ddlArtister);
 }
 
 private void formaterTextFields(){
     
   refreshTextFields();
     
      String[] artistAttributes={"Fornavn","Etternavn","Tlf","Type artist"};
      String[] lokaleAttributes={"Lokalenavn","Antall plasser"};
      String[] arrangAttributes={"Arrangementnavn","Program", "Sted", "Dato","Type arrangement"};
      String[] kontaktPersonAttributes={"Fornavn","Etternavn","Tlf","Firma","Info","Nettsted"};
      String[] billettAttributes={"Plassnummer","Lokale Navn", "Dato", "Pris","Telefonnummer"};
      
      switch(valgt){
          case "Artist":
               attributter=artistAttributes;
               break;
          case "Lokale":
               attributter=lokaleAttributes;
               break;
          case "Arrangement":
              register.test();
             
              attributter=arrangAttributes;
              break;
          case "KontaktPerson":
              attributter=kontaktPersonAttributes;
              break;
          case "Billett":
              attributter=billettAttributes;
              break;
          
      }
    
     input=new TextField[attributter.length];
   
     int teller=0;
     
     if (valgt.equals("Arrangement")){
        formaterDropdown();
         // legg til ddl med alle artister og kontaktpersoner
     }
    for (TextField t : input){
        t=new TextField();
       t.setPromptText(attributter[teller]);
        input[teller]=t;
       
        // Gir første textfield startposisjon, og resten bygger på den
        try{
            t.setLayoutY(input[teller-1].getLayoutY()+40);
            t.setLayoutX(50);
            }
        catch(Exception e){
            t.setLayoutY(130);
            t.setLayoutX(50);
             }
      
       
        anchorPane.getChildren().add(t);
        teller++;
        
    }
    
     btn=new Button("Bekreft");
   
     btn.setLayoutY(input[teller-1].getLayoutY()+40);
    btn.setLayoutX(50);
      btn.addEventHandler(ActionEvent.ACTION, ev->registrer());
      anchorPane.getChildren().add(btn);
 }
 
 ArrayList<Object> objekter=new ArrayList<>();
 Register register=Register.getInstance();
 @FXML
 private void registrer(){
     
     ArrayList<String> data=new ArrayList<>();
     // Samler data fra textfieldene i arraylist
     for (TextField f : input){
         System.out.print(f.getId());
         data.add(f.getText());
     }
     
      switch(valgt){
          case "Artist":
              Artist artist=new Artist(data);
              utskriftRegistrert.setText("\n"+artist.toString());
              objekter.add(artist);
               break;
          case "Lokale":
              Lokale lokale=new Lokale(data);
              utskriftRegistrert.setText("\n"+lokale.toString());
              objekter.add(lokale);
               break;
          case "Arrangement":
           Arrangement arrang=new Arrangement(new Artist("","","",""),new KontaktPerson("","","","","",""),data);
           utskriftRegistrert.setText(arrang.toString());
           objekter.add(arrang);
              break;
          case "KontaktPerson":
              KontaktPerson kontaktPerson=new KontaktPerson(data);
              utskriftRegistrert.setText(kontaktPerson.toString());
              objekter.add(kontaktPerson);
              break;
          case "Billett":
              Billett billett=new Billett(data);
              objekter.add(billett);
              break;
          
      }
    register.registrer(objekter);
   
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
      public void changed(ObservableValue<? extends Toggle> obsVal,
          Toggle oldToggle, Toggle newToggle) {
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
     
     
    
          
    


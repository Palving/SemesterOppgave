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
 
 // textfields elementer
 private String[] attributter;
 
 // register elemeneter
 private ArrayList<Object> objekter;
 private Register register=Register.getInstance();
 
 // ddl elelementer
 private ComboBox ddlArtister=null;
 private ComboBox ddlKontaktPerson=null;
 private ComboBox ddlArrangement=null;
 private ObservableList<Artist> obsArtister;
 private ObservableList<KontaktPerson> obsKontaktPerson;
 private ObservableList<Arrangement> obsArrangement;
 

 // Fjerner textfields på radiobutton-onchange()
 private void refreshTextFields(){
     if (input==null){
         return;
     }
     for (TextField f : input){
         anchorPane.getChildren().remove(f);
     }
     
     if (ddlArtister!=null){
         anchorPane.getChildren().remove(ddlArtister);
     anchorPane.getChildren().remove(ddlKontaktPerson);
     }
     if (ddlArrangement!=null){
         System.out.println("ddl arrang removed");
         anchorPane.getChildren().remove(ddlArrangement);
     }
     
     anchorPane.getChildren().remove(btn);
     
 }
 
  
 // lager dropdownlist dersom registreringen tar inn et objekt framfor streng aka valget er Arrangement
 private void formaterDropdownArrangement(){
     obsArtister=FXCollections.observableArrayList(register.getArtister());
     obsKontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
          
     ddlArtister=new ComboBox(obsArtister);
     ddlKontaktPerson=new ComboBox(obsKontaktPerson);
      
    ddlArtister.setLayoutX(50);
    ddlArtister.setLayoutY(130);
    
    ddlKontaktPerson.setLayoutX(50);
    ddlKontaktPerson.setLayoutY(170);
    
     anchorPane.getChildren().add(ddlArtister);
     anchorPane.getChildren().add(ddlKontaktPerson);
     
 }
 
 private void formaterDropdownBillett(){
     obsArrangement=FXCollections.observableArrayList(register.getArrangement());
   
   ddlArrangement=new ComboBox(obsArrangement);
  
    ddlArrangement.setLayoutX(50);
    ddlArrangement.setLayoutY(130);
    ddlArrangement.setMaxSize(50, 20);
    
    
     anchorPane.getChildren().add(ddlArrangement);
     
 }



 private void formaterTextFields(){
     
   refreshTextFields();
     
      String[] artistAttributes={"Fornavn","Etternavn","Tlf","Type artist"};
      String[] lokaleAttributes={"Lokalenavn","Antall plasser"};
      String[] arrangAttributes={"Type arrangement", "Arrangementnavn","Program", "Sted", "Dato - dag/måned/år", "Tidspunkt"};
      String[] kontaktPersonAttributes={"Fornavn","Etternavn","Tlf","Firma","Info","Nettsted"};
      String[] billettAttributes={"Plassnummer","Telefonnummer"};
      
      switch(valgt){
          case "Artist":
               attributter=artistAttributes;
               break;
          case "Lokale":
               attributter=lokaleAttributes;
               break;
          case "Arrangement":
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
     
      // legg til ddl med alle artister og kontaktpersoner
     if (valgt.equals("Arrangement")){
        formaterDropdownArrangement();  
     }
     else if(valgt.equals("Billett")){
         System.out.println("billettddl formatert");
         formaterDropdownBillett();
     }
     
    for (TextField textfields : input){
        textfields=new TextField();
       textfields.setPromptText(attributter[teller]);
        input[teller]=textfields;
       
        if (teller!=0){
             textfields.setLayoutY(input[teller-1].getLayoutY()+40);
            textfields.setLayoutX(50);
        }
          // Gir første textfield startposisjon, og resten bygger på den
        else{
            if (valgt.equals("Arrangement")){
                 textfields.setLayoutY(210);
            textfields.setLayoutX(50);
            }
            else if(valgt.equals("Billett")){
                textfields.setLayoutY(170);
                textfields.setLayoutX(50);
            }
            else{
                 textfields.setLayoutY(130);
            textfields.setLayoutX(50);
            }
        }
      
        anchorPane.getChildren().add(textfields);
        teller++;
        
    }
    // end for
    
     btn=new Button("Bekreft");
   
     btn.setLayoutY(input[teller-1].getLayoutY()+40);
    btn.setLayoutX(50);
      btn.addEventHandler(ActionEvent.ACTION, event->registrer());
      anchorPane.getChildren().add(btn);
 }
 
 
 
 int artistIndex;
 int kontaktPersonIndex;
 @FXML
 private void registrer(){
     objekter=new ArrayList<>();
     ArrayList<String> data=new ArrayList<>();
     // Samler data fra textfieldene i arraylist
     for (TextField f : input){
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
              artistIndex=ddlArtister.getSelectionModel().getSelectedIndex();
              kontaktPersonIndex=ddlKontaktPerson.getSelectionModel().getSelectedIndex();
           
              obsArtister=FXCollections.observableArrayList(register.getArtister());
               obsKontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
           Arrangement arrang=new Arrangement(obsArtister.get(artistIndex),obsKontaktPerson.get(kontaktPersonIndex),data);
           
           utskriftRegistrert.setText(arrang.toString());
           objekter.add(arrang);
              break;
              
          case "KontaktPerson":
              KontaktPerson kontaktPerson=new KontaktPerson(data);
              utskriftRegistrert.setText(kontaktPerson.toString());
              objekter.add(kontaktPerson);
              break;
              
          case "Billett":
               //obsArrangement=FXCollections.observableArrayList(register.getArrangement());
               Arrangement tilhørendeArrangement=obsArrangement.get(ddlArrangement.getSelectionModel().getSelectedIndex());
              Billett billett=new Billett(tilhørendeArrangement,data);
              System.out.print("Arrang size"+obsArrangement.size());
        
              utskriftRegistrert.setText(billett.toString());
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
      register.test();
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
          
        }
            }
                });
                         }
}       
     
     
    
          
    


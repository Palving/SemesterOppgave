package org.openjfx;

import Model.Avvik.InvalidComboBoxValueException;
import Model.Avvik.InvalidInputException;
import Model.Avvik.InvalidTextFieldInputException;
import Model.Avvik.InvalidTimeFormatException;
import Model.Domene.*;
import Model.Registrering.Register;
import Model.Registrering.ValideringSystem;
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
import javafx.scene.control.DatePicker;
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
 private InputFormatter inputFormatter=new InputFormatter();
 
 // ddl elelementer
 private ComboBox ddlArtister=null;
 private ComboBox ddlKontaktPerson=null;
 private ComboBox ddlLokale=null;
 private ComboBox ddlArrangement=null;
 
 private ObservableList<Artist> obsArtister;
 private ObservableList<KontaktPerson> obsKontaktPerson;
 private ObservableList<Lokale> obsLokale;
 private ObservableList<Arrangement> obsArrangement;
 
 // peker på element i listen basert på valg i combobox
 private int artistIndex;
 private int kontaktPersonIndex;
 private int lokaleIndex;
 
 // date elementer
 private DatePicker datePicker=null;
 

 // Fjerner textfields på radiobutton-onchange()
 private void refreshInputElementer(){
     if (input==null){
         return;
     }
     for (TextField f : input){
         anchorPane.getChildren().remove(f);
     }
     
     if (ddlArtister!=null){
         anchorPane.getChildren().remove(ddlArtister);
     anchorPane.getChildren().remove(ddlKontaktPerson);
     anchorPane.getChildren().remove(ddlLokale);
     anchorPane.getChildren().remove(datePicker);
     }
     
     if (ddlArrangement!=null){
       
         anchorPane.getChildren().remove(ddlArrangement);
     }
    
   anchorPane.getChildren().remove(btn);
     
 }
 

 private void formaterTextFields(){
     refreshInputElementer();
     input=inputFormatter.lagTextFields(valgt);
     int teller=0;
     attributter=inputFormatter.getTextFieldAttributter(valgt);
     
    
      // legg til ddl med alle artister og kontaktpersoner
     if (valgt.equals("Arrangement")){
        formaterDropdownArrangement();  
        formaterDatePicker();
     }
     else if(valgt.equals("Billett")){
        formaterDropdownBillett();
     }
     
    for (TextField textfields : input){
       textfields.setPromptText(attributter[teller]);
        //input[teller]=textfields; // -> jævlig rart
       
        if (teller!=0){
             textfields.setLayoutY(input[teller-1].getLayoutY()+40);
            textfields.setLayoutX(50);
        }
          // Gir første textfield startposisjon, og resten bygger på den
        else{
            if (valgt.equals("Arrangement")){
                 textfields.setLayoutY(290);
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
      btn=new Button("Bekreft");
   
     btn.setLayoutY(input[teller-1].getLayoutY()+40);
    btn.setLayoutX(50);
      btn.addEventHandler(ActionEvent.ACTION, event->registrer());
      anchorPane.getChildren().add(btn);
 }
 
 private void formaterDropdownArrangement(){
    ddlArtister=inputFormatter.formaterDropdownArtist();
    ddlArtister.setLayoutX(50);
    ddlArtister.setLayoutY(130);
    ddlArtister.setMaxSize(50, 20);
    
    ddlKontaktPerson=inputFormatter.formaterDropdownKontaktPerson();
   ddlKontaktPerson.setLayoutX(50);
    ddlKontaktPerson.setLayoutY(170);
    ddlKontaktPerson.setMaxSize(50, 20);
    
    ddlLokale=inputFormatter.formaterDropdownLokale();
    ddlLokale.setLayoutX(50);
    ddlLokale.setLayoutY(210);
    ddlLokale.setMaxSize(50, 20);
    
     anchorPane.getChildren().add(ddlArtister);
     anchorPane.getChildren().add(ddlKontaktPerson);
     anchorPane.getChildren().add(ddlLokale);
     
    
}

private void formaterDropdownBillett(){
    ddlArrangement=inputFormatter.formaterDropdownBillett();
     ddlArrangement.setLayoutX(50);
    ddlArrangement.setLayoutY(130);
    ddlArrangement.setMaxSize(50, 20);
    
    anchorPane.getChildren().add(ddlArrangement);
}

private void formaterDatePicker(){
    datePicker=inputFormatter.formaterDatePicker();
    datePicker.setLayoutX(50);
    datePicker.setLayoutY(250);
    
    anchorPane.getChildren().add(datePicker);
}

  private int getArtistIndex() throws InvalidComboBoxValueException{
     return inputFormatter.getArtistIndex(ddlArtister);
 }
 
 private int getKontaktPersonIndex() throws InvalidComboBoxValueException{
    return inputFormatter.getKontaktPersonIndex(ddlKontaktPerson);
 }
 
 private int getLokaleIndex() throws InvalidComboBoxValueException{
    return inputFormatter.getLokaleIndex(ddlLokale);
 }

 @FXML
 private void registrer(){
    //objekter=new ArrayList<>();
    Object objToReg=null;
   ArrayList<String> data=null;
     try{
          data=new ArrayList<>(inputFormatter.getTextFieldData());
     }
     catch(InvalidTextFieldInputException e){
         utskriftRegistrert.setText(e.getMessage());
         
         System.err.println(e.getMessage());
         return;
     }
    try {
         ValideringSystem.validerInputiTextFields(input);
     }
     catch(InvalidInputException e){
         utskriftRegistrert.setText(e.getMessage());
         System.err.println(e.getMessage());
         return;
     }
    
      switch(valgt){
          case "Artist":
              Artist artist=new Artist(data);
              utskriftRegistrert.setText("\n"+artist.toString());
             // objekter.add(artist);
              objToReg=artist;
               break;
               
          case "Lokale":
              Lokale lokale=new Lokale(data);
              utskriftRegistrert.setText("\n"+lokale.toString());
              objToReg=lokale;
               break;
               
          case "Arrangement":
              // hent valg fra combobox
              try{
                 artistIndex=getArtistIndex();
                 kontaktPersonIndex=getKontaktPersonIndex();
                 lokaleIndex=getLokaleIndex();
              }
              catch (InvalidComboBoxValueException e){
                  utskriftRegistrert.setText((e.getMessage()));
                  System.out.println(e.getMessage());
                  return;
              }
              
              try{
                  ValideringSystem.validerTimeFormat(data.get(0));
              }
              catch(InvalidTimeFormatException e){
                  System.err.println(e.getMessage());
                  return;
              }
             
                  
              // Objektene som skal lagres sammen med Arrangement
              ObservableList<Artist> obsArtister=FXCollections.observableArrayList(register.getArtister());
              ObservableList<Lokale> obsLokale=FXCollections.observableArrayList(register.getLokale());
              ObservableList<KontaktPerson> obsKontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
             
              data.add(obsLokale.get(lokaleIndex).getLokaleNavn());
               Arrangement arrang=new Arrangement(obsArtister.get(artistIndex),obsKontaktPerson.get(kontaktPersonIndex),data,datePicker.getValue());
           
                utskriftRegistrert.setText(arrang.toString());
                objToReg=arrang;
             break;
              
          case "KontaktPerson":
              KontaktPerson kontaktPerson=new KontaktPerson(data);
              utskriftRegistrert.setText(kontaktPerson.toString());
              objToReg=kontaktPerson;
              break;
              
          case "Billett":
              
              ObservableList<Arrangement> obsArrangement=FXCollections.observableArrayList(register.getArrangement());
              Arrangement tilhørendeArrangement=obsArrangement.get(ddlArrangement.getSelectionModel().getSelectedIndex());
              Billett billett=new Billett(tilhørendeArrangement,data);
              
              if (register.sjekkLedigPlass(billett)){
                  utskriftRegistrert.setText("Plassen er opptatt, vennligst velg annet plassnummer");
                  return;
              }
              utskriftRegistrert.setText(billett.toString());
              objToReg=billett;
              break;
          
      }
     
     
    register.registrer(objToReg);
   
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
        
          // Lager textfields basert på valgt
          formaterTextFields();
          
        } // end if
            } // end changed
                }); // end changeListenener
                         } // end init
}       
     
     
    
          
    


package org.openjfx;

import Model.Avvik.InvalidComboBoxValueException;
import Model.Avvik.InvalidInputException;
import Model.Avvik.InvalidTextFieldInputException;
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
         System.out.println("ddl arrang removed");
         anchorPane.getChildren().remove(ddlArrangement);
     }
    
   anchorPane.getChildren().remove(btn);
     
 }
 
  
 // lager dropdownlist dersom registreringen tar inn et objekt framfor streng aka valget er Arrangement
 private void formaterDropdownArrangement(){
     
     obsArtister=FXCollections.observableArrayList(register.getArtister());
     obsKontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
     obsLokale=FXCollections.observableArrayList(register.getLokale());
     
     ddlArtister=new ComboBox(obsArtister);
     ddlKontaktPerson=new ComboBox(obsKontaktPerson);
     ddlLokale=new ComboBox(obsLokale);
      
    ddlArtister.setLayoutX(50);
    ddlArtister.setLayoutY(130);
    ddlArtister.setPromptText("Velg artist");
    
    ddlKontaktPerson.setLayoutX(50);
    ddlKontaktPerson.setLayoutY(170);
    ddlKontaktPerson.setPromptText("Velg kontaktperson");
    
    ddlLokale.setLayoutX(50);
    ddlLokale.setLayoutY(210);
    ddlLokale.setPromptText("Velg sted");
    
    datePicker=new DatePicker();
    
    datePicker.setLayoutX(50);
    datePicker.setLayoutY(250);
    datePicker.setPromptText("Velg dato");
    
     anchorPane.getChildren().add(ddlArtister);
     anchorPane.getChildren().add(ddlKontaktPerson);
     anchorPane.getChildren().add(ddlLokale);
     
     anchorPane.getChildren().add(datePicker);
     
 }
 
 private void formaterDropdownBillett(){
     obsArrangement=FXCollections.observableArrayList(register.getArrangement());
   
   ddlArrangement=new ComboBox(obsArrangement);
  
    ddlArrangement.setLayoutX(50);
    ddlArrangement.setLayoutY(130);
    ddlArrangement.setMaxSize(50, 20);
    
    
     anchorPane.getChildren().add(ddlArrangement);
     
 }

 // Attributtene som avgjør antall textfields og textPrompt
 private String[] getAttributter(){
     String[] artistAttributes={"Fornavn","Etternavn","Tlf","Type artist"};
      String[] lokaleAttributes={"Lokalenavn","Antall plasser"};
      String[] arrangAttributes={"Type arrangement", "Arrangementnavn","Program", "Pris", "Tidspunkt - 00:00"};
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
      return attributter;
 }


 private void formaterTextFields(){
     
     // Tømmer alt av elementer utenom radio-group
     // når bruker bytter valg i radiogroup
   refreshInputElementer();
     
      attributter=getAttributter();
    
     input=new TextField[attributter.length];
   
     int teller=0;
     
      // legg til ddl med alle artister og kontaktpersoner
     if (valgt.equals("Arrangement")){
        formaterDropdownArrangement();  
     }
     else if(valgt.equals("Billett")){
         
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
    // end for
    
     btn=new Button("Bekreft");
   
     btn.setLayoutY(input[teller-1].getLayoutY()+40);
    btn.setLayoutX(50);
      btn.addEventHandler(ActionEvent.ACTION, event->registrer());
      anchorPane.getChildren().add(btn);
   
 }
 
 private ArrayList<String> getTextFieldData()  throws InvalidTextFieldInputException{
     ArrayList<String> data=new ArrayList<>();
     // Samler data fra textfieldene i arraylist
     for (TextField f : input){
         if (f.getText().isEmpty()) throw new InvalidTextFieldInputException("Feltene kan ikke være tomme");
         data.add(f.getText());
     }
     return data;
 }
 public int getArtistIndex() throws InvalidComboBoxValueException{
     artistIndex=ddlArtister.getSelectionModel().getSelectedIndex();
     if (artistIndex==-1){
         throw new InvalidComboBoxValueException("Du må velge en artist");
     }
       return artistIndex;
 }
 public int getKontaktPersonIndex() throws InvalidComboBoxValueException{
     kontaktPersonIndex=ddlKontaktPerson.getSelectionModel().getSelectedIndex();
       if (kontaktPersonIndex==-1){
         throw new InvalidComboBoxValueException("Du må velge en kontaktperson");
     }
    return kontaktPersonIndex;
 }
 public int getLokaleIndex() throws InvalidComboBoxValueException{
     lokaleIndex=ddlLokale.getSelectionModel().getSelectedIndex();
       if (lokaleIndex==-1){
         throw new InvalidComboBoxValueException("Du må velge lokale");
     }
     return lokaleIndex;
 }

 @FXML
 private void registrer(){
     objekter=new ArrayList<>();
      ArrayList<String> data=null;
     try{
          data=new ArrayList<>(getTextFieldData());
     }
     catch(InvalidTextFieldInputException e){
         utskriftRegistrert.setText(e.getMessage());
         System.err.println(e.getMessage());
         return;
     }
    /* try {
         ValideringSystem.validerInputiTextFields(input);
     }
     catch(InvalidInputException e){
         utskriftRegistrert.setText(e.getMessage());
         System.err.println(e.getMessage());
         return;
     }*/
    
     //data=getTextFieldData();
     
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
             
              //sted
              data.add(obsLokale.get(lokaleIndex).getLokaleNavn());
           Arrangement arrang=new Arrangement(obsArtister.get(artistIndex),obsKontaktPerson.get(kontaktPersonIndex),data,datePicker.getValue());
           
           utskriftRegistrert.setText(arrang.toString());
           objekter.add(arrang);
             break;
              
          case "KontaktPerson":
              KontaktPerson kontaktPerson=new KontaktPerson(data);
              utskriftRegistrert.setText(kontaktPerson.toString());
              objekter.add(kontaktPerson);
              break;
              
          case "Billett":
               Arrangement tilhørendeArrangement=obsArrangement.get(ddlArrangement.getSelectionModel().getSelectedIndex());
              Billett billett=new Billett(tilhørendeArrangement,data);
              utskriftRegistrert.setText(billett.toString());
              objekter.add(billett);
              break;
          
      }
     
     // Sketchy
     // TODO: Fiks dette
    register.registrer(objekter.get(0));
   
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
          
        } // end if
            } // end changed
                }); // end changeListenener
                         } // end init
}       
     
     
    
          
    


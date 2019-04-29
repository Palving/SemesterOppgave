/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import Model.Avvik.InvalidComboBoxValueException;
import Model.Avvik.InvalidTextFieldInputException;
import Model.Domene.Arrangement;
import Model.Domene.Artist;
import Model.Domene.KontaktPerson;
import Model.Domene.Lokale;
import Model.Registrering.Register;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Magnus
 */
public class InputFormatter {
    
    @FXML
private TextField[] input=null;
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

private String[] attributter; 
 @FXML
 private AnchorPane anchorPane;
 
 @FXML
 private Button btn;
 
 private Register register=Register.getInstance();
 
 
  protected String[] getTextFieldAttributter(String valgt){
     String[] artistAttributes={"Fornavn","Etternavn","Tlf","Type artist"};
      String[] lokaleAttributes={"Lokalenavn","Antall plasser"};
      String[] arrangAttributes={"Klokkeslett 00:00","Arrangementnavn","Program","Type","Pris",};
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
    
 
 // lager dropdownlist dersom registreringen tar inn et objekt framfor streng aka valget er Arrangement
 protected ComboBox formaterDropdownArtist(){
     
    obsArtister=FXCollections.observableArrayList(register.getArtister());
    ddlArtister=new ComboBox(obsArtister);
    ddlArtister.setPromptText("Velg artist");
   
    return ddlArtister;
   
 }
 
 protected ComboBox formaterDropdownKontaktPerson(){
    
     obsKontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
     ddlKontaktPerson=new ComboBox(obsKontaktPerson);
     ddlKontaktPerson.setPromptText("Velg kontaktperson");
    
     return ddlKontaktPerson;
     
 }
 protected ComboBox formaterDropdownLokale(){
      
     obsLokale=FXCollections.observableArrayList(register.getLokale());
     ddlLokale=new ComboBox(obsLokale);
     ddlLokale.setPromptText("Velg sted");
    
     return ddlLokale;
     
 }
 protected DatePicker formaterDatePicker(){
     
   datePicker=new DatePicker();
   datePicker.setPromptText("Velg dato");
   
    return datePicker;
 }
 
 
 protected ComboBox formaterDropdownBillett(){
     
   obsArrangement=FXCollections.observableArrayList(register.getArrangement());
   ddlArrangement=new ComboBox(obsArrangement);
   ddlArrangement.setMaxSize(50, 20);
    
    return ddlArrangement;
     
 }

 // Attributtene som avgjør antall textfields og textPrompt
 
 protected TextField[] lagTextFields(String valgt){
     
   
      attributter=getTextFieldAttributter(valgt);
 
     input=new TextField[attributter.length];
   
     int teller=0;
  
    for (TextField textfields : input){
        textfields=new TextField();
      
    //  textfields.setText(felter[teller]);
        input[teller]=textfields;
       
        if (teller!=0){
             textfields.setLayoutY(input[teller-1].getLayoutY()+40);
            textfields.setLayoutX(700);
        }
          // Gir første textfield startposisjon, og resten bygger på den
        else{
            if (valgt.equals("Arrangement")){
                 textfields.setLayoutY(290);
            textfields.setLayoutX(700);
            }
            else if(valgt.equals("Billett")){
                
                textfields.setLayoutY(170);
                textfields.setLayoutX(700);
            }
            else{
                 textfields.setLayoutY(130);
            textfields.setLayoutX(700);
            }
        }
      
       //anchorPane.getChildren().add(textfields);
        teller++;
        
    }
    // end for
    
    return input;
   
 }
 // Formatering ferdig
 
  protected ArrayList<String> getTextFieldData()  throws InvalidTextFieldInputException{
     ArrayList<String> data=new ArrayList<>();
     // Samler data fra textfieldene i arraylist
     for (TextField f : input){
         if (f.getText().isEmpty()) throw new InvalidTextFieldInputException("Feltene kan ikke være tomme");
         data.add(f.getText());
     }
     return data;
 }
  
 public int getArtistIndex(ComboBox ddlArtister) throws InvalidComboBoxValueException{
     artistIndex=ddlArtister.getSelectionModel().getSelectedIndex();
     if (artistIndex==-1){
         throw new InvalidComboBoxValueException("Du må velge en artist");
     }
       return artistIndex;
 }
 
 public int getKontaktPersonIndex(ComboBox ddlKontaktPerson) throws InvalidComboBoxValueException{
     kontaktPersonIndex=ddlKontaktPerson.getSelectionModel().getSelectedIndex();
       if (kontaktPersonIndex==-1){
         throw new InvalidComboBoxValueException("Du må velge en kontaktperson");
     }
    return kontaktPersonIndex;
 }
 
 public int getLokaleIndex(ComboBox ddlLokale) throws InvalidComboBoxValueException{
     lokaleIndex=ddlLokale.getSelectionModel().getSelectedIndex();
       if (lokaleIndex==-1){
         throw new InvalidComboBoxValueException("Du må velge lokale");
     }
     return lokaleIndex;
 }
    
}

package org.openjfx;


import Model.Avvik.InvalidComboBoxValueException;
import Model.Avvik.InvalidInputException;
import Model.Avvik.InvalidTextFieldInputException;
import Model.Domene.Arrangement;
import Model.Domene.Artist;
import Model.Domene.Billett;
import Model.Domene.KontaktPerson;
import Model.Domene.Lokale;
import Model.Endring.EndringSystem;
import Model.Endring.SletteSystem;
import Model.Registrering.Register;
import Model.Registrering.ValideringSystem;
import java.io.IOException;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;



public class EndringController implements Initializable {

   @FXML
 private RadioButton artistRadio, lokaleRadio, kontaktPersonRadio, arrangRadio, billettRadio;
   ToggleGroup radioGrp;
   
   @FXML
   private AnchorPane anchorPane;
   
   
   
   private String valgt;
   private String[] attributter;
   
   private InputFormatter inputFormatter=new InputFormatter();
   private TableViewFormatter tableViewFormatter=new TableViewFormatter();
   private EndringSystem endreSystem=new EndringSystem();
   private Register register=Register.getInstance();
   private SletteSystem sletteSystem=new SletteSystem();
   
   // textfields elementer
 
 
 // register elemeneter
 private ArrayList<Object> objekter;
 private Button btn;

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
 
 
   @FXML
   private TableView table=null;
   
   
  
   public int getArtistIndex() throws InvalidComboBoxValueException{
   return inputFormatter.getArtistIndex(ddlArtister);
 }
 
 public int getKontaktPersonIndex() throws InvalidComboBoxValueException{
   return inputFormatter.getArtistIndex(ddlKontaktPerson);
 }
 
 public int getLokaleIndex() throws InvalidComboBoxValueException{
    return inputFormatter.getArtistIndex(ddlLokale);
 }
  
   
   
   private void endreObjekt(){
         objekter=new ArrayList<>();
      ArrayList<String> data=null;
     try{
          data=new ArrayList<>(inputFormatter.getTextFieldData());
     }
     catch(InvalidTextFieldInputException e){
        // utskriftRegistrert.setText(e.getMessage());
         System.err.println(e.getMessage());
         return;
     }
   /* try {
         ValideringSystem.validerInputiTextFields(input);
     }
     catch(InvalidInputException e){
         //utskriftRegistrert.setText(e.getMessage());
         System.err.println(e.getMessage());
         return;
     }*/
    
    
   
      switch(valgt){
          case "Artist":
              Artist artist=(Artist)objToChange;
          
             endreSystem.endreObject(artist, "Artist");
             artist=new Artist(data);
             register.registrer(artist);
               break;
               
          case "Lokale":
              Lokale lokale=(Lokale)objToChange;
          
             endreSystem.endreObject(lokale, "Lokale");
             lokale=new Lokale(data);
             register.registrer(lokale);
               break;
               
          case "Arrangement":
              // hent valg fra combobox
              try{
                 artistIndex=getArtistIndex();
                 kontaktPersonIndex=getKontaktPersonIndex();
                 lokaleIndex=getLokaleIndex();
              }
              catch (InvalidComboBoxValueException e){
              //  utskriftRegistrert.setText((e.getMessage()));
                  System.out.println(e.getMessage());
                  return;
              }
              Arrangement arrang=(Arrangement)objToChange;
              // må ta vare på antall solgte billetter, siden den settes 0 i konstruktøren
              int billettSalg=arrang.getBillettSalg();
             // utskriftRegistrert.setText("\n"+artist.toString());
             endreSystem.endreObject(arrang, "Arrangement");
            
            
              //sted
              // burde heller kanskje lagre selve objektet i det og heller hente stedet et annet sted
        ObservableList<Artist> obsArtister=FXCollections.observableArrayList(register.getArtister());
        ObservableList<Lokale> obsLokale=FXCollections.observableArrayList(register.getLokale());         
        ObservableList<KontaktPerson> obsKontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
        
         data.add(obsLokale.get(lokaleIndex).getLokaleNavn());
             
           arrang=new Arrangement(obsArtister.get(artistIndex),obsKontaktPerson.get(kontaktPersonIndex),data,datePicker.getValue());
           arrang.setBillettSalg(billettSalg);
           //utskriftRegistrert.setText(arrang.toString());
          register.registrer(arrang);
             break;
              
          case "KontaktPerson":
              KontaktPerson kontaktPerson=(KontaktPerson)objToChange;
             // utskriftRegistrert.setText("\n"+artist.toString());
             endreSystem.endreObject(kontaktPerson, "KontaktPerson");
             kontaktPerson=new KontaktPerson(data);
             register.registrer(kontaktPerson);
              break;
              
          case "Billett":
               ObservableList<Arrangement> obsArrangement=FXCollections.observableArrayList(register.getArrangement());
               Arrangement tilhørendeArrangement=obsArrangement.get(ddlArrangement.getSelectionModel().getSelectedIndex());
               
               Billett billett=(Billett) objToChange;
               endreSystem.endreObject(objToChange, "Billett");
           billett=new Billett(tilhørendeArrangement,data);
             // utskriftRegistrert.setText(billett.toString());
           register.registrer(billett);
              break;
          
      }
    
     refreshInputElementer();
     visData(valgt);
 
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
   
   public void visData(String valgt){
       table=tableViewFormatter.visData(valgt);
      table.setLayoutX(57);
     table.setLayoutY(120);
     table.setPrefHeight(200);
     table.setPrefWidth(600);
       anchorPane.getChildren().add(table);
   }
   
 
   
   // TODO
   // Slette fra alt
   public void sletteKnapp()
    {
      
        ObservableList<Object> valgtRad, getObject;
        getObject = table.getItems();
                
        valgtRad = table.getSelectionModel().getSelectedItems();
                
        for (Object obj: valgtRad)
        {
             getObject.remove(obj);
             sletteSystem.remove(obj);
          
        }
       refreshInputElementer();
    }
   
// Endre metoder
   public Object objToChange;
   
   @FXML
   public void endre(ActionEvent event) throws IOException{
       
       refreshInputElementer();
       objToChange=getDataFromTable();
       formaterTextFields();
      
    btn=new Button("Bekreft");
    btn.setLayoutY(input[input.length-1].getLayoutY()+40);
    btn.setLayoutX(700);
    btn.addEventHandler(ActionEvent.ACTION, even->endreObjekt());
    
    anchorPane.getChildren().add(btn);
    
   }
   
   private Object getDataFromTable(){
      Object obj=null;
       
       if (table.getSelectionModel().getSelectedItem()!=null){
          System.out.print(table.getSelectionModel().getSelectedItem());
           obj=table.getSelectionModel().getSelectedItem();
      
       }
       return obj;
       
   }
   
 

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
private void formaterDropdownArrangement(){
    ddlArtister=inputFormatter.formaterDropdownArtist();
    ddlArtister.setLayoutX(700);
    ddlArtister.setLayoutY(130);
     ddlArtister.setMaxSize(50, 20);
    
    ddlKontaktPerson=inputFormatter.formaterDropdownKontaktPerson();
    ddlKontaktPerson.setLayoutX(700);
    ddlKontaktPerson.setLayoutY(170);
     ddlKontaktPerson.setMaxSize(50, 20);
    
    ddlLokale=inputFormatter.formaterDropdownLokale();
    ddlLokale.setLayoutX(700);
    ddlLokale.setLayoutY(210);
     ddlLokale.setMaxSize(50, 20);
    
     anchorPane.getChildren().add(ddlArtister);
     anchorPane.getChildren().add(ddlKontaktPerson);
     anchorPane.getChildren().add(ddlLokale);
     
    
}

private void formaterDropdownBillett(){
    ddlArrangement=inputFormatter.formaterDropdownBillett();
     ddlArrangement.setLayoutX(700);
    ddlArrangement.setLayoutY(130);
     ddlArrangement.setMaxSize(300, 20);
     
    
    anchorPane.getChildren().add(ddlArrangement);
}

private void formaterDatePicker(){
    datePicker=inputFormatter.formaterDatePicker();
    datePicker.setLayoutX(700);
    datePicker.setLayoutY(250);
    anchorPane.getChildren().add(datePicker);
}

 private void formaterTextFields(){
     input=inputFormatter.lagTextFields(valgt);
     int teller=0;
         String text=objToChange.toString();
    String[] felter=text.split("\n ");
    
      if (valgt.equals("Arrangement")){
        formaterDropdownArrangement();  
        formaterDatePicker();
        Arrangement a=(Arrangement)objToChange;
        datePicker.setValue(a.getDato());
        
        
     }
     else if(valgt.equals("Billett")){
         formaterDropdownBillett();
        // Billett b=(Billett)objToChange;
        // datePicker.setValue(b.getDato());
        
     }
    
     for (TextField f : input){
         anchorPane.getChildren().add(f);
         f.setText(felter[teller]);
         teller++;
     }
 }
 
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        initRadioGroup();
         
        radioGrp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> ov,
          Toggle old_toggle, Toggle new_toggle) {
        if (radioGrp.getSelectedToggle() != null) {
            // Valget i radio-gruppen
          valgt=radioGrp.getSelectedToggle().getUserData().toString();
          System.out.println(valgt);
          
          visData(valgt);
         
          
        }
      }
      });
        
            }
}
           
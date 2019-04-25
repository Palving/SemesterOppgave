package org.openjfx;


import Model.Avvik.InvalidComboBoxValueException;
import Model.Avvik.InvalidTextFieldInputException;
import Model.Domene.Arrangement;
import Model.Domene.Artist;
import Model.Domene.Billett;
import Model.Domene.KontaktPerson;
import Model.Domene.Lokale;
import Model.Endring.SletteSystem;
import Model.Registrering.Register;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;



public class EndringController implements Initializable {

   @FXML
 private RadioButton artistRadio, lokaleRadio, kontaktPersonRadio, arrangRadio, billettRadio;
   ToggleGroup radioGrp;
   
   @FXML
   private AnchorPane anchorPane;
   
   Register register=Register.getInstance();
   SletteSystem sletteSystem=new SletteSystem();
   
   private String valgt;
   private String[] attributter;
   
  
   
   
   @FXML
   private TableView table=null;
   
   private void endreObjekt(){
         objekter=new ArrayList<>();
      ArrayList<String> data=null;
     try{
          data=new ArrayList<>(getTextFieldData());
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
         utskriftRegistrert.setText(e.getMessage());
         System.err.println(e.getMessage());
         return;
     }*/
    
     //data=getTextFieldData();
      switch(valgt){
          case "Artist":
              Artist artist=new Artist(data);
             // utskriftRegistrert.setText("\n"+artist.toString());
              objekter.add(artist);
             
               break;
               
          case "Lokale":
              Lokale lokale=new Lokale(data);
             // utskriftRegistrert.setText("\n"+lokale.toString());
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
                //  utskriftRegistrert.setText((e.getMessage()));
                  System.out.println(e.getMessage());
                  return;
              }
             
              //sted
              // burde heller kanskje lagre selve objektet i det og heller hente stedet et annet sted
              data.add(obsLokale.get(lokaleIndex).getLokaleNavn());
           Arrangement arrang=new Arrangement(obsArtister.get(artistIndex),obsKontaktPerson.get(kontaktPersonIndex),data,datePicker.getValue());
           
           //utskriftRegistrert.setText(arrang.toString());
           objekter.add(arrang);
             break;
              
          case "KontaktPerson":
              KontaktPerson kontaktPerson=new KontaktPerson(data);
              //utskriftRegistrert.setText(kontaktPerson.toString());
              objekter.add(kontaktPerson);
              break;
              
          case "Billett":
               Arrangement tilhørendeArrangement=obsArrangement.get(ddlArrangement.getSelectionModel().getSelectedIndex());
              Billett billett=new Billett(tilhørendeArrangement,data);
             // utskriftRegistrert.setText(billett.toString());
              objekter.add(billett);
              break;
          
      }
     
     
     objToChange=(Artist)objekter.get(0);
   register.registrer(objToChange);
       
   }
  
   private String[] getAttributter(){
     String[] artistAttributes={"Fornavn","Etternavn","Tlf","typeArtist"};
      String[] lokaleAttributes={"lokaleNavn","antallPlasser"};
      String[] arrangAttributes={"type", "navnPaaArrangement","program", "billettPris", "Tidspunkt"};
      String[] kontaktPersonAttributes={"fornavn","etternavn","tlf","firma","info","nettSide"};
      String[] billettAttributes={"plassNummer","lokaleNavn","dato", "kundeTlf","arrangementNavn"};
      
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
   
   
   
   public void hentData(ObservableList<Object> liste){
       
       attributter=getAttributter();
       table=new TableView();
       
       TableColumn[] columns=new TableColumn[attributter.length];
       System.out.println("columns length"+columns.length);
       int teller=0;
       for (String att : attributter){
           columns[teller]=new TableColumn(att);
           columns[teller].setCellValueFactory(new PropertyValueFactory<Object, Object>(attributter[teller]));
          
           //columns[teller].setCellFactory(TextFieldTableCell.forTableColumn());
           teller++;
       }
    /*   
     TableColumn fornavnCol = new TableColumn("Fornavn");
        TableColumn etternavnCol = new TableColumn("Etternavn");
        TableColumn tlfCol = new TableColumn("Tlf");
        TableColumn typeCol=new TableColumn("Type artist");
        
  
            ObservableList<Artist> data = FXCollections.observableArrayList(register.getArtister());
        
           fornavnCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("fornavn"));
           etternavnCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("etternavn"));
           tlfCol.setCellValueFactory(new PropertyValueFactory<Artist,String>("tlf"));
           typeCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("typeArtist"));
           // layoutX="57.0" layoutY="120.0" prefHeight="200.0" prefWidth="459.0">
          table.setItems(data);
     table.getColumns().addAll(fornavnCol, etternavnCol, tlfCol, typeCol);
*/
    // table.getColumns().addAll((Object) columns);
    table.setItems(liste);
    for (TableColumn t : columns){
        table.getColumns().addAll(t);
    }
     table.setLayoutX(57);
     table.setLayoutY(120);
     table.setPrefHeight(200);
     table.setPrefWidth(600);
     table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

       anchorPane.getChildren().add(table);
       
        table.setEditable(true);
       
     
       /*
       fornavnCol.setCellFactory(TextFieldTableCell.forTableColumn());
       etternavnCol.setCellFactory(TextFieldTableCell.forTableColumn());
       tlfCol.setCellFactory(TextFieldTableCell.forTableColumn());
       typeCol.setCellFactory(TextFieldTableCell.forTableColumn());*/
       
        }
   
    
   public void visData(String valgt){
     
   switch(valgt){
       case "Artist":
           ObservableList<Object> artister=FXCollections.observableArrayList(register.getArtister());
           System.out.println("1");
           hentData(artister);
           System.out.println("2");
           break;
       case "Lokale":
           ObservableList<Object> lokale=FXCollections.observableArrayList(register.getLokale());
           hentData(lokale);
           break;
       case "Arrangement":
           ObservableList<Object> arrangement=FXCollections.observableArrayList(register.getArrangement());
           hentData(arrangement);
           break;
       case "KontaktPerson":
           ObservableList<Object> kontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
           hentData(kontaktPerson);
           break;
       case "Billett":
           ObservableList<Object> billett=FXCollections.observableArrayList(register.getBillett());
           hentData(billett);
           break;
        }   
   
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
    }
   
   // TODO
   // Endre metoder
   // Send bruker til nytt vindu og pass objektet som er valgt til det vinduet sammen med et textfield for hver attributt
   // og lagre det 
   
   

   public Object objToChange;
   
   @FXML
   public void endre(ActionEvent event) throws IOException{
       
       
       objToChange=getDataFromTable();
      formaterTextFields();
       
       
   }
   
   public Object getDataFromTable(){
      Object obj=null;
       
       if (table.getSelectionModel().getSelectedItem()!=null){
          System.out.print(table.getSelectionModel().getSelectedItem());
           obj=table.getSelectionModel().getSelectedItem();
           System.out.println("if kjørt");
           
           
       }
       return obj;
       
   }
   
   // endre kode
   
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
 
  
 // lager dropdownlist dersom registreringen tar inn et objekt framfor streng aka valget er Arrangement
 private void formaterDropdownArrangement(){
     
     obsArtister=FXCollections.observableArrayList(register.getArtister());
     obsKontaktPerson=FXCollections.observableArrayList(register.getKontaktPerson());
     obsLokale=FXCollections.observableArrayList(register.getLokale());
     
     ddlArtister=new ComboBox(obsArtister);
     ddlKontaktPerson=new ComboBox(obsKontaktPerson);
     ddlLokale=new ComboBox(obsLokale);
      
    ddlArtister.setLayoutX(700);
    ddlArtister.setLayoutY(130);
    ddlArtister.setPromptText("Velg artist");
    //ddlArtister.setValue();
    
    ddlKontaktPerson.setLayoutX(700);
    ddlKontaktPerson.setLayoutY(170);
    ddlKontaktPerson.setPromptText("Velg kontaktperson");
    
    ddlLokale.setLayoutX(700);
    ddlLokale.setLayoutY(210);
    ddlLokale.setPromptText("Velg sted");
    
    datePicker=new DatePicker();
    
    datePicker.setLayoutX(700);
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
  
    ddlArrangement.setLayoutX(700);
    ddlArrangement.setLayoutY(130);
    ddlArrangement.setMaxSize(50, 20);
    
    
     anchorPane.getChildren().add(ddlArrangement);
     
 }

 // Attributtene som avgjør antall textfields og textPrompt
 
 private void formaterTextFields(){
     
     // Tømmer alt av elementer utenom radio-group
     // når bruker bytter valg i radiogroup
   refreshInputElementer();
     
      attributter=getAttributter();
    String text=objToChange.toString();
    String[] felter=text.split("\n ");
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
      
      textfields.setText(felter[teller]);
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
      
        anchorPane.getChildren().add(textfields);
        teller++;
        
    }
    // end for
    
     btn=new Button("Bekreft");
   
     btn.setLayoutY(input[teller-1].getLayoutY()+40);
    btn.setLayoutX(700);
      btn.addEventHandler(ActionEvent.ACTION, event->endreObjekt());
      anchorPane.getChildren().add(btn);
   
 }
 // Formatering ferdig
 
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
           
package org.openjfx;

import Model.Lagring.Lagring;
import Model.Nedlasting.Nedlasting;
import Model.Registrering.Register;
import Model.Tråder.ThreadSystem;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLController {
    private Stage stage;
    @FXML
    private Label label;
    
    @FXML
    private TableView tabell;
    
    @FXML
    private ComboBox dropdownliste;
    
    @FXML
    private AnchorPane ap;
    
    
   Register register=Register.getInstance();
   
  
   private String[] attributter;
    @FXML
    private Button registrer;
    @FXML
    private Button endre;
   
  
   
    
    
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
    private void endringVindu(ActionEvent event) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Endring.fxml"));
    Parent root2 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Endre elementer");
    stage.setScene(new Scene(root2));  
    stage.show();
    }
    
    // private ExecutorService service = Executors.newSingleThreadExecutor();
   
     @FXML
    private void lagre(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Åpne fil");
        //fileChooser.setInitialDirectory(new File);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("csv fil", "*.csv"),
                new FileChooser.ExtensionFilter("jobj fil", "*.")
        );
        
        File file = fileChooser.showOpenDialog(stage);
       
                 String vei = ""+ file;
        System.out.println(vei);
        Lagring g = new Lagring(vei);
      
      
       ExecutorService service = Executors.newSingleThreadExecutor();
       registrer.setDisable(true);
       endre.setDisable(true);
Task<Void> task = new ThreadSystem(this::threadDone);
service.execute(task);


      
  /* Runnable runnable = () -> {
    try {
        
          g.lesFil();
      
        System.out.println("Ferdig tråd da ferdig");
       
    }
    catch (Exception e) {
        e.printStackTrace();
    }
};*/

/*ThreadSystem thread = new ThreadSystem(runnable);
//service.execute(runnable);
thread.run();

runnable.run();*/
       
    }
    @FXML
    private void lastNed(ActionEvent event){
        Nedlasting nedlast = new Nedlasting();
        nedlast.nedLastVei();
        nedlast.NedTilFil();
    }
    
    @FXML
    Label trådResult;
    private void threadDone(){
        System.out.println("Tråd ferdig");
        trådResult.setText("Innlesing av fil vellykket");
        registrer.setDisable(false);
        endre.setDisable(false);
    }
    
    
    public void populerDropdown(){
       //dropdownlist.getItems().addAll("Artist","Lokale","Arrangement","Kontaktperson","Billett");
      ObservableList<String> valg=FXCollections.observableArrayList("Artist","Lokale","Arrangement","KontaktPerson","Billett");
    
      for (String elementer : valg){
          dropdownliste.getItems().add(elementer);
      }
    }
  
    public String valgt;
    public void initialize() {
        // TODO
      
           register.test();
           this.populerDropdown();
           
        dropdownliste.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue obsVal, String t, String t1) {
          System.out.println(obsVal);
          //  System.out.println(t);
         valgt=t1;
         System.out.println(valgt);
          
            visData(t1);
            formaterCheckBoxes();
        }    
    });
       
          
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

   public void refreshTableView(){
       for (Object column : tabell.getColumns()){
           tabell.getColumns().remove(column);
       }
   }
 
    private TableColumn[] columns;
    
   // tar dataen ut fra listen gitt og viser det i tabell
   public void hentData(ObservableList<Object> liste){
       tabell.getColumns().clear();
       attributter=getAttributter();
    
     
     
        columns=new TableColumn[attributter.length];
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
    tabell.setItems(liste);
    for (TableColumn t : columns){
        tabell.getColumns().addAll(t);
        System.out.println("tabeller laget");
    }
      tabell.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
  

     
       /*
       fornavnCol.setCellFactory(TextFieldTableCell.forTableColumn());
       etternavnCol.setCellFactory(TextFieldTableCell.forTableColumn());
       tlfCol.setCellFactory(TextFieldTableCell.forTableColumn());
       typeCol.setCellFactory(TextFieldTableCell.forTableColumn());*/
       
        }
   
   // Filteringskode
   
   // TODO 
   // Plasser Checkboxer med attributter til høyre for tabellen som tilsvarer valget i ddl
   // Plasser Filtrer-knapp under de ijgen
   // Last inn liste med objekt og ikke ta med columnsa som er merket i checkboxene
   
    private CheckBox[] cbArray=null;
    private Button btn;
   
   private void formaterCheckBoxes(){
       
       deleteCheckBoxes();
        cbArray=new CheckBox[attributter.length];
       
       int teller=0;
       
       for (String attributt : attributter){
           cbArray[teller]=new CheckBox(attributt);
           if (teller==0){
                cbArray[teller].setLayoutX(700);
                cbArray[teller].setLayoutY(200);
           }
           else{
               cbArray[teller].setLayoutY( cbArray[teller-1].getLayoutY() + 50);
                 cbArray[teller].setLayoutX(700);
           }
          
           ap.getChildren().add(cbArray[teller]);
           teller++;
       }
       
       btn=new Button("Filtrer");
   
     btn.setLayoutY(cbArray[teller-1].getLayoutY()+40);
    btn.setLayoutX(700);
      btn.addEventHandler(ActionEvent.ACTION, event->filtrerData());
      ap.getChildren().add(btn);
       
       
   }
   
   private void deleteCheckBoxes(){
       if (cbArray!=null){
           for (CheckBox cb : cbArray){
               ap.getChildren().remove(cb);
               ap.getChildren().remove(btn);
           }
       }
       
   }
   
   private ArrayList<Boolean> getCheckBoxValues(){
       
        ArrayList<Boolean> checked=new ArrayList<>();
       
       for (CheckBox cb : cbArray){
          checked.add(cb.selectedProperty().get());
         System.out.println(cb.selectedProperty().get());  
       }
       
       return checked;
   }
   
   
   private void filtrerData(){
     
      ArrayList<Boolean> check=getCheckBoxValues();
      
      visData(valgt);
      ObservableList columns=FXCollections.observableArrayList(tabell.getColumns());
      
 
    
     // fjerner fra collections trygt
       int teller=0;
       
      Iterator<Object> i=columns.iterator();
      while(i.hasNext()){
          Object col=i.next();
      
         if(check.get(teller)){
          i.remove();
            }
          teller++;
      }
      
      // slette columns før de nye(de som ikke er avhuket) legges til
       tabell.getColumns().clear();
       
       for (int z=0;z<columns.size();z++){
           tabell.getColumns().add(columns.get(z));
       }
       
   
       
   }
   
   
   
    
}

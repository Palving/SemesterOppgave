package org.openjfx;

import Model.Lagring.Lagring;
import Model.Nedlasting.Nedlasting;
import Model.Registrering.Register;
import Model.Tråder.ThreadSystem;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    
     private ExecutorService service = Executors.newSingleThreadExecutor();
   
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
      
      
       
      
   Runnable runnable = () -> {
    try {
        
          g.lesFil();
      
        System.out.println("Ferdig tråd da ferdig");
       
    }
    catch (Exception e) {
        e.printStackTrace();
    }
};

ThreadSystem thread = new ThreadSystem(runnable);
service.execute(runnable);
thread.run();
       
    }
    @FXML
    private void lastNed(ActionEvent event){
        Nedlasting nedlast = new Nedlasting();
        nedlast.nedLastVei();
        nedlast.NedTilFil();
    }
    
    
    private void threadDone(){
        System.out.println("Tråd ferdig");
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
 
   // tar dataen ut fra listen gitt og viser det i tabell
   public void hentData(ObservableList<Object> liste){
       tabell.getColumns().clear();
       attributter=getAttributter();
     //  tabell=new TableView();
      // tabell.setLayoutY(100);
     
     
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
    tabell.setItems(liste);
    for (TableColumn t : columns){
        tabell.getColumns().addAll(t);
        System.out.println("tabeller laget");
    }
  

     
       /*
       fornavnCol.setCellFactory(TextFieldTableCell.forTableColumn());
       etternavnCol.setCellFactory(TextFieldTableCell.forTableColumn());
       tlfCol.setCellFactory(TextFieldTableCell.forTableColumn());
       typeCol.setCellFactory(TextFieldTableCell.forTableColumn());*/
       
        }
   
   
    
}

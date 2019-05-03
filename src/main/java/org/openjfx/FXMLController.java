package org.openjfx;


import Model.Avvik.InvalidJavaObjectFormatException;
import Model.Nedlastning.TilCSV;
import Model.Nedlastning.TilJOBJ;
import Model.Opplastning.FraCSV;
import Model.Opplastning.FraJOBJ;
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
import javafx.scene.control.TableView;
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
    
    
  private Register register=Register.getInstance();
  private TableViewFormatter tableViewFormatter=new TableViewFormatter();
   
  
   private String[] attributter;
    @FXML
    private Button registrer;
    @FXML
    private Button endre, lastInn, lastNed;
   
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
    private void lastInn(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Åpne fil");
        //fileChooser.setInitialDirectory(new File);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("csv fil", "*.csv"),
                new FileChooser.ExtensionFilter("jobj fil", "*.jobj")
        );
        
        File file = fileChooser.showOpenDialog(stage);
       
        String vei = ""+ file;
        System.out.println(vei);
      
         if (fileChooser.getSelectedExtensionFilter().getDescription().equals("jobj fil")){
         try{
             FraJOBJ jobjInnlesing=new FraJOBJ(file);
           jobjInnlesing.lagreTilFil();
         }
         catch(ClassNotFoundException e){
             FeilmeldingSystem.visFeilmelding(e.getMessage());
             return;
         }
            
           
      }
         else if (fileChooser.getSelectedExtensionFilter().getDescription().equals("csv fil")){
           
        try{ 
      FraCSV CSVInnlesning = new FraCSV(file);
       CSVInnlesning.lagreTilFil();
        }
        catch(ClassNotFoundException e){
             FeilmeldingSystem.visFeilmelding(e.getMessage());
             return;
        }
          
        }
       
      
      // Tråder
       ExecutorService service = Executors.newSingleThreadExecutor();
       // stop manipulering av data mens tråden kjører
       registrer.setDisable(true);
       endre.setDisable(true);
       lastInn.setDisable(true);
       lastNed.setDisable(true);
       
        Task<Void> task = new ThreadSystem(this::threadDone);
        service.execute(task);

   
    }
    // default value
     public String valgt="Artist";
    @FXML
    private void lastNed(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
       // fileChooser.setInitialDirectory(file);
        fileChooser.setTitle("Skriv filnavn");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("csv fil", "*.csv"),
                new FileChooser.ExtensionFilter("jobj fil", "*.jobj")
        );
        File file = fileChooser.showSaveDialog(stage);
  
  
      if (fileChooser.getSelectedExtensionFilter().getDescription().equals("jobj fil")){
         
            TilJOBJ jobjLagring=new TilJOBJ(file);
            jobjLagring.lagreTilFil(getObjects(),valgt);
           
      }
         else if (fileChooser.getSelectedExtensionFilter().getDescription().equals("csv fil")){
         
            TilCSV csvLagring = new TilCSV(file);
            csvLagring.lagreTilFil(getObjects(), valgt);
          
        }
    
 
    }
    
    private ObservableList<Object> getObjects(){
        return tabell.getItems();
    }
    
    @FXML
    Label trådResult;
    private void threadDone(){
        System.out.println("Tråd ferdig");
        trådResult.setText("Innlesing av fil vellykket");
        registrer.setDisable(false);
        endre.setDisable(false);
        lastInn.setDisable(false);
        lastNed.setDisable(false);
        
        visData(valgt);
    }
    
    
    public void populerDropdown(){
      ObservableList<String> valg=FXCollections.observableArrayList("Artist","Lokale","Arrangement","KontaktPerson","Billett");
    
      for (String elementer : valg){
          dropdownliste.getItems().add(elementer);
      }
    }
  
   
    public void initialize() {
      
           register.fyllRegister();
           this.populerDropdown();
          
        dropdownliste.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue obsVal, String t, String t1) {
          System.out.println(obsVal);
      
         valgt=t1;
         System.out.println(valgt);
          
            visData(t1);
            formaterCheckBoxes();
        }    
    });
       
          
        } 
       public void visData(String valgt){
       tabell.getColumns().clear();
       tabell=tableViewFormatter.visData(valgt);
       tabell.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       tabell.setLayoutX(25);
       tabell.setLayoutY(111);
       tabell.setPrefHeight(522);
       tabell.setPrefWidth(658);
       
       ap.getChildren().add(tabell);
      
   }    
       
   public void refreshTableView(){
       for (Object column : tabell.getColumns()){
           tabell.getColumns().remove(column);
       }
   }
 
    private CheckBox[] cbArray=null;
    private Button btn;
   
   private void formaterCheckBoxes(){
       
       // refresh checkBoxes hver gang dropdownlist-onchange kalles
       deleteCheckBoxes();
       
       // hent attributter som tilsvarer elementet valgt i dropdownlist
       attributter=tableViewFormatter.getTableViewAttributter(valgt);
      cbArray=new CheckBox[attributter.length];
       
       int teller=0;
       
       for (String attributt : attributter){
           
           cbArray[teller]=new CheckBox(attributt);
           
           if (teller>0){
                 cbArray[teller].setLayoutY( cbArray[teller-1].getLayoutY() + 30);
                 cbArray[teller].setLayoutX(700);
           }
           else{
               cbArray[teller].setLayoutX(700);
               cbArray[teller].setLayoutY(200);
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
   
   // Kalles på hver gang valg i radiogroup byttes
   private void deleteCheckBoxes(){
       if (cbArray!=null){
           for (CheckBox cb : cbArray){
               ap.getChildren().remove(cb);
             
           }
             ap.getChildren().remove(btn);
       }
       
   }
   
   // looper gjennom hver checkbox og ser hvem som er trykket på
   private ArrayList<Boolean> getCheckBoxValues(){
       
        ArrayList<Boolean> checked=new ArrayList<>();
       
       for (CheckBox cb : cbArray){
          checked.add(cb.selectedProperty().get());
      
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


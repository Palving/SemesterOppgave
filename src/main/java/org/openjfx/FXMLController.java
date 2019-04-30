package org.openjfx;


import Model.Nedlastning.TilJOBJ;
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
        Register register = Register.getInstance();
        FraJOBJ g = new FraJOBJ(file,register);
       
      
      // Tråder
       ExecutorService service = Executors.newSingleThreadExecutor();
       registrer.setDisable(true);
       endre.setDisable(true);
       
        Task<Void> task = new ThreadSystem(this::threadDone);
        service.execute(task);

   
    }
    
    @FXML
    private void lastNed(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Skriv filnavn");
        File path = fileChooser.showSaveDialog(stage);
        String file=""+path;
        Register register = Register.getInstance();
        //Nedlasting r = new TilJOBJfil (file , register);
       
         TilJOBJ test=new TilJOBJ(file,register);
           test.lagreTilFil();
           
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
      
           register.test();
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
       //layoutX="25.0" layoutY="111.0" prefHeight="522.0" prefWidth="658.0" />
   }    
        // private TableColumn[] columns;
    
   
   public void refreshTableView(){
       for (Object column : tabell.getColumns()){
           tabell.getColumns().remove(column);
       }
   }
 
    private CheckBox[] cbArray=null;
    private Button btn;
   
   private void formaterCheckBoxes(){
       
       deleteCheckBoxes();
       attributter=tableViewFormatter.getTableViewAttributter(valgt);
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

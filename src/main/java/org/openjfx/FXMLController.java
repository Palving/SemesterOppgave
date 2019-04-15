package org.openjfx;

import Model.Lagring.Lagring;
import Model.Registrering.Register;
import Model.Tråder.ThreadSystem;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private AnchorPane ap;
    
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
    
    private void threadDone(){
        System.out.println("Tråd ferdig");
    }
    
    Register register=Register.getInstance();
    public void initialize() {
        // TODO
        
           register.test();
    }  
    
}

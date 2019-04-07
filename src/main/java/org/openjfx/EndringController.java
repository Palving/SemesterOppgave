package org.openjfx;


import Model.Domene.Artist;
import Model.Registrering.Register;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;



public class EndringController implements Initializable {

   @FXML
 private RadioButton artistRadio, lokaleRadio, kontaktPersonRadio, arrangRadio, billettRadio;
   ToggleGroup radioGrp;
   
   @FXML
   private AnchorPane anchorPane;
   
   Register register=Register.getInstance();
   String valgt;
   
  
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
   
   ArrayList <Artist> artistEndre=new ArrayList<>();
   
   public void hentData(ObservableList<Object> liste){
       TableView tableview=new TableView();
       
       List<String> columns = new ArrayList<String>();
       TableColumn[] rader=new TableColumn[columns.size()];
    columns.add("Fornavn");
    columns.add("Etternavn");
    columns.add("Telefon");
    columns.add("Type artist");
    TableColumn [] tableColumns = new TableColumn[columns.size()];     
    int columnIndex = 0;
    for(int i=0 ; i<columns.size(); i++) {
        final int j = i;
        TableColumn col = new TableColumn(columns.get(i));
                    
        
        tableview.getColumns().addAll(col);
    }       
        
    ObservableList<String> row = FXCollections.observableArrayList();
    ObservableList<String> row1 = FXCollections.observableArrayList();
    row.addAll("d1");
    row.addAll("d11");
    row1.addAll("d2");
    row1.addAll("d22");
    tableview.getItems().add(row);
    tableview.getItems().add(row1);
    
       anchorPane.getChildren().add(tableview);
       
   }
   
   public void visData(String valgt){
     
   switch(valgt){
       case "Artist":
           ObservableList<Object> artister=FXCollections.observableArrayList(register.getArtister());
           hentData(artister);
           break;
    
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
          //System.out.println(Registrer.registrer(new Artist("Jon","Rafoss","123","Sanger")));
          
        }
      }
      });
            }
}
           

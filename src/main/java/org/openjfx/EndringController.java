package org.openjfx;


import Model.Domene.Artist;
import Model.Registrering.Register;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;



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
       TableView table=new TableView();
       
      TableColumn fornavnCol = new TableColumn("Fornavn");
        TableColumn etternavnCol = new TableColumn("Etternavn");
        TableColumn tlfCol = new TableColumn("Tlf");
        TableColumn typeCol=new TableColumn("Type artist");
        
       register.test();
            ObservableList<Artist> data = FXCollections.observableArrayList(register.getArtister());
        
           fornavnCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("fornavn"));
           etternavnCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("etternavn"));
           tlfCol.setCellValueFactory(new PropertyValueFactory<Artist,String>("tlf"));
           typeCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("typeArtist"));
           // layoutX="57.0" layoutY="120.0" prefHeight="200.0" prefWidth="459.0">
          table.setItems(data);
     table.getColumns().addAll(fornavnCol, etternavnCol, tlfCol, typeCol);
     table.setLayoutX(57);
     table.setLayoutY(120);
     table.setPrefHeight(200);
     table.setPrefWidth(459);
     table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

       anchorPane.getChildren().add(table);
       
       table.setEditable(true);
       fornavnCol.setCellFactory(TextFieldTableCell.forTableColumn());
       etternavnCol.setCellFactory(TextFieldTableCell.forTableColumn());
       tlfCol.setCellFactory(TextFieldTableCell.forTableColumn());
       typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
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
           

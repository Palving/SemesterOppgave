package org.openjfx;


import Model.Domene.Artist;
import Model.Registrering.Register;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;



public class EndringController implements Initializable {

   @FXML
 private RadioButton artistRadio, lokaleRadio, kontaktPersonRadio, arrangRadio, billettRadio;
   ToggleGroup radioGrp;
   
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
   
   public void visData(){
     
   switch(valgt){
       case "Artist":
           artistEndre=register.getArtister();
           break;
    
}   
   
   for(Artist artistEndre: artistEndre ){
       // tablecolum(0) = artistEndre.getFornavn();
       // tablecolum(1) = artistEndre.getEtternavn();
       // tablecolum(2) = artistEndre.getTlf();
       // tablecolum(3) = artistEndre.gettypeArtist();
       
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
          
          visData();
          //System.out.println(Registrer.registrer(new Artist("Jon","Rafoss","123","Sanger")));
          
        }
      }
      });
            }
}
           

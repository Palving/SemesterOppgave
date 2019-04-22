
package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;




public class FilteringsController implements Initializable {
    
 @FXML
TableView tabell;
 
 @FXML
 ComboBox dropdownliste;
 
 public String valgt;

   @Override
    public void initialize(URL url, ResourceBundle rb) {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
        fxmlLoader.load();
    } catch (IOException exception) {
        throw new RuntimeException(exception);
    }
   
    TableView test=new TableView();
    System.out.print(tabell.toString());
    
     dropdownliste.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue obsVal, String t, String t1) {
          System.out.println(obsVal);
          //  System.out.println(t);
         valgt=t1;
         System.out.println("Fra annen controller!!!"+valgt);
          
          
        }    
    });
    
    
                         } // end init
    
    
    protected static void test(){
        System.out.println("hei fra annen controller");
      
    }
}


package Model.Registrering;

import Model.Avvik.InvalidInputException;
import Model.Avvik.InvalidTimeFormatException;
import javafx.scene.control.TextField;


public class ValideringSystem {
    
    public static boolean validerInputiTextFields(TextField[] input) throws InvalidInputException{
        String regex="^[A-Za-z0-9:\\s]{1,30}$";
        
        for (TextField textfield : input){
            if (!textfield.getText().matches(regex)){
                throw new InvalidInputException("Ugyldig data");
               // return false;
            }
        }
        
        return true;
    }
    
    public static boolean validerTimeFormat(String tid) throws InvalidTimeFormatException{
        String regex="^[0-9]{2}:[0-9]{2}$";
        
        if (!tid.matches(regex)){
            throw new InvalidTimeFormatException("Ugyldig format på klokkeslett");
        }
       String[] elementer=tid.split(":");
       int pos1=Integer.parseInt(elementer[0]);
       int pos2=Integer.parseInt(elementer[1]);
       if ((pos1>24) || pos2>60){
           throw new InvalidTimeFormatException("Tiden må være under 24:00");
       }
       // minustall sjekk er unødvnedig - minustegn fanges i regex
     
        return true;
    }
    
}

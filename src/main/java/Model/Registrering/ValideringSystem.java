
package Model.Registrering;

import Model.Avvik.InvalidInputException;
import javafx.scene.control.TextField;


public class ValideringSystem {
    
    public static boolean validerInputiTextFields(TextField[] input) throws InvalidInputException{
        String regex="^A-Za-z0-9{2,30}$";
        
        for (TextField textfield : input){
            if (!textfield.getText().matches(regex)){
                throw new InvalidInputException("Ugyldig data");
               // return false;
            }
        }
        
        return true;
    }
    
}

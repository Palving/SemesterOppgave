
package Model.Opplastning;

import Model.Registrering.Register;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;

public abstract class OpplastingSystem {
    public File file;
    public Register register;
    
    public OpplastingSystem ( File file){
        this.file = file;
       
    }
    
    public abstract void lagreTilFil() throws ClassNotFoundException;
}





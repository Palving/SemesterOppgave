
package Model.Nedlasting;

import Model.Registrering.Register;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;

public abstract class Nedlasting {
    public File file;
    public Register register;
    
    public Nedlasting ( File file, Register register){
        this.file = file;
        this.register = register;
    }
   
    
    /*public void nedLastVei(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Åpne fil");
        
        file = fileChooser.showSaveDialog(stage);
        
    }*/
    

}





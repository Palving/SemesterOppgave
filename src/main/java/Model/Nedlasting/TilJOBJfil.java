
package Model.Nedlasting;

import Model.Registrering.Register;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;




public class TilJOBJfil extends Nedlasting {
    
    
    public TilJOBJfil(File file, Register register){
        super(file, register);
    }
    
    public void fil (){
        //  Person kari = new Person("Kari", 20, 1);
        ArrayList artister =register.getArtister();
        String filepath = "" + file;
        int i = 0;
        try (
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            ) {
            
            while(i <= artister.size()){
                        out.writeObject(artister.get(i));
                        i++;
        
                    }
        }
         catch (IOException e) {
        e.printStackTrace(); // This should not happen, so we print debug information here.
        }
        
        }
        }
    





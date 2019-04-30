
package Model.Nedlasting;

import Model.Registrering.Register;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class FraCSV extends OpplastingSystem {
    
    public FraCSV(File file, Register register){
        super(file, register);
    }
    
    public void NedTilFil(){

        PrintWriter printWriter = null;
        
            
        try {
            printWriter = new PrintWriter (file);
            
            ArrayList artister =register.getArtister();
            
            for (int i = 0 ; i < artister.size(); i ++){
            String artistString = artister.get(i) +"";
            String[] artistStringArray = artistString.split(" ");
            for(int j = 1; j < artistStringArray.length; j++){
                printWriter.print(artistStringArray[j] + ";");
            }
            printWriter.print("\n");
            }
                    
            printWriter.close (); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
        System.out.println("funka");
   }
}

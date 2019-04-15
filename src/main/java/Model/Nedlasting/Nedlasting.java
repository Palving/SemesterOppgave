/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Nedlasting;

import Model.Registrering.Register;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Nedlasting {
    private Stage stage;
    private File file;
    private Register register=Register.getInstance();
    
    
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
    
    public void nedLastVei(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Åpne fil");
        
        file = fileChooser.showSaveDialog(stage);
        
    }
    

}

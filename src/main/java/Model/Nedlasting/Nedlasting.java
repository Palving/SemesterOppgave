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

/**
 *
 * @author jonny
 */
public class Nedlasting {
    private Stage stage;
    //private String fileName = "out.txt";
    private String vei;
    private File file;
    private Register register=Register.getInstance();
    
    public void NedTilFil(){

        PrintWriter printWriter = null;
        
            /*String fileName = "out.txt";
            ArrayList<String> list = new ArrayList<>();
            list.add("Jon");
            list.add("Jon2");
            list.add("Jon3");
            try {
            PrintWriter outputStream = new PrintWriter(fileName);
            for (int i = 0; i < list.size(); i++){
            outputStream.print(list.get(i) +";" );
            }
            outputStream.close();
            }
            catch (FileNotFoundException e){
            e.printStackTrace();
            }*/
        try {
            //File file = new File (nedLastVei());
            printWriter = new PrintWriter (file);
            //printWriter.println ("hello");
            ArrayList a =register.getArtister();
            for (int i = 0; i < a.size();i++){
                printWriter.print(a.get(i) +";" );
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
        
        vei = ""+ file;
        System.out.println(vei);
        
    }
    

}

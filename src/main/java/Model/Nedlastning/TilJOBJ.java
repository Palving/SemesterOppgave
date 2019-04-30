package Model.Nedlastning;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.Domene.Artist;
import Model.Registrering.Register;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.collections.ObservableList;


public class TilJOBJ extends NedlastingSystem{
   
    private Register register=Register.getInstance();
    
    public TilJOBJ(String path){
        super(path);
    }
    
    public void lagreTilFil(ObservableList<Object> data){
       
        
        try{
           //  ArrayList<Artist> artister=register.getArtister();
             FileOutputStream fos = new FileOutputStream(super.getPath()+".jobj");
            ObjectOutputStream out = new ObjectOutputStream(fos);
           
            for (Object a : data){
                out.writeObject(a);
            }
          
            out.close();
         
          
        }
        catch(IOException e){
            
        }
        
    
    }
    
    
}


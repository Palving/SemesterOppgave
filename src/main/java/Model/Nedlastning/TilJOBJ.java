/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Lagring;

import Model.Domene.Artist;
import Model.Registrering.Register;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class TilJOBJ extends NedlastingSystem{
    public String file;
    private Register register=Register.getInstance();
    
    public TilJOBJ(String file){
        super(file);
    }
    
    public void lagreTilFil(){
       
        
        try{
             ArrayList<Artist> artister=register.getArtister();
             FileOutputStream fos = new FileOutputStream(file+".jobj");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            
            for (Object a : artister){
                out.writeObject(a);
                System.out.println(a.toString() +" lagret");
      
            }
          
        }
        catch(IOException e){
            
        }
        
    
    }
    
    
}


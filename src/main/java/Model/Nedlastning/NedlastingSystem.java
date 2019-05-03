/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Nedlastning;

import Model.Registrering.Register;
import java.io.File;
import javafx.collections.ObservableList;



public abstract class NedlastingSystem {
    private String path;
    public File file;
    private Register register;

    
    
    public NedlastingSystem (File file){
      this.file=file;
   
    }
    
    public String getPath(){
        return file.getPath();
    }
    
 public abstract void lagreTilFil(ObservableList<Object> data, String valgt);
        
   
}

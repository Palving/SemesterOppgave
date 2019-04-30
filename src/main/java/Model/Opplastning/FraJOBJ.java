
package Model.Opplastning;

import Model.Domene.Artist;
import Model.Lagring.TilJOBJ;
import Model.Nedlasting.OpplastingSystem;
import Model.Registrering.Register;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;




public class FraJOBJ extends OpplastingSystem {
    
   // Register register=Register.getInstance();
    
    private File file;
    private Register register;
    
    
    public FraJOBJ(File file, Register register){
        super(file, register);
    }
    
    public void lesFil(String filepath){
       // TilJOBJ objectIO = new TilJOBJ();
 
        //Read object from file
      //  Artist st = (Artist) objectIO.ReadObjectFromFile(filepath);
       // System.out.println(st);
    }
    public Object ReadObjectFromFile(String filepath) {
 
        try {
 
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Object obj = objectIn.readObject();
 
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
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
    





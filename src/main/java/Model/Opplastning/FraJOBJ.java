
package Model.Opplastning;

import Model.Domene.Artist;
//import Model.Lagring.TilJOBJ;
import Model.Registrering.Register;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.collections.ObservableList;




public class FraJOBJ extends OpplastingSystem {
    
   // Register register=Register.getInstance();
    
    private File file;
    private Register register;
    
    
    public FraJOBJ(File file, Register register){
        super(file, register);
    }
    
    public void registrerFraFil(ObservableList<Object> data){
         // FraJOBJ objectIO = new FraJOBJ(file, register);
 
          //Read object from file
          //Artist st = (Artist) objectIO.ReadObjectsFromFile(filepath);
        /*  for (Object obj : data){
              if (obj!=null){
                    register.registrer(obj);
              }
            
          }
         */
    }
    public ObservableList<Object> ReadObjectsFromFile(String filepath) {
 ObservableList<Object> objekter=null;
        try {
            
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 boolean cont = true;
 
   while(cont){
      Object obj = objectIn.readObject();
      if(obj != null){
            System.out.println("objekt=="+obj.toString());
         objekter.add(obj);
         register.registrer(obj);
         return objekter;
      }
        
         else{
                 cont = false;
                  objectIn.close();
            return objekter;
                 }
         
   }

           
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }
    
    public void fil (){
        //  Person kari = new Person("Kari", 20, 1);
        /*ArrayList artister =register.getArtister();
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
        
        }*/}
        }
    





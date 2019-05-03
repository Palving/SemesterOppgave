
package Model.Opplastning;

//import Model.Lagring.TilJOBJ;
import Model.Avvik.InvalidJavaObjectFormatException;
import Model.Registrering.Register;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openjfx.FeilmeldingSystem;




public class FraJOBJ extends OpplastingSystem {
  
    private File file;
    private Register register=Register.getInstance();
    
    
    public FraJOBJ(File file){
        super(file);
    }
  
 
    @Override
    public void lagreTilFil() throws ClassNotFoundException{
        ObservableList<Object> objekter=null;
        try{
           objekter=this.ReadObjectsFromFile(super.file.getPath());
            if(objekter==null){
               // runtimeexception så test bare for null
               FeilmeldingSystem.visFeilmelding("Filen kan ikke leses");
                 return;
             }
        }
        catch(EOFException e){
             FeilmeldingSystem.visFeilmelding(e.getMessage());
             return;
        } catch (IOException e) {
            FeilmeldingSystem.visFeilmelding(e.getMessage());
            return;
        } catch (InvalidJavaObjectFormatException e) {
           FeilmeldingSystem.visFeilmelding(e.getMessage());
           return;
        }
       
         for (Object obj : objekter){
          register.registrer(obj);
              }
    }
   
   
    private ObservableList<Object> ReadObjectsFromFile(String filepath) throws ClassNotFoundException, EOFException, IOException, InvalidJavaObjectFormatException  {
     
        try {
            
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            List<Object> objekter=(List<Object>)objectIn.readObject();
      
       
       ObservableList<Object> liste=FXCollections.observableArrayList(objekter);
       if (liste.get(0)==null){
           throw new InvalidJavaObjectFormatException("Ugyldig javaobject fil");
       }
      return liste;
        }
        catch(EOFException e){
               FeilmeldingSystem.visFeilmelding(e.getMessage());
        }
     catch (IOException e){
            FeilmeldingSystem.visFeilmelding(e.getMessage());
        }
        catch(ClassCastException e){
               FeilmeldingSystem.visFeilmelding(e.getMessage());
        }
 
      return null;
    }
 
   }
        
    

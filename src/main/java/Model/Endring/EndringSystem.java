
package Model.Endring;

import java.util.ArrayList;
import Model.Domene.*;
import Model.Registrering.Register;


public class EndringSystem {
    
    private Register register=Register.getInstance();
    
    public static ArrayList<Object> fjernObject(Object o){
        return null;
    }
    
    public static Object endreObject(Object o){
        return null;
        
        
    }
    
    // returnerer typen
    // ser penere ut
    public static String typeClass(Object o){
        String classType=o.getClass().toString();
        String type=null;
           switch(classType){
          case "class Model.Domene.Artist":
             type="Artist";
               break;
          case "class Model.Domene.Lokale":
             type="Lokale";
               break;
          case "class Model.Domene.Arrangement":
             type="Arrangement";
              break;
          case "class Model.Domene.KontaktPerson":
              type="KontaktPerson";
              break;
          case "class Model.Domene.Billett":
            type="Billett";
              break;
         
      }
      return type;
        
        
    }
    
    
}

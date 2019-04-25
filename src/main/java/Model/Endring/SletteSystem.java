/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Endring;

import Model.Domene.*;
import Model.Registrering.Register;

/**
 *
 * @author Magnus
 */
public class SletteSystem {
    
    
    Register register=Register.getInstance();
    public void remove(Object o){
        String typeObjekt=o.getClass().toString();
        
         switch(typeObjekt){
         case "class Model.Domene.Artist":
             register.getArtister().remove((Artist)o);
               break;
          case "class Model.Domene.Lokale":
            register.getLokale().remove((Lokale)o);
               break;
          case "class Model.Domene.Arrangement":
            register.getArrangement().remove((Arrangement)o);
              break;
          case "class Model.Domene.KontaktPerson":
           register.getKontaktPerson().remove((KontaktPerson)o);
              break;
          case "class Model.Domene.Billett":
           register.getBillett().remove((Billett)o);
              break;
        
        
        }
    }   
}

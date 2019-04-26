
package Model.Endring;

import java.util.ArrayList;
import Model.Domene.*;
import Model.Registrering.Register;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EndringSystem {
    
    private Register register=Register.getInstance();
    
    public static ArrayList<Object> fjernObject(Object o){
        return null;
    }
    
    public void endreObject(Object objToChange, String type){
       
          ObservableList list;
          switch(type){
              case "Artist":
                  list=FXCollections.observableArrayList(register.getArtister());
                   for(int i=0;i<list.size();i++) {
                  if (objToChange.equals(register.getArtister().get(i))){
                      System.out.print("slettet"+objToChange);
                      register.getArtister().remove(register.getArtister().get(i));
                      
                      break;
                    }
                 }     
             break;
              case "Lokale":
                  list=FXCollections.observableArrayList(register.getLokale());
                   for(int i=0;i<list.size();i++) {
                  if (objToChange.equals(register.getLokale().get(i))){
                      System.out.print("slettet"+objToChange);
                      register.getLokale().remove(register.getLokale().get(i));
                      
                      break;
                    }
                 }     
             break;
              case "Arrangement":
                  list=FXCollections.observableArrayList(register.getArrangement());
                   for(int i=0;i<list.size();i++) {
                  if (objToChange.equals(register.getArrangement().get(i))){
                      System.out.print("slettet"+objToChange);
                      register.getArrangement().remove(register.getArrangement().get(i));
                      
                      break;
                    }
                 }     
             break;
              case "KontaktPerson":
                  list=FXCollections.observableArrayList(register.getKontaktPerson());
                   for(int i=0;i<list.size();i++) {
                  if (objToChange.equals(register.getKontaktPerson().get(i))){
                      System.out.print("slettet"+objToChange);
                      register.getKontaktPerson().remove(register.getKontaktPerson().get(i));
                      
                      break;
                    }
                 }     
             break;
              case "Billett":
                  list=FXCollections.observableArrayList(register.getBillett());
                   for(int i=0;i<list.size();i++) {
                  if (objToChange.equals(register.getBillett().get(i))){
                      System.out.print("slettet"+objToChange);
                      register.getBillett().remove(register.getBillett().get(i));
                      
                      break;
                    }
                 }     
             break;
             
             
              }
            
            
           
        
        
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
